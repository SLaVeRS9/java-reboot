package ru.sberbank.edu;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class GreetingImplTest {
    private GreetingImpl greeting;
    @Before
    public void init() {
        greeting = new GreetingImpl();
    }

    @After
    public void tearDown() {
        greeting = null;
    }

    @Test
    public void getBestHobbyCorrectHobby() {
        Assert.assertEquals("Airsoft", greeting.getBestHobby());
    }

    @Test
    public void getBestHobbyWrongHobby() {
        Assert.assertNotEquals("Knitting", greeting.getBestHobby());
    }
}
