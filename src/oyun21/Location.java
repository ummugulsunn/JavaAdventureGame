// Group Members:
// 1. Sueda Esra Akbulut - 220611007
// 2. Ümmügülsün Türkmen - 230611056
// 3. Ayşenur Otaran - 220611034
// 4. Büşra Demirel - 220611029
// 5. Şeyma Akın - 220611012
package oyun21;

import java.io.Serializable;

public abstract class Location implements Serializable {
    private static final long serialVersionUID = 1L;

	protected Player player;
	protected String locName;
	Location(Player player){
		this.player=player;
	}
	public abstract boolean getLoc(); // Handles Location's events
	
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public String getLocName() {
		return locName;
	}
	public void setLocName(String locName) {
		this.locName = locName;
	}

}

