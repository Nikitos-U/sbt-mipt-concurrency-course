package ru.sbt.exercise95;

public class RunnableAccount implements Runnable{
    private final SavingsAccount savingsAccount;
    private final SavingsAccount debtor;

    public RunnableAccount(SavingsAccount savingsAccount, SavingsAccount debtor) {
        this.savingsAccount = savingsAccount;
        this.debtor = debtor;
    }

    @Override
    public void run() {
        savingsAccount.transfer(100, debtor);
    }
}
