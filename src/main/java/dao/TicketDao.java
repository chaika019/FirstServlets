package dao;

import entity.Ticket;
import exception.DaoException;
import utils.ConnectionManager;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Statement;

public class TicketDao {
    private final static TicketDao INSTANCE = new TicketDao();

    /* language=SQL */
    private final static String SAVE_SQL =
            """
            INSERT INTO ticket (passport_number, passenger_name, flight_id, seat_number, cost) 
            VALUES (?, ?, ?, ?, ?)
            """;

    private final static String DELETE_SQL =
            """
            DELETE FROM ticket
            Where id = ?
            """;

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