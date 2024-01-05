package cinema;

public class ReturnResponse {
    ReturnResponse(Seat ticket) {
        this.ticket = ticket;
    }
    private Seat ticket;
    public Seat getTicket() {
        return ticket;
    }
    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }
}
