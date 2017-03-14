public class Money {
  public static int calculateYears(double principal, double interest,  double tax, double desired) {
    // your code
    return (int) Math.ceil(Math.log(desired/principal)/Math.log(1.0+interest-interest*tax));
  }
}