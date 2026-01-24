
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class AlocaProj{

    abstract static class Profissional{

        private final int idProf;
        private final String nome;
        private double valHora;

        private static final Set<Integer> idsDisp = new HashSet<>();
        static Random geracao = new Random();

        public Profissional (int idProj, String nome, double valHora){
            this.idProf = GenUnicId();
            this.nome=nome;
            this.valHora=valHora;
        }

        private static int GenUnicId(){
            int novoId;
            if(idsDisp.size()>=10){
                throw new RuntimeException("Numero maximo de funcionarios atingidos");
            }
            do{
               novoId=geracao.nextInt(100);
            }while(idsDisp.contains(novoId));
            idsDisp.add(novoId);
            return novoId;
        }

        public static boolean IdExiste(int id){
            return idsDisp.contains(id);
        }

        public abstract double calcCustoMensal(int valHora);
        public void setValHora(double novoSalario){this.valHora=novoSalario;}

        public int getIdProf() {return idProf;}
        public String getNome() {return nome;}
        public double  getValHora() {return valHora;}
    }

    interface promovivel{
        void promocao(double valAumento);
    }

    static class Dev extends Profissional{
        public Dev(int idProj, String nome, double valHora){
            super(idProj, nome, valHora);}
        @Override
        public double calcCustoMensal(int valHora){
            return this.getValHora()*8;
        }/* 
        @Override
        public void promocao(double valAumento){
            double novoSalario = this.getValHora()*(1+valAumento/100);
            this.setValHora(novoSalario);
            System.out.println("Salario atualizado para: "+novoSalario);
        }*/
    }

    static class Gerente extends Profissional implements promovivel{
         public Gerente(int idProj, String nome, double valHora){
            super(idProj, nome, valHora);}
        @Override
        public double calcCustoMensal(int valHora){
            return (this.getValHora()*8)+1000;
        }
        @Override
        public void promocao(double valAumento){
            double novoSalario = this.getValHora()*(1+valAumento/100);
            this.setValHora(novoSalario);
            System.out.println("Salario atualizado para: "+novoSalario);
        }
    }

    static class Projeto{
        int idProj;
        int idFunc;
        String nomeProj;
        Profissional responsavel;

        private static final Set<Integer> idsDisp = new HashSet<>();
        static Random geracao = new Random();


        private static int GenProjId(){
            int novoId;
            if(idsDisp.size()>=10){
                throw new RuntimeException("Numero maximo de funcionarios atingidos");
            }
            do{
               novoId=geracao.nextInt(100);
            }while(idsDisp.contains(novoId));
            idsDisp.add(novoId);
            return novoId;
        }

        public Projeto(int idFunc, int idBuscaFunc, String nomeProjeto, Profissional responsavel){
            this.idProj=GenProjId();
            this.idFunc=idFunc;
            this.nomeProj=nomeProjeto;
            this.responsavel=responsavel;
        }

        public static boolean IdExiste(int id){
            return idsDisp.contains(id);
        }

        public int getIdProj() {return idProj;}
        public int getIdFunc() {return idFunc;}
        public String getNomeProj() {return nomeProj;}
        public Profissional getResponsavel() {return responsavel;}
    }

    public static void main(String[] args) {
        System.out.println("Sistema iniciando");
        for(int i=0;i<3;i++){
            System.out.println(".");
            System.out.println("..");
            System.out.println("...");
            try { Thread.sleep(400); } catch (InterruptedException e) {}
        }
        List<Profissional> ListaFuncionarios = new ArrayList<>();
        List<Projeto> ListaProjetos = new ArrayList<>();
        int tipofunc, opcao;
        try (Scanner entrada = new Scanner(System.in)) {
            do{
                System.out.println("Sistema de alocacao de projeto");
                System.out.println("1. Cadastrar Profissional (Dev/Gerente)");
                System.out.println("2. Criar Projeto e Associar Responsável");
                System.out.println("3. Aplicar Promoção/Aumento");
                System.out.println("4. Relatório de Custos");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
    
                opcao = entrada.nextInt();
                entrada.nextLine();
                switch(opcao){
                    case 1->{
                        System.out.println("Deseja adicionar quantos funcionarios: ");
                        int qtndFunc= entrada.nextInt();
                        entrada.nextLine();
                        for(int i=0;i<qtndFunc;i++){
                            System.out.println("Cadastro do funcionario "+(i+1));
                            do{
                                System.out.println("Ele(a) sera Dev[1] ou Gerente[2]");
                                tipofunc = entrada.nextInt();
                                entrada.nextLine();
                                if(tipofunc!=1 && tipofunc!=2){System.out.println("Area ou setor nao cadastrado ou nao encontrado");}
                            }while(tipofunc!=1 && tipofunc!=2);

                        int idFunci;
                    do{
                        System.out.println("Digite o id desejado");
                        idFunci= entrada.nextInt();
                        entrada.nextLine();
                        if(Profissional.IdExiste(idFunci)){
                        System.out.println("Id disponivel");
                        }
                        else
                        System.out.println("Id indisponivel tente outra vez");
                    }while(!Profissional.IdExiste(idFunci));

                    System.out.println("Digite o nome do funcionario: ");
                    String nomeFunci = entrada.nextLine();

                    System.out.println("Digite o o valor do salario/hora: ");
                    double salHora = entrada.nextDouble();
                    entrada.nextLine();

                    switch (tipofunc) {
                        case 1->{
                            ListaFuncionarios.add(new Dev(idFunci, nomeFunci, salHora));
                            break;
                        }
                        case 2->{
                            ListaFuncionarios.add(new Gerente(idFunci, nomeFunci, salHora));
                            break;
                        }
                    }
                }
                System.out.println("\n=== Funcionarios Cadastrados ===");
                for(Profissional prof : ListaFuncionarios){
                    System.out.println("ID: "+prof.getIdProf()+" | Nome: "+prof.getNome()+" | Valor/Hora: "+prof.getValHora());
                }
                    }

                case 2->{
                    System.out.println("Deseja cadastrar quantos projetos: ");
                    int qtndProj=entrada.nextInt();
                    entrada.nextLine();
                    int idBuscaFunc, idBuscaProj;
                    Profissional FuncSelecionado=null;
                    for(int i=0;i<qtndProj;i++){
                        do{
                        System.out.println("Digite o ID do Dev/Gerente: ");
                        idBuscaFunc=entrada.nextInt();
                        entrada.nextLine();
                            if(Profissional.IdExiste(idBuscaFunc)){
                                    System.out.println("Id validado");
                                    for(Profissional prof : ListaFuncionarios){
                                            if(prof.getIdProf()==idBuscaFunc){
                                                FuncSelecionado = prof;
                                                System.out.println("Funcionario "+ idBuscaFunc + " : "+prof.getNome());
                                            }  
                                    }       
                            }
                            else
                        System.out.println("Id do funcionario nao encontrado.");
                    }while(!Profissional.IdExiste(idBuscaFunc));

                    do{
                        System.out.println("Agora digite o ID do projeto");
                        idBuscaProj=entrada.nextInt();
                        entrada.nextLine();
                        if(Projeto.IdExiste(idBuscaProj)){
                            System.out.println("ID de projeto valido");
                                for(Projeto proj: ListaProjetos){
                                    if(proj.getIdProj()==idBuscaProj){
                                    System.out.println("Projeto ja existente");
                                    break;
                                }
                            }
                        }
                    }while(!Projeto.IdExiste(idBuscaProj));
                    
                    System.out.println("Agora digite o nome do projeto"); 
                    String nomeProjeto = entrada.nextLine();        
                
                    Projeto novoProj = new Projeto(idBuscaProj, idBuscaFunc, nomeProjeto, FuncSelecionado);
                    ListaProjetos.add(novoProj);

                    System.out.println("Projeto "+ (i+1)+" de ID "+ idBuscaProj+" castrado com sucesso");
                    }
                }
                case 3->{
                    System.out.println("Deseja fazer o aumento de salario de algum funcionario, SIM[y], NAO[n]");
                    if(!entrada.next().equalsIgnoreCase("y")){
                        System.out.println("Operacao encerrada");
                    }
                    else{
                        System.out.println("=== Funcionarios Cadastrados ===");
                        for(Profissional prof : ListaFuncionarios){
                            System.out.println("ID: "+prof.getIdProf()+" | Nome: "+prof.getNome());
                        }
                        System.out.println("Digite o ID do funcionario escolhido:");
                        int funcEscolhido = entrada.nextInt();
                        entrada.nextLine();
                        for(Profissional prof : ListaFuncionarios){
                            if(prof.getIdProf()==funcEscolhido){
                                if(prof instanceof promovivel){
                                    System.out.println("Funcionario elegivel, por favor digite a porcentagem do aumento: ");
                                    double porcentagem = entrada.nextDouble();
                                    entrada.nextLine();

                                    promovivel p = (promovivel) prof;

                                    p.promocao(porcentagem);
                                }
                                else{
                                    System.out.println("Cargo sem permissao de aumento");
                                }
                            break;
                            }
                        }
                    }
                }
                case 4->{
                    System.out.println("Relatorio de projetos");
                    System.out.println("Informe a carga horaria mensal: ");
                    int horaMes = entrada.nextInt();
                    entrada.nextLine();
                    for(Projeto proj : ListaProjetos){
                        Profissional responsavel = proj.getResponsavel();
                        double custoTotal =responsavel.calcCustoMensal(horaMes);

                        System.out.println("Projeto: "+proj.getNomeProj());
                        System.out.println("Responsavel: "+responsavel.getNome());
                        System.out.println("Custo total com :"+horaMes+"h: R$ "+ custoTotal);
                    }
                }
                }
            }while(opcao!=0);
        }
    }
}
//arrumar para 1 gerente e n devs