package com.marclucchini.vote;

import com.marclucchini.kafka.Topics;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class VoteLogger {

  private static final Log logger = LogFactory.getLog(VoteLogger.class);

  @KafkaListener(topics = Topics.VOTE)
  public void listen(ConsumerRecord<String, String> record) {
    logger.info(String.format("Record %s processed", record));
  }
}
