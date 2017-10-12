package subscription.demo;

import java.util.List;

public class Subscription {

    String username;
    List<String> packages;

    public Subscription(String username, List<String> packages) {
        this.username = username;
        this.packages = packages;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getPackages() {
        return packages;
    }

    public void setPackages(List<String> packages) {
        this.packages = packages;
    }

    public void addPackage(String _package) {
        this.packages.add(_package);
    }

    public void removePackage(String _package) {
        this.packages.remove(_package);
    }
}
