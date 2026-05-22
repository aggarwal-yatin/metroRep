package org.example;

import org.example.Store.Bank;
import org.example.Store.MetroCardStore;
import org.example.Store.PersonStore;
import org.example.Store.TripHistory;
import org.example.entity.BankAccount;
import org.example.entity.MetroCard;
import org.example.entity.Person;
import org.example.entity.Trip;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for(;;){
            System.out.println("Press numeric key to perform action");
            System.out.println("1. Create a person");
            System.out.println("2. Create a bankAccount");
            System.out.println("3. Add a money in bank account");
            System.out.println("4. Create a Metro Card");
            System.out.println("5. Add money in a Metro Card");
            System.out.println("6. Travel a metro");
            System.out.println("7. To get analytics");
            System.out.println("8. Exit");
            try {
                Integer option = sc.nextInt();
                System.out.println("user selected option " + option);
                switch (option) {
                    case (1):
                        System.out.println("Enter first name ");
                        String firstName = sc.next();
                        System.out.println("Enter last name ");
                        String lastName = sc.next();
                        System.out.println("Enter age");
                        int age = sc.nextInt();
                        Person p = new Person()
                                    .setFirstName(firstName)
                                    .setLastName(lastName)
                                    .setAge(age)
                                    .build();
                        PersonStore.addPerson(p);
                        System.out.println("Person Created "+p.getId());
                        break;
                    case (2):
                        System.out.println("Enter person id ");
                        String  id = sc.next();
                        Optional<Person> person = PersonStore.getPerson(id);
                        if(person.isPresent()){
                            BankAccount account = new BankAccount()
                                    .setPerson(person.get())
                                    .build();
                            Optional<BankAccount> acc = Bank.addAccount(account);
                            System.out.println("bank account created " + acc.get().getBankAccountNo());

                        }else{
                            System.out.println("Person does not exist ");
                        }
                        break;
                    case (3):
                        System.out.println("Enter bank account number:  ");
                        String number = sc.next();
                        Optional<BankAccount> account = Bank.getAccount(number);
                        if(account.isPresent()){
                            System.out.println("Add money in account:  ");
                            Double money = sc.nextDouble();
                            account.get().addBalance(money);
                            System.out.println("Updated money in account is "+ account.get().getBalance());
                        }else{
                            System.out.println("Bank account does not exist");
                        }

                        break;
                    case (4):
                        System.out.println("Enter bank account number ");
                        String accountNo = sc.next();
                        Optional<BankAccount> account1 = Bank.getAccount(accountNo);
                    if( account1.isPresent()){
                        MetroCard card = new MetroCard()
                                .setAmount(0.0)
                                .setIssuesAt(LocalDateTime.now())
                                .setLinkedAccount(account1.get())
                                .setPerson(account1.get().getPerson())
                                .build();
                        Optional<MetroCard> res = MetroCardStore.addCard(card);
                        System.out.println("card created "+res.get());
                    }else{
                        System.out.println("Metro card not created because person or bank does Not exist");
                    }
                    break;
                    case (5):
                        System.out.println("Enter metro card number ");
                        String cardNo = sc.next();
                        Optional<MetroCard> card =  MetroCardStore.getCard(cardNo);
                        System.out.println("Enter the amount to deposited in metro card");
                        Double rechargeMoney = sc.nextDouble();
                        if(card.isPresent()){
                            card.get().recharge(rechargeMoney);
                        }else {
                            System.out.println("This metro card is not available");
                        }
                        break;
                    case (6):
                        System.out.println("Enter metro card number ");
                        String cardNo1 = sc.next();
                        Optional<MetroCard> card2 =  MetroCardStore.getCard(cardNo1);

                        if(card2.isPresent()){
                            System.out.println("You have enter the metro station. Select where are you riding metro from");
                            System.out.println("1. The Central Station");
                            System.out.println("2. The Airport");
                            System.out.println("Enter the option 1 or 2 ");
                            int choiceOption = sc.nextInt();
                            String currentStation;
                            if(choiceOption == 1){
                                currentStation = "The Central Station";
                            } else if (choiceOption ==2 ) {
                                currentStation = "The Airport";
                            } else{
                                System.out.println("Entered the wrong input");
                                break;
                            }
                            Optional<Trip> previousTrip = TripHistory
                                    .getUserLatestTrip(card2.get());

                                if(previousTrip.isPresent() && !previousTrip
                                        .get()
                                        .getStartingStation()
                                        .equals(currentStation)
                                ){
                                    double fair = (card2.get().getFair()*50/100);
                                    Trip trip = Trip
                                            .Builder()
                                            .setMetroCard(card2.get())
                                            .setStartingStation(currentStation)
                                            .setFair(fair)
                                            .setCurrentDate()
                                            .build();
                                    System.out.println("You have travelled in metro " + trip);
                                    TripHistory.addHistory(trip);
                                }else{
                                    double fair = card2.get().getFair();
                                    Trip trip = Trip
                                            .Builder()
                                            .setMetroCard(card2.get())
                                            .setStartingStation(currentStation)
                                            .setFair(fair)
                                            .setCurrentDate()
                                            .build();
                                    System.out.println("You have travelled in metro " + trip);
                                    TripHistory.addHistory(trip);
                                }

                        }else{
                            System.out.println("Enter correct card");
                        }
                        break;
                    case(7):
                        System.out.println("Select Metro station you want to see analytics of:");
                        System.out.println("1. The Central Station");
                        System.out.println("2. The Airport");
                        System.out.println("Enter the option 1 or 2 ");
                        int choiceOption = sc.nextInt();
                        String location;
                        if(choiceOption == 1){
                            location = "The Central Station";
                        } else if (choiceOption ==2 ) {
                            location = "The Airport";
                        } else{
                            System.out.println("Entered the wrong input");
                            break;
                        }
                        System.out.println("Analytics");
                        TripHistory.getAnalytics(location);
                        break;
                    case (8):
                        System.out.println("Exiting");
                        break;

                }
                if (option == 8) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("something went wrong");

            }finally {
                continue;
            }

        }




    }
}