import java.util.*;

/**---------------------------------------------------

 * Akdeniz University CSE102L Assignments
 * author: Yahya Efe Kuruçay
 * since: 07.04.2024
 * Description: Homework04
 * Score: ?
 * Website: https://efekurucay.com
*---------------------------------------------------*/
/***
 *    ███████ ███████ ███████ 
 *    ██      ██      ██      
 *    █████   █████   █████   
 *    ██      ██      ██      
 *    ███████ ██      ███████ 
 *                            
 *                            
 */
// Interfaces
interface Damageable {
    void takeDamage(int damage);
    void takeHealing(int healing);
    boolean isAlive();
}

interface Caster {
    void castSpell(Damageable target);
    void learnSpell(Spell spell);
}

interface Combat extends Damageable {
    void attack(Damageable target);
    void lootWeapon(Weapon weapon);
}

interface Useable {
    int use();
}

// Classes
class Spell implements Useable {
    private int minHeal;
    private int maxHeal;
    private String name;

    public Spell(String name, int minHeal, int maxHeal) {
        this.name = name;
        this.minHeal = minHeal;
        this.maxHeal = maxHeal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int heal() {
        return minHeal + (int) (Math.random() * (maxHeal - minHeal + 1));
    }

    public int use() {
        return heal();
    }
}

class Weapon implements Useable {
    private int minDamage;
    private int maxDamage;
    private String name;

    public Weapon(String name, int minDamage, int maxDamage) {
        this.name = name;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int attack() {
        return minDamage + (int) (Math.random() * (maxDamage - minDamage + 1));
    }

    public int use() {
        return attack();
    }
}

class Attributes {
    private int strength;
    private int intelligence;

    public Attributes() {
        this.strength = 3;
        this.intelligence = 3;
    }

    public Attributes(int strength, int intelligence) {
        this.strength = strength;
        this.intelligence = intelligence;
    }

    public void increaseStrength(int amount) {
        strength += amount;
    }

    public void increaseIntelligence(int amount) {
        intelligence += amount;
    }

    public int getStrength() {
        return strength;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public String toString() {
        return "Attributes [Strength= " + strength + ", intelligence= " + intelligence + "]";
    }
}

abstract class Character implements Comparable<Character>, Damageable {
    protected String name;
    protected int level;
    protected Attributes attributes;
    protected int health;

    public Character(String name, Attributes attributes) {
        this.name = name;
        this.attributes = attributes;
        this.level = 1;
        this.health = 100;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public abstract void levelUp();

    public String toString() {
        return this.getClass().getSimpleName() + " LvL: " + level + " – " + attributes;
    }

    public int compareTo(Character other) {
        return Integer.compare(this.level, other.level);
    }

    public void takeDamage(int damage) {
        health -= damage;
    }

    public void takeHealing(int healing) {
        health += healing;
    }

    public boolean isAlive() {
        return health > 0;
    }
}

abstract class PlayableCharacter extends Character {
    protected boolean inParty;
    protected Party party;

    public PlayableCharacter(String name, Attributes attributes) {
        super(name, attributes);
        this.inParty = false;
        this.party = null;
    }

    public boolean isInParty() {
        return inParty;
    }

    public abstract void levelUp();

    public void joinParty(Party party) {
        if (inParty)
            throw new AlreadyInPartyException("Character is already in a party");
        
        try {
            party.addCharacter(this);
            inParty = true;
            this.party = party;
        } catch (PartyLimitReachedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void quitParty() {
        if (!inParty)
            throw new CharacterIsNotInPartyException("Character is not in any party");
        
        try {
            party.removeCharacter(this);
            inParty = false;
            party = null;
        } catch (CharacterIsNotInPartyException e) {
            System.out.println(e.getMessage());
        }
    }
}

abstract class NonPlayableCharacter extends Character {
    public NonPlayableCharacter(String name, Attributes attributes) {
        super(name, attributes);
    }
}

class Merchant extends NonPlayableCharacter {
    private Collection<Useable> inventory;

    public Merchant(String name) {
        super(name, new Attributes(0, 0));
        inventory = new ArrayList<>();
    }

    public void display() {
        for (Useable item : inventory) {
            System.out.println(item);
        }
    }

    public Useable buy(int itemNumber) throws ItemNotFoundException {
        try {
            List<Useable> itemList = new ArrayList<>(inventory);
            return itemList.get(itemNumber);
        } catch (IndexOutOfBoundsException e) {
            throw new ItemNotFoundException("Item not found");
        }
    }

    public void sell(Useable item) {
        inventory.add(item);
    }

    public void levelUp() {
        // Empty
    }
}

class Skeleton extends NonPlayableCharacter implements Combat {
    public Skeleton(String name, Attributes attributes) {
        super(name, attributes);
    }

    public void lootWeapon(Weapon weapon) {
        // Empty
    }

    public void levelUp() {
        level++;
        attributes.increaseStrength(1);
        attributes.increaseIntelligence(1);
    }

    public void takeHealing(int healing) {
        super.takeDamage(healing);
    }

    public void attack(Damageable target) {
        // Implementation specific to Skeleton attacking logic
    }
}

class Warrior extends PlayableCharacter implements Combat {
    private Useable weapon;

    public Warrior(String name) {
        super(name, new Attributes(4, 2));
        this.health = 35;
    }

    public void levelUp() {
        super.levelUp();
        attributes.increaseStrength(2);
        attributes.increaseIntelligence(1);
    }

    public void lootWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void attack(Damageable target) {
        // Implementation specific to Warrior attacking logic
    }
}

class Cleric extends PlayableCharacter implements Caster {
    private Useable spell;

    public Cleric(String name) {
        super(name, new Attributes(2, 4));
        this.health = 25;
    }

    public void levelUp() {
        super.levelUp();
        attributes.increaseStrength(1);
        attributes.increaseIntelligence(2);
    }

    public void castSpell(Damageable target) {
        // Implementation specific to Cleric casting spell logic
    }

    public void learnSpell(Spell spell) {
        this.spell = spell;
    }
}

class Paladin extends PlayableCharacter implements Combat, Caster {
    private Useable weapon;
    private Useable spell;

    public Paladin(String name) {
        super(name, new Attributes());
        this.health = 30;
    }

    public void levelUp() {
        super.levelUp();
        if (level % 2 == 0) {
            attributes.increaseStrength(2);
            attributes.increaseIntelligence(1);
        } else {
            attributes.increaseStrength(1);
            attributes.increaseIntelligence(2);
        }
    }

    public void lootWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void castSpell(Damageable target) {
        // Implementation specific to Paladin casting spell logic
    }

    public void learnSpell(Spell spell) {
        this.spell = spell;
    }

    public void attack(Damageable target) {
        // Implementation specific to Paladin attacking logic
    }
}

class Party {
    private static final int partyLimit = 8;
    private List<Character> characters;
    private int mixedCount;

    public Party() {
        characters = new ArrayList<>();
    }

    public void addCharacter(Character character) throws PartyLimitReachedException {
        if (characters.size() >= partyLimit) {
            throw new PartyLimitReachedException("Party limit reached");
        }

        characters.add(character);
    }

    public void removeCharacter(Character character) throws CharacterIsNotInPartyException {
        if (!characters.remove(character)) {
            throw new CharacterIsNotInPartyException("Character is not in the party");
        }
    }

    public void partyLevelUp() {
        for (Character character : characters) {
            character.levelUp();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Character character : characters) {
            sb.append(character).append("\n");
        }
        return sb.toString();
    }
}

// Exceptions
class PartyLimitReachedException extends Exception {
    public PartyLimitReachedException(String message) {
        super(message);
    }
}

class AlreadyInPartyException extends Exception {
    public AlreadyInPartyException(String message) {
        super(message);
    }
}

class CharacterIsNotInPartyException extends Exception {
    public CharacterIsNotInPartyException(String message) {
        super(message);
    }
}

class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String message) {
        super(message);
    }
}


public class HW05_20220808005 {
    public static void main(String[] args) {
        // Creating some characters
        Warrior warrior = new Warrior("Warrior1");
        Cleric cleric = new Cleric("Cleric1");
        Paladin paladin = new Paladin("Paladin1");

        // Creating some spells
        Spell spell1 = new Spell("Spell1", 5, 10);
        Spell spell2 = new Spell("Spell2", 8, 15);

        // Learning spells
        cleric.learnSpell(spell1);
        paladin.learnSpell(spell2);

        // Creating a party
        Party party = new Party();

        // Adding characters to the party
        try {
            party.addCharacter(warrior);
            party.addCharacter(cleric);
            party.addCharacter(paladin);
        } catch (PartyLimitReachedException e) {
            System.out.println(e.getMessage());
        }

        // Displaying party information
        System.out.println("Initial party:");
        System.out.println(party);

        // Leveling up the party
        party.partyLevelUp();

        // Displaying party information after leveling up
        System.out.println("Party after leveling up:");
        System.out.println(party);

        // Testing buying and selling with a merchant
        Merchant merchant = new Merchant("Merchant1");
        merchant.sell(spell1);
        merchant.sell(spell2);

        try {
            Useable boughtSpell = merchant.buy(0);
            System.out.println("Bought spell: " + boughtSpell);
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // Testing attacking and casting spells
        Warrior enemyWarrior = new Warrior("EnemyWarrior");
        Skeleton enemySkeleton = new Skeleton("EnemySkeleton", new Attributes());

        warrior.attack(enemyWarrior);
        cleric.castSpell(enemySkeleton);

        // Displaying enemy status
        System.out.println("Enemy warrior health: " + enemyWarrior.health);
        System.out.println("Enemy skeleton health: " + enemySkeleton.health);
    }
}

 /***
 *              _____                    _____                    _____          
 *             /\    \                  /\    \                  /\    \         
 *            /::\    \                /::\    \                /::\    \        
 *           /::::\    \              /::::\    \              /::::\    \       
 *          /::::::\    \            /::::::\    \            /::::::\    \      
 *         /:::/\:::\    \          /:::/\:::\    \          /:::/\:::\    \     
 *        /:::/__\:::\    \        /:::/__\:::\    \        /:::/__\:::\    \    
 *       /::::\   \:::\    \      /::::\   \:::\    \      /::::\   \:::\    \   
 *      /::::::\   \:::\    \    /::::::\   \:::\    \    /::::::\   \:::\    \  
 *     /:::/\:::\   \:::\    \  /:::/\:::\   \:::\    \  /:::/\:::\   \:::\    \ 
 *    /:::/__\:::\   \:::\____\/:::/  \:::\   \:::\____\/:::/__\:::\   \:::\____\
 *    \:::\   \:::\   \::/    /\::/    \:::\   \::/    /\:::\   \:::\   \::/    /
 *     \:::\   \:::\   \/____/  \/____/ \:::\   \/____/  \:::\   \:::\   \/____/ 
 *      \:::\   \:::\    \               \:::\    \       \:::\   \:::\    \     
 *       \:::\   \:::\____\               \:::\____\       \:::\   \:::\____\    
 *        \:::\   \::/    /                \::/    /        \:::\   \::/    /    
 *         \:::\   \/____/                  \/____/          \:::\   \/____/     
 *          \:::\    \                                        \:::\    \         
 *           \:::\____\                                        \:::\____\        
 *            \::/    /                                         \::/    /        
 *             \/____/                                           \/____/         
 *                                                                               
 */
