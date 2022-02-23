public class TowerPoisonFactory implements ITowerFactory {

    //TODO

    /**
     * @param position Vector2D
     * @return Tower
     */
    @Override
    public Tower createTower(Vector2D position) {
        Tower poisonTower = new TowerPoison();
        poisonTower.setRate(10);
        poisonTower.setDamage(5);
        poisonTower.setCost(25);
        poisonTower.setRange(75);
        return poisonTower;
    }
}
