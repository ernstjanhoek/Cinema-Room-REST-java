package cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Cinema {
    private final int rows;
    private final int columns;
    private final List<Seat> seats;
    private final Map<SeatLocation, Seat> seatsByLocation;

    public Cinema(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        List<Seat> threadSafeList = new ArrayList<>();
        seatsByLocation = new ConcurrentHashMap<>();
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                Seat seat = new Seat(i, j, calculatePrice(rows, i));
                threadSafeList.add(seat);
                seatsByLocation.put(new SeatLocation(i, j), seat);
            }
        }
        seats = Collections.synchronizedList(threadSafeList);
    }

    private int calculatePrice(int totalRows, int currentRow) {
        return totalRows / 2 >= currentRow ? 10 : 8;
    }

    public Seat getSeat(int row, int column) {
        return seatsByLocation.get(new SeatLocation(row, column));
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public List<Seat> getSeats() {
        return seats.stream()
                .map(Seat::copy)
                .toList();
    }

    public record SeatLocation(int row, int column) {}
}