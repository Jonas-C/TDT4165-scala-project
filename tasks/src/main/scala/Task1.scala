package task1

class Task1 {


  def generate1To50Arr(): Array[Int] = {
    var arr = Array[Int]()
    for(n <- 1 to 50){
      arr = arr :+ n
    }
    arr
  }

  def sumArray(arr: Array[Int]): Int = {
    var sum = 0
    for(n <- arr){
      sum += n
    }
    sum
  }

  def sumArray_recursively(arr: Array[Int]): Int = sumArray_recursively_helper(0, arr)

  def sumArray_recursively_helper(sum: Int, arr: Array[Int]): Int = {
    if(arr.length == 1) sum + arr(0)
    else sumArray_recursively_helper(sum + arr(0), arr.drop(1))

  }

  /*
  There's actually quite a big difference when it comes to  using Int and BigInt.
  BigInt allows storing numbers that are way bigger than what Int allows.
  If one were to use int, the fibonacci numbers would start overflowing at some point.
   */
  def fibonacci(n : BigInt): BigInt = {
    if(n < 2) return n
    fibonacci(n -1) + fibonacci(n - 2)
  }

}
