/*
  Recursive descent parse using the following grammar:
  E = T | T + E | T - E
  T = F | T * F | T / F
  F = V | (E)
  V = number
*/
public class MathEvaluator {

  public double calculate(String expression) {
    expression = expression.replace(" ", "");
    Expression expr = parseE(expression);
    return expr.eval();
  }

  private Expression parseE(String expression) {
    Expression t = parseT(expression);
    if (t != null) return t;

    int indexAdd = expression.indexOf("+");
    int indexSub = expression.indexOf("-");
    boolean parseAsAdd = (indexAdd >= 0 && (indexSub == -1 || indexSub > indexAdd));
    boolean parseAsSub = (indexSub >= 0 && (indexAdd == -1 || indexAdd > indexSub));

    if (parseAsAdd) {
      return new Add(parseT(expression.substring(0, indexAdd)),
              parseE(expression.substring(indexAdd + 1)));
    }
    else if (parseAsSub) {
      return new Subtract(parseT(expression.substring(0, indexSub)),
              parseE(expression.substring(indexSub + 1)));
    }
    else {
      return null;
    }
  }

  private Expression parseT(String expression) {
    Expression f = parseF(expression);
    if (f != null) return f;

    int indexMult = expression.indexOf("*");
    int indexDiv = expression.indexOf("/");
    boolean parseAsMult = (indexMult >= 0 && (indexDiv == -1 || indexDiv > indexMult));
    boolean parseAsDiv = (indexDiv >= 0 && (indexMult == -1 || indexMult > indexDiv));

    if (parseAsMult) {
      return new Multiply(parseT(expression.substring(0, indexMult)),
              parseF(expression.substring(indexMult + 1)));
    }
    else if (parseAsDiv) {
      return new Divide(parseT(expression.substring(0, indexDiv)),
              parseF(expression.substring(indexDiv + 1)));
    }
    else {
      return null;
    }
  }

  private Expression parseF(String expression) {
    if (expression.startsWith("(") && expression.endsWith(")")) {
      return new Group(parseE(expression.substring(1, expression.length() - 2)));
    }
    else {
      return parseV(expression);
    }
  }

  private Expression parseV(String expression) {
    try {
      // this is slightly more general than necessary
      return new Value(Double.parseDouble(expression));
    }
    catch (NumberFormatException e) {
      return null;
    }
  }
}

/* AST types */
interface Expression {
  double eval();
}

class Value implements Expression {
  private final double val;
  Value(double val) { this.val = val; }
  public double eval() { return val; }
  public String toString() { return Double.toString(val); }
}

class Group implements Expression {
  private final Expression expr;
  Group(Expression expr) { this.expr = expr; }
  public double eval() { return expr.eval(); }
  public String toString() { return "(" + expr + ")"; }
}

class Add implements Expression {
  private final Expression left, right;
  Add(Expression left, Expression right) { this.left = left; this.right = right; }
  public double eval() { return left.eval() + right.eval(); }
  public String toString() { return left + " + " + right; }
}

class Subtract implements Expression {
  private final Expression left, right;
  Subtract(Expression left, Expression right) { this.left = left; this.right = right; }
  public double eval() { return left.eval() - right.eval(); }
  public String toString() { return left + " - " + right; }
}

class Multiply implements Expression {
  private final Expression left, right;
  Multiply(Expression left, Expression right) { this.left = left; this.right = right; }
  public double eval() { return left.eval() * right.eval(); }
  public String toString() { return left + " * " + right; }
}

class Divide implements Expression {
  private final Expression left, right;
  Divide(Expression left, Expression right) { this.left = left; this.right = right; }
  public double eval() { return left.eval() / right.eval(); }
  public String toString() { return left + " / " + right; }
}