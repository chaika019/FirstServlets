package dto;

public record TicketFilter (String passenger_name, String seat_number, int limit, int offset) {

}