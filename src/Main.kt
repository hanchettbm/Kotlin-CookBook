import java.util.Scanner

fun add() {
    print("What are you making: ")
    val recipeName: String? = readLine()
    val recipe = mutableMapOf<String, Any?>()
    recipe["Recipe Name"] = recipeName
    var leave = false
    while (!leave) {
        val scanner = Scanner(System.`in`)
        print("Add an ingredient and amount? (y/n): ")
        val input : String? = scanner.next()?.lowercase()

        if (input == "y"){

            print("What ingredient do you need: ")
            val ingredient : String? = readLine()

            print("How much $ingredient do you need: ")
            val amount : String? = readLine()

            if (ingredient != null && amount != null) {
                recipe[ingredient] = amount
            }
            if (ingredient == null || amount == null) {
                println("No input received Please try again.")
            }
        }
        if (input == "n"){
            leave = true
        }
    }

    print("Will this be baked in the A) oven B) Stove: ")
    val bakeInput : String? = readLine()?.uppercase()


    if (bakeInput == "A") {
        print("What will be the Oven temp in \u2109: ")
        var bakeTemp: String? = readLine()
        bakeTemp = ("$bakeTemp \u2109")
        recipe["Baking Temp"] = bakeTemp
    }
    if (bakeInput == "B"){
        print("Pick A) Low B) Medium C) High heat: ")
        val stoveTemp = when (readLine()?.uppercase()) {
            "A" -> "Stove: Low"
            "B" -> "Stove: Medium"
            "C" -> "Stove: High"
            else -> "Stove: Off"
        }
        recipe["CookingTemp"] = stoveTemp
    } else {
        println ("Wrong input")
    }


    print("How long is the cook time (In Minutes): ")
    val cookTime : String = readLine() + " min"
    recipe["Cook Time"] = cookTime

    println("Type your brief baking instructions here: ")
    print("> ")
    val instructions : String? = readLine()
    recipe["Instructions"] = instructions

    println(recipe)
}

fun main() {
    var leave = false
    while (!leave) {
        val scanner = Scanner(System.`in`)
        println("Welcome to your CookBook! What would you like to do?")
        println("A) Add a recipe B) Remove a recipe C) View a recipe D) View CookBook Q) Quit")
        print("> ")
        val input : String = scanner.next().uppercase()

        if (input == "A") {
            add()
        }
        if (input == "B") {
            println ("Testing B")
        }
        if (input == "C") {
            println ("Testing C")
        }
        if (input == "D") {
            println ("Testing D")
        }
        if (input == "Q") {
            println ("Goodbye!")
            leave = true
        }


    }

}