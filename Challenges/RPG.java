import java.util.Random;
import java.util.Scanner;

public class RPG{

    public static class BaseStats{
        private String originName;
        private double maxLife, armor, life;
        private int damage;
    }

    public static BaseStats character(int classChoice){
        BaseStats playerNow = new BaseStats();

        switch(classChoice){
            case 10->{
                playerNow.originName= "Warrior";
                playerNow.maxLife=100.0;
                playerNow.life=playerNow.maxLife;
                playerNow.armor=0.7;
                playerNow.damage=50;
            }
            case 20->{
                playerNow.originName="Archer";
                playerNow.maxLife=70.0;
                playerNow.life=playerNow.maxLife;
                playerNow.armor=0.5;
                playerNow.damage=70;
            }
            case 30->{
                playerNow.originName="Mage";
                playerNow.maxLife=50.0;
                playerNow.life=playerNow.maxLife;
                playerNow.armor=0.3;
                playerNow.damage=85;
            }
        }

        return playerNow;
    }

    public static BaseStats enemy(int encouter){
        BaseStats monsterNow = new BaseStats();
        
        switch (encouter) {
            case 0->{
                monsterNow.originName="wolf";
                monsterNow.life=40.0;
                monsterNow.armor=0.2;
                monsterNow.damage=30;
            }
            case 1->{
                monsterNow.originName="slime";
                monsterNow.life=15.0;
                monsterNow.armor=0.1;
                monsterNow.damage=20;
            }
            case 2->{
                monsterNow.originName="orc";
                monsterNow.life=80.0;
                monsterNow.armor=0.8;
                monsterNow.damage=40;
            }
            case 3->{
                monsterNow.originName="elf";
                monsterNow.life=90.0;
                monsterNow.armor=0.2;
                monsterNow.damage=60;
            }
            case 4->{
                monsterNow.originName="bear";
                monsterNow.life=100.0;
                monsterNow.armor=0.5;
                monsterNow.damage=65;
            }
            case 5->{
                monsterNow.originName="witcher";
                monsterNow.life=40.0;
                monsterNow.armor=0.2;
                monsterNow.damage=45;
            }
        }
        return monsterNow;
    }

    public record endTurn(BaseStats player, BaseStats enemy){}

    public static endTurn fight(BaseStats player, BaseStats enemy, boolean playerTurn, boolean monsterTurn){
        if(playerTurn && player.life>0){
            double damage = player.damage;
            if(dodge())
                damage=0;
            if(crit())
                damage=damage*2;
            enemy.life=enemy.life-(damage*(1-enemy.armor));
        }
        if(monsterTurn && enemy.life>0){
            int damage=enemy.damage;
             if(dodge())
                damage=0;
            if(crit())
                damage=damage*2;
            player.life=player.life-(damage*(1-enemy.armor));
        }       
            return new endTurn(player, enemy);
    }

   public static boolean dodge() {
        return new Random().nextInt(5) == 1;
    }

    public static boolean crit() {
        return new Random().nextInt(5) == 1;
    }

    public static double rest(BaseStats jogador){
        jogador.life=Math.min(jogador.life+(jogador.life/2), jogador.maxLife);
        return jogador.life;
    }

    public static void main(String[] args) {
        System.out.println("Bem vindo a sua aventura herói");
        System.out.println("--Voce deve passar pela floresta e sobreviver para continuar sua jornada--");
        System.out.println("Para comecar digite [y], para sair digite [n]");
        Random rngMain = new Random();
        BaseStats inimigo;
        BaseStats jogador;
        try(Scanner entrada = new Scanner(System.in)){
            String opcoes = entrada.next();
            
            if(opcoes.equalsIgnoreCase("y")){
                System.out.println("Qual classe deseja escolher:[10][warrior]  [20][mage]  [30][archer]\n");
                int classes=entrada.nextInt();
                entrada.nextLine();
                jogador = character(classes);
                
                for(int i=0;i<10;i++){
                    int encontro = rngMain.nextInt(6);
                    inimigo = enemy(encontro);
                    System.out.printf("Voce andou %d0 metros, Voce encontrou um %s, a luta se inicia: \n", i+1, inimigo.originName);
                    while(inimigo.life>0 && jogador.life>0){
                        boolean turnoJogador = rngMain.nextBoolean();
                        if(turnoJogador){
                            fight(jogador, inimigo, true, false);
                            System.out.printf("O heroi ataca %s deixando-o com %.1f\n", inimigo.originName, inimigo.life);
                        }
                        else{
                            fight(jogador, inimigo, false, true);
                            System.out.printf("\nO heroi é atacado por %s e fica com %.1f\n", inimigo.originName, jogador.life);
                        }
                        try { Thread.sleep(800); } catch (InterruptedException e) {} 
                    }
                    if(inimigo.life<=0)
                        System.out.printf("O %s morreu\n", inimigo.originName);
                    if(jogador.life<=0){
                        System.out.println("O HEROI MORREU TENTE NOVAMENTE\n");
                        break;
                    }
                    System.out.printf("O heroi esta com %.1f/%.1f pv\n", jogador.life, jogador.maxLife);
                    if(jogador.life>0){
                        System.out.println("Deseja descansar [1]SIM  [2]NAO");
                        int restJogador = entrada.nextInt();
                        entrada.nextLine();
                        if(restJogador==1){
                           jogador.life= rest(jogador);
                        }
                    }
                }
                if(jogador.life>0)
                    System.out.println("O heroi completou sua jornada parabens!!\n");
            }
            System.out.println("jogo encerrado\n");
        }
    }
}

