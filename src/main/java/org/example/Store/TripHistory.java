package org.example.Store;


import org.example.entity.MetroCard;
import org.example.entity.Trip;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class TripHistory {
    private static final List<Trip> history = new ArrayList<>();

    public static void addHistory(Trip trip) {

        history.add(trip);
    }

    public static Optional<Trip>  getUserLatestTrip(MetroCard card ){
        return history
                .parallelStream()
                .filter(val-> val.getMetroCard().equals(card))
                .sorted(Comparator.comparing(Trip::getCurrentDate).reversed())
                .findFirst();
    }
    public static  void getAnalytics(String Location){
        System.out.println("total cost at airport"+MetroCardStore.getAccumulatedCostAtAirport());
        ArrayList<Integer> age=new  ArrayList<>(Arrays.asList(0,0,0));
        history.stream()
                .filter(val->val.getStartingStation().equals(Location)).collect(Collectors.toList()).forEach(
                        val-> {
                            int userAge = val.getMetroCard().getPerson().getAge();
                            if(userAge<= 10 ){
                                age.add(0,age.get(0)+1);
                            }else if(userAge<60){
                                age.add(1,age.get(1)+1);
                            }else{
                                age.add(2,age.get(2)+1);
                            }
                        }

                );
        System.out.println("children: "+age.get(0)+ " adult: "+age.get(1)+ " senior citizen:"+ age.get(2));
    }
}
