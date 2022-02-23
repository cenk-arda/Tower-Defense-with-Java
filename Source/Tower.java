public abstract class Tower extends Entity {

    //TODO
    private int range;
    private int rate; //1 in rate steps
    private int damage;
    private int cost;
    private Vector2D position;
    private boolean isDrawn;
    private Game gameInstance = Game.getInstance();
    private int killNumber = 0;
    private int grade = 0;

    /**
     * @return int
     */
    public int getRange() {
        return range;
    }

    /**
     * @param range int
     */
    public void setRange(int range) {
        this.range = range;
    }

    /**
     * @return int
     */
    public int getRate() {
        return rate;
    }

    /**
     * @param rate int
     */
    public void setRate(int rate) {
        this.rate = rate;
    }

    /**
     * @return int
     */
    public int getDamage() {
        return damage;
    }

    /**
     * @param damage int
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * @return int
     */
    public int getCost() {
        return cost;
    }

    /**
     * @param cost int
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     *
     */
    public void incrementKill() {
        killNumber += 1;
    }

    /**
     * @return int
     */
    public int getKillNumber() {
        return killNumber;
    }

    /**
     * @return int
     */
    public int getGrade() {
        return grade;
    }

    /**
     * @param grade int
     */
    public void setGrade(int grade) {
        this.grade = grade;
    }

    /**
     * @return Vector2D
     */
    public Vector2D getPosition() {
        return position;
    }

    /**
     * @param position Vector2D
     */
    public void setPosition(Vector2D position) {
        this.position = position;
    }

    /**
     * @param x double
     * @param y double
     */
    public void setPosition(double x, double y) {
        this.position = new Vector2D(x, y);
    }

    /**
     *
     */
    public void centralizePosition() { //returns the X point of the closest square's center to the clicked point.
        int x_grid = (this.getPosition().getIntX() - 100) / 50;
        if (x_grid < 0) x_grid = 0;
        if (x_grid > 3) x_grid = 3;
        int x_index = 125 + 50 * x_grid;
        int y_grid = (this.getPosition().getIntY() - 100) / 50;
        if (y_grid < 0) y_grid = 0;
        if (y_grid > 3) y_grid = 3;
        int y_index = 125 + 50 * y_grid;
        this.setPosition(x_index, y_index);

    }

    /**
     *
     */
    public void shootNearestMonster() {
        double minDistance = 1000000.0; //insert max double value here
        Monster closest_monster = null;
        for (Monster aliveMonster : Game.getInstance().aliveMonsters) {
            Vector2D center_of_monster = aliveMonster.getPosition().add(new Vector2D(Commons.MonsterSize / 2, Commons.MonsterSize / 2));
            double distance = this.getPosition().distance(center_of_monster); //not center now, change it.
            if (distance < minDistance) {
                closest_monster = aliveMonster;
                minDistance = distance;
            }
        }
        if (minDistance <= this.getRange() && closest_monster != null) {
            closest_monster.decrementHealth(this.getDamage());
            if (closest_monster.getHealth() <= 0) {
                gameInstance.incrementGold(closest_monster.getReward());
                gameInstance.aliveMonsters.remove(closest_monster);
                gameInstance.updateMonsterCount();
                gameInstance.incrementKill(1);
                this.incrementKill();

            }
        }
    }

    /**
     *
     */
    public abstract void step();

}
