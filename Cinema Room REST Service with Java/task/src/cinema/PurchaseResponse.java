package cinema;

import java.util.UUID;

public class PurchaseResponse {
    private UUID token;
    private Seat ticket;
    PurchaseResponse(int row, int column) {
        this.ticket = new Seat(row, column);
        this.token = UUID.randomUUID();
    }
    public Seat getTicket() {
        return ticket;
    }
    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }
    public UUID getToken() {
        return token;
    }
    public void setToken(UUID token) {
        this.token = token;
    }
}