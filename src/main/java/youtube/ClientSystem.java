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
    public Integer totalView;

    @PrePersist
    public void onPrePersist(){

        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;

        CreatedCustomer createdCustomer = new CreatedCustomer();

        BeanUtils.copyProperties(this, createdCustomer);
        createdCustomer.publishAfterCommit();


    }

    @PreUpdate
    public void onPostEdited(){
        CheckedRefund checkedRefund = new CheckedRefund();
        BeanUtils.copyProperties(this, checkedRefund);

        checkedRefund.publishAfterCommit();
        System.out.print(clientId+"님"+totalView+"조회수 환급신청함");
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
