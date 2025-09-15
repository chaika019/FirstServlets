package entity;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Objects;

public class Ticket {
    private Long id;
    private String passport_number;
    private String passenger_name;
    private Long flight_id;
    private String seat_number;
    private BigDecimal cost;

    public Ticket() {
    }

    public Ticket(Long id, String passport_number, String passenger_name, Long flight_id, String seat_number, BigDecimal  cost) {
        this.id = id;
        this.passport_number = passport_number;
        this.passenger_name = passenger_name;
        this.flight_id = flight_id;
        this.seat_number = seat_number;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassport_number() {
        return passport_number;
    }

    public void setPassport_number(String passport_number) {
        this.passport_number = passport_number;
    }

    public String getPassenger_name() {
        return passenger_name;
    }

    public void setPassenger_name(String passenger_name) {
        this.passenger_name = passenger_name;
    }

    public Long getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(Long flight_id) {
        this.flight_id = flight_id;
    }

    public String getSeat_number() {
        return seat_number;
    }

    public void setSeat_number(String seat_number) {
        this.seat_number = seat_number;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal  cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id) && Objects.equals(passport_number, ticket.passport_number) && Objects.equals(passenger_name, ticket.passenger_name) && Objects.equals(flight_id, ticket.flight_id) && Objects.equals(seat_number, ticket.seat_number) && Objects.equals(cost, ticket.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passport_number, passenger_name, flight_id, seat_number, cost);
    }

    @Override
    public String toString() {
        return "Ticket{" +
               "id=" + id +
               ", passport_number='" + passport_number + '\'' +
               ", passenger_name='" + passenger_name + '\'' +
               ", flight_id=" + flight_id +
               ", seat_number='" + seat_number + '\'' +
               ", cost=" + cost +
               '}';
    }
}
