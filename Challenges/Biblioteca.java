
import java.util.Scanner;


public class Biblioteca{
    static class Livro{
      String titulo;
      String autor;
      boolean disponibiblidade;
    }

    public static boolean Estoque(Livro[] biblioteca, String titulo, String autor, int quantidade){
      for(int j=0;j<quantidade;j++){
        if(biblioteca[j]!=null)
          if((titulo.equalsIgnoreCase(biblioteca[j].titulo) && autor.equalsIgnoreCase(biblioteca[j].autor))||(titulo.equals(biblioteca[j].titulo)))
           return false;
      }
      return true;
    } 

    public static Livro AddLivro(Livro[] biblioteca, String titulo, String autor, int quantidade){
      if(Estoque(biblioteca, titulo, autor, quantidade)){
        Livro novoLivro = new Livro();
        novoLivro.titulo = titulo;
        novoLivro.autor = autor;
        novoLivro.disponibiblidade = true;
        return novoLivro;
      }
      return null;
    }

    public static boolean EmprestaLivro(Livro livro, String titulo, Livro[] biblioteca){
      if(livro!=null && BuscaLivro(titulo, biblioteca)==livro){
        livro.disponibiblidade = false;
        return true;
      } else {
        return false;
      }
    }

    public static Livro BuscaLivro(String titulo, Livro[] biblioteca){
      for(Livro livro : biblioteca){
        if((livro!=null && livro.titulo.equalsIgnoreCase(titulo)) && livro.disponibiblidade){
          return livro;
        }
      }
      return null;
    }
    public static void main(String[] args) {
      try(Scanner entrada = new Scanner(System.in)){
        System.out.println("Digite a quantidade de livros a ser adicionada");
        int qtndLivro = entrada.nextInt();
        entrada.nextLine();//se entra int depois string tem que limpar o enter no int
        Livro[] UnidLivro = new Livro[qtndLivro];
        for(int i=0;i<qtndLivro;i++){
          System.out.println("Digite o nome do titulo do livro");
          String Titulo = entrada.nextLine();
          System.out.println("Digite o nome do autor:");
          String Autor = entrada.nextLine();
          
          UnidLivro[i]=AddLivro(UnidLivro, Titulo, Autor, qtndLivro);
          if(UnidLivro[i]==null){
            System.out.println("Livro ja existe");
            i--;
          }
          else
            System.out.println("Livro adicionado com sucesso");
        }
        while (true) { 
          System.out.println("Opções: buscar livro(1), emprestar livro(2), sair(3)");
          int opcoes = entrada.nextInt();
          entrada.nextLine();
            switch (opcoes) {
                case 1 -> {
                    System.out.println("Digite o nome do livro:");
                    String nomeLiv = entrada.nextLine();
                    if(BuscaLivro(nomeLiv, UnidLivro)!=null)
                        System.out.println("Livro em estoque");
                    else
                        System.out.println("Livro não encontrado no estoque ou nao cadastrado");
                }
                case 2 -> {
                    System.out.println("Qual livro deseja emprestar:");
                    String emprLiv=entrada.nextLine();
                    Livro livro = BuscaLivro(emprLiv, UnidLivro);
                    if(livro != null && EmprestaLivro(livro, emprLiv, UnidLivro))
                        System.out.println("Atividade realizada com sucesso");
                    else
                        System.out.println("Livro não encontrado no estoque ou nao cadastrado");
                }
                case 3 -> {
                    return;
                }
                default -> {
                  throw new IllegalArgumentException("Opcao invalida");
                }
            }
          }
        }
      }
    }
