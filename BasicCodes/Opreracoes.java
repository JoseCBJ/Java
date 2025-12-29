import java.util.Scanner;

public class Opreracoes {
    public static void main(String[] args) {
        //int a, b; //soma, subt, mult
        double a, b, div;
        Scanner Main = new Scanner(System.in);
        System.out.println("Digite um numeros inteiros: "); 
        a = Main.nextDouble();
        System.out.println("Digite outro numero inteiro: ");
        b = Main.nextDouble();
        //soma = a + b;
       // System.out.println("A soma de " + a + " + " + b + " = " + soma);

        // subt = a - b;
        //System.out.println("A subtracao de " + a + " - " + b + " = " + subt);

        //mult = a * b;
        //System.out.println("A multiplicacao de " + a + " * " + b + " = " + mult);

        //div = a / b; //divisao inteira
        div = a / b; //divisao com numeros decimais
        System.out.println("A divisao de " + a + " / " + b + " = " + div);
    }
}
