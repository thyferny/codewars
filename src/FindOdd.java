import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FindOdd {
	public static int findIt(int[] intArr) {

        Map<Integer, Long> appearTimes = IntStream.of(intArr).boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return appearTimes.entrySet()
                .stream()
                .filter(e -> e.getValue() % 2 != 0)
                .map(Map.Entry::getKey)
                .findFirst()
                .get();
    
  }
}