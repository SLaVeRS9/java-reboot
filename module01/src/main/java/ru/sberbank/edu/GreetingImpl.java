package ru.sberbank.edu;

/**
 * My greeting
 * @author Viacheslav Lopusov
 * @version 1.0
 */
public class GreetingImpl implements Greeting{
    /**
     * Field is name of my hobby
     */
    private static final String MY_BEST_HOBBY = "Airsoft";

    /**
     * Method for get my best hobby
     * @return my best hobby
     */
    @Override
    public String getBestHobby() {
        return MY_BEST_HOBBY;
    }
}
