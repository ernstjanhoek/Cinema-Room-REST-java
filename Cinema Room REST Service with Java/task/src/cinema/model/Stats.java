package cinema.model;

public class Stats {
    private int income;
    private int available;
    private int purchased;

    public Stats(int amountOfSeats) {
        this.income = 0;
        this.available = amountOfSeats;
        this.purchased = 0;
    }

    public void handlePurchase(int price) {
        updateIncome(price);
        updateAvailable(-1);
        updatePurchased(1);
    }

    public void handleReturn(int price) {
        updateIncome(-price);
        updateAvailable(1);
        updatePurchased(-1);
    }

    private void updateIncome(int value) {
        income+=value;
    }

    private void updateAvailable(int value) {
        available+=value;
    }

    private void updatePurchased(int value) {
        purchased+=value;
    }

    public int getIncome() {
        return income;
    }

    public int getAvailable() {
        return available;
    }

    public int getPurchased() {
        return purchased;
    }
}
