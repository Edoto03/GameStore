# GameStore 🎮 🛒

An online game store platform inspired by Steam and Epic Games, developed as a Java assignment. It allows managing, filtering, and rating various store items.

## Features

*   **Item Management:** Supports `Game`, `DLC`, and `GameBundle` types, all implementing the `StoreItem` interface.
*   **Item Filtering:** Users can filter items using various criteria:
    *   `PriceItemFilter`: By price range.
    *   `RatingItemFilter`: By minimum rating.
    *   `ReleaseDateItemFilter`: By release date range.
    *   `TitleItemFilter`: By title (case-sensitive or insensitive).
*   **Promo Codes:** Apply discounts using predefined codes:
    *   `VAN40`: 40% discount.
    *   `100YO`: 100% discount (Legendary).
*   **Rating System:** Allows rating items on a scale of 1 to 5 via the `rateItem` method in `StoreAPI`.

## Technology

*   Java
*   `java.math.BigDecimal` for precise pricing (formatted to X.XX).
*   `java.time.LocalDateTime` for release dates.
*   Implemented using only standard arrays as per assignment constraints.

## Project Structure

```
src
└─ bg.sofia.uni.fmi.mjt.gameplatform.store
    ├──── item
    │      ├──── category
    │      │      ├─ DLC.java
    │      │      ├─ Game.java
    │      │      ├─ GameBundle.java
    │      │      └─ Item.java
    │      │
    │      ├──── filter
    │      │      ├─ ItemFilter.java
    │      │      ├─ PriceItemFilter.java
    │      │      ├─ RatingItemFilter.java
    │      │      ├─ ReleaseDateItemFilter.java
    │      │      └─ TitleItemFilter.java
    │      │
    │      └──── StoreItem.java
    │
    ├──── GameStore.java
    ├──── GameStoreDemo.java
    └──── StoreAPI.java
```

## Author

*   **Edoto03** ([https://github.com/Edoto03](https://github.com/Edoto03))