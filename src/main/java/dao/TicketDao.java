package dao;

import dto.TicketFilter;
import entity.Flight;
import entity.FlightStatus;
import entity.Ticket;
import exception.DaoException;
import utils.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TicketDao implements Dao<Long, Ticket> {
    private final static TicketDao INSTANCE = new TicketDao();

    private final static FlightDao flightDao = FlightDao.getInstance();
    private final static String SAVE_SQL =
            """
            INSERT INTO tickets (passport_number, passenger_name, flight_id, seat_number, cost)
            VALUES (?, ?, ?, ?, ?)
            """;

    private final static String UPDATE_SQL =
            """
            UPDATE tickets
            SET passport_number = ?,
                passenger_name = ?,
                flight_id = ?,
                seat_number = ?,
                cost = ?
            WHERE id = ?
            """;

    private final static String DELETE_SQL =
            """
            DELETE FROM tickets
            Where id = ?
            """;

    private final static String FIND_ALL_SQL =
            """
            SELECT t.id, t.passport_number, t.passenger_name, t.flight_id, t.seat_number, t.cost,
                   f.flight_number, f.departure_date, f.departure_airport_code,
                   f.arrival_date, f.arrival_airport_code, f.aircraft_id, f.status
            FROM tickets t
            JOIN flights f on f.id = t.flight_id
            """;

    private final static String FIND_BY_ID_SQL = FIND_ALL_SQL +
            """
            WHERE t.id = ?
            """;

    private final static String FIND_ALL_BY_FLIGHT_ID = FIND_ALL_SQL +
            """
            WHERE t.flight_id = ?
            """;

    public List<Ticket> findAllByFlightId(Long id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_ALL_BY_FLIGHT_ID)) {
            List<Ticket> tickets = new ArrayList<>();
            statement.setLong(1, id);
            var result = statement.executeQuery();
            while (result.next()) {
                tickets.add(getTicket(result));
            }
            return tickets;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<Ticket> findAll(TicketFilter filter) {
        List<Object> params = new ArrayList<>();
        List<String> whereSql = new ArrayList<>();
        if (filter.passenger_name() != null) {
            params.add(filter.passenger_name());
            whereSql.add("passenger_name=?");
        }
        if (filter.seat_number() != null) {
            params.add("%" + filter.seat_number() + "%");
            whereSql.add("seat_number like ?");
        }
        params.add(filter.limit());
        params.add(filter.offset());
        var where = whereSql.stream().collect(Collectors.joining(
                " AND ",
                params.size() > 2 ? " WHERE " : " ",
                " LIMIT ? OFFSET ?"
        ));
        String sql = FIND_ALL_SQL + where ;
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(sql)) {
            List<Ticket> tickets = new ArrayList<>();
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }
            var result = statement.executeQuery();
            while (result.next()) {
                tickets.add(
                        getTicket(result)
                );
            }
            return tickets;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<Ticket> findAll() {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_ALL_SQL)) {
            List<Ticket> tickets = new ArrayList<>();

            var result = statement.executeQuery();
            while (result.next()) {
                tickets.add(
                        getTicket(result)
                );
            }
            return tickets;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Optional<Ticket> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, id);
            var result = statement.executeQuery();
            Ticket ticket = null;
            if(result.next()) {
                ticket = getTicket(result);
            }
            return Optional.ofNullable(ticket);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }



    public Ticket save(Ticket ticket) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, ticket.getPassport_number());
            statement.setString(2, ticket.getPassenger_name());
            statement.setLong(3, ticket.getFlight().getId());
            statement.setString(4, ticket.getSeat_number());
            statement.setBigDecimal(5, ticket.getCost());

            statement.executeUpdate();

            var keys = statement.getGeneratedKeys();
            if (keys.next()) {
                ticket.setId(keys.getLong("id"));
            }

            return ticket;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private static Ticket getTicket(ResultSet result) throws SQLException {
        var flight = new Flight(
                result.getLong("flight_id"),
                result.getString("flight_number"),
                result.getTimestamp("departure_date").toLocalDateTime(),
                result.getString("departure_airport_code"),
                result.getTimestamp("arrival_date").toLocalDateTime(),
                result.getString("arrival_airport_code"),
                result.getInt("aircraft_id"),
                FlightStatus.valueOf(result.getString("status"))
        );
        return new Ticket(
                result.getLong("id"),
                result.getString("passport_number"),
                result.getString("passenger_name"),
                flightDao.findById(
                        result.getLong("flight_id"),
                        result.getStatement().getConnection()
                ).orElse(null),
                result.getString("seat_number"),
                result.getBigDecimal("cost")
        );
    }

    public boolean update(Ticket ticket) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1, ticket.getPassport_number());
            statement.setString(2, ticket.getPassenger_name());
            statement.setLong(3, ticket.getFlight().getId());
            statement.setString(4, ticket.getSeat_number());
            statement.setBigDecimal(5, ticket.getCost());
            statement.setLong(6, ticket.getId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public boolean delete(Long id) {
        try (var connection = ConnectionManager.get();
        var statement = connection.prepareStatement(DELETE_SQL)) {
            statement.setLong(1, id);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public static TicketDao getInstance() {
        return INSTANCE;
    }

    private TicketDao() {
    }
}