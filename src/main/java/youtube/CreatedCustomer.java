package youtube;

public class CreatedCustomer extends AbstractEvent {

    private Long clientId;

    public CreatedCustomer(){
        super();
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
