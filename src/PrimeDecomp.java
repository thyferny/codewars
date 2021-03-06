import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.function.Function;

import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.*;

public class PrimeDecomp {
    public List<Long> factorList(long n) {
        List<Long> list = new ArrayList<Long>();
        if (n <= 2) {
            list.add(n);
        } else {
            for (long i = 2; i < n; i++) {
                while (n > 2 && n % i == 0) {
                    list.add(i);
                    n = n / i;
                }
            }
            if (n > 1) {
                list.add(n);
            }
        }
        return list;
    }

    public static String factors(int n) {
        return new PrimeDecomp().factorList(n).stream().collect(
                groupingBy(identity(), TreeMap::new, counting())
        ).entrySet().stream().map(i -> "(" + i.getKey() + (i.getValue() > 1 ? "**" + i.getValue() : "") + ")").collect(joining());
    }

    public static void main(String[] args) {
        System.out.println(factors(12345*3));
    }
}