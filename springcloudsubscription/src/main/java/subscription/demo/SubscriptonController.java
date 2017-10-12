package subscription.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sun.tools.javac.util.List;

@RestController
@RequestMapping(value="subscriptons")
class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/createSubscription", method = RequestMethod.POST)
    public Subscription bookReservation(@RequestParam(value = "username") String username,
                                       @RequestParam(value = "packages") List<String> packages) {

//        ResponseEntity<Subscription> restExchange = restTemplate.exchange(
//                "http://user-service/users/{username}",
//                HttpMethod.GET,
//                null, User.class, username);
//
//        User user = restExchange.getBody();
//        Reservation reservation = null;

        Subscription subscription = subscriptionService.createSubscriber(username, packages);

        return subscription;
    }

    @RequestMapping(value = "/add/{packagename}", method = RequestMethod.POST)
    public Subscription addPackage(@PathVariable("username") String username,
                                  @PathVariable("packagename") String packagename) {


        return reservationService.getReservation(reservationNum);
    }

}

