package service;

import dao.FlightDao;
import dto.FlightDto;

import java.util.List;
import java.util.stream.Collectors;

public class FlightService {
    private final static FlightService INSTANCE = new FlightService();

    private final FlightDao flightDao = FlightDao.getInstance();
    public List<FlightDto> findAll() {


        return flightDao.findAll().stream().map(flight ->
                new FlightDto(flight.getId(),
                        "%s - %s - %s".formatted(
                                flight.getArrivalAirportCode(),
                                flight.getDepartureAirportCode(),
                                flight.getStatus()
                        ))).collect(Collectors.toList());
    }

    public static FlightService getINSTANCE() {
        return INSTANCE;
    }

    private FlightService() {
    }
}
