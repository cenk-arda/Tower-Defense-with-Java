import java.awt.*;
import java.awt.geom.Ellipse2D;

public class TowerIce extends Tower {
    //TODO
    Color color = Color.BLUE;

    /**
     * @return Color
     */
    public Color getColor() {
        return color;
    }

    /**
     *
     */
    @Override
    public void step() {
        //TODO
        if (Game.getInstance().getstepNumber() % this.getRate() == 1) {

            double minDistance = 1000000.0; //insert max double value here
            Monster closest_monster = null;
            for (Monster aliveMonster : Game.getInstance().aliveMonsters) {
                double distance = this.getPosition().distance(aliveMonster.getPosition()); //not center now, change it.
                if (distance < minDistance) {
                    closest_monster = aliveMonster;
                    minDistance = distance;
                }
            }
            if (minDistance <= this.getRange() && closest_monster != null && closest_monster.getMonsterState() == null) {
                closest_monster.setMonsterState(new MonsterIceState(closest_monster));
            }

            if (minDistance <= this.getRange() && closest_monster != null) {
                closest_monster.decrementHealth(this.getDamage());
                if (closest_monster.getHealth() <= 0) {
                    Game gameInstance = Game.getInstance();
                    gameInstance.incrementGold(closest_monster.getReward());
                    gameInstance.aliveMonsters.remove(closest_monster);
                    gameInstance.updateMonsterCount();
                    gameInstance.incrementKill(1);
                    this.incrementKill();
                }
            }
        }
    }

    /**
     * @param g Graphics
     */
    @Override
    public void paint(Graphics g) {

        g.setColor(color);
        g.fillOval(getPosition().getIntX() - 22, getPosition().getIntY() - 22, 45, 45);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.RED);
        float fl[] = {12.0f};
        g2d.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 12.0f, fl, 0.0f));
        g2d.draw(new Ellipse2D.Double(this.getPosition().getIntX() - this.getRange(), this.getPosition().getIntY() - this.getRange(), 2 * this.getRange(), 2 * this.getRange()));
    }
}
