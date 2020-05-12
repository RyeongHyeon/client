package youtube;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="ClientSystem_table")
public class ClientSystem {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String clientId;
    private Integer totalView;

    @PrePersist
    public void onPrePersist(){
        CreatedCustomer createdCustomer = new CreatedCustomer();
        createdCustomer.setClientId("##번 고객");

        BeanUtils.copyProperties(this, createdCustomer);
        createdCustomer.publishAfterCommit();


        CheckedRefund checkedRefund = new CheckedRefund();
        checkedRefund.setClientId("##번 고객");
        checkedRefund.setTotalView(300000);
        BeanUtils.copyProperties(this, checkedRefund);
        checkedRefund.publishAfterCommit();
        System.out.print(clientId+"님"+totalView+"조회수 환급신청함");


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
