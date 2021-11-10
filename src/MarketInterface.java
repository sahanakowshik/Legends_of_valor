public interface MarketInterface {
    // Interface used to implement facade pattern
    void createArmory();
    void createWeapons();
    void createPotions();
    void createFireSpells();
    void createIceSpells();
    void createLightningSpells();
    void displayArmory();
    void displayPotions();
    void displayFireSpells();
    void displayIceSpells();
    void displayWeaponry();
    void createMarketList();
    void buySell(LegendsPlayer player);

}
