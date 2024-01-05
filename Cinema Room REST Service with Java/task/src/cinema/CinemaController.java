package cinema;

import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
public class CinemaController {
    private final Cinema cinema = new Cinema(9, 9);
    @GetMapping("/seats")
    public Cinema returnCinema() {
        return cinema;
    }
    @PostMapping("/purchase")
    public PurchaseResponse purchaseTicket(@RequestBody SeatRequest seat) {
        int localRow = seat.getRow();
        int localColumn = seat.getColumn();
        if (localColumn > cinema.getColumns() || localRow > cinema.getRows() || localRow <= 0 || localColumn <= 0) {
            throw new SeatIndexOutOfBoundsException("The number of a row or a column is out of bounds!");
        }
        Seat localSeat = cinema.getSeats().get(cinema.returnSeatIndex(localRow, localColumn));
        if (localSeat.retrieveAvailable()) {
            localSeat.setAvailable(false);
            PurchaseResponse response = new PurchaseResponse(localRow, localColumn);
            cinema.insertKeyValue(response.getToken(), localSeat);
            return response;
        } else {
            throw new AlreadyBookedException("The ticket has been already purchased!");
        }
    }
    @PostMapping("/return")
    public ReturnResponse returnTicket(@RequestBody TicketRequest ticket) {
        UUID localUUID;
        try {
            localUUID = ticket.getToken();
        } catch (Exception e) {
            throw new WrongTokenException("Wrong token!");
        }
        Seat localTicket = cinema.retrieveTickets().get(localUUID);
        if (localTicket != null) {
            ReturnResponse response = new ReturnResponse(localTicket);
            cinema.retrieveTickets().remove(localUUID);
            cinema.getSeats().get(cinema.returnSeatIndex(localTicket.getRow(), localTicket.getColumn())).setAvailable(true);
            return response;
        } else {
            throw new WrongTokenException("Wrong token!");
        }
    }
}