package task2

class Task2 {

  //Task 2A
  def createUnstartedThread(f: => Unit): Thread = {
    new Thread {
      override def run(): Unit = f
    }
  }

  //Task 2B
  def runTask2B(): Unit = {
    var counter: Int = 0

    def increaseCounter(): Unit = this.synchronized{
      counter += 1
    }

    val incThread1 = createUnstartedThread(increaseCounter())
    val incThread2 = createUnstartedThread(increaseCounter())
    val printThread = createUnstartedThread({
      print(s"$counter, ")
    })

    incThread1.start()
    incThread2.start()
    incThread2.join()
    incThread2.join()
    printThread.start()
    printThread.join()




  }
  /*
  Task 2B continuation

  The phenomenon that occurs is one called race condition, which is a situation that occurs when writing
  programs with several threads. The threads that are being ran are accessing and modifying the same data.
  However, there's no way to know in what order the threads will run, which might lead to different results
  appearing. Here's an example on where race conditions can go really wrong:
  Say you have a bank application that handles purchases, and that one thread has initiated a purchase. It first
  checks if the account in question has enough funds available to fulfill the purchase. However, before the actual
  funds are subtracted from the account, the application switches over to another thread that's also going to
  initiate a purchase. The values that were being worked on is stored on the stack so that they can be returned to later on.
  The new thread uses the same value as the first thread because the other thread didn't get far enough to subtract the funds.
  The second threads subtracts the funds and finishes. Then it switches back to the original thread. This thread doesn't
  know that the other thread subtracted funds from the account, and assumes that the funds are as they were when the
  context switch between the two threads occured. It also subtracts some funds, which in turn leaves the account with a negative balance.
   */

  /*
  Task 2C
  This task threw me for a loop. I first tried adding "this.synchronized to the increaseCounter function, like so:
  def increaseCounter(): Unit = this.synchronized{
      counter.synchronized {counter += 1}
  }

  However, the race condition still occured, although more rarely. Adding synchronized to a function makes it so that
  the two calls to increaseCounter won't overlap, but there's still a chance that the thread that prints the counter
  value will be called before one of the threads containing increaseCounter. I therefore decided to join the two
  increaseCounter-threads to the parent thread before the print-thread could run, so that it would always print 2.
  SO: To answer the question: add this.synchronized to the increaseCounter function to make it thread-safe.
  If you want the same result printed every time, join the increaseCounter-threads before starting the print-thread.
   */

  /*
  Task 2D
  A deadlock is a situation that occurs when two threads are sharing resources whilst they have locked the resource
  the other thread wants to access, causing the threads to halt completely. For a deadlock to occur all of the following
  conditions must be met:
  1. Mutual exclusion: A resource has to be "locked" by some part of a program, making it inaccessible for other programs.
  2. Resource holding: A program is holding a resource and requests a resource that is held by another process.
  3. No preemtion: A locked resource can only be released by the program that locked it.
  4. Circular wait: The first program p1 is waiting for a resource locked by program p2 , which in turn is waiting for a
     process locked by p3. This goes on until program pn, which is waiting for a resource locked by p1.

  There are several ways to avoid deadlock:
  Prevention: Making sure that one of the conditions listed above will never happen.
  Detection: Detect occuring deadlocks and attempt to resolve them.
  Ignoring: Let the deadlock occur and reboot the system if neccesary.

  An example of a lazy deadlock will follow:
   */

  def lazyDeadlock(): Unit = {

  }


}
