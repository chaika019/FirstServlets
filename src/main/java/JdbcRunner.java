import dao.TicketDao;
import entity.Ticket;
import utils.ConnectionManager;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcRunner {
    public static void main(String[] args) throws SQLException {
        var ticketDao = TicketDao.getInstance();
        Ticket ticket = new Ticket();
        ticket.setPassport_number("BAMBAMBAM");
        ticket.setPassenger_name("Kizaru");
        ticket.setFlight_id(5L);
        ticket.setSeat_number("C2ads");
        ticket.setCost(BigDecimal.valueOf(170.00));


        System.out.println(ticketDao.save(ticket));

        System.out.println(ticketDao.delete(16L));

//        String sql = """
//                SELECT * FROM employee
//                """;
//
//        try(var connection = ConnectionManager.get();
//        var statement = connection.createStatement()) {
////            System.out.println(statement.executeUpdate(sql));
//            statement.setFetchSize(2);
//            statement.setMaxRows(2);
//            statement.setQueryTimeout(1);
//            var result = statement.executeQuery(sql);
//            while(result.next()) {
//                System.out.println(result.getString(1));
//                System.out.println(result.getString(2));
//                System.out.println(result.getString(3));
//                System.out.println(result.getString(5));
//                System.out.println("----------------------------------------");
//            }
//        }
//
//        checkMetaData();
    }





//
//    public static void checkMetaData() throws SQLException {
//        try (Connection connection = ConnectionManager.get()) {
//            var metaData = connection.getMetaData();
//            var catalogs = metaData.getCatalogs();
//            while (catalogs.next()) {
//                System.out.println(catalogs.getString(1));
//            }
//        }
//    }
}