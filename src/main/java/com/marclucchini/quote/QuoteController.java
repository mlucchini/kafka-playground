package com.marclucchini.quote;

import com.marclucchini.reactor.Channels;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.bus.EventBus;

import static reactor.bus.selector.Selectors.$;

@Controller
@RequestMapping("quote")
public class QuoteController {

  private EventBus eventBus;
  private QuotePublisher publisher;
  private QuoteReceiver receiver;

  public QuoteController(EventBus eventBus, QuotePublisher publisher, QuoteReceiver receiver) {
    this.eventBus = eventBus;
    this.publisher = publisher;
    this.receiver = receiver;

    this.eventBus.on($(Channels.QUOTE), receiver);
  }

  @PostMapping("{nbQuotes}")
  @ResponseBody
  public String publishQuotes(@PathVariable Integer nbQuotes) throws InterruptedException {
    publisher.publishQuotes(nbQuotes);
    return "Done";
  }
}
