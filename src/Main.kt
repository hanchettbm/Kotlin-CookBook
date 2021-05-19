import java.util.Scanner

fun main() {
    var leave = false
    while (!leave) {
        val scanner = Scanner(System.`in`)
        println("Welcome to your CookBook! What would you like to do?")
        println("A) Add a recipe B) Remove a recipe C) View a recipe D) View CookBook Q) Quit")
        print("> ")
        val input : String = scanner.next()
        if (input == "A") {
            println ("Testing A!")
//            leave = true
        }
        if (input == "B") {
            println ("Testing B")
//            leave = true
        }
        if (input == "C") {
            println ("Testing C")
//            leave = true
        }
        if (input == "D") {
            println ("Testing D")
//            leave = true
        }
        if (input == "Q") {
            println ("Goodbye!")
            leave = true
        }


    }

}