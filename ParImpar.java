import java.util.Scanner;

public class ParImpar{
    public static void main(String[] args) {
        Scanner entrada =  new Scanner(System.in);
        int Saida;

        System.out.println("Digite um numero inteiro positivo: ");
        int Entrada = entrada.nextInt();
        
        if(Entrada==0)
            System.out.println("0 Nao eh par");

        if(Entrada<0)
            System.out.println("Deve ser maior que 0");

        if(Entrada>0)
            {
                Saida = Entrada % 2;
                if(Saida==0)
                    System.out.println(Entrada + " Numero par");
                if(Saida==1)
                    System.out.println(Entrada + " Numero Impar");
            }    
        
        entrada.close();
    }
}