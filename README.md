# Kotlin_Sequences
You may reach the Medium story from [here]().
## Functions and Outputs
### Case-1: sumByDouble()
```kotlin
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
```
```
Find the total money gained from FOOD products
Iterable implementation
Total money gained from FOOD: 3169452.9117080076
Time: 2824 ms
Sequence implementation
Total money gained from FOOD: 3169452.9117080076
Time: 681 ms
```

### Case-2: average()
```kotlin
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
```

### Case-3: take()
```kotlin
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
```

### Case-4: flatMap()
```kotlin
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
```

I got `OutOfMemory` exception when I tried with `toList()`. If you want to test with `toList()` you may decrease the `ORDER_COUNT` value.