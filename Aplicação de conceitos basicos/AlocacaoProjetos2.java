
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class AlocacaoProjetos2{

    abstract static class Profissional{

        private final int idProf;
        private final String nome;
        private double valHora;

        private static final Set<Integer> idsDisp = new HashSet<>();
        //static Random geracao = new Random();

        public Profissional (int idProf, String nome, double valHora){
            this.idProf = idProf;
            idsDisp.add(idProf);
            this.nome=nome;
            this.valHora=valHora;
        }

        /*private static int GenUnicId(){
            int novoId;
            if(idsDisp.size()>=10){
                throw new RuntimeException("Numero maximo de funcionarios atingidos");
            }
            do{
               novoId=geracao.nextInt(100);
            }while(idsDisp.contains(novoId));
            idsDisp.add(novoId);
            return novoId;
        }*/
        public static int getQtndCadastrado(){
            return idsDisp.size();
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
        private final int idProj;
        private final String nomeProj;
        private  final Gerente gerenteResp;
        private  final List<Dev> equipeDevs;

        private static final Set<Integer> idsDisp = new HashSet<>();
        //static Random geracao = new Random();


        /*private static int GenProjId(){
            int novoId;
            if(idsDisp.size()>=10){
                throw new RuntimeException("Numero maximo de funcionarios atingidos");
            }
            do{
               novoId=geracao.nextInt(100);
            }while(idsDisp.contains(novoId));
            idsDisp.add(novoId);
            return novoId;
        }*/

        public Projeto(int idProj, String nomeProjeto, Gerente gerente){
            this.idProj=idProj;
            idsDisp.add(idProj);
            this.nomeProj=nomeProjeto;
            this.gerenteResp=gerente;
            this.equipeDevs= new ArrayList<>();
        }

        public void addDev(Dev dev){
            this.equipeDevs.add(dev);
        }
        public static boolean IdExiste(int id){
            return idsDisp.contains(id);
        }
        public double calcularCustoTotalProjeto(int horasMes) {
            double total = gerenteResp.calcCustoMensal(horasMes);
    
            for (Dev d : equipeDevs) {
                total += d.calcCustoMensal(horasMes);
            }
    
            return total;
        }

        public int getIdProj() {return idProj;}
        public String getNomeProj() {return nomeProj;}
        public Gerente getGerenteResp() {return gerenteResp;}
        public List<Dev> getEquipeDevs(){return equipeDevs;}
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
        int opcao;
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
                    case 1 -> {
                        int cadastrados = Profissional.getQtndCadastrado();
                        int limite = 10;
    
                        if (cadastrados >= limite) {
                            System.out.println("\n[ERRO] Limite de 10 funcionários atingido. Remova alguém ou reinicie o sistema.");
                        } else {
                        int espacoDisponivel = limite - cadastrados;
                        System.out.println("\nEspaço disponível para novos cadastros: " + espacoDisponivel);
                        System.out.print("Quantos funcionários deseja adicionar agora? ");
        
                        int qtndFunc = entrada.nextInt();
                        entrada.nextLine();

                        if (qtndFunc > espacoDisponivel || qtndFunc <= 0) {
                            System.out.println("[ERRO] Quantidade inválida. Você só pode cadastrar mais " + espacoDisponivel + ".");
                        } else {
                            for (int i = 0; i < qtndFunc; i++) {
                                System.out.println("\n--- Cadastro do Funcionário " + (i + 1) + " ---");
                                int tipofunc;
                                do {
                                    System.out.print("Tipo: Dev [1] ou Gerente [2]: ");
                                    tipofunc = entrada.nextInt();
                                    entrada.nextLine();
                                    if (tipofunc != 1 && tipofunc != 2) System.out.println("Opção inválida!");
                                } while (tipofunc != 1 && tipofunc != 2);

                                int idFunci;
                                do {
                                    System.out.print("Digite o ID desejado: ");
                                    idFunci = entrada.nextInt();
                                    entrada.nextLine();
                    
                                    if (Profissional.IdExiste(idFunci)) {
                                        System.out.println("Este ID já está em uso! Tente outro.");
                                    }
                                } while (Profissional.IdExiste(idFunci));

                                System.out.print("Nome: ");
                                String nomeFunci = entrada.nextLine();

                                System.out.print("Valor/Hora: ");
                                double salHora = entrada.nextDouble();
                                entrada.nextLine();

                                if (tipofunc == 1) {
                                    ListaFuncionarios.add(new Dev(idFunci, nomeFunci, salHora));
                                    System.out.println("Dev cadastrado com sucesso!");
                                } else {
                                    ListaFuncionarios.add(new Gerente(idFunci, nomeFunci, salHora));
                                    System.out.println("Gerente cadastrado com sucesso!");
                                }
                            }
                        }
                        }
                    }
                    case 2->{
                        System.out.println("Deseja cadastrar quantos projetos: ");
                        int qtndProj=entrada.nextInt();
                        entrada.nextLine();
                        for(int i=0;i<qtndProj;i++){
                            
                            System.out.println("ID do Gerente Responsável:");
                            int idG = entrada.nextInt();
                            entrada.nextLine();
                            Gerente gSelecionado = null;

                            for (Profissional p : ListaFuncionarios) {
                                if (p.getIdProf() == idG && p instanceof Gerente) {
                                gSelecionado = (Gerente) p;
                                break;
                                }
                            }   

                            if (gSelecionado == null) {
                                System.out.println("Erro: Gerente não encontrado ou ID pertence a um Dev.");
                            } else {
                                System.out.println("ID do Projeto:");
                                int idProj = entrada.nextInt();
                                entrada.nextLine();
                                System.out.println("Nome do Projeto:");
                                String nomeP = entrada.nextLine();
        
                                Projeto novoProj = new Projeto(idProj, nomeP, gSelecionado);
        
                                String continuar;
                                do {
                                    System.out.println("Deseja adicionar um Desenvolvedor a este projeto? [s/n]");
                                    continuar = entrada.next();
            
                                    if (continuar.equalsIgnoreCase("s")) {
                                        System.out.println("Digite o ID do Dev:");
                                        int idD = entrada.nextInt();
                                        Dev dSelecionado = null;
                
                                        for (Profissional p : ListaFuncionarios) {
                                            if (p.getIdProf() == idD && p instanceof Dev) {
                                                dSelecionado = (Dev) p;
                                                break;
                                            }
                                        }
                
                                        if(dSelecionado != null) {
                                            novoProj.addDev(dSelecionado);
                                            System.out.println("Dev " + dSelecionado.getNome() + " adicionado!");
                                        } else {
                                        System.out.println("ID inválido ou profissional não é um Dev.");
                                        }
                                    }   
                                } while (continuar.equalsIgnoreCase("s"));

                                ListaProjetos.add(novoProj);
                            }     
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
                        if(ListaProjetos.isEmpty()){System.out.println("Nenhum projeto cadastrado");}
                        else{
                            System.out.println("Informe a carga horaria mensal: ");
                            int horaMes = entrada.nextInt();
                            entrada.nextLine();
                            for (Projeto proj : ListaProjetos) {
                                System.out.println("PROJETO: " + proj.getNomeProj());
                                System.out.println("LIDERANÇA: " + proj.getGerenteResp().getNome());
        
                                System.out.print("EQUIPE: ");
                                if (proj.getEquipeDevs().isEmpty()) {
                                    System.out.print("Nenhum dev alocado.");
                                } else {
                                    for (Dev d : proj.getEquipeDevs()) {
                                    System.out.print(d.getNome() + " | ");
                                    }
                                }
                            System.out.println("\nCUSTO TOTAL: R$ " + proj.calcularCustoTotalProjeto(horaMes));
                            System.out.println("-----------------------------------");
                            }
                        }
                    }
                    case 0->{System.out.println("Saindo...");}
                    default->{System.out.println("Opcao invalida, tente numeros de 0 a 4");}
                    }
            }while(opcao!=0);
        }
    }
}
    
