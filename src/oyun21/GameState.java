// Group Members:
// 1. Sueda Esra Akbulut - 220611007
// 2. Ümmügülsün Türkmen - 230611056
// 3. Ayşenur Otaran - 220611034
// 4. Büşra Demirel - 220611029
// 5. Şeyma Akın - 220611012
package oyun21;

import java.io.Serializable;

public class GameState implements Serializable {
    private static final long serialVersionUID = 1L;

    private Player player;
    private boolean caveCompleted;
    private boolean forestCompleted;
    private boolean riverCompleted;

    public GameState(Player player, boolean caveCompleted, boolean forestCompleted, boolean riverCompleted) {
        this.player = player;
        this.caveCompleted = caveCompleted;
        this.forestCompleted = forestCompleted;
        this.riverCompleted = riverCompleted;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isCaveCompleted() {
        return caveCompleted;
    }

    public boolean isForestCompleted() {
        return forestCompleted;
    }

    public boolean isRiverCompleted() {
        return riverCompleted;
    }
}