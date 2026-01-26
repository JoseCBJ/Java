
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class InterfaceGrafica{

        private int cliques=0;

        public void InterGraf(){
            JFrame janela = new JFrame("Teste");//cria a moldura da janela
            janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//fechou a janela=fechou o processo
            janela.setSize(300, 200);//tamanho
            janela.setLayout(new FlowLayout());//definindo os lugares dos objetos/o flow faz isso automaticamente, se nao usar o null

            JLabel texto = new JLabel("Cliques: 0");
            JButton botao =new JButton("Clique aqui!!");

            botao.addActionListener(e -> {//define comportamento por um evento de escuta, se algo acontecer faça este bloco, atualiza o estado anterior
                cliques++;
                texto.setText("Cliques: "+cliques);
            });

            janela.add(texto);
            janela.add(botao);
            janela.setVisible(true);
        }
    public static void main(String[] args) {
        new InterfaceGrafica().InterGraf();
    }
}