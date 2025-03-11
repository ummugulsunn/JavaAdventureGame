// Group Members:
// 1. Sueda Esra Akbulut - 220611007
// 2. Ümmügülsün Türkmen - 230611056
// 3. Ayşenur Otaran - 220611034
// 4. Büşra Demirel - 220611029
// 5. Şeyma Akın - 220611012
package oyun21;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Obstacles {
    private String obsName;
    private int damage, award, defObsHealth, maxNum;
    private List<Integer> healthList;
    private String enemyImagePath;
    private int initialObstacleCount;

    private static GameDifficulty difficulty = GameDifficulty.EASY;

    public Obstacles(String obsName, int damage, int health, int award, int maxNum, String enemyImagePath) {
        this.obsName = obsName;

     
        this.damage = calculateDamage(damage);
        this.defObsHealth = calculateHealth(health);
        this.award = award;
        this.maxNum = maxNum;
        this.enemyImagePath = enemyImagePath;

        
        this.initialObstacleCount = calculateInitialObstacleCount();
        initializeEnemies();
    }

    private int calculateDamage(int baseDamage) {
        return difficulty == GameDifficulty.HARD ? baseDamage + 5 : baseDamage;
    }

    private int calculateHealth(int baseHealth) {
        return difficulty == GameDifficulty.HARD ? (int) (baseHealth * 1.2) : baseHealth;
    }
    public int getCurrentHealth() {
        return healthList.isEmpty() ? 0 : healthList.get(0);
    }

    private int calculateInitialObstacleCount() {
        Random random = new Random();
        int baseCount = random.nextInt(maxNum) + 1;

        return (difficulty == GameDifficulty.HARD) 
               ? Math.min(baseCount + (baseCount / 2), maxNum * 2)
               : baseCount;
    }

    private void initializeEnemies() {
        healthList = new ArrayList<>();
        for (int i = 0; i < initialObstacleCount; i++) {
            int enemyHealth = (difficulty == GameDifficulty.HARD)
                              ? (int) (defObsHealth * 1.2) // hard modda enemies have %20 more health
                              : defObsHealth;
            healthList.add(enemyHealth);
        }
    }

    public int obstacleCount() {
        return healthList.size();
    }

    public void reduceHealth(int damage) {
        if (!healthList.isEmpty()) {
            int currentHealth = healthList.get(0) - damage; 
            if (currentHealth <= 0) {
                healthList.remove(0); 
            } else {
                healthList.set(0, currentHealth); 
            }
        } else {
            System.out.println("No enemies left to attack!");
        }
    }
    public boolean allObstaclesDefeated() {
        return healthList.isEmpty();
    }

    public int getInitialObstacleCount() {
        return initialObstacleCount; 
    }

    public String getObsName() {
        return obsName;
    }

    public String getEnemyImagePath() {
        return enemyImagePath;
    }

    public static void setDifficulty(GameDifficulty newDifficulty) {
        difficulty = newDifficulty;
    }
    public int getDamage() {
        return difficulty == GameDifficulty.HARD ? 15 : 10; 
    }
    public static GameDifficulty getDifficulty() {
        return difficulty;
    }
}