package bg.sofia.uni.fmi.mjt.gameplatform.store.item.category;

import bg.sofia.uni.fmi.mjt.gameplatform.store.item.StoreItem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Item implements StoreItem {
    private String title;
    private BigDecimal price;
    private LocalDateTime releaseDate;

    protected ArrayList<Double> ratings;


    public Item(String title, BigDecimal price, LocalDateTime releaseDate) {
        this.title = title;
        this.price = price.setScale(2, RoundingMode.HALF_UP);
        this.releaseDate = releaseDate;
        this.ratings = new ArrayList<>();
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public BigDecimal getPrice() {
        return this.price;
    }

    @Override
    public double getRating() {
        double rating = 0;
        for (double r : this.ratings) {
            rating += r;
        }
        return this.ratings.isEmpty() ? 0 : rating / this.ratings.size();
    }

    @Override
    public LocalDateTime getReleaseDate() {
        return this.releaseDate;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setPrice(BigDecimal price) {
        this.price = price.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public void rate(double rating) {
        ratings.add(rating);
    }
}
