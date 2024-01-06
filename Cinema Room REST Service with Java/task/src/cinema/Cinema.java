package cinema;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class Cinema {
    private int available;
    private int purchased;
    private int income;
    private int rows;
    private int columns;
    private List<Seat> seats = new ArrayList<>();
    private Map<UUID, Seat> tickets = new ConcurrentHashMap<>();
    Cinema() {}
    Cinema(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.available = rows * columns;
        this.purchased = 0;
        this.income = 0;
        List<Seat> threadSafeList = new ArrayList<>();
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                threadSafeList.add(new Seat(i, j));
            }
        }
        seats = Collections.synchronizedList(threadSafeList);
    }
    public void insertKeyValue(UUID key, Seat value) {
        this.tickets.put(key, value);
    }
    public int getColumns() {
        return columns;
    }
    public void setColumns(int columns) {
        this.columns = columns;
    }
    public int getRows() {
        return rows;
    }
    public void setRows(int rows) {
        this.rows = rows;
    }
    public List<Seat> getSeats() {
        return this.seats;
    }
    public int returnSeatIndex(int row, int column) {
        return ((row - 1) * this.columns) + (column - 1);
    }
    public Map<UUID, Seat> retrieveTickets() {
        return tickets;
    }
    public void setTickets(Map<UUID, Seat> tickets) {
        this.tickets = tickets;
    }
    public void incrementAvailable() {
        this.available++;
    }
    public void decrementAvailable() {
        this.available--;
    }
    public int retrieveAvailable() {
        return this.available;
    }
    public void incrementPurchased() {
        this.purchased++;
    }
    public void decrementPurchased() {
        this.purchased--;
    }
    public int retrievePurchased() {
        return this.purchased;
    }
    public void increaseIncome(int value) {
        this.income += value;
    }
    public void decreaseIncome(int value) {
        this.income -= value;
    }
    public int retrieveIncome() {
        return this.income;
    }
}