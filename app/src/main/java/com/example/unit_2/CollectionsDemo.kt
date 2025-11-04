package com.example.unit_2

class CollectionsDemo {
    fun main() {
        val cities = listOf("Almaty", "Astana", "Shymkent", "Aktobe")

        println("Cities:")
        cities.forEach { println(it) }

        val mutableCities = mutableListOf("Paris", "London")
        mutableCities.add("Tokyo")
        println("Mutable list: $mutableCities")
    }
}