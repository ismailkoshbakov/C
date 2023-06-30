public class Oper {
    public void getFree(String operator, String operand1, String operand2) {
        String result = "";
        switch (operator) {
            case "+":
                if (operand1.matches("-?\\d+(\\.\\d+)?")) {
                    throw new IllegalArgumentException("Первый операнд не может быть числом");
                }
                result = operand1 + operand2;
                break;
            case "-":
                if (operand1.matches("\".*\"") && operand2.matches("\".*\"")) {
                    String str1 = operand1.substring(1, operand1.length() - 1);
                    String str2 = operand2.substring(1, operand2.length() - 1);
                    if (str1.contains(str2) && !str1.equals(str2)) {
                        result = "\"" + str1.replace(str2, "") + "\"";
                    } else {
                        result = operand1;
                    }
                } else {
                    throw new IllegalArgumentException("Невозможно выполнить операцию вычитания: " + operand1 + ", " + operand2);
                }
                if (operand2.length() > operand1.length()) {
                    throw new IllegalArgumentException("Нельзя вычитать строку большей длины из строки меньшей длины!");
                }
                break;
            case "*":
                if (operand1.matches("\".*\"") && operand2.matches("[0-9]+")) {
                    String str = operand1.substring(1, operand1.length() - 1);
                    int num = Integer.parseInt(operand2);
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < num; i++) {
                        sb.append(str);
                    }
                    String resultString = "\"" + sb.toString() + "\"";
                    if (resultString.length() > 41) {
                        resultString = resultString.substring(0, 41) + "...";
                    }
                    result = resultString;
                } else {
                    throw new IllegalArgumentException("Невозможно выполнить операцию умножения: " + operand1 + ", " + operand2);
                }
                break;
            case "/":
                if (operand1.matches("\".*\"") && operand2.matches("[0-9]+")) {
                    String str = operand1.substring(1, operand1.length() - 1);
                    int num = Integer.parseInt(operand2);
                    int len = str.length() / num;
                    result = str.substring(0, len);
                } else {
                    throw new IllegalArgumentException("Невозможно выполнить операцию деления: " + operand1 + ", " + operand2);
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + operator);
        }
        result = result.replace("\"", "");
        System.out.println("Результат: " + "\"" + result + "\"");
    }

    static boolean isNumber(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}

