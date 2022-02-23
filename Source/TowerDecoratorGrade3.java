import java.awt.*;

public class TowerDecoratorGrade3 extends TowerDecorator {
    //TODO

    /**
     *
     * @param t
     */
    public TowerDecoratorGrade3(Tower t) {
        super(t);
    }

    /**
     *
     * @param g Graphics
     */
    @Override
    public void paint(Graphics g) {
        wrappee.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.ORANGE);
        g2d.setStroke(new BasicStroke(5));
        Vector2D center_position = wrappee.getPosition();
        int topLeftX = center_position.getIntX() - Commons.MonsterSize / 2;
        int topLeftY = center_position.getIntY() - Commons.MonsterSize / 2;
        g2d.drawLine(topLeftX+32,topLeftY+10, topLeftX+32,topLeftY+Commons.MonsterSize-10);
    }

    /**
     *
     */
    @Override
    public void step() {
        this.wrappee.step();
    }
}
