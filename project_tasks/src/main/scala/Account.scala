import exceptions._

class Account(val bank: Bank, initialBalance: Double) {

    class Balance(var amount: Double) {}

    val balance = new Balance(initialBalance)

    // TODO
    // for project task 1.2: implement functions
    // for project task 1.3: change return type and update function bodies
    def withdraw(amount: Double): Either[String, String] = this.synchronized{
        if(amount > 0 && this.balance.amount >= amount){
            this.balance.amount -= amount
            Left("Money withdrawn")
        } else Right("Amount greater than balance!")
    }
    def deposit (amount: Double): Either[String, String] = this.synchronized{
        if(amount > 0.0) {
            this.balance.amount += amount
            Left("Money deposited")
        }
        else{
            Right("Invalid amount specified!")
        }
    }
    def getBalanceAmount: Double = this.synchronized{
        this.balance.amount
    }

    def transferTo(account: Account, amount: Double) = {
        bank addTransactionToQueue (this, account, amount)
    }


}
