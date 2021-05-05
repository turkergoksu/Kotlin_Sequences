import java.util.*
import kotlin.random.Random
import kotlin.system.measureTimeMillis

const val PRODUCT_COUNT = 100_000
const val ORDER_COUNT = 5_000_000
const val PRODUCT_CATEGORY_COUNT = 4
val products = createProducts(PRODUCT_COUNT)
val orders = createOrders(ORDER_COUNT)

fun main() {
    exampleSumByDouble()
    exampleAverage()
    exampleTake()
    exampleFlatMap()
}

fun exampleSumByDouble() {
    println("\nFind the total money gained from FOOD products")
    println("Iterable implementation")
    measure {
        val foodProducts = orders
            .flatMap { it.products }
            .filter { it.category == ProductCategory.FOOD }

        println("Total money gained from FOOD: ${foodProducts.sumByDouble { it.price }}")
    }

    println("Sequence implementation")
    measure {
        val foodProducts = orders
            .asSequence()
            .flatMap { it.products }
            .filter { it.category == ProductCategory.FOOD }

        println("Total money gained from FOOD: ${foodProducts.sumByDouble { it.price }}")
    }
}

fun exampleAverage() {
    println("\nFind the average price of sold ELECTRONIC products")
    println("Iterable implementation")
    measure {
        val electronicProducts = orders
            .flatMap { it.products }
            .filter { it.category == ProductCategory.ELECTRONIC }
            .map { it.price }

        println("Average price of sold ELECTRONIC products: ${electronicProducts.average()}")
    }

    println("Sequence implementation")
    measure {
        val electronicProducts = orders
            .asSequence()
            .flatMap { it.products }
            .filter { it.category == ProductCategory.ELECTRONIC }
            .map { it.price }

        println("Average price of sold ELECTRONIC products: ${electronicProducts.average()}")
    }
}

fun exampleTake() {
    println("\nGet 100 CLOTHING products that price is bigger than 0.7")
    println("Iterable implementation")
    measure {
        val clothingProducts = orders
            .flatMap { it.products }
            .filter { it.category == ProductCategory.CLOTHING && it.price > 0.7 }
            .take(100)

        println("100 CLOTHING products that price is bigger than 0.7: $clothingProducts")
    }

    println("Sequence implementation")
    measure {
        val clothingProducts = orders
            .asSequence()
            .flatMap { it.products }
            .filter { it.category == ProductCategory.CLOTHING && it.price > 0.7 }
            .take(100)
            .toList()

        println("100 CLOTHING products that price is bigger than 0.7: $clothingProducts")
    }
}

fun exampleFlatMap(){
    println("\nTesting flatMap()")
    println("\nIterable implementation")
    measure {
        val products = orders
            .flatMap { it.products }
            .toSet()
    }

    println("Sequence implementation")
    measure {
        val products = orders
            .asSequence()
            .flatMap { it.products }
            .toSet()
    }
}

fun createOrders(orderCount: Int): List<Order> {
    println("...Creating orders...")
    val orders = mutableListOf<Order>()
    for (i in 1..orderCount) {
        val p = mutableListOf<Product>()
        val orderProductCount = Random.nextInt(1, 10)
        for (j in 1..orderProductCount) {
            val randomProduct = products[Random.nextInt(PRODUCT_COUNT)]
            p.add(randomProduct)
        }
        val order = Order(id = i, products = p)
        orders.add(order)
    }
    println("...Finished creating orders...")
    return orders
}

fun createProducts(productCount: Int): List<Product> {
    println("...Creating products...")
    val products = mutableListOf<Product>()
    for (i in 1..productCount) {
        val productCategoryId = Random.nextInt(PRODUCT_CATEGORY_COUNT)
        val product = Product(
            id = i,
            name = UUID.randomUUID().toString(),
            price = Random.nextDouble(),
            category = ProductCategory.fromInt(productCategoryId)
        )
        products.add(product)
    }
    println("...Finished creating products...")
    return products
}

fun measure(block: () -> Unit) {
    val elapsedTime = measureTimeMillis {
        block()
    }
    println("Time: $elapsedTime ms")
}