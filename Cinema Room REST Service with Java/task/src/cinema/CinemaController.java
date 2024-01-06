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
    @GetMapping("/stats")
    public ReturnStats returnStats(@RequestParam(value = "password", required = false) String password) {
        if ("super_secret".equals(password)) {
            return new ReturnStats(cinema.retrieveIncome(), cinema.retrieveAvailable(), cinema.retrievePurchased());
        } else {
            throw new WrongPasswordException("The password is wrong!");
        }
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
            cinema.decrementAvailable();
            cinema.incrementPurchased();
            cinema.increaseIncome(localSeat.getPrice());
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
            cinema.decreaseIncome(localTicket.getPrice());
            cinema.incrementAvailable();
            cinema.decrementPurchased();
            return response;
        } else {
            throw new WrongTokenException("Wrong token!");
        }
    }
}