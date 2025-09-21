package entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Flight {
    private Long id;
    private String flightNumber;
    private LocalDateTime departureDate;
    private String departureAirportCode;
    private LocalDateTime arrivalDate;
    private String arrivalAirportCode;
    private Integer aircraftId;
    private FlightStatus status;

    public Flight(Long id, String flightNumber, LocalDateTime departureDate, String departureAirportCode, LocalDateTime arrivalDate, String arrivalAirportCode, Integer aircraftId, FlightStatus status) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.departureDate = departureDate;
        this.departureAirportCode = departureAirportCode;
        this.arrivalDate = arrivalDate;
        this.arrivalAirportCode = arrivalAirportCode;
        this.aircraftId = aircraftId;
        this.status = status;
    }

    public Flight() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public void setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    public void setArrivalAirportCode(String arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
    }

    public Integer getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(Integer aircraftId) {
        this.aircraftId = aircraftId;
    }

    public FlightStatus getStatus() {
        return status;
    }

    public void setStatus(FlightStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Flight{" +
               "id=" + id +
               ", flight_number='" + flightNumber + '\'' +
               ", departure_date=" + departureDate +
               ", departure_airport_code='" + departureAirportCode + '\'' +
               ", arrival_date=" + arrivalDate +
               ", arrival_airport_code='" + arrivalAirportCode + '\'' +
               ", aircraft_id=" + aircraftId +
               ", status=" + status +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(id, flight.id) && 
                Objects.equals(flightNumber, flight.flightNumber) && 
                Objects.equals(departureDate, flight.departureDate) && 
                Objects.equals(departureAirportCode, flight.departureAirportCode) && 
                Objects.equals(arrivalDate, flight.arrivalDate) && 
                Objects.equals(arrivalAirportCode, flight.arrivalAirportCode) && 
                Objects.equals(aircraftId, flight.aircraftId) && 
                status == flight.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flightNumber, departureDate, departureAirportCode, arrivalDate, arrivalAirportCode, aircraftId, status);
    }
}
