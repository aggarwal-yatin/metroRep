package org.example.Store;

import org.example.entity.BankAccount;
import org.example.entity.Person;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class Bank {

    private final static HashSet<BankAccount> bank  = new HashSet<>();
    public static Optional<BankAccount> addAccount(BankAccount account ){
        if(!bank.contains(account)){
            bank.add(account);
            return Optional.ofNullable(account);
        }
        return  bank.parallelStream().filter(val-> val.equals(account)).findFirst();
    }
    public static Optional<BankAccount> getAccount(String accountNo){

        return bank
                .stream()
                .filter(val -> val.getBankAccountNo().equals(accountNo))
                .findFirst();
    }

}
