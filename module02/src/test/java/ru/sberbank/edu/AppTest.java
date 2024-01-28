package ru.sberbank.edu;

/*
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.*;

*/
/**
 * Unit test for simple App.
 *//*

public class AppTest
    extends TestCase
{
    @Mock
    FileToFileStatistic fileToFileStatistic;
    private static final String LONGEST_LINE = "The longest line";
    private static final String NOT_LONGEST_LINE = "Not the longest line";
    */
/**
     * Create the test case
     *
     * @param testName name of the test case
     *//*

    public AppTest( String testName )
    {
        super( testName );
    }

    */
/**
     * @return the suite of tests being tested
     *//*

    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    */
/**
     * Test for retrieving lines count from a file
     *//*

    public void testAppLineCount()
    {
        openMocks(this);
        when(fileToFileStatistic.getLineCount()).thenReturn(10);
        assertEquals(10, fileToFileStatistic.getLineCount());
    }

    */
/**
     * Test for retrieving spaces count from a file
     *//*

    public void testAppSpaceCount()
    {
        openMocks(this);
        when(fileToFileStatistic.getSpaceCount()).thenReturn(20);
        assertEquals(20, fileToFileStatistic.getSpaceCount());

    }

    */
/**
     * Test for retrieving the longest line from a file
     *//*

    public void testAppGetLongestLine()
    {
        openMocks(this);
        when(fileToFileStatistic.getLongestLine()).thenReturn(LONGEST_LINE);
        assertEquals(LONGEST_LINE, fileToFileStatistic.getLongestLine());
    }

    */
/**
     * Test for retrieving wrong longest line from a file
     *//*

    public void testAppGetWrongLongestLine()
    {
        openMocks(this);
        when(fileToFileStatistic.getLongestLine()).thenReturn(NOT_LONGEST_LINE);
        assertNotSame(LONGEST_LINE, fileToFileStatistic.getLongestLine());
    }
}
*/
