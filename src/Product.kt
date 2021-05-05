data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val category: ProductCategory
)

enum class ProductCategory(val value: Int) {
    ELECTRONIC(0),
    FOOD(1),
    CLOTHING(2),
    COSMETIC(3)
    ;

    companion object {
        fun fromInt(value: Int) = ProductCategory.values().first { it.value == value }
    }
}