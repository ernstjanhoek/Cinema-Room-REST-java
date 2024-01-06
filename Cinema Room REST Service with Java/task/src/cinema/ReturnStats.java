package cinema;

public class ReturnStats {
    private int income;
    private int available;
    private int purchased;
    ReturnStats(int income, int available, int purchased) {
        this.available = available;
        this.income = income;
        this.purchased = purchased;
    }
    public int getIncome() {
        return income;
    }
    public void setIncome(int income) {
        this.income = income;
    }
    public int getAvailable() {
        return available;
    }
    public void setAvailable(int available) {
        this.available = available;
    }
    public int getPurchased() {
        return purchased;
    }
    public void setPurchased(int purchased) {
        this.purchased = purchased;
    }
}
