// Group Members:
// 1. Sueda Esra Akbulut - 220611007
// 2. Ümmügülsün Türkmen - 230611056
// 3. Ayşenur Otaran - 220611034
// 4. Büşra Demirel - 220611029
// 5. Şeyma Akın - 220611012

package oyun21;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public abstract class BattleLoc extends Location {
    protected Obstacles obstacles;
    protected String award;
    private Game game; 

    public BattleLoc(Player player, String locName, Obstacles obstacles, String award, Game game) {
        super(player);
        this.locName = locName;
        this.obstacles = obstacles;
        this.award = award;
        this.game = game; 
    }


    @Override
    public boolean getLoc() {
        if (obstacles == null || obstacles.obstacleCount() == 0) {
            JOptionPane.showMessageDialog(null, "No obstacles found in " + this.getLocName());
            return false;
        }

        JOptionPane.showMessageDialog(null, "<html>You are in the " + this.getLocName() + "<br>"
                + "Be careful, there are " + obstacles.obstacleCount() + " " + obstacles.getObsName() + "</html>");

        BattleScreen battleScreen;
        try {
            String backgroundPath = ""; // default
            if (locName.equalsIgnoreCase("Cave")) {
                backgroundPath = "src/image/cave.jpg";
            } else if (locName.equalsIgnoreCase("River")) {
                backgroundPath = "src/image/river.jpg";
            } else if (locName.equalsIgnoreCase("Forest")) {
                backgroundPath = "src/image/forest.jpg";
            }
            battleScreen = new BattleScreen(player, obstacles, award, this.game, backgroundPath);
            battleScreen.setLocationRelativeTo(null);
            battleScreen.setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error initializing battle screen: " + ex.getMessage());
            return false;
        }
        

        Timer timer = new Timer(100, e -> {
            try {
                if (!battleScreen.isShowing()) {
                    ((Timer) e.getSource()).stop();

                    if (battleScreen.isBattleWon()) {
                        int defeatedObstacles = obstacles.getInitialObstacleCount() - obstacles.obstacleCount();
                        int scoreReward = defeatedObstacles * 10;
                        int moneyReward = defeatedObstacles * 5;

                        player.addReward(this.award, scoreReward, moneyReward);

                        JOptionPane.showMessageDialog(null, "Congratulations! " + this.getLocName()
                                + " Completed!\nYou earned:\n- Item: " + this.award
                                + "\n- Score: " + scoreReward
                                + "\n- Money: $" + moneyReward);

                        game.updatePlayerInfo(); 
                    } else {
                        JOptionPane.showMessageDialog(null, "You failed to complete " + this.getLocName());
                    }
                }
            } catch (Exception ex) {
                ((Timer) e.getSource()).stop();
                JOptionPane.showMessageDialog(null, "An error occurred during the battle: " + ex.getMessage());
            }
        });
        timer.start();
        return true;
    }
}