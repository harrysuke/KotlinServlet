package org.example

//Bundle to WAR file: ./gradlew clean war
fun main() {
    val name = "Kotlin"
    println("Hello, " + name + "!")

    for (i in 1..5) {
        println("i = $i")
    }
}