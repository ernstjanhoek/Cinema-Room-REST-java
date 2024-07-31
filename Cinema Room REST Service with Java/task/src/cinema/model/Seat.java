package cinema.model;

public class Seat {
    private final int row;
    private final int column;
    private final int price;
    private boolean available;

    public Seat(int row, int column, int price) {
        this.row = row;
        this.column = column;
        this.price = price;
        this.available = true;
    }

    public Seat(int row, int column, int price, boolean available) {
        this.row = row;
        this.column = column;
        this.price = price;
        this.available = true;
    }

    static public Seat copy(Seat seat) {
        return new Seat(
                seat.getRow(),
                seat.getColumn(),
                seat.getPrice(),
                seat.isAvailable()
        );
    }

    public void setAvailable(boolean available) {
        this.available = available;
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

    public boolean isAvailable() {
        return available;
    }
}
