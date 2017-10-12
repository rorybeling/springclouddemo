package subscription.demo;

import java.util.List;

public class Subscription {

    String username;
    List<SkyPackage> packages;

    public Subscription(String username, List<SkyPackage> packages) {
        this.username = username;
        this.packages = packages;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<SkyPackage> getPackages() {
        return packages;
    }

    public void setPackages(List<SkyPackage> packages) {
        this.packages = packages;
    }

    public void addPackage(SkyPackage _package) {
        this.packages.add(_package);
    }

    public void removePackage(String packageName) {

        this.packages.removeIf(packagename -> packagename.equals(packageName));
    }
}
