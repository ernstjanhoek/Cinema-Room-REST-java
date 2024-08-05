package cinema;

import cinema.model.Cinema;
import cinema.model.Seat;
import cinema.model.Stats;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CinemaService {
    private final Cinema cinema;
    private final Stats stats;

    public CinemaService() {
        cinema = new Cinema(9, 9);
        stats = new Stats(9 * 9);
    }

    public Stats getStats() {
        return this.stats;
    }

    public Seat purchaseTicket(int row, int column) {
        if (row < 0 || row >= cinema.getRows() || column < 0 || column >= cinema.getColumns()) {
            throw new IllegalArgumentException("The number of a row or a column is out of bounds!");
        }
        Seat seat = cinema.getSeat(row, column);
        if (!seat.isAvailable()) {
            throw new IllegalStateException("The ticket has been already purchased!");
        }
        seat.generateNewToken();
        stats.handlePurchase(seat.getPrice());
        return seat;
    }

    public Seat returnTicket(UUID token) {
        Optional<Seat> seat = cinema.getSeatByToken(token);
        if (seat.isPresent()) {
            cinema.makeSeatAvailable(seat.get().getRow(), seat.get().getColumn());
            stats.handleReturn(seat.get().getPrice());
            return seat.get();
        } else {
            throw new IllegalStateException("Wrong token!");
        }
    }

    public List<Seat> getSeats() {
        return cinema.getSeats();
    }

    public int getRows() {
        return cinema.getRows();
    }

    public int getColumns() {
        return cinema.getColumns();
    }
}
