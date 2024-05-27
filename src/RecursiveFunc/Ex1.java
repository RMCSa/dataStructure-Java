package RecursiveFunc;

public class Ex1 {

    public static boolean exe01(int n){
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
        System.out.println(exe01(10));
    }
}
