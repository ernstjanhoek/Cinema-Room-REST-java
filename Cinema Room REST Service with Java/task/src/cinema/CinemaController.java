package cinema;

import cinema.model.Seat;
import cinema.model.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class CinemaController {
    @Autowired
    CinemaService cinemaService;
    private final String SECRET_PASSWORD = "super_secret";

    @GetMapping(value = "/stats")
    public Stats cinemaStats(@RequestParam(required = false) String password) {
        if (!SECRET_PASSWORD.equals(password)) {
            throw new WrongPasswordException("The password is wrong!");
        }
        return cinemaService.getStats();
    }

    @GetMapping("/seats")
    public CinemaDTO getSeatsEndpoint() {
        List<Ticket> seats = cinemaService.getSeats().stream()
                .map(s -> new Ticket(s.getRow(), s.getColumn(), s.getPrice()))
                .toList();
        return new CinemaDTO(cinemaService.getRows(), cinemaService.getColumns(), seats);
    }

    @PostMapping("/purchase")
    public PurchaseResponse purchaseSeats(@RequestBody PurchaseRequest purchaseRequest) {
        Seat purchasedSeat = cinemaService.purchaseTicket(purchaseRequest.row(), purchaseRequest.column());
        return new PurchaseResponse(purchasedSeat.getToken(), new Ticket(purchasedSeat.getRow(), purchasedSeat.getColumn(), purchasedSeat.getPrice()));
    }

    @PostMapping("/return")
    public ReturnResponse returnTicket(@RequestBody TokenRequest returnTicketRequest) {
        Seat returnedSeat = cinemaService.returnTicket(returnTicketRequest.token);
        return new ReturnResponse( new Ticket(returnedSeat.getRow(), returnedSeat.getColumn(), returnedSeat.getPrice()));
    }

    public record Ticket(int row, int column, int price) {}

    public record PurchaseRequest(int row, int column) {}

    public record TokenRequest(UUID token) {}

    public record ReturnResponse(Ticket ticket) {}

    public record PurchaseResponse(UUID token, Ticket ticket) {}

    public record CinemaDTO(int rows, int columns, List<Ticket> seats) {}
}
