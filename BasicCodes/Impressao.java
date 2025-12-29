public class Impressao {

    public static void main(String[] args) {
        byte a=10;
        short b=20;
        int c=30;
        long d=40L; //var long deve ser finalizada com L

        float f=50.0f; //var float deve ser finalizada com f
        double g=60.0;

        char h='A'; //var char deve ser inicializada com aspas simples

        boolean i=true; //var boolean deve ser inicializada com true ou false

        String j="Ola Mundo!"; //var string deve ser inicializada com aspas duplas

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println(f);
        System.out.println(g);
        System.out.println(h);
        System.out.println(i);
        System.out.println(j);
        
        System.out.println("printf: " + a); //usando printf .out é usado para saida normal

        System.err.println("int eh: "+ a); //usando concatenacao .err é usado para erros

        System.out.format("float eh: %.2f%n", f); //usando format pode ser usado \n ou %n para nova linha

        String nome = "Joao ";
        String sobrenome = "Silva";
        String nomeCompleto = nome + sobrenome; //concatenacao de strings, string.format tambem pode ser usado
        System.out.println("Nome completo: " + nomeCompleto);
    }
}
