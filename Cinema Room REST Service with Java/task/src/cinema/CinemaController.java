package cinema;

import cinema.model.Cinema;

import cinema.model.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CinemaController {
    @Autowired
    CinemaService cinemaService;

    @GetMapping("/seats")
    public CinemaDTO getSeatsEndpoint() {
        List<PurchaseResponse> seats = cinemaService.getSeats().stream()
                .map(s -> new PurchaseResponse(s.getRow(), s.getColumn(), s.getPrice()))
                .toList();
        return new CinemaDTO(cinemaService.getRows(), cinemaService.getColumns(), seats);
    }

    @PostMapping("/purchase")
    public PurchaseResponse purchaseSeats(@RequestBody PurchaseRequest purchaseRequest) {
        Seat purchasedSeat = cinemaService.purchaseSeat(purchaseRequest.row(), purchaseRequest.column());
        return new PurchaseResponse(purchasedSeat.getRow(), purchasedSeat.getColumn(), purchasedSeat.getPrice());
    }

    public record CinemaDTO(int rows, int columns, List<PurchaseResponse> seats) {}
    public record PurchaseResponse(int row, int column, int price) {}
    public record PurchaseRequest(int row, int column) {}

}
