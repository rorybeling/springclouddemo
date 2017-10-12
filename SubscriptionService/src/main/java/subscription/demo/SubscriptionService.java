package subscription.demo;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SubscriptionService {

    ConcurrentHashMap<String,Subscription> subscribers = new ConcurrentHashMap<>();

    public Subscription createSubscriber(String name, List<SkyPackage> packages) {

        Subscription subscription = subscribers.get(name);

        if (subscription == null) {
            subscription = new Subscription(name, packages);
        }

        return subscription;
    }

    public Subscription addPackage(String name, SkyPackage _package) {

        Subscription subscription = subscribers.get(name);
        subscription.addPackage(_package);

        return subscription;
    }

    public Subscription removePackage(String name, String _package) {

        Subscription subscription = subscribers.get(name);
        subscription.removePackage(_package);

        return subscription;
    }

    public ConcurrentHashMap<String,Subscription> getSubscriberDetails() {
        return subscribers;
    }
}
