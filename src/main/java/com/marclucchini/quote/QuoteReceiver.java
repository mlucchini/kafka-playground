package com.marclucchini.quote;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.bus.Event;
import reactor.fn.Consumer;

@Service
public class QuoteReceiver implements Consumer<Event<Integer>> {

  private static Log logger = LogFactory.getLog(QuoteReceiver.class);
  private RestTemplate restTemplate = new RestTemplate();

  @Override
  public void accept(Event<Integer> ev) {
    QuoteResource resource = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", QuoteResource.class);
    logger.info("Quote " + ev.getData() + ": " + resource.getValue().getQuote());
  }
}
