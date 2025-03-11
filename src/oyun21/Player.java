// Group Members:
// 1. Sueda Esra Akbulut - 220611007
// 2. Ümmügülsün Türkmen - 230611056
// 3. Ayşenur Otaran - 220611034
// 4. Büşra Demirel - 220611029
// 5. Şeyma Akın - 220611012
package oyun21;

import java.io.Serializable;

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;

	Game game;
	private String Name,cName,Id;
	private int Damage,Healthy,money,rHealthy,score;
	private boolean inSafeHouse; 
	private Inventory inventory;
	
	   private Game gameInstance; 
	   
	public Player(String name) {
		this.Name=name;
		this.inventory= new Inventory();
		 this.inSafeHouse = false; 
		 this.money=200;
	}
	public void setCharacter(String cName, int healthy, int damage , int money) {
		setcName(cName);
		setDamage(damage);
		setHealthy(healthy);
		setMoney(money);
		setrHealthy(healthy);
	}
	 
    public void increaseAttack(int attackBonus) {
        this.Damage += attackBonus;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore(int amount) {
        this.score += amount;
    }

    public Inventory getInventory() {
        return inventory;
    }
 // Setter for game instance
    public void setGameInstance(Game gameInstance) {
        this.gameInstance = gameInstance;
    }

    // Getter for game instance
    public Game getGameInstance() {
        return this.gameInstance;
    }

    // Add reward method
    public void addReward(String item, int score, int money) {
        this.score += score;
        this.money += money;
        if (!item.isEmpty()) {
            this.inventory.addItem(item); //add to inventory
        }
    }
 
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		this.Name=name;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public int getDamage() {
		return Damage;
	}
	public void setDamage(int damage) {
		Damage = damage;
	}
	public int getHealthy() {
		
		return Healthy;
	}
	public void setHealthy(int healthy) {
	   this.Healthy = Math.max(healthy, 0); 
	
				}
	// Player sınıfında
	public void setMoney(int money) {
	    this.money = money; 
	}

	public int getMoney() {
	    return this.money;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
		}
	public int getrHealthy() {
		return rHealthy;
	}
	public void setrHealthy(int rHealthy) {
		this.rHealthy = rHealthy;
	}
	public int getTotalDamage() {
		return this.getDamage()+this.getInventory().getDamage();

	} 
	public void reduceHealth(int damage) {
	    this.Healthy = Math.max(this.Healthy - damage, 0); 
	}
	 public void resetHealth() {
	        if (this.isInSafeHouse()) {
	            this.setHealthy(this.getrHealthy()); 
	        }
	    }
	public boolean isInSafeHouse() {
        return inSafeHouse;
    }

    public void setInSafeHouse(boolean inSafeHouse) {
        this.inSafeHouse = inSafeHouse;
    }
	
    public void enterSafeHouse() {
        this.setInSafeHouse(true);
        this.setHealthy(this.getrHealthy()); 
    }

    private String characterClass;

    public String getCharacterClass() {
        return characterClass;
    }


    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }
}

