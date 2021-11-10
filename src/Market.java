import java.util.Scanner;

public class Market implements MarketInterface{
    // Class to create an instance of the market
    // Implements facade pattern to get items from inventory
    private MarketItems armory;
    private MarketItems weaponry;
    private Potions potion;
    private Spell fireSpell;
    private Spell iceSpell;
    private Spell lightningSpell;

    public Market(){
        armory = new Armory();
        weaponry = new Weaponry();
        potion = new Potions();
        fireSpell = new FireSpell();
        iceSpell = new IceSpell();
        lightningSpell = new LightningSpell();
    }

    public MarketItems getArmory() {
        return armory;
    }

    public MarketItems getWeaponry() {
        return weaponry;
    }

    public Potions getPotion() {
        return potion;
    }

    public Spell getFireSpell() {
        return fireSpell;
    }

    public Spell getIceSpell() {
        return iceSpell;
    }

    public Spell getLightningSpell() {
        return lightningSpell;
    }

    // Creating list of market items
    public void createArmory(){
        armory.createList();
    }

    public void createWeapons(){
        weaponry.createList();
    }

    public void createPotions(){
        potion.createList();
    }

    public void createFireSpells(){
        fireSpell.createList();
    }

    public void createIceSpells(){
        iceSpell.createList();
    }

    public void createLightningSpells(){
        lightningSpell.createList();
    }

    // Displaying market items
    public void displayArmory(){
        armory.display();
    }

    public void displayWeaponry(){
        weaponry.display();
    }

    public void displayPotions(){
        potion.display();
    }

    public void displayFireSpells(){
        fireSpell.display();
    }

    public void displayIceSpells(){
        iceSpell.display();
    }

    public void displayLightningSpells(){
        lightningSpell.display();
    }

    public void createSpells(){
        this.createFireSpells();
        this.createIceSpells();
        this.createLightningSpells();
    }

    public void createMarketList(){
        // Creates a market with all the items
        this.createArmory();
        this.createWeapons();
        this.createPotions();
        this.createSpells();
    }



    public void buySell(LegendsPlayer player){
        // Implement buying and selling logic
        System.out.println("\u001B[44m You have entered the market \u001B[0m");
        for(Heroes hero: player.getHeroes()){
            int heroChoice = GameFunctions.safeScanIntWithLimit(new Scanner(System.in), hero.getName() + " would you like to enter the store\n1. Yes\n2. No\n", 1, 2);
            if(heroChoice == 2)
                continue;
            else {
                do {
                    int val = GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "What would you like to do?\n1. Buy\n2. Sell\n3. Exit\n", 1, 3);
                    if (val == 1) {
                        int mchoice = GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "What would you like to buy?\n0. Exit\n1. Armory\n2. Weapon\n3. Potion\n4. Spell\n", 0, 4);
                        if (mchoice == 1) {
                            // Buying an armory
                            do {
                                this.displayArmory();
                                int id = GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "Please enter the Id of the item you want to buy\n0. Exit\n", 0, this.getArmory().armories.size());
                                if (id != 0) {
                                    id = id-1;
                                    if (this.getArmory().armories.get(id).getReq_level() > hero.getLevel()) {
                                        System.out.println("You cannot buy this item. it needs level " + this.getArmory().armories.get(id).getReq_level());
                                    } else {
                                        if (hero.getStarting_money() < this.getArmory().armories.get(id).getCost()) {
                                            System.out.println("You don't have the balance to buy this item");
                                        }
                                        else if(hero.getArmories().contains(this.getArmory().armories.get(id))){
                                            System.out.println("You already own this item");
                                        }
                                        else {
                                            hero.setStarting_money(hero.getStarting_money() - this.getArmory().armories.get(id).getCost()); // Updating the money
                                            if(GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "Would you like to equip this item?\n1. Yes\n2. No\n", 1, 2) == 1){
                                                // Equipping the item
                                                for(int i=0;i<hero.getArmories().size();i++){
                                                    hero.getArmories().get(i).setEquip("No");
                                                }
                                                Armory item = this.getArmory().armories.get(id);
                                                item.setEquip("Yes");
                                                hero.getArmories().add(item);
                                                hero.setIsEquipped(true);
                                                hero.setCurArmory(item);
                                            }
                                            else{
                                                hero.getArmories().add(this.getArmory().armories.get(id));
                                            }
                                            break;
                                        }
                                    }
                                }
                                else
                                    break;
                            }while (true);
                            for(int i=0;i<hero.getArmories().size();i++){
                                hero.getArmories().get(i).setId(i+1); // Resetting the ids of armories
                            }
                        } else if (mchoice == 2) {
                            // Buying a weapon
                            do {
                                this.displayWeaponry();
                                int id = GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "Please enter the Id of the item you want to buy\n0. Exit\n", 0, this.getWeaponry().weapons.size());
                                if (id != 0) {
                                    id = id-1;
                                    if (this.getWeaponry().weapons.get(id).getLevel() > hero.getLevel()) {
                                        System.out.println("You cannot buy this item. it needs level " + this.getWeaponry().weapons.get(id).getLevel());
                                    } else {
                                        if (hero.getStarting_money() < this.getWeaponry().weapons.get(id).getCost()) {
                                            System.out.println("You don't have the balance to buy this item");
                                        }
                                        else if(hero.getWeapons().contains(this.getWeaponry().weapons.get(id))){
                                            System.out.println("You already own this item");
                                        }
                                        else {
                                            hero.setStarting_money(hero.getStarting_money() - this.getWeaponry().weapons.get(id).getCost());
                                            if(GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "Would you like to equip this item?\n1. Yes\n2. No\n", 1, 2) == 1){
                                                // Equipping the weapon
                                                for(Weaponry item: hero.getWeapons()){
                                                    item.setEquip("No");
                                                }
                                                Weaponry item = this.getWeaponry().weapons.get(id);
                                                item.setEquip("Yes");
                                                hero.getWeapons().add(item);
                                                hero.setIsEquipped(true);
                                                hero.setCurWeapon(item);
                                            }
                                            else{
                                                hero.getWeapons().add(this.getWeaponry().weapons.get(id));
                                            }
                                            break;
                                        }
                                    }
                                }
                                else
                                    break;
                            }while (true);
                            for(int i=0;i<hero.getWeapons().size();i++){
                                hero.getWeapons().get(i).setId(i+1); // Resetting the ids of the weapons
                            }
                        } else if (mchoice == 3) {
                            // Buying a potion
                            do {
                                this.displayPotions();
                                int id = GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "Please enter the Id of the item you want to buy\n0. Exit\n", 0, this.getPotion().potions.size());
                                if (id != 0) {
                                    id = id-1;
                                    if (this.getPotion().potions.get(id).getReq_level() > hero.getLevel()) {
                                        System.out.println("You cannot buy this item. it needs level " + this.getPotion().potions.get(id).getReq_level());
                                    } else {
                                        if (hero.getStarting_money() < this.getPotion().potions.get(id).getCost()) {
                                            System.out.println("You don't have the balance to buy this item");
                                        }
                                        else if(hero.getPotions().contains(this.getPotion().potions.get(id))){
                                            System.out.println("You already own this item");
                                        }
                                        else {
                                            hero.getPotions().add(this.getPotion().potions.get(id));
                                            hero.setStarting_money(hero.getStarting_money() - this.getPotion().potions.get(id).getCost());
                                            break;
                                        }
                                    }
                                }
                                else
                                    break;
                            }while (true);
                            for(int i=0;i<hero.getPotions().size();i++){
                                hero.getPotions().get(i).setId(i+1); // Resetting the ids of the potions
                            }
                        } else if (mchoice == 4) {
                            // Buying a spell
                            int sp = GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "Which spell would you like to buy?\n0. Exit\n1. Fire Spell\n2. Ice Spell\n3. Lightning Spell\n", 0, 3);
                            if (sp == 1) {
                                // Fire spell
                                do {
                                    this.displayFireSpells();
                                    int id = GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "Please enter the Id of the item you want to buy\n0. Exit\n", 0, this.getFireSpell().fireSpells.size());
                                    if (id != 0) {
                                        id = id-1;
                                        if (this.getFireSpell().fireSpells.get(id).getReq_level() > hero.getLevel()) {
                                            System.out.println("You cannot buy this item. it needs level " + this.getFireSpell().fireSpells.get(id).getReq_level());
                                        } else {
                                            if (hero.getStarting_money() < this.getFireSpell().fireSpells.get(id).getCost()) {
                                                System.out.println("You don't have the balance to buy this item");
                                            }
                                            else if(hero.getSpells().contains(this.getFireSpell().fireSpells.get(id))){
                                                System.out.println("You already own this item");
                                            }
                                            else {
                                                hero.getSpells().add(this.getFireSpell().fireSpells.get(id));
                                                hero.setStarting_money(hero.getStarting_money() - this.getFireSpell().fireSpells.get(id).getCost());
                                                break;
                                            }
                                        }
                                    }
                                    else
                                        break;
                                }while (true);
                            } else if (sp == 2) {
                                // Ice spell
                                do {
                                    this.displayIceSpells();
                                    int id = GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "Please enter the Id of the item you want to buy\n0. Exit\n", 0, this.getIceSpell().iceSpells.size());
                                    if (id != 0) {
                                        id = id-1;
                                        if (this.getIceSpell().iceSpells.get(id).getReq_level() > hero.getLevel()) {
                                            System.out.println("You cannot buy this item. it needs level " + this.getIceSpell().iceSpells.get(id).getReq_level());
                                        } else {
                                            if (hero.getStarting_money() < this.getIceSpell().iceSpells.get(id).getCost()) {
                                                System.out.println("You don't have the balance to buy this item");
                                            }
                                            else if(hero.getSpells().contains(this.getIceSpell().iceSpells.get(id))){
                                                System.out.println("You already own this item");
                                            }
                                            else {
                                                hero.getSpells().add(this.getIceSpell().iceSpells.get(id));
                                                hero.setStarting_money(hero.getStarting_money() - this.getIceSpell().iceSpells.get(id).getCost());
                                                break;
                                            }
                                        }
                                    }
                                    else
                                        break;
                                }while (true);
                            } else if (sp == 3) {
                                // Lightning spell
                                do {
                                    this.displayLightningSpells();
                                    int id = GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "Please enter the Id of the item you want to buy\n0. Exit\n", 0, this.getLightningSpell().lightningSpells.size());
                                    if (id != 0) {
                                        id = id-1;
                                        if (this.getLightningSpell().lightningSpells.get(id).getReq_level() > hero.getLevel()) {
                                            System.out.println("You cannot buy this item. it needs level " + this.getLightningSpell().lightningSpells.get(id).getReq_level());
                                        } else {
                                            if (hero.getStarting_money() < this.getLightningSpell().lightningSpells.get(id).getCost()) {
                                                System.out.println("You don't have the balance to buy this item");
                                            }
                                            else if(hero.getSpells().contains(this.getLightningSpell().lightningSpells.get(id))){
                                                System.out.println("You already own this item");
                                            }
                                            else {
                                                hero.getSpells().add(this.getLightningSpell().lightningSpells.get(id));
                                                hero.setStarting_money(hero.getStarting_money() - this.getLightningSpell().lightningSpells.get(id).getCost());
                                                break;
                                            }
                                        }
                                    }
                                    else
                                        break;
                                }while (true);
                            }
                            for(int i=0;i<hero.getSpells().size();i++){
                                hero.getSpells().get(i).setId(i+1); // Resetting the ids of the spells
                            }
                        }
                        System.out.println("\u001B[36m " + hero.getName() + " Inventory \u001b[0m");
                        hero.showInventory(); // Shows the inventory of the hero
                        System.out.println(hero.getName() + "Info");
                        Display.displayHeroes(player.getHeroes()); // Displays the hero info

                    } else if (val == 2) {
                        // Selling an item
                        hero.showInventory();
                        int mchoice = GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "What would you like to sell?\n0. Exit\n1. Armory\n2. Weapon\n3. Potion\n4. Spell\n", 0, 4);
                        if(mchoice == 1){
                            // Selling an armory
                            if(hero.getArmories().size() == 0){
                                System.out.println("You don't own any armories to sell");
                            }
                            else {
                                hero.showArmories();
                                int id = GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "Please enter the id of item you want to sell\n0. Exit\n", 0, hero.getArmories().size());
                                if(id != 0) {
                                    id = id - 1;
                                    hero.setStarting_money(hero.getStarting_money() + (hero.getArmories().get(id).getCost() / 2));
                                    hero.getArmories().remove(id);
                                }
                            }
                        }
                        else if(mchoice == 2){
                            // Selling a weapon
                            if(hero.getWeapons().size() == 0){
                                System.out.println("You don't own any weapons to sell");
                            }
                            else {
                                hero.showWeapons();
                                int id = GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "Please enter the id of item you want to sell\n0. Exit\n", 0, hero.getWeapons().size());
                                if(id != 0) {
                                    id = id - 1;
                                    hero.setStarting_money(hero.getStarting_money() + (hero.getWeapons().get(id).getCost() / 2));
                                    hero.getWeapons().remove(id);
                                }
                            }
                        }
                        else if(mchoice == 3){
                            // Selling a potion
                            if(hero.getPotions().size() == 0){
                                System.out.println("You don't own any potions to sell");
                            }
                            else {
                                hero.showPotions();
                                int id = GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "Please enter the id of item you want to sell\n0. Exit\n", 0, hero.getPotions().size());
                                if(id != 0) {
                                    id = id - 1;
                                    hero.setStarting_money(hero.getStarting_money() + (hero.getPotions().get(id).getCost() / 2));
                                    hero.getPotions().remove(id);
                                }
                            }
                        }
                        else if(mchoice == 4){
                            // Selling a spell
                            if(hero.getSpells().size() == 0){
                                System.out.println("You don't own any spells to sell");
                            }
                            else {
                                hero.showSpells();
                                int id = GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "Please enter the id of item you want to sell\n0. Exit\n", 0, hero.getSpells().size());
                                if(id != 0) {
                                    id = id - 1;
                                    hero.setStarting_money(hero.getStarting_money() + (hero.getSpells().get(id).getCost() / 2));
                                    hero.getSpells().remove(id);
                                }
                            }
                        }
                        System.out.println("\u001B[36m " + hero.getName() + " Inventory \u001b[0m");
                        hero.showInventory();
                        System.out.println(hero.getName() + "Info");
                        Display.displayHeroes(player.getHeroes());
                    }
                    if(GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "Would you like to buy or sell any other item?\n1. Yes\n2. No\n", 1, 2) == 2){
                        System.out.println(hero.getName() + " You have left the market");
                        break;
                    }
                } while (true);
            }
        }
    }

}
