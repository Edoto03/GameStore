package bg.sofia.uni.fmi.mjt.gameplatform.store.item.category;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class GameBundle extends Item {
    private Game[] games;

    public GameBundle(String title, BigDecimal price, LocalDateTime releaseDate, Game[] games) {
        super(title, price, releaseDate);
        this.games = games != null ? games : new Game[0];
    }

    public Game[] getGames() {
        return games;
    }

    public void setGames(Game[] games) {
        this.games = games;
    }

    @Override
    public double getRating() {
        if(ratings.isEmpty() && (this.games == null || games.length == 0)) {
            return 0.00;
        }

        double sum = 0;
        for(Game game : this.games) {
            sum += game.getRating();
        }

        for (Double rating : ratings) {
            sum += rating;
        }

        return sum / (this.games.length + ratings.size());
    }
}