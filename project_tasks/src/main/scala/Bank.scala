class Bank(val allowedAttempts: Integer = 3) {

    private val transactionsQueue: TransactionQueue = new TransactionQueue()
    private val processedTransactions: TransactionQueue = new TransactionQueue()

    def addTransactionToQueue(from: Account, to: Account, amount: Double): Unit = this.synchronized{
        val transaction = new Transaction(transactionsQueue, processedTransactions, from, to, amount, allowedAttempts)

        this.transactionsQueue.push(transaction)

        val thread = new Thread {
            override def run(): Unit = processTransactions
        }
        thread.start()
    }

    private def processTransactions: Unit = {
        val transaction = transactionsQueue.pop
        val thread = new Thread{
            transaction.run()
            if(transaction.status == TransactionStatus.SUCCESS){
                processedTransactions.push(transaction)
                processTransactions
            }
            else if(transaction.status == TransactionStatus.PENDING){
                addTransactionToQueue(transaction.from, transaction.to, transaction.amount)
            }
            else {
                processedTransactions.push(transaction)
            }
        }

    }

    def addAccount(initialBalance: Double): Account = {
        new Account(this, initialBalance)
    }

    def getProcessedTransactionsAsList: List[Transaction] = {
        processedTransactions.iterator.toList
    }

}
