package service;

import dao.TicketDao;
import dto.TicketDto;

import java.util.List;
import java.util.stream.Collectors;

public class TicketService {
    private final static TicketService INSTANCE = new TicketService();
    private final TicketDao ticketDao = TicketDao.getInstance();

    public static TicketService getInstance() {
        return INSTANCE;
    }

    public List<TicketDto> findAllByFlightId(Long flightId) {
        return ticketDao.findAllByFlightId(flightId).stream().map(
                ticket -> new TicketDto(ticket.getId(), ticket.getFlight().getId(), ticket.getSeat_number())
        ).collect(Collectors.toList());
    }

    public static TicketService getINSTANCE() {
        return INSTANCE;
    }

    private TicketService() {
    }
}
