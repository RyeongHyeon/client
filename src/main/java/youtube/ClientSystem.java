package youtube;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="ClientSystem_table")
public class ClientSystem {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long clientId;
    public Integer totalView = 0;

    @PrePersist
    public void onPrePersist(){

        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;

        CreatedCustomer createdCustomer = new CreatedCustomer();
        BeanUtils.copyProperties(this, createdCustomer);
        createdCustomer.publishAfterCommit();
        System.out.println("Number "+clientId+"Client! Welcome to our U-tube!!");


    }

    @PreUpdate
    public void onPostEdited(){
        CheckedRefund checkedRefund = new CheckedRefund();
        BeanUtils.copyProperties(this, checkedRefund);
        System.out.println("Number "+clientId+" Client Applyed for a refund by " + totalView +"View.");
        checkedRefund.publishAfterCommit();

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
