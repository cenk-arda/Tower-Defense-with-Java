import java.awt.*;
import java.awt.geom.Ellipse2D;

public class MonsterIceState extends MonsterState {
    //TODO
    int remaining;
    double old_speed;

    /**
     * @param m Monster
     */
    public MonsterIceState(Monster m) {
        super(m);
        this.remaining = 5;
        this.old_speed = m.getSpeed();
    }

    /**
     *
     */
    @Override
    public void update() {
        //TODO
        if (remaining > 0) {

            if (remaining == 5) mon.setSpeed(0.2 * old_speed);

            remaining--;
        } else {
            mon.setSpeed(old_speed);
            if (remaining == 0) {
                mon.setMonsterState(null);
            }
        }


    }

    /**
     * @param g Graphics
     */
    @Override
    public void paint(Graphics g) {
        //TODO
        if (remaining > 0) {
            g.setColor(Color.BLUE);
            int top_left_x = mon.getPosition().getIntX();
            int top_left_y = mon.getPosition().getIntY();

            g.fillRect(top_left_x, top_left_y, Commons.MonsterSize, Commons.MonsterSize);

        }
    }
}
