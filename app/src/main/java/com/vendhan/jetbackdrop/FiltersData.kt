package com.vendhan.jetbackdrop

enum class FilterType {
    SLIDER,
    MULTISELECT_CHIPS,
    SINGLE_SELECT_CHIPS
}

data class FiltersData(
    val price: Price,
    val filters: List<Filters>
)

data class Price(
    val categoryName: String,
    val categoryType: FilterType = FilterType.SLIDER,
    val min: String,
    val max: String
)

data class Filters(
    val categoryName: String,
    val categoryType: FilterType = FilterType.MULTISELECT_CHIPS,
    val data: List<String>
)

fun getData(): FiltersData {
    val priceData = Price(
        categoryName = "Price range",
        min = "1000",
        max = "10000"
    )
    val filtersList = arrayListOf<Filters>()
    filtersList.addAll(
        listOf(
            Filters(
                categoryName = "Types",
                data = listOf("Sofa", "LoveSeat", "Ottomans", "Benches", "Chairs")
            ),
            Filters(
                categoryName = "Brand",
                data = listOf("Godrej", "Durian", "Solimo", "Wipro", "Ikea", "Pepperfry")
            ),
            Filters(
                categoryName = "Material",
                data = listOf("Leather", "Fabric", "Wood", "Plastic", "Steel")
            ),
            Filters(
                categoryName = "Colors",
                data = listOf("Black", "Blue", "Brown", "White", "Green", "MultiColor")
            ),
            Filters(
                categoryName = "Features",
                data = listOf("Eco-Friendly", "Handcrafted", "Stain-Resistant")
            ),
            Filters(
                categoryName = "Rating",
                categoryType = FilterType.SINGLE_SELECT_CHIPS,
                data = listOf("4+ Rating", "3+ Rating", "2+ Rating")
            )
        )
    )
    return FiltersData(
        price = priceData,
        filters = filtersList
    )
}
