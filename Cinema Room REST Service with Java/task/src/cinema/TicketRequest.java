package cinema;

import java.util.UUID;
public class TicketRequest {
    UUID token;
    TicketRequest() {}
    public TicketRequest(String value) {
        this.token = UUID.fromString(value);
    }
    public UUID getToken() {
        return token;
    }
    public void setToken(UUID token) {
        this.token = token;
    }
}
