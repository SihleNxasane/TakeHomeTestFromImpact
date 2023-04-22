import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class NumberRangeSummarizerImplTest {
    private NumberRangeSummarizerImpl summarizerUnderTest;

    @BeforeEach
    void setUp() {
        this.summarizerUnderTest = new NumberRangeSummarizerImpl();
    }

    @Test
    public void testIFCollectReturnsExpectedListSize() {
        Collection<Integer> actualListOfNumbers= summarizerUnderTest.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31");
        Collection<Integer> expectedListOfNumbers = new ArrayList<>(Arrays.asList(1, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31));

        assertEquals(expectedListOfNumbers.size(), actualListOfNumbers.size());
    }

    @Test
    public void testIfCollectReturnsExpectedListOfNumbers() {
        Collection<Integer> actualListOfNumbers = summarizerUnderTest.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31");
        Collection<Integer> expectedListOfNumbers = new ArrayList<>(Arrays.asList(1, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31));

        Iterator<Integer> actualResultsIterator = actualListOfNumbers.iterator();
        Iterator<Integer> expectedResultIterator = expectedListOfNumbers.iterator();

        while (actualResultsIterator.hasNext() && expectedResultIterator.hasNext()) {  //Deep comparison of numbers in both lists
            Integer actualResultNumber = actualResultsIterator.next();
            Integer expectedResultNumber = expectedResultIterator.next();

            assertEquals(expectedResultNumber, actualResultNumber);
        }
    }

    @Test
    void testIfSummarizeCollectionReturnsExpectedFinalOutput() {
        Collection<Integer> actualListOfNumbers = summarizerUnderTest.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31");
        String actualFinalOutput = this.summarizerUnderTest.summarizeCollection(actualListOfNumbers);
        String expectedFinalOutput = "1, 3, 6-8, 12-15, 21-24, 31";

        assertEquals(expectedFinalOutput, actualFinalOutput);
    }

    @Test
    public void testAddRange() {
        List<String> ranges = new ArrayList<>();
        this.summarizerUnderTest.addRange(ranges, 1, 3);
        String result = ranges.get(0);
        String expected = "1-3";
        assertEquals(expected, result);
    }

    @Test
    public void testAddRangeSingleNumber() {
        List<String> ranges = new ArrayList<>();
        this.summarizerUnderTest.addRange(ranges, 1, 1);
        String result = ranges.get(0);
        String expected = "1";
        assertEquals(expected, result);
    }
}