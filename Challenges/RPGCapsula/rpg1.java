import java.util.Random;
import java.util.Scanner;

public class rpg1 {

    public static class BaseStats {
        private String originName;
        private double maxLife, armor, life;
        private int damage;

        // CONSTRUTOR
        public BaseStats(String name, double maxLife, double armor, int damage) {
            this.originName = name;
            this.maxLife = maxLife;
            this.life = maxLife;
            this.armor = armor;
            this.damage = damage;
        }

        // --- GETTERS E SETTERS DEVEM FICAR DENTRO DA CLASSE BASESTATS ---
        public String getOriginName() { return originName; }
        public double getMaxLife() { return maxLife; }
        public double getLife() { return life; }
        public double getArmor() { return armor; }
        public int getDamage() { return damage; }

        public void setLife(double life) {
            if (life > this.maxLife) this.life = this.maxLife;
            else if (life < 0) this.life = 0;
            else this.life = life;
        }
    }

    // Métodos de criação
    public static BaseStats character(int classChoice) {
        return switch (classChoice) {
            case 10 -> new BaseStats("Warrior", 100.0, 0.7, 50);
            case 20 -> new BaseStats("Archer", 70.0, 0.5, 70);
            case 30 -> new BaseStats("Mage", 50.0, 0.3, 85);
            default -> null;
        };
    }

    public static BaseStats enemy(int encouter) {
        return switch (encouter) {
            case 0 -> new BaseStats("Wolf", 40.0, 0.2, 30);
            case 1 -> new BaseStats("Slime", 15.0, 0.0, 20);
            case 2 -> new BaseStats("Orc", 80.0, 0.8, 40);
            case 3 -> new BaseStats("Elf", 90.0, 0.2, 60);
            case 4 -> new BaseStats("Bear", 100.0, 0.5, 65);
            case 5 -> new BaseStats("Witcher", 40.0, 0.2, 45);
            default -> new BaseStats("Goblin", 20.0, 0.1, 10);
        };
    }

    public record endTurn(BaseStats player, BaseStats enemy) {}

    public static endTurn fight(BaseStats player, BaseStats enemy, boolean playerTurn, boolean monsterTurn) {
        if (playerTurn && player.getLife() > 0) {
            double damage = player.getDamage();
            if (dodge()) damage = 0;
            if (crit()) damage = damage * 2;
            
            // O dano é reduzido pela armadura do inimigo
            enemy.setLife(enemy.getLife() - (damage * (1 - enemy.getArmor())));
        }
        if (monsterTurn && enemy.getLife() > 0) {
            double damage = enemy.getDamage();
            if (dodge()) damage = 0;
            if (crit()) damage = damage * 2;
            
            player.setLife(player.getLife() - (damage * (1 - player.getArmor())));
        }
        return new endTurn(player, enemy);
    }

    public static boolean dodge() {
        return new Random().nextInt(10) == 1; // 10% de chance
    }

    public static boolean crit() {
        return new Random().nextInt(10) == 1; // 10% de chance
    }

    public static void rest(BaseStats jogador) {
        jogador.setLife(jogador.getLife() + (jogador.getMaxLife() / 2));
    }

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Random rng = new Random();

        System.out.println("--- BEM VINDO À FLORESTA PERIGOSA ---");
        System.out.print("Deseja iniciar sua jornada? [y/n]: ");

        if (!entrada.next().equalsIgnoreCase("y")) {
            System.out.println("O herói fugiu!");
            return;
        }

        System.out.println("Escolha: [10]Warrior [20]Archer [30]Mage");
        BaseStats jogador = character(entrada.nextInt());

        if (jogador == null) {
            System.out.println("Classe inválida!");
            return;
        }

        for (int i = 1; i <= 10 && jogador.getLife() > 0; i++) {
            BaseStats inimigo = enemy(rng.nextInt(6));
            System.out.printf("\n--- FASE %d: Um %s apareceu! ---\n", i, inimigo.getOriginName());

            while (inimigo.getLife() > 0 && jogador.getLife() > 0) {
                if (rng.nextBoolean()) {
                    fight(jogador, inimigo, true, false);
                    System.out.printf("> Você atacou! %s vida: %.1f\n", inimigo.getOriginName(), inimigo.getLife());
                } else {
                    fight(jogador, inimigo, false, true);
                    System.out.printf("> %s atacou! Sua vida: %.1f\n", inimigo.getOriginName(), jogador.getLife());
                }
                try { Thread.sleep(400); } catch (Exception e) {}
            }

            if (jogador.getLife() <= 0) {
                System.out.println("Você morreu...");
                break;
            }

            System.out.println("Vitória! Descansar? [1]Sim [2]Não");
            if (entrada.nextInt() == 1) {
                rest(jogador);
                System.out.printf("Vida atual: %.1f/%.1f\n", jogador.getLife(), jogador.getMaxLife());
            }
        }
        
        if(jogador.getLife() > 0) System.out.println("VOCÊ VENCEU O JOGO!");
        entrada.close();
    }
}