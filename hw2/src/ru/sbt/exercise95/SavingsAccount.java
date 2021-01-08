package ru.sbt.exercise95;

public class SavingsAccount {
    private int balance;
    private int numberOfQueenTransactions;

    public SavingsAccount() {
        balance = 0;
        numberOfQueenTransactions = 0;
    }

    public void deposit(int k) {
        synchronized (this) {
            balance += k;
            System.out.println("++++++++++++++++++++++++++++++++++");
            System.out.println(k + " gold was added, your majesty. Total balance: " + balance);
            System.out.println("++++++++++++++++++++++++++++++++++");
            this.notifyAll();
        }
    }

    public void withdraw(int k) {
        synchronized (this) {
            while (balance < k || numberOfQueenTransactions > 0) {
                sleep();
            }
            balance -= k;
            System.out.println("----------------------------------");
            System.out.println( k + " gold was withdrawn, the treasury is getting empty, my lord. Total balance: " + balance);
            System.out.println("----------------------------------");
        }
    }

    public void withdrawPreferred(int k) {
        synchronized (this) {
            numberOfQueenTransactions++;
            while (balance < k) {
                sleep();
            }
            balance -= k;
            numberOfQueenTransactions--;
            System.out.println("----------------------------------");
            System.out.println( k + " gold was withdrawn by THE QUEEN. Total balance: " + balance);
            System.out.println("----------------------------------");
        }
    }

    void transfer(int k, SavingsAccount reserve) {
        synchronized (this) {
            reserve.withdraw(k);
            deposit(k);
        }
    }

    private void sleep() {
        try {
            this.wait();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}

