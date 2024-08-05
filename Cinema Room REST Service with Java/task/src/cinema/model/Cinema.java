package cinema.model;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Cinema {
    private final int rows;
    private final int columns;
    private final Map<SeatLocation, Seat> seatsByLocation;

    public Cinema(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        seatsByLocation = new ConcurrentHashMap<>();
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                Seat seat = new Seat(i, j, calculatePrice(rows, i));
                seatsByLocation.put(new SeatLocation(i, j), seat);
            }
        }
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

    public void makeSeatAvailable(int row, int column) {
        seatsByLocation.get(new SeatLocation(row, column)).makeAvailable();
    }

    public Optional<Seat> getSeatByToken(UUID token) {
        return seatsByLocation.values().stream()
                .filter(s -> s.getToken() != null && s.getToken().equals(token))
                .findFirst();
    }

    public List<Seat> getSeats() {
        return seatsByLocation.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .map(entry -> Seat.copy(entry.getValue()))
                .toList();
    }

    public record SeatLocation(int row, int column) implements Comparable<SeatLocation> {
        @Override
        public int compareTo(SeatLocation seatLocation) {
            int rowComparison = Integer.compare(this.row, seatLocation.row);
            if (rowComparison == 0) return Integer.compare(this.column, seatLocation.column);
            else return rowComparison;
        }
    }
}
