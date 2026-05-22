package org.example.Store;

import org.example.entity.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonStore {
    private final static  List<Person> store  = new ArrayList<>();
    public static void addPerson(Person p ){
        store.add(p);
    }
    public static Optional<Person> getPerson(String id){
        return store.stream().filter(val -> val.getId().equals(id)).findFirst();
    }
}
