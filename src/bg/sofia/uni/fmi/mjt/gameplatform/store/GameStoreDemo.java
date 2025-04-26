package bg.sofia.uni.fmi.mjt.gameplatform;

import bg.sofia.uni.fmi.mjt.gameplatform.store.GameStore;
import bg.sofia.uni.fmi.mjt.gameplatform.store.item.StoreItem;
import bg.sofia.uni.fmi.mjt.gameplatform.store.item.category.DLC;
import bg.sofia.uni.fmi.mjt.gameplatform.store.item.category.Game;
import bg.sofia.uni.fmi.mjt.gameplatform.store.item.category.GameBundle;
import bg.sofia.uni.fmi.mjt.gameplatform.store.item.category.Item;
import bg.sofia.uni.fmi.mjt.gameplatform.store.item.filter.ItemFilter;
import bg.sofia.uni.fmi.mjt.gameplatform.store.item.filter.PriceItemFilter;
import bg.sofia.uni.fmi.mjt.gameplatform.store.item.filter.RatingItemFilter;
import bg.sofia.uni.fmi.mjt.gameplatform.store.item.filter.ReleaseDateItemFilter;
import bg.sofia.uni.fmi.mjt.gameplatform.store.item.filter.TitleItemFilter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GameStoreDemo {

    public static void main(String[] args) {
        // Създаване на няколко примерни игри
        Game game1 = new Game("Cyberpunk 2077", new BigDecimal("59.99"),
            LocalDateTime.of(2020, 12, 10, 0, 0), "RPG");

        Game game2 = new Game("Witcher 3", new BigDecimal("29.99"),
            LocalDateTime.of(2015, 5, 19, 0, 0), "RPG");

        Game game3 = new Game("Counter-Strike 2", new BigDecimal("0.00"),
            LocalDateTime.of(2023, 9, 27, 0, 0), "FPS");

        Game game4 = new Game("Baldur's Gate 3", new BigDecimal("59.99"),
            LocalDateTime.of(2023, 8, 3, 0, 0), "RPG");

        Game game5 = new Game("Call of Duty: Modern Warfare III", new BigDecimal("69.99"),
            LocalDateTime.of(2023, 11, 10, 0, 0), "FPS");

        // Добавяне на рейтинги към игрите
        game1.rate(4.2);
        game1.rate(3.8);
        game2.rate(4.8);
        game2.rate(4.9);
        game3.rate(4.0);
        game4.rate(4.7);
        game4.rate(4.9);
        game5.rate(3.5);

        // Създаване на DLC-та
        DLC dlc1 = new DLC("Cyberpunk 2077: Phantom Liberty", new BigDecimal("29.99"),
            LocalDateTime.of(2023, 9, 26, 0, 0), game1);

        DLC dlc2 = new DLC("Witcher 3: Blood and Wine", new BigDecimal("19.99"),
            LocalDateTime.of(2016, 5, 31, 0, 0), game2);

        // Добавяне на рейтинги към DLC-тата
        dlc1.rate(4.5);
        dlc2.rate(4.9);

        // Създаване на пакети от игри
        GameBundle bundle1 = new GameBundle("CD Projekt Red Bundle", new BigDecimal("79.99"),
            LocalDateTime.of(2023, 11, 1, 0, 0), new Game[]{game1, game2});

        GameBundle bundle2 = new GameBundle("FPS Bundle", new BigDecimal("69.99"),
            LocalDateTime.of(2023, 11, 15, 0, 0), new Game[]{game3, game5});

        // Добавяне на рейтинги към пакетите
        bundle1.rate(4.6);
        bundle2.rate(4.2);

        // Създаване на масив с всички артикули
        Item[] storeItems = {
            game1, game2, game3, game4, game5,
            dlc1, dlc2,
            bundle1, bundle2
        };

        // Инициализиране на магазина
        GameStore gameStore = new GameStore(storeItems);

        // Демонстрация на използването на филтри
        System.out.println("=== Демонстрация на филтри ===");

        // 1. Филтриране по цена (артикули между 20 и 60 лева)
        ItemFilter priceFilter = new PriceItemFilter(new BigDecimal("20.00"), new BigDecimal("60.00"));

        System.out.println("\n1. Артикули с цена между 20.00 и 60.00 лева:");
        StoreItem[] priceFilteredItems = gameStore.findItemByFilters(new ItemFilter[]{priceFilter});
        printItems(priceFilteredItems);

        // 2. Филтриране по рейтинг (артикули с рейтинг поне 4.5)
        ItemFilter ratingFilter = new RatingItemFilter(4.5);

        System.out.println("\n2. Артикули с рейтинг поне 4.5:");
        StoreItem[] ratingFilteredItems = gameStore.findItemByFilters(new ItemFilter[]{ratingFilter});
        printItems(ratingFilteredItems);

        // 3. Филтриране по дата на издаване (артикули, издадени през 2023 година)
        LocalDateTime startOf2023 = LocalDateTime.of(2023, 1, 1, 0, 0);
        LocalDateTime endOf2023 = LocalDateTime.of(2023, 12, 31, 23, 59);
        ItemFilter dateFilter = new ReleaseDateItemFilter(startOf2023, endOf2023);

        System.out.println("\n3. Артикули, издадени през 2023 година:");
        StoreItem[] dateFilteredItems = gameStore.findItemByFilters(new ItemFilter[]{dateFilter});
        printItems(dateFilteredItems);

        // 4. Филтриране по заглавие (артикули, съдържащи "Witcher" в заглавието, без значение на малки/големи букви)
        ItemFilter titleFilter = new TitleItemFilter("witcher", false);

        System.out.println("\n4. Артикули със заглавие, съдържащо 'Witcher':");
        StoreItem[] titleFilteredItems = gameStore.findItemByFilters(new ItemFilter[]{titleFilter});
        printItems(titleFilteredItems);

        // 5. Комбиниране на филтри (артикули с цена между 20 и 60 лева И рейтинг поне 4.5)
        System.out.println("\n5. Артикули с цена между 20.00 и 60.00 лева И рейтинг поне 4.5:");
        StoreItem[] combinedFilteredItems = gameStore.findItemByFilters(new ItemFilter[]{priceFilter, ratingFilter});
        printItems(combinedFilteredItems);

        // Демонстрация на прилагане на отстъпки
        System.out.println("\n=== Демонстрация на промоционални кодове ===");

        // Показване на цените преди отстъпката
        System.out.println("\nЦени преди отстъпката:");
        printItems(storeItems);

        // Прилагане на промоционален код VAN40 (40% отстъпка)
        gameStore.applyDiscount("VAN40");
        System.out.println("\nЦени след прилагане на промоционален код VAN40 (40% отстъпка):");
        printItems(storeItems);

        // Връщане на оригиналните цени за демонстрация на 100YO промокода
        resetPrices(storeItems);

        // Прилагане на промоционален код 100YO (100% отстъпка)
        gameStore.applyDiscount("100YO");
        System.out.println("\nЦени след прилагане на промоционален код 100YO (100% отстъпка):");
        printItems(storeItems);

        // Демонстрация на оценяване на артикул
        System.out.println("\n=== Демонстрация на оценяване на артикул ===");

        System.out.println("\nРейтинг на 'Cyberpunk 2077' преди оценяването: " + game1.getRating());

        // Оценяване на артикул с рейтинг 5
        boolean ratingSuccessful = gameStore.rateItem(game1, 5);
        System.out.println("Успешно оценяване: " + ratingSuccessful);
        System.out.println("Рейтинг на 'Cyberpunk 2077' след оценяването: " + game1.getRating());

        // Опит за оценяване с невалиден рейтинг
        ratingSuccessful = gameStore.rateItem(game1, 6); // Рейтинг извън валидния диапазон [1, 5]
        System.out.println("Опит за оценяване с невалиден рейтинг (6): " + ratingSuccessful);
    }

    private static void printItems(StoreItem[] items) {
        if (items.length == 0) {
            System.out.println("Няма намерени артикули.");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        for (StoreItem item : items) {
            System.out.printf("Заглавие: %-35s | Цена: %8.2f | Рейтинг: %.2f | Дата на издаване: %s",
                item.getTitle(),
                item.getPrice().doubleValue(),
                item.getRating(),
                item.getReleaseDate().format(formatter));

            if (item instanceof Game) {
                System.out.printf(" | Жанр: %s", ((Game) item).getGenre());
            } else if (item instanceof DLC) {
                System.out.printf(" | За игра: %s", ((DLC) item).getGame().getTitle());
            } else if (item instanceof GameBundle) {
                System.out.print(" | Игри в пакета: ");
                Game[] bundleGames = ((GameBundle) item).getGames();
                for (int i = 0; i < bundleGames.length; i++) {
                    System.out.print(bundleGames[i].getTitle());
                    if (i < bundleGames.length - 1) {
                        System.out.print(", ");
                    }
                }
            }

            System.out.println();
        }
    }

    private static void resetPrices(StoreItem[] items) {
        // Връщане на оригиналните цени за демонстрационни цели
        items[0].setPrice(new BigDecimal("59.99")); // Cyberpunk 2077
        items[1].setPrice(new BigDecimal("29.99")); // Witcher 3
        items[2].setPrice(new BigDecimal("0.00"));  // Counter-Strike 2
        items[3].setPrice(new BigDecimal("59.99")); // Baldur's Gate 3
        items[4].setPrice(new BigDecimal("69.99")); // Call of Duty
        items[5].setPrice(new BigDecimal("29.99")); // Cyberpunk 2077 DLC
        items[6].setPrice(new BigDecimal("19.99")); // Witcher 3 DLC
        items[7].setPrice(new BigDecimal("79.99")); // CD Projekt Red Bundle
        items[8].setPrice(new BigDecimal("69.99")); // FPS Bundle
    }
}