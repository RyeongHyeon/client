package youtube;

public class CheckedRefund extends AbstractEvent {

    private String clientId;
    private Integer totalView;

    public CheckedRefund(){
        super();
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    public Integer getTotalView() {
        return totalView;
    }

    public void setTotalView(Integer totalView) {
        this.totalView = totalView;
    }
}
