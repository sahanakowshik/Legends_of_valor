public interface isDrinkable {
    // Interface to use potions
    default void use(Heroes hero, int id){
        // Checks the attributes affected the potions and updates them
        String[] words = hero.getPotions().get(id-1).getAtt_affected().split("/");
        hero.usePotion(words, hero.getPotions().get(id-1).getAtt_increase());
        hero.getPotions().remove(id-1); // Updates the potions list
    }
}
