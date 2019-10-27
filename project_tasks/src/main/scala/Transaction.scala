import exceptions._

import scala.collection.mutable

object TransactionStatus extends Enumeration {
  val SUCCESS, PENDING, FAILED = Value
}

class TransactionQueue {

    // TODO
    // project task 1.1
    // Add datastructure to contain the transactions
    private val transactionQueue = new mutable.Queue[Transaction]

    // Remove and return the first element from the queue
    def pop: Transaction = this.synchronized({this.transactionQueue.dequeue()})

    // Return whether the queue is empty
    def isEmpty: Boolean = this.synchronized({this.transactionQueue.isEmpty})

    // Add new element to the back of the queue
    def push(t: Transaction): Unit = this.synchronized({this.transactionQueue.enqueue(t)})

    // Return the first element from the queue without removing it
    def peek: Transaction = this.synchronized({this.transactionQueue.head})

    // Return an iterator to allow you to iterate over the queue
    def iterator: Iterator[Transaction] = this.synchronized({this.transactionQueue.iterator})
}

class Transaction(val transactionsQueue: TransactionQueue,
                  val processedTransactions: TransactionQueue,
                  val from: Account,
                  val to: Account,
                  val amount: Double,
                  val allowedAttemps: Int) extends Runnable {

  var status: TransactionStatus.Value = TransactionStatus.PENDING
  var attempt = 0

  override def run: Unit = {

      def doTransaction() = {

          val attemptWithdraw = from.withdraw(amount)
          if(attemptWithdraw.isRight){
              val attemptDeposit = to.deposit(amount)
              if(!attemptDeposit.isRight){
                  from.deposit(amount)
                  status = TransactionStatus.FAILED
              }
              else status = TransactionStatus.SUCCESS
          }
      }

      // TODO - project task 3
      // make the code below thread safe
      if (status == TransactionStatus.PENDING) {
          attempt += 1
          doTransaction
          Thread.sleep(50) // you might want this to make more room for
                           // new transactions to be added to the queue
      }


    }
}
