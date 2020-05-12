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
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverEditedChannel_CheckRefund(@Payload EditedChannel editedChannel){


        if(editedChannel.isMe()){
            System.out.println("##### listener CheckRefund : " + editedChannel.toJson());
        }
        else
        {
            if(editedChannel.getClientID() != null)
            System.out.println(editedChannel.getClientID()+"회원님의 조회수는" +editedChannel.getTotalView()+"입니다.");
            ClientSystem cs = new ClientSystem();
            cs.clientId = editedChannel.getClientID();
            cs.totalView = editedChannel.getTotalView();

        }
    }

}
