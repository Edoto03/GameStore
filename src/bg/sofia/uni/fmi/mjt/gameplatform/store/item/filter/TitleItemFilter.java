package bg.sofia.uni.fmi.mjt.gameplatform.store.item.filter;

import bg.sofia.uni.fmi.mjt.gameplatform.store.item.StoreItem;

public class TitleItemFilter implements ItemFilter {

    private String title;
    private boolean caseSensitive;

    public TitleItemFilter(String title, boolean caseSensitive) {
        this.title = title;
        this.caseSensitive = caseSensitive;
    }

    @Override
    public boolean matches(StoreItem item) {
        if(this.caseSensitive)
            return item.getTitle().contains(this.title);
        return item.getTitle().toLowerCase().contains(this.title.toLowerCase());
    }
}
