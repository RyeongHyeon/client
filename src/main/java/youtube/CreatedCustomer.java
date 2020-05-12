package youtube;

public class CreatedCustomer extends AbstractEvent {

    private String clientId;

    public CreatedCustomer(){
        super();
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
