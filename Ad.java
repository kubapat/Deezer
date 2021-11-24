import java.util.Objects;

public class Ad extends Track {
    private double price;

    public Ad(String name, Duration duration, double price) {
        super(name, duration);
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Ad ad = (Ad) o;
        return Double.compare(ad.price, price) == 0;
    }


    @Override
    public String toString() {
        return "Ad{" +
                "price=" + price +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                '}';
    }


}
