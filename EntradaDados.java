import javax.swing.JOptionPane;

public class EntradaDados {

    public static void main(String[] args) {
        String nome = JOptionPane.showInputDialog(null, "Digite seu nome: ", "Entrada de Dados", JOptionPane.QUESTION_MESSAGE);//caixa de dialogo para entrada de dados
        JOptionPane.showMessageDialog(null, "Seu nome é: " + nome, "Saida de Dados", JOptionPane.INFORMATION_MESSAGE);//caixa de dialogo para saida de dados
        //String texto;
        //int num;
        //Scanner entrada = new Scanner(System.in);//scaner usa terminal para entrada de dados
       
       /*  System.out.print("Digite um numero inteiro: ");
        num = entrada.nextInt();
        System.out.println("Numero digitado: " + num);*/

       /* entrada.useDelimiter("  ; \n  \r"); //define o delimitador como nova linha para ler strings com espacos
        System.out.print("Digite um texto: ");
        texto = entrada.nextLine();
        System.out.println("Texto digitado: " + texto);*/


    }
    
}
