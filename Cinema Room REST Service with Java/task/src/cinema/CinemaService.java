package cinema;

import cinema.model.Cinema;
import cinema.model.Seat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaService {
    Cinema cinema;

    public CinemaService() {
        cinema = new Cinema(9, 9);
    }

    public Seat purchaseSeat(int row, int column) {
        if (row < 0 || row >= cinema.getRows() || column < 0 || column >= cinema.getColumns()) {
            throw new IllegalArgumentException("The number of a row or a column is out of bounds!");
        }
        Seat seat = cinema.getSeat(row, column);
        if (!seat.isAvailable()) {
            throw new IllegalStateException("The ticket has been already purchased!");
        }
        seat.setAvailable(false);
        return seat;
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
