package ru.sberbank.edu;

import java.util.Objects;

public class Person implements Comparable<Person>{
    private String name;
    private String city;
    private int age;

    public Person(String name, String city) {
        this.name = name;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && name.equalsIgnoreCase(person.name) && city.equalsIgnoreCase(person.city);

    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase(), city.toLowerCase(), age) * 31;
    }

    @Override
    public int compareTo(Person o) {
        if(this.equals(o))
            return 0;
        if(this.city.compareTo(o.city) == 0) {
            return this.name.compareToIgnoreCase(o.name);
        }
        return this.city.compareToIgnoreCase(o.city);
    }
}
