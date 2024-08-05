package cinema.model;

import java.util.UUID;

public class Seat {
    private final int row;
    private final int column;
    private final int price;
    private UUID token;

    public Seat(int row, int column, int price) {
        this.row = row;
        this.column = column;
        this.price = price;
    }

    public Seat(int row, int column, int price, UUID uuid) {
        this.row = row;
        this.column = column;
        this.price = price;
        this.token = uuid;
    }

    static public Seat copy(Seat seat) {
        return new Seat(
                seat.getRow(),
                seat.getColumn(),
                seat.getPrice(),
                seat.getToken()
        );
    }

    public void generateNewToken() {
        this.token = UUID.randomUUID();
    }

    public boolean isAvailable() {
        return this.token == null;
    }

    public void makeAvailable() {
        this.token = null;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getPrice() {
        return price;
    }
}
