// Group Members:
// 1. Sueda Esra Akbulut - 220611007
// 2. Ümmügülsün Türkmen - 230611056
// 3. Ayşenur Otaran - 220611034
// 4. Büşra Demirel - 220611029
// 5. Şeyma Akın - 220611012
package oyun21;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class BattleScreen extends JFrame {
    private Player player;
    private Obstacles obstacles;
    private String award;
    private JLabel enemyLabel;
    private JTextArea battleLog;
    private JProgressBar playerHealthBar, obstacleHealthBar;
    private Timer enemyMovementTimer;
    private int enemyX, enemyY;
    private int enemySpeedX, enemySpeedY;
    private Random random = new Random();
    private String backgroundPath;
    private Game game;

    public BattleScreen(Player player, Obstacles obstacles, String award, Game game, String backgroundPath) {
        this.player = player;
        this.obstacles = obstacles;
        this.award = award;
        this.game = game;
        this.backgroundPath = backgroundPath; // Seviyeye özgü arka plan
        setTitle("Battle Arena");
        setSize(800, 600);
        setLayout(new BorderLayout());
        createBattlePanel();
        createControlPanel();
        initEnemyMovement();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public boolean isBattleWon() {
       
        return obstacles.allObstaclesDefeated() && player.getHealthy() > 0;
    }

    private void performAttack() {
        int defeatedObstacles = obstacles.getInitialObstacleCount() - obstacles.obstacleCount();
        System.out.println("Defeated Obstacles: " + defeatedObstacles);
        System.out.println("canavar kalan can " + obstacles.getCurrentHealth());
        System.out.println("vurus " + player.getTotalDamage());
     
        int damageDealt = player.getTotalDamage();
  
        obstacles.reduceHealth(damageDealt);

        battleLog.append("You dealt " + damageDealt + " damage!\n");
        obstacleHealthBar.setValue(obstacles.getCurrentHealth());

     
        int damageTaken = obstacles.getDamage();
        player.reduceHealth(damageTaken);

        battleLog.append("Enemy dealt " + damageTaken + " damage to you!\n");
        playerHealthBar.setValue(player.getHealthy());

        
        if (player.getHealthy() <= 0) {
            JOptionPane.showMessageDialog(this, "You were defeated!");
            dispose(); 
            return;
        }

       
        if (obstacles.allObstaclesDefeated()) {
            int scoreReward = defeatedObstacles * 10;
            int moneyReward = defeatedObstacles * 5;

            player.addReward(award, scoreReward, moneyReward);

            

            Game gameInstance = player.getGameInstance();
            if (gameInstance != null) {
                gameInstance.updatePlayerInfo(); 
            }

            dispose(); 
        }
    }
    private void createBattlePanel() {
        JPanel battlePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                Image backgroundImage = new ImageIcon(backgroundPath).getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        battlePanel.setLayout(null);
        battlePanel.setBackground(Color.DARK_GRAY);

        
        ImageIcon enemyIcon = new ImageIcon(obstacles.getEnemyImagePath());
        if (enemyIcon.getIconWidth() == -1) {
            JOptionPane.showMessageDialog(this, "Enemy image not found: " + obstacles.getEnemyImagePath());
            return;
        }
        Image scaledImage = enemyIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        enemyLabel = new JLabel(new ImageIcon(scaledImage));
        enemyX = random.nextInt(600);
        enemyY = random.nextInt(300);
        enemyLabel.setBounds(enemyX, enemyY, 120, 120);
        enemyLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                performAttack();
            }
        });
        battlePanel.add(enemyLabel);
        add(battlePanel, BorderLayout.CENTER);
    }
    private void createControlPanel() {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBackground(Color.LIGHT_GRAY);

        playerHealthBar = createHealthBar(player.getHealthy(), Color.GREEN);
        obstacleHealthBar = createHealthBar(obstacles.getCurrentHealth(), Color.RED);

        battleLog = new JTextArea(5, 30);
        battleLog.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(battleLog);

        JButton defendButton = new JButton("Defend");
        JButton exitButton = new JButton("Run Away");

        defendButton.addActionListener(e -> appendToBattleLog("You defended!"));
        exitButton.addActionListener(e -> dispose());

        controlPanel.add(new JLabel("Player Health:"));
        controlPanel.add(playerHealthBar);
        controlPanel.add(new JLabel("Enemy Health:"));
        controlPanel.add(obstacleHealthBar);
        controlPanel.add(scrollPane);
        controlPanel.add(defendButton);
        controlPanel.add(exitButton);

        add(controlPanel, BorderLayout.SOUTH);
    }

    private JProgressBar createHealthBar(int maxHealth, Color color) {
        JProgressBar healthBar = new JProgressBar(0, maxHealth);
        healthBar.setValue(maxHealth);
        healthBar.setStringPainted(true);
        healthBar.setForeground(color);
        return healthBar;
    }

    private void initEnemyMovement() {
        enemySpeedX = random.nextInt(5) + 1;
        enemySpeedY = random.nextInt(5) + 1;

        enemyMovementTimer = new Timer(50, e -> {
            enemyX += enemySpeedX;
            enemyY += enemySpeedY;

            if (enemyX < 0 || enemyX + enemyLabel.getWidth() > getWidth()) {
                enemySpeedX = -enemySpeedX;
            }
            if (enemyY < 0 || enemyY + enemyLabel.getHeight() > getHeight()) {
                enemySpeedY = -enemySpeedY;
            }

            enemyLabel.setLocation(enemyX, enemyY);
            repaint();
        });
        enemyMovementTimer.start();
    }

    

    private void appendToBattleLog(String message) {
        battleLog.append(message + "\n");
    }
}
