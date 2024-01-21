import java.util.ArrayList;
public class Ex7_20210808053 {
    public static void main(String[] args) throws PartyLimitReachedException, AlreadyInPartyException, CharacterIsNotInPartyException {
        Attributes a = new Attributes(2, 4);
        PlayableCharacter p = new Warrior("name");
        Weapon weapon = new Weapon("tu", 12, 2);
        Paladin paladin = new Paladin("ahp");
        Party c = new Party();
        Warrior war = new Warrior("a");
        System.out.println(p.health);
        Skeleton ske = new Skeleton("asldv", a);


       p.quitParty();
        c.addCharacter(p);
        c.addCharacter(p);
        war.joinParty(c);
        war.quitParty();
        c.removeCharacter(war);

        war.lootWeapon(weapon);
        war.attack(p);war.attack(p);war.attack(p);
        System.out.println(war.health);
        war.takeHealing(10);
        System.out.println(war.health);


    }
}
interface Damageable{
    void takeDamage(int damage);
    void takeHealing(int healing);
    boolean isAlive();
}
interface Caster {
    void castSpell(Damageable target);
    void learnSpell(Spell spell);
}
interface Combat extends Damageable{
    void attack(Damageable target);
    void lootWeapon(Weapon weapon);
}
interface Useable{
    int use();
}
//CLASSES


class Spell implements Useable{
    private int minHeal;
    private int maxHeal;

    private String name;

    Spell(String name, int minHeal, int maxHeal){
        this.maxHeal = maxHeal;
        this.minHeal = minHeal;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int heal(){
        return (int) (Math.random() * (maxHeal - minHeal) + minHeal);
    }

    @Override
    public int use() {
        return heal();
    }
}

class Weapon implements Useable{
    private int minDamage;
    private int maxDamage;
    private String name;

    Weapon(String name, int maxDamage, int minDamage){
        this.maxDamage = maxDamage;
        this.name = name;
        this.minDamage = minDamage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int attack(){
        return (int) (Math.random() * (maxDamage - minDamage) + minDamage);
    }
    @Override
    public int use() {
        return attack();
    }
}

class Attributes{
    private int strength;
    private int intelligence;

    Attributes(){
        this.strength = 3;
        this.intelligence = 3;
    }

    Attributes(int strength, int intelligence){
        this.strength = strength;
        this.intelligence = intelligence;
    }

    public void increaseStrength(int amount){
        strength += amount;
    }
    public void increaseIntelligence(int amount){
        intelligence += amount;
    }

    public int getStrength(){
        return strength;
    }
    public int getIntelligence(){
        return intelligence;
    }

    public String toString() {
        return "Attributes [Strength= " + strength + ", intelligence= " + intelligence +"]";
    }
}

abstract class Character implements Comparable<Character>{
    private String name;
    protected int level;
    protected Attributes attributes;
    protected int health;

    Character(String name, Attributes attributes){
        this.name = name;
        this.attributes = attributes;
    }
    public String getName() {
        return name;
    }
    public int getLevel() {
        return level;
    }
    abstract public void levelUp();

    public int compareTo(Character other) {
        return Integer.compare(this.level, other.level);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "LvL" + level + " – " + attributes;
    }
}

abstract class PlayableCharacter extends Character implements Damageable{

    private boolean inParty;
    private Party party;

    PlayableCharacter(String name, Attributes attributes) {
        super(name, attributes);
    }

    public boolean isInParty() {
        return inParty;
    }

    public void joinParty(Party party){
        if (!isInParty()){
            try{
                party.addCharacter(this);
                inParty = true;
                this.party = party;
            }catch (PartyLimitReachedException | AlreadyInPartyException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
    public void quitParty(){
        if(isInParty()){
            try {
                party.removeCharacter(this);
                inParty = false;
                party = null;
            }catch (CharacterIsNotInPartyException ex){
                System.out.println(ex.getMessage());//////////////////
            }
        }
    }
}

abstract class NonPlayableCharacter extends Character{
    NonPlayableCharacter(String name, Attributes attributes) {
        super(name, attributes);
    }
}

class Merchant extends NonPlayableCharacter{

    Merchant(String name) {
        super(name, new Attributes(0, 0));
    }
    @Override
    public void levelUp() {

    }
}

class Skeleton extends NonPlayableCharacter implements Combat{

    private int healingReceived;
    Skeleton(String name, Attributes attributes) {
        super(name, attributes);
    }

    @Override
    public void takeDamage(int damage) {
        health -= Math.min(damage, health);

    }

    public void takeHealing(){
        health -= healingReceived;
    }

    @Override
    public void takeHealing(int healing) {
        health += healing;
        healingReceived = healing;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public void attack(Damageable target) {
        target.takeDamage(attributes.getIntelligence() + attributes.getStrength());//////////
    }

    @Override
    public void lootWeapon(Weapon weapon) {

    }

    @Override
    public void levelUp() {
        level++;
        attributes.increaseIntelligence(1);
        attributes.increaseStrength(1);

    }
}

class Warrior extends PlayableCharacter implements Combat{
    private Useable weapon;
    private int maxHealth;
    Warrior(String name){
        super(name, new Attributes(4, 2));
        health = 35;
        maxHealth = health;
    }
    public void levelUp(){
        attributes.increaseStrength(2);
        attributes.increaseIntelligence(1);
        level++;
    }

    @Override
    public void takeDamage(int damage) {
        health -= Math.min(damage, health);
    }

    @Override
    public void takeHealing(int healing) {
        if(health + healing <= maxHealth)
            health += healing;
        else
            health = maxHealth;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public void attack(Damageable target) {
        target.takeDamage(attributes.getStrength() + weapon.use());
    }

    @Override
    public void lootWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}

class Cleric extends PlayableCharacter implements Caster{
    private Useable spell;
    private int maxHealth;
    Cleric(String name){
        super(name, new Attributes(2, 4));
        health = 25;
        maxHealth = health;
    }

    @Override
    public void levelUp() {
        attributes.increaseIntelligence(2);
        attributes.increaseStrength(1);
        level++;
    }

    @Override
    public void takeDamage(int damage) {
        health -= Math.min(damage, health);
    }
    @Override
    public void takeHealing(int healing) {
        if(health + healing <= maxHealth)
            health += healing;
        else
            health = maxHealth;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public void castSpell(Damageable target) {
        target.takeHealing(attributes.getIntelligence() + spell.use());
    }

    @Override
    public void learnSpell(Spell spell) {
        this.spell = spell;
    }
}

class Paladin extends PlayableCharacter implements Combat, Caster{
    private Useable weapon;
    private Useable spell;
    private int maxHealth;

    Paladin(String name){
        super(name, new Attributes());
        health = 30;
        maxHealth = health;
    }


    @Override
    public void takeDamage(int damage) {
        health -= Math.min(damage, health);
    }

    @Override
    public void takeHealing(int healing) {
        if(health + healing <= maxHealth)
            health += healing;
        else
            health = maxHealth;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public void castSpell(Damageable target) {
        target.takeHealing(attributes.getIntelligence() + spell.use());
    }

    @Override
    public void learnSpell(Spell spell) {
        this.spell = spell;
    }

    @Override
    public void attack(Damageable target) {
        target.takeDamage(attributes.getStrength() + weapon.use());
    }

    @Override
    public void lootWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public void levelUp() {
        if (level % 2 == 1){
            attributes.increaseStrength(2);
            attributes.increaseIntelligence(1);
        }else{
            attributes.increaseStrength(1);
            attributes.increaseIntelligence(2);
        }
        level++;
    }
}

class Party{
    private final int partyLimit = 8;
    private ArrayList<Combat> fighters = new ArrayList<>() ;
    private ArrayList<Caster> healers = new ArrayList<>();
    private int mixedCount = 0;

    public void addCharacter(PlayableCharacter character) throws PartyLimitReachedException, AlreadyInPartyException {
        if (fighters.size() + healers.size() - mixedCount >= partyLimit) {
            throw new PartyLimitReachedException("Party limit has been reached. Cannot add more characters.");
        }
        if(character instanceof Paladin){
            if (fighters.contains(character)){
                throw new AlreadyInPartyException("Character is already in the party.");
            }
            mixedCount++;
            fighters.add((Combat) character);
            healers.add((Caster) character);

        }else if (character instanceof Combat) {
            if (fighters.contains(character)){
                throw new AlreadyInPartyException("Character is already in the party.");
            }
            fighters.add((Combat) character);

        }else if (character instanceof Caster) {
            if (healers.contains(character)){
                throw new AlreadyInPartyException("Character is already in the party.");
            }
            healers.add((Caster) character);
        }
    }

    public void removeCharacter(PlayableCharacter character) throws CharacterIsNotInPartyException {
        if (character instanceof Paladin) {
            if (!fighters.contains(character)) {
                throw new CharacterIsNotInPartyException("Character is not in the party.");
            }
            mixedCount--;
            fighters.remove((Combat) character);//silinebilir ,okumak içi
            healers.remove((Caster) character);

        } else if (character instanceof Combat) {
            if (!fighters.contains(character)) {
                throw new CharacterIsNotInPartyException("Character is not in the party.");
            }
            fighters.remove((Combat) character);

        } else if (character instanceof Caster) {
            if (!healers.contains(character)) {
                throw new CharacterIsNotInPartyException("Character is not in the party.");
            }
            healers.remove((Caster) character);
        }
    }

    public void partyLevelUp() {
        for (Combat fighter : fighters) {
            if (fighter instanceof Warrior)
                ((Warrior) fighter).levelUp();
            else if (fighter instanceof Paladin)
                ((Paladin) fighter).levelUp();
        }
        for (Caster healer : healers) {
            if (healer instanceof Cleric)
                ((Cleric) healer).levelUp();
        }
    }

    public String toString() {
        // Adding all to new arraylist
        ArrayList<PlayableCharacter> characters = new ArrayList<>();
        for (Combat fighter : fighters) {
            characters.add((PlayableCharacter) fighter);
        }
        for (Caster healer : healers) {
            if (!(healer instanceof Paladin))
                characters.add((PlayableCharacter) healer);
        }
        characters.sort(Character::compareTo);
        StringBuilder sb = new StringBuilder();
        for (PlayableCharacter pc : characters) {
            sb.append(pc).append("\n");
        }
        return sb.toString();
    }
}

class Barrel implements Damageable {
    private int health = 30;
    private int capacity = 10;

    public void explode() {
        System.out.println("Explodes");
    }

    public void repair() {
        System.out.println("Repairing");
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0)
            explode();
    }

    public void takeHealing(int healing) {
        health += healing;
        repair();
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }
}
class TrainingDummy implements Damageable {
    private int health = 25;

    @Override
    public void takeDamage(int damage) {
        health -= damage;
    }

    @Override
    public void takeHealing(int healing) {
        health += healing;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }
}


class PartyLimitReachedException extends Exception {
    public PartyLimitReachedException(String a) {
        super(a);
    }
}

class AlreadyInPartyException extends Exception {
    public AlreadyInPartyException(String a) {
        super(a);
    }
}

class CharacterIsNotInPartyException extends Exception {
    public CharacterIsNotInPartyException(String a) {
        super(a);
    }
}
