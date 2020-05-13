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
            System.out.println(editedChannel.getClientId()+"회원님의 조회수는" +editedChannel.getTotalView()+"입니다.1");
            ClientSystem cs = new ClientSystem();
            cs.clientId = editedChannel.getClientId();
            cs.totalView += editedChannel.getTotalView();
            if(editedChannel.getClientId() != null)
            {
                clientSystemRepository.findById(editedChannel.getClientId()).ifPresent(
                        clientSystem -> {
                             // 조회수 세팅
                            clientSystemRepository.save(clientSystem);
                        }
                );
            }

        }
        else
        {
            if(editedChannel.getClientId() != null){
                System.out.println(editedChannel.getClientId()+"회원님의 조회수는" +editedChannel.getTotalView()+"입니다.2");
                ClientSystem cs = new ClientSystem();
                cs.clientId = editedChannel.getClientId();
                cs.totalView = editedChannel.getTotalView();
            }
        }
    }

}
