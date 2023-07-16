import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите первое чесло");
        int a = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите второе чеслло");
        int b = Integer.parseInt(scanner.nextLine());
        System.out.println("Выберите операцию: (+,-,*,/)");
        String oper = scanner.nextLine();
        int result;
        TestF testF = new TestF();
        testF.Food( int a,int b, int result, String oper);
    }
}


