package org.example.Store;

import org.example.entity.MetroCard;
import org.example.entity.Person;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class MetroCardStore {
    private static double accumulatedCostAtAirport = 0.0 ;
    private static double accumulatedCostAtCentralStation = 0.0 ;
    private final static HashSet<MetroCard> store  = new HashSet<>();
    public static Optional<MetroCard> addCard(MetroCard card ){
        if(!store.contains(card)){
            store.add(card);
            return Optional.ofNullable(card);
        }
        return store.parallelStream().filter(val->val.equals(card)).findFirst();

    }
    public static Optional<MetroCard> getCard(String id){
        return store
                .stream()
                .filter(val -> val.getId().equals(id))
                .findFirst();
    }

    public static double getAccumulatedCostAtCentralStation() {
        return accumulatedCostAtCentralStation;
    }

    public static double getAccumulatedCostAtAirport() {
        return accumulatedCostAtAirport;
    }

    public static void addAccumulatedCostAtAirport(double accumulatedCost) {
        MetroCardStore.accumulatedCostAtAirport += accumulatedCost;
    }
    public static void addAccumulatedCostAtCentralStation(double accumulatedCost) {
        MetroCardStore.accumulatedCostAtCentralStation+= accumulatedCost;
    }
}
