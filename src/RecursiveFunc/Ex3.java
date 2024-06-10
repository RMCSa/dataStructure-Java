package RecursiveFunc;

public class Ex3 {

    public static int subtracaoRecursiva(int num1, int num2) {
        if (num2 == 0)
            return num1;
        if (Ex1.ehNegativo(num2)) {
            num1++;
            num2++;
            return subtracaoRecursiva(num1, num2);
        }
        num1--;
        num2--;
        return subtracaoRecursiva(num1, num2);
    }

    public static void main(String[] args) {
        System.out.println(subtracaoRecursiva(3, 4));
    }

}
