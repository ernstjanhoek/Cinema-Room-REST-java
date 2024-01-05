package cinema;

public class Seat {
    private int row;
    private int column;
    private int price;
    private boolean available;
    public Seat() {}
    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.price = row <= 4 ? 10 : 8;
        this.available = true;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }
    public void setColumn(int column) {
        this.column = column;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setAvailable(boolean value) {
        this.available = value;
    }
    // Alternatieve naam voor getter om te voorkomen dat available veld door Spring verwerkt wordt.
    public boolean retrieveAvailable() {
        return available;
    }
}
