package RecursiveFunc;

public class Aula {
    public static int fatorial(int x) throws Exception {
        if (x < 0)
            throw new Exception("Negativos nao aceitaveis");
        if (x < 2)
            return 1;
        return x * fatorial(x - 1);
    }
    // daqui para cima temos o exemplo de recursão usado na aula
    // daqui para baixo temos os exercícios de recursão da lista

    public static boolean isNegativo(int a, int d) {
        if (a == 0)
            return true;
        if (d == 0)
            return false;
        return isNegativo(++a, --d);
    }

    public static boolean isNegativo(int x) {
        if (x == 0)
            return false;
        return isNegativo(x, x);
    }

    public static int soma(int x, int y) {
        if (y == 0)
            return x;
        if (isNegativo(y))
            return soma(--x, ++y);
        return soma(++x, --y);
    }

    public static int subtracao(int x, int y) {
        if (y == 0)
            return x;
        if (isNegativo(y))
            return subtracao(++x, ++y);
        return soma(--x, --y);
    }

    public static int modulo(int x) {
        if (isNegativo(x)) {
            return subtracao(0, x);
        }
        return x;
    }

    public static int multiplicacao(int x, int y)
	{
		if (y == 0) return 0;
		
		if (isNegativo(y))
		{
			if (isNegativo(x))
			{
				return multiplicacao(modulo(x), modulo(y));
			}
			
			return subtracao(0, multiplicacao(modulo(x), modulo(y)));
		}
		
		y--;
		
		return soma(x, multiplicacao(x, y));
	}

    public static int divisao(int x, int y) throws Exception {
        if (y == 0)
            throw new Exception("Não pode dividir por 0");
        if (y == 1)
            return x;
        if (isMaior(modulo(y), modulo(x)))
            return 0;

        if (isNegativo(x) == isNegativo(y)) {
            return soma(divisao(subtracao(x, y), y), 1);
        }
        return subtracao(divisao(soma(x, y), y), 1);
    }

    public static boolean isMenor (int x, int y)
    {
		return isNegativo(subtracao(x,y));
	}

    public static boolean isMaior(int x, int y) {
        if (x == y)
            return false;
        if (isMenor(x, y))
            return false;
        return true;
    }

    // façam o que falta

    public static void main(String[] args) {
        try {
            System.out.println("Fatorial de 4: " + fatorial(4));
        } catch (Exception erro) {
            System.err.println(erro.getMessage());
        }
        // daqui para cima testamos o exemplo de recursão usado na aula
        // daqui para baixo testamos os exercícios de recursão da lista
        System.out.println("-1 é negativo: " + isNegativo(-1));
        System.out.println(" 0 é negativo: " + isNegativo(0));
        System.out.println("11 é negativo: " + isNegativo(11));
        System.out.println(" 5 mais  3: " + soma(5, 3));
        System.out.println(" 5 mais -3: " + soma(5, -3));
        System.out.println("-5 mais  3: " + soma(-5, 3));
        System.out.println("-5 mais -3: " + soma(-5, -3));
        System.out.println(" 0 mais  3: " + soma(0, 3));
        System.out.println(" 0 mais -3: " + soma(0, -3));
    }

    // faça tudo o mais da lista de exercícios sobre recursão
}
