public class TowerIceFactory implements ITowerFactory {
    //TODO

    /**
     * @param position Vector2D
     * @return Tower
     */
    @Override
    public Tower createTower(Vector2D position) {
        Tower iceTower = new TowerIce();
        iceTower.setRate(20);
        iceTower.setDamage(10);
        iceTower.setCost(20);
        iceTower.setRange(150);
        return iceTower;
    }

}
