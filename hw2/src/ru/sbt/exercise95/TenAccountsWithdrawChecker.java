package ru.sbt.exercise95;

import static java.lang.Math.random;
import static java.lang.Thread.sleep;

public class TenAccountsWithdrawChecker {
    public static void main(String[] args) throws InterruptedException {
        SavingsAccount savingsAccount0 = new SavingsAccount();
        SavingsAccount savingsAccount1 = new SavingsAccount();
        SavingsAccount savingsAccount2 = new SavingsAccount();
        SavingsAccount savingsAccount3 = new SavingsAccount();
        SavingsAccount savingsAccount4 = new SavingsAccount();
        SavingsAccount savingsAccount5 = new SavingsAccount();
        SavingsAccount savingsAccount6 = new SavingsAccount();
        SavingsAccount savingsAccount7 = new SavingsAccount();
        SavingsAccount savingsAccount8 = new SavingsAccount();
        SavingsAccount savingsAccount9 = new SavingsAccount();
        SavingsAccount[] savingsAccounts = new SavingsAccount[]{savingsAccount0, savingsAccount1, savingsAccount2, savingsAccount3, savingsAccount4, savingsAccount5, savingsAccount6, savingsAccount7, savingsAccount8, savingsAccount9};
        for (SavingsAccount savingsAccount : savingsAccounts) {
            savingsAccount.deposit((int) (random() * 150));
        }
        RunnableAccount runnableAccount0 = new RunnableAccount(savingsAccount0, savingsAccount1);
        RunnableAccount runnableAccount1 = new RunnableAccount(savingsAccount1, savingsAccount2);
        RunnableAccount runnableAccount2 = new RunnableAccount(savingsAccount2, savingsAccount3);
        RunnableAccount runnableAccount3 = new RunnableAccount(savingsAccount3, savingsAccount4);
        RunnableAccount runnableAccount4 = new RunnableAccount(savingsAccount4, savingsAccount5);
        RunnableAccount runnableAccount5 = new RunnableAccount(savingsAccount5, savingsAccount6);
        RunnableAccount runnableAccount6 = new RunnableAccount(savingsAccount6, savingsAccount7);
        RunnableAccount runnableAccount7 = new RunnableAccount(savingsAccount7, savingsAccount8);
        RunnableAccount runnableAccount8 = new RunnableAccount(savingsAccount8, savingsAccount9);
        RunnableAccount runnableAccount9 = new RunnableAccount(savingsAccount9, savingsAccount0);
        Thread thread0 = new Thread(runnableAccount0);
        Thread thread1 = new Thread(runnableAccount1);
        Thread thread2 = new Thread(runnableAccount2);
        Thread thread3 = new Thread(runnableAccount3);
        Thread thread4 = new Thread(runnableAccount4);
        Thread thread5 = new Thread(runnableAccount5);
        Thread thread6 = new Thread(runnableAccount6);
        Thread thread7 = new Thread(runnableAccount7);
        Thread thread8 = new Thread(runnableAccount8);
        Thread thread9 = new Thread(runnableAccount9);
        Thread[] threads = new Thread[]{thread0, thread1, thread2, thread3, thread4, thread5, thread6, thread7, thread8, thread9};
        for (Thread thread : threads) {
            thread.start();
        }
        sleep(1000);
        for (SavingsAccount savingsAccount : savingsAccounts) {
            savingsAccount.deposit(1000);
        }
    }
}
