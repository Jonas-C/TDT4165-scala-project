class Bank(val allowedAttempts: Integer = 3) {

    private val transactionsQueue: TransactionQueue = new TransactionQueue()
    private val processedTransactions: TransactionQueue = new TransactionQueue()


    def addTransactionToQueue(from: Account, to: Account, amount: Double): Unit = this.synchronized{
        val transaction = new Transaction(this.transactionsQueue, this.processedTransactions, from, to, amount, allowedAttempts)

        this.transactionsQueue.push(transaction)

        val thread = new Thread {    //Thread used to process a transaction.
            override def run(): Unit = processTransactions()    //Call processTransactions every time a new transaction is added.
        }
        thread.start()
    }


    private def processTransactions(): Unit = {
        val transaction = transactionsQueue.pop
        transaction.run()
        if(transaction.status == TransactionStatus.PENDING){    //if the transaction is still not completed add it back to queue to retry later.
            this.transactionsQueue.push(transaction)
            processTransactions()
        }
        else this.processedTransactions.push(transaction)    //Transaction completed. Push result to processed queue.
    }


    def addAccount(initialBalance: Double): Account = {
        new Account(this, initialBalance)
    }


    def getProcessedTransactionsAsList: List[Transaction] = {
        this.processedTransactions.iterator.toList
    }

}
