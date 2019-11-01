package task1

class Task1 {

    /*
    Task 1A
    This function generates and returns an array containing the values from 1 to 50.
    */
    def generate1To50Arr(): Array[Int] = {
        var arr = Array[Int]()    //Create an empty Array.
        for(n <- 1 to 50){
            arr = arr :+ n    //Appends the number n to the existing list.
        }
        arr    //Return array.
    }


    /*
    Task 1B
    Takes in an array of integers and returns the sum of the elements in the array.
    */
    def sumArray(arr: Array[Int]): Int = {
        var sum = 0
        for(n <- arr){    //For each element in array.
            sum += n
        }
        sum
    }


    /*
    Task 1C
    Returns the sum of the elements of an array recursively by using a helper function to achieve tail recursion.
    */
    def sumArray_recursively(arr: Array[Int]): Int = {
        if(arr.length == 0) return 0    //If array is empty.
        sumArray_recursively_helper(0, arr)
    }


    /*
    Helper function for task 1C
    */
    def sumArray_recursively_helper(sum: Int, arr: Array[Int]): Int = {
        if(arr.length == 1) sum + arr(0)    //Last element of the array (exit condition). Returns the final sum.
        else sumArray_recursively_helper(sum + arr(0), arr.drop(1))    //Calculate sum + first element of array, drop first array element for next recursive call.
    }


    /*
    Task 1D
    There's actually quite a big difference when it comes to  using Int and BigInt.
    BigInt allows storing numbers that are way bigger than what Int allows.
    If one were to use int, the fibonacci numbers would start overflowing at some point.
    */
    def fibonacci(n : BigInt): BigInt = {    //Returns a BigInt to avoid overflow.
        if(n < 2) return n
        fibonacci(n -1) + fibonacci(n - 2)
    }
}
