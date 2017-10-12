package subscription.demo;

import java.util.Date;

public class SkyPackage {

    private long id;
    private long price;
    private String name;

    public SkyPackage(long id, long price, String name, Date reservationDate) {
        this.id = id;
        this.price = price;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%d Package[%s] price for %d", id, name, price);
    }
}
