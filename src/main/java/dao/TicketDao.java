package dao;

import entity.Ticket;
import exception.DaoException;
import utils.ConnectionManager;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketDao {
    private final static TicketDao INSTANCE = new TicketDao();

    /* language=SQL */
    private final static String SAVE_SQL =
            """
            INSERT INTO ticket (passport_number, passenger_name, flight_id, seat_number, cost) 
            VALUES (?, ?, ?, ?, ?)
            """;

    private final static String UPDATE_SQL =
            """
            UPDATE ticket
            SET passport_number = ?,
                passenger_name = ?,
                flight_id = ?,
                seat_number = ?,
                cost = ?
            WHERE id = ?
            """;

    private final static String DELETE_SQL =
            """
            DELETE FROM ticket
            Where id = ?
            """;

    private final static String FIND_ALL_SQL =
            """
            SELECT id, passport_number, passenger_name, flight_id, seat_number, cost
            FROM ticket
            """;

    private final static String FIND_BY_ID_SQL = FIND_ALL_SQL +
            """
            WHERE id = ?
            """;


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
            statement.setLong(3, ticket.getFlight_id());
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
        return new Ticket(result.getLong("id"),
                result.getString("passport_number"),
                result.getString("passenger_name"),
                result.getLong("flight_id"),
                result.getString("seat_number"),
                result.getBigDecimal("cost")
        );
    }

    public boolean update(Ticket ticket) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1, ticket.getPassport_number());
            statement.setString(2, ticket.getPassenger_name());
            statement.setLong(3, ticket.getFlight_id());
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