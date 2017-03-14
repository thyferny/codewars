import javafx.util.Pair;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interpreter {
    private static final String PARAM = "param0";
    private HashMap<Character, String> variableValueMap = new HashMap<>();
    private HashMap<String, Pair<Integer, String>> functionValueMap = new HashMap<>();

    public Double input(String input) {
        if(input.replace(" ","").isEmpty())
            return null;
        if (isFunctionDeclaration(input)) {
            saveFunction(input);
            return null;
        }
        String noSpaceInput = getRidOfSpaces(input);
        if (noSpaceInput.isEmpty()) {
            return null;
        }
        return calculateInRecursion(noSpaceInput);
    }

    private void saveFunction(String input) {
        String[] declarationBodyArray = input.split("=>");
        String[] declarationArray = declarationBodyArray[0].trim().split(" ");
        String functionBody = declarationBodyArray[1].replaceAll(" ", "");
        String name = declarationArray[1];
        for (int i = 2; i < declarationArray.length; i++) {
            if (functionBody.contains(declarationArray[i]))
                functionBody = functionBody.replaceAll(declarationArray[i], PARAM + (i - 2));
            else
                throw new RuntimeException();
        }
        if (containsLetters(functionBody.replaceAll(PARAM, "")))
            throw new RuntimeException();
        if (name.length() == 1 && variableValueMap.containsKey(name.charAt(0)))
            throw new RuntimeException();
        functionValueMap.put(name, new Pair<>(declarationArray.length - 2, functionBody));
    }

    private String getRidOfSpaces(String input) {
        StringBuilder sb = new StringBuilder();
        char[] chars = input.trim().toCharArray();
        sb.append(chars[0]);
        for (int i = 1; i < chars.length - 1; i++) {
            char current = chars[i];
            if (current != ' ')
                sb.append(current);
            else {
                char previous = chars[i - 1];
                char next = chars[i + 1];
                if (!isSpaceNeededToRemove(previous, next))
                    sb.append(current);
            }
        }
        if (chars.length > 1)
            sb.append(chars[chars.length - 1]);
        return sb.toString();
    }

    private boolean isSpaceNeededToRemove(char previous, char next) {
        return !(previous == ')' && next == '(') && !(Character.isLetterOrDigit(previous) && Character.isLetterOrDigit(next));
    }

    private double calculateInRecursion(String expression) {
        int lastIndexOfFunction = lastIndexFunction(expression);
        if (lastIndexOfFunction > -1) {
            String secondPart = expression.substring(lastIndexOfFunction);
            String[] functionParts = secondPart.split(" ");
            String functionName = functionParts[0];
            Pair<Integer, String> currentFunction = functionValueMap.get(functionName);
            int paramsCount = currentFunction.getKey();
            String functionBody = currentFunction.getValue();
            for (int i = 0; i < paramsCount; i++) {
                functionBody = functionBody.replace(PARAM + i, functionParts[i + 1]);
                functionParts[i + 1] = "";
            }
            functionParts[0] = functionBody + " ";
            StringBuilder sb = new StringBuilder();
            for (String functionPart : functionParts) {
                if (!sb.toString().isEmpty() && Character.isLetterOrDigit(sb.charAt(sb.length() - 1)) && !functionPart.isEmpty())
                    sb.append(" ");
                sb.append(functionPart);
            }
            String firstPartOfExpression = expression.substring(0, lastIndexOfFunction);
            return calculateInRecursion(firstPartOfExpression + sb.toString().trim());
        } else if (isExpressionNoVariables(expression)) {
            return calculate(expression);
        } else if (matchesVariable(expression)) {
            Character variable = expression.charAt(0);
            Double result = calculateInRecursion(expression.substring(2));
            variableValueMap.put(variable, String.valueOf(result));
            return result;
        } else if (isInParenthesis(expression)) {
            return calculateInRecursion(expression.substring(1, expression.length() - 1));
        } else if (expression.contains("=")) {
            Matcher nearbyParenthesisMatcher = getNearbyParenthesis(expression);
            nearbyParenthesisMatcher.find();
            int startingIndex = nearbyParenthesisMatcher.start();
            int endingIndex = nearbyParenthesisMatcher.end();
            return calculateInRecursion(expression.substring(0, startingIndex) + calculateInRecursion(expression.substring(startingIndex, endingIndex)) + expression.substring(endingIndex));
        } else {
            for (Character character : variableValueMap.keySet()) {
                expression = expression.replaceAll(String.valueOf(character), variableValueMap.get(character));
            }
            return calculate(expression);
        }
    }

    private int lastIndexFunction(String expression) {
        int maxInt = -1;
        for (String s : functionValueMap.keySet()) {
            int lastIndexOf = expression.lastIndexOf(s);
            if (lastIndexOf > maxInt) maxInt = lastIndexOf;
        }
        return maxInt;
    }


    private double calculate(String expression) {
        String result = null;
        try {
            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript");
            result = engine.eval(expression).toString();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return Double.valueOf(result);
    }

    private boolean matchesVariable(String input) {
        Pattern pattern = Pattern.compile("^[A-Za-z_]{1}[=]{1}.+");
        return pattern.matcher(input).find();
    }

    private boolean isExpressionNoVariables(String input) {
        return Pattern.compile("^[\\d\\+\\-\\*\\%\\/\\(\\)]+$").matcher(input).find();
    }

    private boolean isInParenthesis(String input) {
        return Pattern.compile("^[\\(].+[\\)]$").matcher(input).find();
    }

    private Matcher getNearbyParenthesis(String input) {
        return Pattern.compile("[\\(]{1}[^(]+[\\)]{1}").matcher(input);
    }

    private boolean isFunctionDeclaration(String input) {
        return Pattern.compile("^fn [A-Za-z_]{1,}.+(\\=\\>)").matcher(input).find();
    }

    private boolean containsLetters(String input) {
        return Pattern.compile("[A-Za-z_]").matcher(input).find();
    }


}