package ru.sberbank.edu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class PersonTest {

    private List<Person> persons;

    /**
     * init data
     */
    @Before
    public void init(){
        Person p1 = new Person("Vasia", "Moscow");
        Person p2 = new Person("Vasia", "Abakan");
        Person p3 = new Person("Ashot", "Moscow");
        Person p4 = new Person("Anton", "Moscow");
        Person p5 = new Person("Anton", "Moscow");
        persons = new ArrayList<>();
        persons.add(p1);
        persons.add(p2);
        persons.add(p3);
        persons.add(p4);
        persons.add(p5);
    }

    /**
     * delete data
     */
    @After
    public void tearDown(){
        persons = null;
    }

    /**
     * check equals() method for Person
     */
    @Test
    public void testEqualsCheck() {
        Person p1 = new Person("Anton", "Moscow");
        Person p2 = new Person("Anton", "Moscow");
        assertEquals("Equals check", p1, p2);
    }

    /**
     * check compareTo() method for Person
     */
    @Test
    public void testCompareTo() {
        init();
        Person p2 = persons.get(1);
        persons.sort(Person::compareTo);
        assertEquals("CompareTo check", p2, persons.get(0));
    }
}
