import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Salario {
    abstract static class Func {
        private final int id;
        private final String nome;
        private final String cargo;
        private final double salario;

        private static final Set<Integer> IDs_EXISTENTES = new HashSet<>();
        private static final Random genRandom = new Random();

        public Func(String nome, String cargo, double salario) {
            this.id = genUnicId(); 
            this.nome = nome;
            this.cargo = cargo;
            this.salario = salario;
        }

        private static int genUnicId() {
            if (IDs_EXISTENTES.size() >= 10) {
                throw new RuntimeException("Limite de funcionários excedido (máx 10)");
            }

            int novoId;
            do {
                novoId = genRandom.nextInt(10);
            } while (IDs_EXISTENTES.contains(novoId));

            IDs_EXISTENTES.add(novoId);
            return novoId;
        }
        public abstract double calcSalFinal();

        public int getId() { return id; }
        public String getNome() {return nome;}
        public String getCargo() {return  cargo;}
        public double getSalario() {return salario;}
    }
    
    static class FuncCDoc extends Func{
        public FuncCDoc (String nome, String cargo, double  salario){
            super(nome, cargo, salario);
        }
        @Override
        public double calcSalFinal(){
            return this.getSalario()*8;
        }
    }
    static class FuncAdm extends Func{
        public FuncAdm (String nome, String cargo, double  salario){
            super(nome, cargo, salario);
        }
        @Override
        public double calcSalFinal(){
            return this.getSalario();
        }
    }

    public static void main(String[] args) { 
        List<Func> ListaFuncionarios = new ArrayList<>();
        int defCargo;
        System.out.println("Quantos funcionarios deseja cadastrar");
        try(Scanner entrada = new Scanner(System.in)){
            int qtndFunc = entrada.nextInt();
            entrada.nextLine();
            for(int i=0;i<qtndFunc;i++){
                System.out.println("Cadastro do funcionario "+(i+1));
                
                do{
                System.out.println("Sera um cargo administrativo[1] ou corpo docente[2]:");
                defCargo = entrada.nextInt();
                entrada.nextLine();
                if(defCargo!=1 && defCargo!=2){System.out.println("Setor nao cadastrado ou nao exixtente");}
                }while(defCargo!=1 && defCargo!=2);

                System.out.println("Digite o nome do funcionario:");
                String nomeFunci = entrada.nextLine();

                System.out.println("Digite o cargo:");
                String cargoFunci = entrada.nextLine();

                System.out.println("Digite o salario ou salario/hora:");
                double salario = entrada.nextDouble();
                entrada.nextLine();

                switch (defCargo) {
                    case 1->{
                    ListaFuncionarios.add(new FuncAdm(nomeFunci, cargoFunci, salario));
                    break;
                    }
                    case 2->{
                    ListaFuncionarios.add(new FuncCDoc(nomeFunci, cargoFunci, salario));
                    break;
                    }
                }
            }
            System.out.println("Relatorio de pagamentos");
            for(Func f : ListaFuncionarios){
                System.out.println("ID: "+ f.getId()+" Nome: " + f.getNome()+" Slario: "+ f.calcSalFinal());
                try { Thread.sleep(400); } catch (InterruptedException e) {}
            }
        }

    }
}
