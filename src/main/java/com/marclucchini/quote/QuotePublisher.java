package com.marclucchini.quote;

import com.marclucchini.reactor.Channels;
import org.springframework.stereotype.Service;
import reactor.bus.Event;
import reactor.bus.EventBus;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@Service
public class QuotePublisher {

  private EventBus eventBus;
  private AtomicInteger counter = new AtomicInteger(1);

  public QuotePublisher(EventBus eventBus) {
    this.eventBus = eventBus;
  }

  public void publishQuotes(int nbQuotes) throws InterruptedException {
    long start = System.currentTimeMillis();

    IntStream.range(0, nbQuotes).forEach(i -> eventBus.notify(Channels.QUOTE, Event.wrap(counter.getAndIncrement())));

    long elapsed = System.currentTimeMillis() - start;

    System.out.println("Elapsed time " + elapsed + " ms");
    System.out.println("Average time per quote: " + elapsed / nbQuotes + "ms");
  }
}
