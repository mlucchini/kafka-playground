package com.marclucchini.vote;

import com.marclucchini.kafka.Topics;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/vote")
public class VoteController {

  private static final Log logger = LogFactory.getLog(VoteController.class);
  private KafkaTemplate<String, String> kafkaTemplate;

  public VoteController(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @PostMapping("/{person}")
  @ResponseBody
  public ResponseEntity<String> vote(@PathVariable String person) {
    kafkaTemplate.send(Topics.VOTE, person).addCallback(
        success -> logger.debug(String.format("\"%s\" was sent successfully", person)),
        failure -> logger.error(String.format("\"%s\" could not be send", person)));
    return ResponseEntity.accepted().body(String.format("Vote \"%s\" accepted", person));
  }
}
