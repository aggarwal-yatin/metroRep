package org.example.entity;

import java.util.Objects;
import java.util.UUID;

public class BankAccount {
    private String bankAccountNo;
    private Person person;
    private Double balance = 0.0;
    public BankAccount(){
        UUID id = UUID.randomUUID();
        bankAccountNo = id.toString();
    }
    public Person getPerson() {
        return person;
    }

    public Double getBalance() {
        return balance;
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public BankAccount setPerson(Person person) {
        this.person = person;
        return this;
    }

    public void addBalance(Double balance) {
        this.balance += balance;
    }

    public void subBalance(Double balance){

        this.balance -= balance;
    }

    public  BankAccount build (){
        return this;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "bankAccountNo='" + bankAccountNo + '\'' +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BankAccount account)) return false;
        return  Objects.equals(person, account.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person);
    }
}

