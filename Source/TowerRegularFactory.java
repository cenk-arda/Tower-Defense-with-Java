public class TowerRegularFactory implements ITowerFactory {
    //TODO

    /**
     * @param position Vector2D
     * @return Tower
     */
    @Override
    public Tower createTower(Vector2D position) {
        Tower regularTower = new TowerRegular();
        regularTower.setRate(20);
        regularTower.setDamage(20);
        regularTower.setCost(20);
        regularTower.setRange(150);
        return regularTower;
    }
}
