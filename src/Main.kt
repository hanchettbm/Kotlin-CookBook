import java.util.Scanner

// Recipe constructor that allows the function to return multiple values.
data class Recipe(var recipeName: String?, val recipe:MutableMap<String, Any?>)

fun add(): Recipe {
//  Get Recipe name from user.
    print("What are you making: ")
    val recipeName = readLine()
//  Declare Recipe Map.
    val recipe = mutableMapOf<String, Any?>()
    var leave = false
//  Start Loop.
    while (!leave) {
//      Ask for user input.
        val scanner = Scanner(System.`in`)
        print("Add an ingredient and amount? (y/n): ")
        val input : String? = scanner.next()?.lowercase()

        if (input == "y"){
//          Ask for Ingredients and how much (On Repeat).
            print("What ingredient do you need: ")
            val ingredient : String? = readLine()

            print("How much $ingredient do you need: ")
            val amount : String? = readLine()

//          Check for valid input and then add to our recipe map.
            if (ingredient != null && amount != null) {
                recipe[ingredient] = amount
            }
            if (ingredient == null || amount == null) {
                println("No input received Please try again.")
            }
        }
//      End Loop.
        if (input == "n"){
            leave = true
        }
    }

//  Check for Oven Vs. Stove.
    print("Will this be baked in the A) oven B) Stove: ")
    val bakeInput : String? = readLine()?.uppercase()

    if (bakeInput == "A") {
//      Ascii code for degree Fahrenheit symbol.
        print("What will be the Oven temp in \u2109: ")
        var bakeTemp: String? = readLine()
        bakeTemp = ("$bakeTemp \u2109")
//      Add temp to Recipe.
        recipe["Baking Temp"] = bakeTemp
    }
    if (bakeInput == "B"){
        print("Pick A) Low B) Medium C) High heat: ")
//      Uses when keyword to check different options.
        val stoveTemp = when (readLine()?.uppercase()) {
            "A" -> "Stove: Low"
            "B" -> "Stove: Medium"
            "C" -> "Stove: High"
            else -> "Stove: Off"
        }
//      Add temp to recipe.
        recipe["CookingTemp"] = stoveTemp
    }

//  Get Cook/bake Time.
    print("How long is the cook time (In Minutes): ")
    val cookTime : String = readLine() + " min"
    recipe["Cook Time"] = cookTime

//  Gives user a place to add instructions.
    println("Type your brief baking instructions here: ")
    print("> ")
    val instructions : String? = readLine()
    recipe["Instructions"] = instructions

//  Returns the class with the name and final recipe.
    return Recipe(recipeName, recipe)
}

fun view(cookBook: MutableMap<String?, Any?>) {

//  Get user input.
    val scanner = Scanner(System.`in`)
    println("What would you like to view? 1) Your Cookbook 2) A specific Recipe")
    print("> ")
    val input : String = scanner.next()
    print("\n")

    if (input == "1"){
//      Runs through each key and displays its values.
        cookBook.forEach { (k, v) ->
            println("Recipe For: $k")

//          Cookbook Value is another mutable map so display those pairs for the user to see.
            val recipe: MutableMap<String?, Any?> = v as MutableMap<String?, Any?>
            recipe.forEach { (k, v) ->
                println("$k = $v")
            }
            print("\n")
        }

    }
    if (input == "2"){

//      Get user input.
        println("What Recipe do you want to view?")
        print("Name: ")
        val recipeName = readLine()

//     Loop through each key and compare it to user input.
        cookBook.forEach { (k, v) ->
            if (recipeName == k){
                println("Recipe For: $k")

//              If True display that value as its own map loop.
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
//  Get user input.
    val scanner = Scanner(System.`in`)
    println("Would you like to remove 1) A Recipe 2) Your Entire Cookbook")
    print("> ")
    val input : String = scanner.next()

    if (input == "1"){
//      Check user input to find recipe.
        println("What Recipe do you want to remove?")
        print("Name: ")
        val recipeName = readLine()
//      Remove Recipe from Cookbook Map.
        cookBook.remove(recipeName)
        println("Recipe for $recipeName Removed")
    }

    if (input == "2"){
//      Removes entire Cookbook.
        cookBook.clear()
        println("Cookbook Removed")
    }
}

fun main() {
//  Sets up main loop and initializes variables.
    var leave = false
    val cookBook = mutableMapOf<String?, Any?>()
    while (!leave) {
//      Get user input.
        val scanner = Scanner(System.`in`)
        println("Welcome to your CookBook! What would you like to do?")
        println("A) Add a recipe B) Remove C) View Q) Quit")
        print("> ")
        val input : String = scanner.next().uppercase()

        if (input == "A") {
//          Calls add function and creates Cookbook.
            val (recipeName, recipe) = add()
            cookBook[recipeName] = recipe
            print("\n")
        }
        if (input == "B") {
//          Calls remove function and passes in the Cookbook.
            remove(cookBook)
            print("\n")
        }
        if (input == "C") {
//          Calls view function and passes in the Cookbook.
            view(cookBook)
        }
        if (input == "Q") {
//          Stops Loop, ends Program.
            println ("Goodbye!")
            leave = true
        }
    }
}