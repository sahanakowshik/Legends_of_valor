public interface isCastable {
    // Interface to check if an item is castable
    default void use(Monsters monster, Heroes hero, int i, int id, Market market){
        // Function to cast a spell
        int dmg = (int) (hero.getSpells().get(id - 1).getDamage() * (1 + (hero.getDexterity() / 10000)));
        monster.setHp(Math.max((monster.getHp() - dmg), 0)); // Reduces hp of the monster
        hero.setMana(hero.getMana() - hero.getSpells().get(id-1).getMana_cost()); // Reduces mana of the hero
        System.out.println(hero.getName() + " has dealt " + dmg + " damage!");
        // Performing enemy’s skill deterioration
        if(market.getFireSpell().fireSpells.contains(hero.getSpells().get(id-1))){
            monster.setDefense((int)(monster.getDefense() * (1 - 0.1)));
        }
        else if(market.getIceSpell().iceSpells.contains(hero.getSpells().get(id-1))){
            monster.setDamage((int)(monster.getDamage() * (1 - 0.1)));
        }
        else
            monster.setDodge_chance((int)(monster.getDodge_chance() * (1 - 0.1)));

    }
}
