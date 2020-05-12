package youtube;

public class CheckedRefund extends AbstractEvent {

    private Long clientId;
    private Integer totalView;

    public CheckedRefund(){
        super();
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
    public Integer getTotalView() {
        return totalView;
    }

    public void setTotalView(Integer totalView) {
        this.totalView = totalView;
    }
}
