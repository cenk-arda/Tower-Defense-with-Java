import java.awt.*;

public class TowerDecorator extends Tower {
    //TODO
    protected Tower wrappee;

    public TowerDecorator(Tower t) {
        this.wrappee = t;

    }

    /**
     *
     */
    @Override
    public void step() {
        this.wrappee.step();
    }

    /**
     * @param g Graphics
     */
    @Override
    public void paint(Graphics g) {
        this.wrappee.paint(g);
    }

    /**
     * @return int
     */
    public int getRange() {
        return wrappee.getRange();
    }

    /**
     * @param range int
     */
    public void setRange(int range) {
        wrappee.setRange(range);
    }

    /**
     * @return int
     */
    public int getRate() {
        return wrappee.getRate();
    }

    /**
     * @param rate int
     */
    public void setRate(int rate) {
        wrappee.setRate(rate);
    }

    /**
     * @return int
     */
    public int getDamage() {
        return wrappee.getDamage();
    }

    /**
     * @param damage int
     */
    public void setDamage(int damage) {
        wrappee.setDamage(damage);
    }

    /**
     * @return int
     */
    public int getCost() {
        return wrappee.getCost();
    }

    /**
     * @param cost int
     */
    public void setCost(int cost) {
        wrappee.setCost(cost);
    }

    /**
     *
     */
    public void incrementKill() {
        wrappee.incrementKill();
    }

    /**
     * @return int
     */
    public int getKillNumber() {
        return wrappee.getKillNumber();
    }

    /**
     * @return int
     */
    public int getGrade() {
        return wrappee.getGrade();
    }

    /**
     * @param grade int
     */
    public void setGrade(int grade) {
        wrappee.setGrade(grade);
    }

    /**
     * @return Vector2D
     */
    public Vector2D getPosition() {
        return wrappee.getPosition();
    }

    /**
     * @param position Vector2D
     */
    public void setPosition(Vector2D position) {
        wrappee.setPosition(position);
    }

    /**
     * @param x double
     * @param y double
     */
    public void setPosition(double x, double y) {
        wrappee.setPosition(x, y);
    }

    /**
     *
     */
    public void centralizePosition() { //returns the X point of the closest square's center to the clicked point.
        wrappee.centralizePosition();
    }

    /**
     *
     */
    public void shootNearestMonster() {
        wrappee.shootNearestMonster();
    }


}
