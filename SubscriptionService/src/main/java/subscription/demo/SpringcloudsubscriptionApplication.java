package subscription.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
public class SpringcloudsubscriptionApplication {

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "producer");
		SpringApplication.run(SpringcloudsubscriptionApplication.class, args);
	}
}

@EnableBinding(Source.class)
class RawTimeProducer {

	@Autowired
	private SubscriptionService subscriptionService;

	@InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay = "10000"))
	public int timerMessageSource() {

		System.out.println("Generating Subscriber details!");
		return subscriptionService.getSubscriberDetails().size();
	}
}