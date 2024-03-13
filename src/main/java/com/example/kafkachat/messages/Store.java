package com.example.kafkachat.messages;

import com.example.kafkachat.messages.Message;
import jakarta.annotation.PostConstruct;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import java.util.List;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Store {
  private final static String topic = "messages";

  @Autowired
  private KafkaTemplate<String, String> template;
  private List<Message> lastMessages;

  @PostConstruct
  private void postConstruct() {
    lastMessages = new ArrayList<Message>();
  }

  void save(final String message) {
    this.template.send(Store.topic, "someid", message);
  }

  // List<Message> readMessagesOffset(int limit, int offset)

  List<Message> readMessages(int limit) {
    // todo: read messages via the offset method
    int toIndex = lastMessages.size();
    int fromIndex = toIndex - limit;

    if (toIndex < 0) {
      toIndex = 0;
    }

    if (fromIndex < 0) {
      fromIndex = 0;
    }

    log.info("read messages from index {} to {}", fromIndex, toIndex);
    return lastMessages.subList(fromIndex, toIndex);
  }

  @KafkaListener(
    id="message-listener",
    topics=Store.topic,
    topicPartitions = @TopicPartition(
      topic=Store.topic,
      partitionOffsets = @PartitionOffset(partition = "0",
      initialOffset="0")
    )
  )
  public void messageListener(@Payload String text) {
    log.info("Store start saving message: {}", text);

    lastMessages.add(new Message("", text));

    log.info("Store finished saving message: {}", text);
  }
}
