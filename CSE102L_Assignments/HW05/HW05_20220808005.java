import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
/**---------------------------------------------------

 * Akdeniz University CSE102L Assignments
 * author: Yahya Efe Kuruçay
 * since: 14.04.2024
 * Description: Homework05
 * Score: 91
 * Website: https://efekurucay.com
*---------------------------------------------------*/
/*
 * Bu bir otomatik maildir!!
 -------------START----------------
20220808005 - HW05->91
Test Result
╷
├─ JUnit Jupiter ✔
├─ JUnit Vintage ✔
│  └─ HW05_20220808005Test ✔
│     ├─ SpellHealMethod ✔
│     ├─ CombatMethods ✔
│     ├─ PaladinFields ✔
│     ├─ PlayableCharacterFields ✔
│     ├─ PaladinConstructor ✔
│     ├─ ClericLevelUp ✔
│     ├─ SpellAcessorMutators ✔
│     ├─ ClericCastSpell ✘ castSpell should increase the health of Damageable parameter object
│     ├─ ClassesShouldImplement ✔
│     ├─ CharacterNotHaveMutators ✔
│     ├─ AttributesNotHaveMutators ✔
│     ├─ PartyRemoveCharacterThrowCharacterNotInPartyExcepiton ✘ Should have thrown CharacterIsNotInPartyExcepiton
│     ├─ PartyRemoveCharacter ✘ removeCharacter should remove from corresponding collection expected:<1> but was:<0>
│     ├─ PartyAddCharacterThrowPartyLimitReached ✔
│     ├─ PartyFields ✔
│     ├─ CharacterConstructor ✔
│     ├─ ClericLearnSpell ✔
│     ├─ NotBeInnerClass ✔
│     ├─ AttributesFields ✔
│     ├─ AbstractClasses ✔
│     ├─ AttributesAcessor ✔
│     ├─ ClericFields ✔
│     ├─ DamageableMethods ✔
│     ├─ ClassesConstructors ✔
│     ├─ PaladinLevelUp ✔
│     ├─ PlayableCharacterConstructor ✔
│     ├─ WarriorAttack ✔
│     ├─ WeaponAttackMethod ✔
│     ├─ WarriorFields ✔
│     ├─ PlayableCharacterQuitParty ✔
│     ├─ ChildClassesShouldExtend ✔
│     ├─ WarriorLevelUp ✔
│     ├─ WeaponConstructor ✔
│     ├─ WarriorTakeDamage ✔
│     ├─ ClericIsAlive ✔
│     ├─ WeaponFields ✔
│     ├─ ClericTakeHealing ✔
│     ├─ ClericTakeDamage ✔
│     ├─ ClericConstructor ✔
│     ├─ CharacterAcessors ✔
│     ├─ PartyAddCharacter ✘ Warrior instance should be added to fighters expected:<1> but was:<0>
│     ├─ CharacterLevelUpMethod ✔
│     ├─ CharacterFields ✔
│     ├─ AttributesIncreaseMethods ✔
│     ├─ UseableMethods ✔
│     ├─ WarriorTakeHealing ✔
│     ├─ WeaponAcessorMutators ✔
│     ├─ SpellFields ✔
│     ├─ CasterMethods ✔
│     ├─ PartyLevelUp ✘ Should increase the level of all party members once (3 failures)
│     │         java.lang.AssertionError: Cleric should be level 1 expected:<1> but was:<0>
│     │         java.lang.AssertionError: Cleric should be level 1 expected:<1> but was:<0>
│     │         java.lang.AssertionError: Paladin should be level 1 expected:<1> but was:<0>
│     ├─ AttributesConstructor ✔
│     ├─ WarriorConstructor ✔
│     ├─ WarriorLootWeapon ✔
│     ├─ NPCConstructor ✔
│     ├─ CharacterAcessor ✔
│     ├─ PlayableCharacterJoinParty ✘ joinParty should add PlayableCharacter to suitable collection expected:<1> but was:<0>
│     ├─ SpellConstructor ✔
│     └─ WarriorIsAlive ✔
└─ JUnit Platform Suite ✔


--------------END-----------------

 */
/***
 *    ███████ ███████ ███████   |    ███████ ███████ ███████ 
 *    ██      ██      ██        |    ██      ██      ██      
 *    █████   █████   █████     |    █████   █████   █████   
 *    ██      ██      ██        |    ██      ██      ██      
 *    ███████ ██      ███████   |    ███████ ██      ███████ 
 *                            
 *                            
 */

/**
 * HW05_20220808005
 */
public class HW05_20220808005 {
public static void main(String[] args) {
    
}
   
}
 
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
 
 class Spell implements Useable {
     private int minHeal;
     private int maxHeal;
     private String name;
 
     Spell(String name, int minHeal, int maxHeal) {
         this.name = name;
         this.minHeal = minHeal;
         this.maxHeal = maxHeal;
     }
 
     public int use() {
         return heal();
     }
 
     private int heal() {
         Random rand = new Random();
         return rand.nextInt(maxHeal - minHeal + 1) + minHeal;
     }
     public String getName() {
         return name;
     }
     public void setName(String name) {
         this.name = name;
     }
 }
 
 class Weapon implements Useable {
     private int minDamage;
     private int maxDamage;
     private String name;
 
     Weapon(String name, int minDamage, int maxDamage) {
         this.name = name;
         this.minDamage = minDamage;
         this.maxDamage = maxDamage;
     }
 
     public int use() {
         return attack();
     }
 
     private int attack() {
         Random rand = new Random();
         return rand.nextInt(maxDamage - minDamage + 1) + minDamage;
     }
     public String getName() {
         return name;
     }public void setName(String name) {
         this.name = name;
     }
 }
 
 class Attributes {
     private int strength;
     private int intelligence;
 
     Attributes() {
         this.strength = 3;
         this.intelligence = 3;
     }
 
     Attributes(int strength, int intelligence) {
         this.strength = strength;
         this.intelligence = intelligence;
     }
 
     public void increaseStrength(int amount) {
         this.strength += amount;
     }
 
     public void increaseIntelligence(int amount) {
         this.intelligence += amount;
     }
 
     public int getStrength() {
         return strength;
     }
 
     public int getIntelligence() {
         return intelligence;
     }
 
     public String toString() {
         return "Attributes [Strength=" + strength + ", intelligence=" + intelligence + "]";
     }
 }
 
 abstract class Character implements Comparable<Character>, Damageable {
     private String name;
     protected int level;
     protected Attributes attributes;
     protected int health;
 
     Character(String name, Attributes attributes) {
         this.name = name;
         this.attributes = attributes;
     }
 
     public String getName() {
         return name;
     }
 
     public int getLevel() {
         return level;
     }
 
     public abstract void levelUp();
 
     public String toString() {
         return getClass().getSimpleName() + " LvL: " + level + " - " + attributes;
     }
 }
 
 abstract class PlayableCharacter extends Character {
     protected boolean inParty;
     protected Party party;
 
     PlayableCharacter(String name, Attributes attributes) {
         super(name, attributes);
     }
 
     public boolean isInParty() {
         return inParty;
     }
 
     public void joinParty(Party party) throws AlreadyInPartyException, PartyLimitReachedException {
         if (inParty) {
             throw new AlreadyInPartyException("Already in a party.");
         }
         if (party.getFighters().size() + party.getHealers().size() >= Party.partyLimit) {
             throw new PartyLimitReachedException("Party limit reached.");
         }
         this.inParty = true;
         this.party = party;
         if (this instanceof Combat) {
             party.getFighters().add((Combat) this);
         } else if (this instanceof Caster) {
             party.getHealers().add((Caster) this);
         }
     }
 
     public void quitParty() throws CharacterIsNotInPartyException {
         if (!inParty) {
             throw new CharacterIsNotInPartyException("Not in a party.");
         }
         inParty = false;
         party.removeCharacter(this);
         party = null;
     }
 
     public void levelUp(){
         level++;
     };
 }
 
 abstract class NonPlayableCharacter extends Character {
     NonPlayableCharacter(String name, Attributes attributes) {
         super(name, attributes);
     }
 }
 
 class Merchant extends NonPlayableCharacter {
     private Collection<Useable> inventory;
 
     Merchant(String name) {
         super(name, new Attributes());
         inventory = new ArrayList<>();
     }
 
     public void display() {
         for (Useable item : inventory) {
             System.out.println(item.getClass().getSimpleName() + ": " + item.use());
         }
     }
 
     public Useable buy(int itemNumber) throws ItemNotFoundException {
         try {
             return (Useable) inventory.toArray()[itemNumber];
         } catch (IndexOutOfBoundsException e) {
             throw new ItemNotFoundException("Item not found.");
         }
     }
 
     public void sell(Useable item) {
         inventory.add(item);
     }
     @Override
     public void levelUp() {
     }
 
     
 
     @Override
     public void takeDamage(int damage) {
         health-= damage;
     }
 
     @Override
     public void takeHealing(int healing) {
         health+= healing;
     }
 
     @Override
     public boolean isAlive() {
         return health > 0;
     }
 
     @Override
     public int compareTo(Character o) {
         return 5;
     }
 }
 
 class Skeleton extends NonPlayableCharacter implements Combat {
     Skeleton(String name) {
         super(name, new Attributes());
     }
 
     public void lootWeapon(Weapon weapon) {
         }
 
     public void levelUp() {
         level++;
         attributes.increaseStrength(1);
         attributes.increaseIntelligence(1);
     }
 
     public void takeDamage(int damage) {
         health -= damage;
     }
 
     public void takeHealing(int healing) {
         takeDamage(healing);
     }
 
     public boolean isAlive() {
         return health > 0;
     }
 
     public void attack(Damageable target) {
         target.takeDamage(attributes.getStrength()+attributes.getIntelligence());
     }
 
     @Override
     public int compareTo(Character o) {
         return 5;
     }
 }
 
 class Warrior extends PlayableCharacter implements Combat {
     private Useable weapon;
 
     Warrior(String name) {
         super(name, new Attributes(4, 2));
         health = 35;
     }
 
     public void levelUp() {
         level++;
         attributes.increaseStrength(2);
         attributes.increaseIntelligence(1);
     }
 
     public void lootWeapon(Weapon weapon) {
         this.weapon = weapon;
     }
 
     public void attack(Damageable target) {
         target.takeDamage(attributes.getStrength()+attributes.getIntelligence());
     }
 
     public void takeDamage(int damage) {
         health -= damage;
     }
 
     public void takeHealing(int healing) {
         health+= healing;
     }
 
     public boolean isAlive() {
         return health > 0;
     }
 
     @Override
     public int compareTo(Character o) {
         return 5;
     }
 }
 
 class Cleric extends PlayableCharacter implements Caster {
     private Useable spell;
 
     Cleric(String name) {
         super(name, new Attributes(2, 4));
         health = 25;
     }
 
     public void levelUp() {
         level++;
         attributes.increaseStrength(1);
         attributes.increaseIntelligence(2);
     }
 
     public void castSpell(Damageable target) {
     }
 
     public void learnSpell(Spell spell) {
         this.spell = spell;
     }
 
     public void takeDamage(int damage) {
         health -= damage;
     }
     public void takeHealing(int healing) {
         health+= healing;
     }
 
     public boolean isAlive() {
         return health > 0;
     }
 
     @Override
     public int compareTo(Character o) {
         return 6;
     }
 }
 
 class Paladin extends PlayableCharacter implements Combat, Caster {
     private Useable weapon;
     private Useable spell;
 
     Paladin(String name) {
         super(name, new Attributes());
         health = 30;
     }
 
     public void levelUp() {
         level++;
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
     }
 
     public void learnSpell(Spell spell) {
         this.spell = spell;
     }
 
     public void attack(Damageable target) {
         target.takeDamage(attributes.getStrength()+attributes.getIntelligence());
     }
 
     public void takeDamage(int damage) {
         health-= damage;
     }
 
     public void takeHealing(int healing) {
         health+= healing;
     }
 
     public boolean isAlive() {
         return health > 0;
     }
 
     @Override
     public int compareTo(Character o) {
         return 5;
     }
 }
 
 class Party {
     public static final int partyLimit = 8;
     private ArrayList<Combat> fighters;
     private ArrayList<Caster> healers;
     private int mixedCount;
 
     Party() {
         fighters = new ArrayList<>();
         healers = new ArrayList<>();
         mixedCount = 0;
     }
 
     public void addCharacter(PlayableCharacter character) throws PartyLimitReachedException {
         if (fighters.size() + healers.size() >= partyLimit) {
             throw new PartyLimitReachedException("Party limit reached.");
         }
 
         if (character instanceof Combat) {
             fighters.add((Combat) character);
         } else if (character instanceof Caster) {
             healers.add((Caster) character);
         }
 
         if (character instanceof Paladin) {
             mixedCount++;
         }
     }
 
     public void removeCharacter(PlayableCharacter character) {
         if (character instanceof Combat) {
             fighters.remove(character);
         } else if (character instanceof Caster) {
             healers.remove(character);
         }
 
         if (character instanceof Paladin) {
             mixedCount--;
         }
     }
 
     public void partyLevelUp() {
         for (Combat fighter : fighters) {
         }
         for (Caster healer : healers) {
         }
     }
 
     public String toString() {
         StringBuilder sb = new StringBuilder();
         ArrayList<Object> allCharacters = new ArrayList<>();
         for(int i = 0 ; i < fighters.size();i++){
             allCharacters.add(fighters.get(i));
         }
         for(int i = 0 ; i < healers.size();i++){
             allCharacters.add(healers.get(i));
         }
 
         for (Object character : allCharacters) {
             sb.append(character.toString()).append("\n");
         }
 
         return sb.toString();
     }
 
     public Collection<Combat> getFighters() {
         return fighters;
     }
 
     public Collection<Caster> getHealers() {
         return healers;
     }
     
 }
 
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
