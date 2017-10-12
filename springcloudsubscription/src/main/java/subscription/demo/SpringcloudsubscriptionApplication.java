package subscription.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.InboundChannelAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class SpringcloudsubscriptionApplication {

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "producer");
		SpringApplication.run(SpringcloudsubscriptionApplication.class, args);
	}
}

@EnableBinding(Source.class)
class RawTimeProducer {

	@InboundChannelAdapter(Source.OUTPUT)
	public String timerMessageSource() {
		return new SimpleDateFormat().format(new Date());
	}
}


//HashMap<String, List<String>>