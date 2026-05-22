package org.example.entity;

import org.example.Store.Bank;
import org.example.Store.MetroCardStore;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class MetroCard {
    private String id;
    private Person person;
    private Double amount;
    private LocalDateTime issuesAt;
    private BankAccount linkedAccount;
    public MetroCard(){
        UUID id = UUID.randomUUID();
        this.id = id.toString();
    }

    public String getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public LocalDateTime getIssuesAt() {
        return issuesAt;
    }

    public Double getAmount() {
        return amount;
    }

    public BankAccount getLinkedAccount() {
        return linkedAccount;
    }

    public MetroCard setAmount(Double amount) {
        this.amount = amount;
        return this;
    }
    public void subAmount(Double amount) {
        this.amount -= amount;

    }


    public MetroCard setPerson(Person person) {
        this.person = person;
        return this;
    }

    public MetroCard setIssuesAt(LocalDateTime issuesAt) {
        this.issuesAt = issuesAt;
        return this;
    }

    public MetroCard setLinkedAccount(BankAccount linkedAccount) {
        this.linkedAccount = linkedAccount;
        return this;
    }

    public void recharge(Double val){
        if(this.linkedAccount.getBalance() >= val ){
            this.linkedAccount.subBalance(val);
            this.amount = this.amount + val;
            System.out.println("amount " + amount);
        }else{
            System.out.println("Unable to add money in card please recharge");
        }
    }

    public double getFair(){
        if(person.getAge() >=60){
            return 100.0;
        } else if (person.getAge()>= 5) {
            return 200.0;
        }
        return 50;
    }

    public MetroCard build(){
        return this;
    }

    @Override
    public String toString() {
        return "MetroCard{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", issuesAt=" + issuesAt +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MetroCard metroCard)) return false;
        return  Objects.equals(person, metroCard.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person);
    }
}
