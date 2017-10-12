package subscription.demo;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SubscriptionService {

    ConcurrentHashMap<String,Subscription> subscribers = new ConcurrentHashMap<>();

    public Subscription createSubscriber(String name, List<String> packages) {

        Subscription subscription = subscribers.get(name);

        if (subscription == null) {
            subscription = new Subscription(name, packages);
        }

        return subscription;
    }

    public void addPackage(String name, String _package) {

        Subscription subscription = subscribers.get(name);
        subscription.addPackage(_package);
    }

    public void removePackage(String name, String _package) {

        Subscription subscription = subscribers.get(name);
        subscription.removePackage(_package);
    }
}
