package RecursiveFunc;

public class Ex1 {

    public static boolean ehNegativo(int n){
        if (n == 0) 
            return false;
        return aux(n, n);
    }

    public static boolean aux(int x, int y){
        x++;
        y--;
        if (x == 0)
            return true;
        if (y == 0)
            return false;
        
        return aux(x, y);
    }
    
    public static void main(String[] args) {
        System.out.println(ehNegativo(-10));
    }
}
