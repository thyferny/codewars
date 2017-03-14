import java.util.*;

import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class PrimeFactorizer{
	public static List<Long> factorList(long n) {
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

	public static HashMap factor(long n) {
		return factorList(n).stream().collect(groupingBy(identity(), HashMap::new, counting()));
	}

	public static void main(String[] args) {
		System.out.println(factor(120));
	}
}