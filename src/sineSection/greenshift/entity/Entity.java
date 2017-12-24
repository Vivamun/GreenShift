package sineSection.greenshift.entity;

public abstract class Entity {
	//Constants
	private static final int MIN_HEALTH = 0;
	
	//Immutable Fields
	private final int maxHealth;
	
	//Mutable fields
	private int health;
	
	public Entity(int maxHealth) {
		this.maxHealth = maxHealth;
		this.health = maxHealth;
	}
	
	public void heal(int heal) {
		health += Math.max(0,heal);
		if(health > maxHealth) health = maxHealth;
	}
	
	public void damage(int dmg) {
		health -= Math.max(0,dmg);
		if(health <= MIN_HEALTH) health = MIN_HEALTH;
	}
	
	public int getCurrentHealth() {
		return health;
	}
}
