import java.util.Scanner;
public class Fibonachi {
    public static void main(String[] args) {
        int FrstNum, ScdNum, ThrdNum;
        Scanner entrada =  new Scanner(System.in);
        System.out.println("Digite o quantos numeros queia ver em uma sequencia de Fibonacci");
        int limite = entrada.nextInt();
        FrstNum=1;
        ScdNum=1;
        for(int i = 1; i <= limite; i++)
        {
            if(i<2)
                System.out.println(FrstNum);
            else
                {
                    ThrdNum = ScdNum + FrstNum;
                    System.out.println(ThrdNum);
                    FrstNum = ScdNum;
                    ScdNum = ThrdNum;
                }
            
        }
        entrada.close();
    }
}
