import java.util.Scanner

data class Recipe(var recipeName: String?, val recipe:MutableMap<String, Any?>)

fun add(): Recipe {
    print("What are you making: ")
    val recipeName = readLine()
    val recipe = mutableMapOf<String, Any?>()
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
    }

    print("How long is the cook time (In Minutes): ")
    val cookTime : String = readLine() + " min"
    recipe["Cook Time"] = cookTime

    println("Type your brief baking instructions here: ")
    print("> ")
    val instructions : String? = readLine()
    recipe["Instructions"] = instructions

    return Recipe(recipeName, recipe)
}

fun view(cookBook: MutableMap<String?, Any?>) {

    val scanner = Scanner(System.`in`)
    println("What would you like to view? 1) Your Cookbook 2) A specific Recipe")
    print("> ")
    val input = scanner.nextInt()
    print("\n")

    if (input == 1){
        cookBook.forEach { (k, v) ->
            println("Recipe For: $k")

            val recipe: MutableMap<String?, Any?> = v as MutableMap<String?, Any?>
            recipe.forEach { (k, v) ->
                println("$k = $v")
            }
            print("\n")
        }

    }
    if (input == 2){

        println("What Recipe do you want to view?")
        print("Name: ")
        val recipeName = readLine()

        cookBook.forEach { (k, v) ->
            if (recipeName == k){
                println("Recipe For: $k")

                val recipe: MutableMap<String?, Any?> = v as MutableMap<String?, Any?>
                recipe.forEach { (k, v) ->
                    println("$k = $v")
                }
            }
            print("\n")
        }

    }
}

fun remove(cookBook: MutableMap<String?, Any?>){
    val scanner = Scanner(System.`in`)
    println("Would you like to remove 1) A Recipe 2) Your Entire Cookbook")
    print("> ")
    val input = scanner.nextInt()
    print("\n")

    if (input == 1){
        println("What Recipe do you want to remove?")
        print("Name: ")
        val recipeName = readLine()
        cookBook.remove(recipeName)
        println("Recipe for $recipeName Removed")
    }

    if (input == 2){
        cookBook.clear()
        println("Cookbook Removed")
    }

}

fun main() {
    var leave = false
    val cookBook = mutableMapOf<String?, Any?>()
    while (!leave) {
        val scanner = Scanner(System.`in`)
        println("Welcome to your CookBook! What would you like to do?")
        println("A) Add a recipe B) Remove C) View Q) Quit")
        print("> ")
        val input : String = scanner.next().uppercase()

        if (input == "A") {
            val (recipeName, recipe) = add()
            cookBook[recipeName] = recipe
            print("\n")
        }
        if (input == "B") {
            remove(cookBook)
        }
        if (input == "C") {
            view(cookBook)
        }
        if (input == "Q") {
            println ("Goodbye!")
            leave = true
        }
    }
}