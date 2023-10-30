package ru.sberbank.edu;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        List<Integer> myArrList = new ArrayList<>();
        myArrList.add(4);
        myArrList.add(7);
        myArrList.add(3);
        myArrList.add(6);
        myArrList.sort(new CustomDigitComparator());
        int firstEl = myArrList.get(0);
        int thirdEl = myArrList.get(2);
        assertEquals(4, firstEl);
        assertEquals(3, thirdEl);
    }
}
