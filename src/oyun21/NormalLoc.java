// Group Members:
// 1. Sueda Esra Akbulut - 220611007
// 2. Ümmügülsün Türkmen - 230611056
// 3. Ayşenur Otaran - 220611034
// 4. Büşra Demirel - 220611029
// 5. Şeyma Akın - 220611012
package oyun21;

public abstract class NormalLoc extends Location {
	public NormalLoc(Player player, String locName) {
		super(player);
		this.locName= locName;
	}
	@Override
	public boolean getLoc(){
		return true;
	}

}
