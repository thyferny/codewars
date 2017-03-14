import java.math.BigDecimal;

public class Kata
{
  public static void main(String[] args) {
    System.out.println(Factorial(25));
  }
  public static String Factorial(int n) {
//    double value = Math.sqrt(2 * Math.PI * n) * Math.pow(n / Math.E, n) * Math.exp(1 / 12 / n - 1 / 360 / n / n);
    if(n==0||n==1) return "1";
    BigDecimal b = new BigDecimal(1);
    for (int i = 1; i <= n; i++) {
      BigDecimal fac = new BigDecimal(i);
      b = b.multiply(fac);
    }
    return n+"! = "+b.toString();
  }
    public static void enter() {
      Kata.sneakyThrow();
    }

    static <T extends Throwable> void sneakyThrow() throws T {
//      throw (T) new Neo();
    }

}