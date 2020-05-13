package youtube;

import youtube.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{



    @Autowired
    private ClientSystemRepository clientSystemRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverEditedChannel_CheckRefund(@Payload EditedChannel editedChannel){
        if(editedChannel.isMe()){
            System.out.println("##### listener CheckRefund : " + editedChannel.toJson());
            ClientSystem cs = new ClientSystem();
            cs.setTotalView(editedChannel.getTotalView());
            cs.setClientId(editedChannel.getClientId());
            System.out.println("Number "+cs.clientId+" client's totalview is " +cs.totalView);
            if(editedChannel.getClientId() != null)
            {
                clientSystemRepository.findById(editedChannel.getClientId()).ifPresent(
                        clientSystem -> {
                            clientSystem.setTotalView(editedChannel.getTotalView());
                            clientSystem.setClientId(editedChannel.getClientId());
                             // 조회수 세팅
                            clientSystemRepository.save(clientSystem);
                        }
                );
            }


        }
    }

}
