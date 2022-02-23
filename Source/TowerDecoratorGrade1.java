import java.awt.*;

public class TowerDecoratorGrade1 extends TowerDecorator {
    /**
     * @param t Tower
     */
    public TowerDecoratorGrade1(Tower t) {
        super(t);
        // maybe additional enhancements can be added here by calling super's setDamage, setRange vs...
    }

    /**
     * @param g Graphics
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.ORANGE);
        g2d.setStroke(new BasicStroke(5));
        Vector2D center_position = wrappee.getPosition();
        int topLeftX = center_position.getIntX() - Commons.MonsterSize / 2;
        int topLeftY = center_position.getIntY() - Commons.MonsterSize / 2;
        g2d.drawLine(topLeftX + 8, topLeftY + 10, topLeftX + 8, topLeftY + Commons.TowerSize - 10);
    }

    /**
     *
     */
    @Override
    public void step() {
        super.step();

    }
}

