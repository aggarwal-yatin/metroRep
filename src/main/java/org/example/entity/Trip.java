package org.example.entity;

import org.example.Store.MetroCardStore;

import java.time.LocalDateTime;
import java.util.Date;

public class Trip{
    private String startingStation;
    private Double fair;
    private MetroCard metroCard;
    private LocalDateTime currentDate;

    public MetroCard getMetroCard() {
        return metroCard;
    }

    public static Trip Builder(){
        return new Trip();
    }

    public String getStartingStation() {
        return startingStation;
    }

    public LocalDateTime getCurrentDate() {
        return currentDate;
    }

    public Trip setMetroCard(MetroCard metroCard) {
        this.metroCard = metroCard;
        return this;
    }

    public Trip setStartingStation(String startingStation) {
        this.startingStation = startingStation;
        return this;
    }

    public Trip setFair(Double fair) throws Exception {
        double val = 0;
        double amount = metroCard.getAmount();
        if(amount  <  fair){
            double remainingVal = fair - amount;
            double amountToBeSubtracted = remainingVal + (remainingVal*2)/100;
            if(metroCard.getLinkedAccount().getBalance() < amountToBeSubtracted){
                System.out.println("not sufficient money in the bank account " );
                throw new RuntimeException();
            }
            metroCard.getLinkedAccount().subBalance(amountToBeSubtracted);
            metroCard.subAmount(amount);
            val = amountToBeSubtracted;
        }else{
            metroCard.subAmount(fair);
            val = fair;
        }
        if(startingStation.equals("The Central Station")){
            MetroCardStore.addAccumulatedCostAtCentralStation(val);
        }else {
            MetroCardStore.addAccumulatedCostAtAirport(val);
        }
        this.fair = val;
        return this;
    }

    public Trip setCurrentDate() {
        this.currentDate = LocalDateTime.now();
        return this;
    }

    public Trip build(){
        return this;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "startingStation='" + startingStation + '\'' +
                ", fair=" + fair +
                ", metroCard=" + metroCard +
                ", currentDate=" + currentDate +
                '}';
    }
}
