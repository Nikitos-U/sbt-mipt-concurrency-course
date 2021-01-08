package ru.sbt.exercise95;

public class Exercise95Checker {
    public static void main(String[] args) {
        SavingsAccount savingAccount = new SavingsAccount();
        for (int i = 0; i < 20; i++) {
            if (i % 3 == 0) {
                new Thread(() -> savingAccount.deposit(1000)).start();
            } else if (i % 5 == 0) {
                new Thread(() -> savingAccount.withdrawPreferred(300)).start();
            } else {
                new Thread(() -> savingAccount.withdraw(100)).start();
            }
        }
    }
}
