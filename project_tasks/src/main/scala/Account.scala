import exceptions._

class Account(val bank: Bank, initialBalance: Double) {

    class Balance(var amount: Double) {}

    val balance = new Balance(initialBalance)


    def withdraw(amount: Double): Either[String, String] = this.synchronized{
        if(amount > 0.0 && this.balance.amount >= amount){    //Amount to withdraw can't be negative. It also can't be greater than the account balance.
            this.balance.amount -= amount
            Left("Money withdrawn")
        } else Right("Amount greater than balance!")
    }


    def deposit (amount: Double): Either[String, String] = this.synchronized{
        if(amount > 0.0) {    //Amount to deposit can't be negative. That's a withdrawal.
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


    def transferTo(account: Account, amount: Double): Unit = {
        bank addTransactionToQueue (this, account, amount)
    }


}
