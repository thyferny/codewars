import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

public class Fibonacci {

  public static void main(String[] args) {
//    for (int i = 1; i < 100 ; i++) {
//      System.out.println(fib(i));
//    }
    System.out.println(fib(BigInteger.valueOf(1500000)));
  }
//  public static BigInteger fib(int n) {
////    (1/√5)*{[(1+√5)/2]^n - [(1-√5)/2]^n}
//
//    BigDecimal sqrt5 = new BigDecimal(Math.sqrt(5));
//    BigDecimal one = new BigDecimal(1);
//    BigDecimal f1 = one.add(sqrt5);
//    BigDecimal f2 = one.subtract(sqrt5);
//    BigDecimal two = new BigDecimal(2);
//    BigDecimal v2n = two.pow(n);
//    BigDecimal vf1n = f1.pow(n);
//    BigDecimal vf2n = f2.pow(n);
//    BigDecimal fab = vf1n.subtract(vf2n).divide(sqrt5.multiply(v2n));
//    return fab.toBigInteger();
//  }

  public static BigInteger fib(BigInteger n) {
    BigInteger result[][] = {{BigInteger.ONE, BigInteger.ONE}, {BigInteger.ONE, BigInteger.ZERO}};

    boolean negated = false;

    switch (n.compareTo(BigInteger.ZERO)) {
      case -1:
        n = n.negate();
        negated = true;
        break;
      case 0:
        return BigInteger.ZERO;
    }

    power(result, n.subtract(BigInteger.ONE));

    if (negated)
      return n.mod(BigInteger.valueOf(2)).equals(BigInteger.ONE) ? result[0][0] : result[0][0].negate();
    return result[0][0];
  }

  private static void power(BigInteger array[][], BigInteger n) {
    if (n.equals(BigInteger.ZERO) || n.equals(BigInteger.ONE))
      return;

    BigInteger identity[][] = {{BigInteger.ONE, BigInteger.ONE}, {BigInteger.ONE, BigInteger.ZERO}};

    power(array, n.divide(BigInteger.valueOf(2)));
    multiply(array, array);
    if (!n.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO))
      multiply(array, identity);
  }

  private static void multiply(BigInteger arr1[][], BigInteger arr2[][]) {
    BigInteger x = (arr1[0][0].multiply(arr2[0][0])).add(arr1[0][1].multiply(arr2[1][0]));
    BigInteger y = (arr1[0][0].multiply(arr2[0][1])).add(arr1[0][1].multiply(arr2[1][1]));
    BigInteger z = (arr1[1][0].multiply(arr2[0][0])).add(arr1[1][1].multiply(arr2[1][0]));
    BigInteger w = (arr1[1][0].multiply(arr2[0][1])).add(arr1[1][1].multiply(arr2[1][1]));
    arr1[0][0] = x;
    arr1[0][1] = y;
    arr1[1][0] = z;
    arr1[1][1] = w;
  }
  
}