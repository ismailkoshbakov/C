import java.util.Arrays;
import java.util.Scanner;
public class Calculator {
    String operatorString = "";
    String operand1String = "";
    String operand2String = "";
    int multiplier = 0;
    boolean inQuotes = false;
    public void getData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение:");
        String input = scanner.nextLine();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == ' ' && !inQuotes) {
                continue;
            } else if (c == '"' && (i == 0 || input.charAt(i - 1) != '\\')) {
                inQuotes = !inQuotes;
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                if (operand1String.isEmpty()) {
                    operand1String = input.substring(0, i).trim();
                }
                if (operand1String.length() > 12) {
                    System.out.println("Ошибка! Компонент  " + operand1String + "  содержит более 10 символов");
                    operand1String = operand1String.substring(0, 12);
                } else {
                    operatorString = Character.toString(c);
                    break;
                }
            }
        }
        if (operatorString.isEmpty()) {
            throw new IllegalArgumentException("Не найден оператор");
        }
        String[] components = input.substring(input.indexOf(operatorString) + 1).split("\\s+");
        for (String component : components) {
            if (!component.isEmpty() && !Character.isWhitespace(component.charAt(0))) {
                if (Oper.isNumber(component) || component.startsWith("\"")) {
                    String trimmedComponent = component.trim();
                    if (operand2String.length() + trimmedComponent.length() > 10) {
                        int componentIndex = Arrays.asList(components).indexOf(component);
                        throw new IllegalArgumentException("Ошибка! Компонент " + component + " содержит более 10 символов");
                    } else {
                        operand2String += trimmedComponent;
                    }
                } else if (component.matches("[0-9]+x")) {
                    multiplier = Integer.parseInt(component.substring(0, component.length() - 1));
                } else {
                    throw new IllegalArgumentException("Недопустимый операнд: " + component);
                }
            }
        }
        Oper oper = new Oper();
        oper.getFree(operatorString, operand1String, operand2String);
    }
}





