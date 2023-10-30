package ru.sberbank.edu;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        List<Integer> myArrList = new ArrayList<>();
        myArrList.add(4);
        myArrList.add(7);
        myArrList.add(3);
        myArrList.add(6);
        System.out.println(myArrList);
        myArrList.sort(new CustomDigitComparator());
        System.out.println(myArrList);
    }
}
