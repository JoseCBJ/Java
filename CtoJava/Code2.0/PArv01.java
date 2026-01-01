
import java.util.Scanner;


public class PArv01{
    public static class Tno{
        public Tno esq, dir, Pai;
        public int valor, qtnd;
    }

    public static Tno AchaPai(Tno r, int n){
       if(r==null)
        return null;
       else
        if(n<=r.valor)
            if(r.esq==null)
                return r;
            else 
                return AchaPai(r.esq, n);
        else 
            if(r.dir==null)
                return r;
            else 
                return AchaPai(r.dir, n);
    }

    public static int ContaNos(Tno r){
        if(r==null)
            return 0;
        else
            return 1 + ContaNos(r.esq) + ContaNos(r.dir);
    }

    public static void Impressao(Tno r){
        if(r==null)
            System.out.println("Arvore vazia");
        else{
            System.out.printf("Arvore possui %d elementos: ",ContaNos(r)); //se usar texto e var precisa usar o System.out.printf
            ImprimeArvore(r, 0);
        }
    }

    public static void ImprimeArvore(Tno r, int n){
        if(r!=null){
            ImprimeArvore(r.esq, n+1);

            for(int c=0;c<n;c++)
                System.out.println(" ");
            System.out.printf("%d", r.valor);
            if(r.qtnd>1)
                System.out.printf(" [%d]", r.qtnd);
            System.out.printf("%n");
            ImprimeArvore(r.dir, n+1);
        }
    }

    public static boolean InclueItem(Tno r, int n){
        Tno aux = new Tno();
        aux.valor=n;
        aux.qtnd=1;
        
        Tno pai = AchaPai(r, n);
        if(pai==null)
            return false;
        
        aux.Pai = pai;
        if(n<=pai.valor)
            pai.esq=aux;
        else
            pai.dir=aux;
        return true;

    }

        public static Tno PesquisaValor(Tno r, int n){
            if(r==null || r.valor==0)
                return r;
            else{
                if(n<r.valor)
                    return PesquisaValor(r.esq, n);
                else
                    return PesquisaValor(r.dir, n);
                    
            }
        }

        public static void ImprimeAncestrais(Tno r){
            if(r==null)
                System.out.println("Raiz");
            else{
                if(r.qtnd>1)
                    System.out.printf("%d [%d] <- ", r.valor, r.qtnd);
                else
                    System.err.printf("%d <- ", r.valor);
                ImprimeAncestrais(r.Pai);
            }
        }

        public static void main(String[] args) {
            Tno No, raiz = null;
            try(Scanner entrada =  new Scanner(System.in)){
                while (true) { 
                
                    int numero = entrada.nextInt();
                    if(numero==-999)
                        break;
                    No=PesquisaValor(raiz, numero);
                    if(No!=null)
                        No.qtnd++;
                    else
                        if(InclueItem(raiz, numero)){
                            System.out.println("Memoria insuficinete");
                            entrada.close();
                            return;
                    }
                }
                Impressao(raiz);

                while(true){
                    System.out.println("Informe ovalor: ");
                    int valor = entrada.nextInt();
                    if(valor==-999)
                        break;
                    No=PesquisaValor(raiz, valor);
                    if(No==null)
                        System.out.println("Valor nao encontrado");
                    else{
                        System.out.printf("Ancestrais do no %d: ", valor);
                        ImprimeAncestrais(No);
                        System.out.printf("%n");
                    }
                }
                entrada.close();
            }
        }
}