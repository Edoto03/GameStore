package bg.sofia.uni.fmi.mjt.gameplatform.store;

import bg.sofia.uni.fmi.mjt.gameplatform.store.item.StoreItem;
import bg.sofia.uni.fmi.mjt.gameplatform.store.item.category.GameBundle;
import bg.sofia.uni.fmi.mjt.gameplatform.store.item.filter.ItemFilter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GameStore implements StoreAPI {

    private StoreItem[] availableItems;

    public GameStore(StoreItem[] avalaibleItems) {
        this.availableItems = avalaibleItems != null ? avalaibleItems : new StoreItem[0];
    }

    @Override
    public StoreItem[] findItemByFilters(ItemFilter[] itemFilters) {
        if (availableItems == null || itemFilters == null) {
            return new StoreItem[0];
        }

        List<StoreItem> matchedItems = new ArrayList<>();

        for (StoreItem itemNow : this.availableItems) {
            boolean matchesAllFilters = true;
            for (ItemFilter filter : itemFilters) {
                if (!filter.matches(itemNow)) {
                    matchesAllFilters = false;
                    break;
                }
            }

            if (matchesAllFilters) {
                matchedItems.add(itemNow);
            }
        }

        return matchedItems.toArray(new StoreItem[0]);
    }

    @Override
    public void applyDiscount(String promoCode) {
        if (promoCode.equals("VAN40")) {
            for (StoreItem item : this.availableItems) {
                BigDecimal priceOfItem = item.getPrice();
                item.setPrice(priceOfItem.multiply(BigDecimal.valueOf(0.6)));
            }
        }
        else if(promoCode.equals("100YO")) {
            for(StoreItem item : this.availableItems) {
                item.setPrice(BigDecimal.valueOf(0.00));
            }
        }
        else System.out.println("Invalid promocode!");
    }

    @Override
    public boolean rateItem(StoreItem item, int rating) {
        if (rating < 0 || rating > 5) {
            return false;
        }

        for (StoreItem itemNow : this.availableItems) {
            if (itemNow.equals(item)) {
                itemNow.rate(rating);
                return true;
            }
        }

        return false;
    }
}
