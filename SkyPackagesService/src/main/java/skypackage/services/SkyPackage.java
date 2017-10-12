package src.src.main.java.skypackage.services;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "skyPackage")
public class SkyPackage {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private double price;
    private String name;

    public SkyPackage(long id, long price, String name) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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
        return String.format("%d SkyPackage[%s] price for %d", id, name, price);
    }
}
