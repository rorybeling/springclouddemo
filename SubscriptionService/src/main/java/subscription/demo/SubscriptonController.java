package subscription.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="subscriptions")
class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    private RestTemplate restTemplate = new RestTemplate();

    @RequestMapping(value = "/createSubscription", method = RequestMethod.POST)
    public Subscription createSubscription(@RequestParam(value = "username") String username,
                                       @RequestParam(value = "packages") List<String> packageNames) {

        List<SkyPackage> userPackages = new ArrayList();

        ResponseEntity<SkyPackages> restExchange = restTemplate.exchange(
                "http://user-service/skypackages/",
                HttpMethod.GET,
                null, SkyPackages.class , "" );

        List<SkyPackage> available = restExchange.getBody().skyPackageList;
        userPackages = available.stream()
                                .filter(skyPackage -> packageNames.contains(skyPackage.getName()))
                                .collect(Collectors.toList());

        Subscription subscription = subscriptionService.createSubscriber(username, userPackages);

        return subscription;
    }

    @RequestMapping(value = "/add/{packagename}", method = RequestMethod.POST)
    public Subscription addPackage(@PathVariable("username") String username,
                                  @PathVariable("packagename") String packagename) {

        ResponseEntity<SkyPackage> restExchange = restTemplate.exchange(
                "http://user-service/getPackageByName/{packagename}",
                HttpMethod.GET,
                null, SkyPackage.class , packagename );

        SkyPackage newPackage = restExchange.getBody();

        return subscriptionService.addPackage(username, newPackage);
    }

    @RequestMapping(value = "/remove/{packagename}", method = RequestMethod.POST)
    public Subscription removePackage(@PathVariable("username") String username,
                                   @PathVariable("packagename") String packagename) {


        return subscriptionService.removePackage(username, packagename);
    }

}

