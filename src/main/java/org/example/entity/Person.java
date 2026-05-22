package org.example.entity;

import java.util.Objects;
import java.util.UUID;

public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private String id ;

    public Person(){
        UUID id = UUID.randomUUID();
        this.id = id.toString();
    }
    public String getId() {
        return id;
    }

    public int getAge() {
        return age;
    }
    public Person setAge(int age){
        this.age = age;
        return this;
    }
    public String getFirstName(){
        return firstName;
    }

    public Person setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Person setLastName(String lastName){
        this.lastName = lastName;
        return this;
    }

    public Person build(){
        return this;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Person person)) return false;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
