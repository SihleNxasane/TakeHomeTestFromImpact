import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;


public class NumberRangeSummarizerImpl implements NumberRangeSummarizer {

    @Override
    public Collection<Integer> collect(String input) {
        List<Integer> listOfNumbers = new ArrayList<>();
        String[] tokens = input.split(",");

        for (String token : tokens) {
            try {
                Integer number = Integer.valueOf(token.trim());
                listOfNumbers.add(number);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(listOfNumbers); //Just to ensure the list Of numbers is sorted.
        return listOfNumbers;
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) {
        List<Integer> numbers = new ArrayList<>(input);
        List<String> ranges = getRanges(numbers);
        return String.join(", ", ranges);
    }

    public List<String> getRanges(List<Integer> numbers) {
        List<String> ranges = new ArrayList<>();
        int start = -1, prev = -1;

        for (int currentNumber : numbers) {
            if (start == -1) {
                start = prev = currentNumber;
            } else if (currentNumber == prev + 1) {
                prev = currentNumber;
            } else {
                addRange(ranges, start, prev);
                start = prev = currentNumber;
            }
        }

        if (start != -1) {
            addRange(ranges, start, prev);
        }

        return ranges;
    }

    public void addRange(List<String> ranges, int start, int end) {
        if (start == end) {
            ranges.add(Integer.toString(start));
        } else {
            ranges.add(start + "-" + end);
        }
    }
}
