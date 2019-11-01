import task1.Task1
import task2.Task2

object Main extends App {
    val t1 = new Task1
    val t2 = new Task2

    println("\nTask 1A: \nInput: none")
    println(t1.generate1To50Arr().mkString(", "))

    println("\nTask 1B:")
    println("Input: [1, 2, 3, 4 ... 50")
    println(t1.sumArray(t1.generate1To50Arr()))

    println("\nTask 1C:")
    println("Input: [1,2,3,4...50]")
    println(t1.sumArray_recursively(t1.generate1To50Arr()))

    println("\nTask 1D:")
    println("My fibonacci function starts at 0. So if you want fib to return the 5th number in the fib sequence, do fib(4)")
    println("Input: 5")
    println(t1.fibonacci(5))

    println("\nTask 2A:")
    println("Input: println(\"some test content\")")
    val task2AThread = t2.createUnstartedThread({println("some test content")})
    task2AThread.start()
    task2AThread.join()

    println("\nTask 2B:")
    println("Explanation of this phenomenon can be found in the Task2.scala file. The code snippet has been updated" +
      "to be thread safe for 2C")

    println("\nTask 2C:")
    println("Running the code snippet 20 times.")
    for(_ <- 1 to 20) t2.runTask2C()

    println("\nTask 2D:")
    println("This MIGHT cause a deadlock.")
    t2.lazyDeadlock()
}
