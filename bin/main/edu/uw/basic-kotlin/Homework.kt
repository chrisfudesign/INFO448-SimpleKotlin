package edu.uw.basickotlin

class Library {
    // This is just here as a placeholder, to make sure tests run and pass
    // before you add any code
    fun someLibraryMethod(): Boolean {
        return true
    }
}

// write a "whenFn" that takes an arg of type "Any" and returns a String
//If it is "Hello", return "world"
// If it is any other string, return "I don't understand"
// If it is 0, return "zero"
// If it is 1, return "one"
// If it is any number between 2 and 10, return "low number"
// If it is any other number, return "a number"
// Otherwise, return "I don't understand"
fun whenFn(arg: Any): String {
    return when (arg) {
        "Hello" -> "world"
        is String -> "I don't understand"
        0 -> "zero"
        1 -> "one"
        in 2..10 -> "low number"
        is Int -> "a number"
        else -> "I don't understand"
    }
}


// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(a: Int, b: Int): Int {
    return a + b
}


// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(a: Int, b: Int): Int {
    return a - b
}

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(a: Int, b: Int, fn: (Int, Int) -> Int): Int {
    return fn(a, b)
}



// write a class "Person" with first name, last name and age
class Person(val firstName: String, val lastName: String, val age: Int) {
     // Define a read-only "debugString" property on it that returns a String containing the Person data in a format like this: "[Person firstName:Ted lastName:Neward age:45]", where of course the values depend on what was used to construct the Person class.
    val debugString: String
        get() = "[Person firstName:$firstName lastName:$lastName age:$age]"
}


// write a class "Money" with amount and currency, and define a convert() method and the "+" operator
//Create a class, "Money", that has two properties, amount and currency. "Currency" can be one of "USD", "EUR", "CAN" and "GBP". "Amount" is a standard Int. Define the properties such that "amount" can never be less than zero, and that "currency" can only be one of those four symbols. Define a public method, convert, that takes a String argument for the currency type to convert to, and return a new Money instance with the amount converted. Conversion rates should be as follows:

// 10 USD converts to 5 GBP (2 USD == 1 GBP)

// 10 USD converts to 15 EUR (2 USD == 3 EUR)

// 12 USD converts to 15 CAN (4 USD == 5 CAN)

// (Make sure you can convert in both directions, and for all combinations, such as GBP to EUR, CAN to GBP, and so on!)

// Define the + operator on Money to return a new instance of Money that adds the amount, converting the currency to the 
// first (left-hand) Money's currency. So adding (10 USD) + (5 GBP) should return a result in USD. Similarly, adding (5 GBP) + (10 USD) should return the result in GBP.
class Money (val amount: Int, val currency: String) {
    init {
        require(amount >= 0) { "Amount must be greater than 0" }
        require(currency == "USD" || currency == "EUR" || currency == "CAN" || currency == "GBP") { "Currency must be USD, EUR, CAN, or GBP" }
    }

    fun convert(toCurrency: String): Money {
        return when (currency) {
            "USD" -> when (toCurrency) {
                "GBP" -> Money(amount / 2, toCurrency)
                "EUR" -> Money(amount * 3 / 2, toCurrency)
                "CAN" -> Money(amount * 5 / 4, toCurrency)
                else -> Money(amount, currency)
            }
            "GBP" -> when (toCurrency) {
                "USD" -> Money(amount * 2, toCurrency)
                "EUR" -> Money(amount * 3, toCurrency)
                "CAN" -> Money(amount * 5 / 2, toCurrency)
                else -> Money(amount, currency)
            }
            "EUR" -> when (toCurrency) {
                "USD" -> Money(amount * 2 / 3, toCurrency)
                "GBP" -> Money(amount / 3, toCurrency)
                "CAN" -> Money(amount * 5 / 6, toCurrency)
                else -> Money(amount, currency)
            }
            "CAN" -> when (toCurrency) {
                "USD" -> Money(amount * 4 / 5, toCurrency)
                "GBP" -> Money(amount * 2 / 5, toCurrency)
                "EUR" -> Money(amount * 6 / 5, toCurrency)
                else -> Money(amount, currency)
            }
            else -> Money(amount, currency)
        }
    }

    operator fun plus(other: Money): Money {
        val converted = other.convert(currency)
        return Money(amount + converted.amount, currency)
    }
}

