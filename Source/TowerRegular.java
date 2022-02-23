import java.awt.*;
import java.awt.geom.Ellipse2D;

public class TowerRegular extends Tower {

    //TODO
    Color color = Color.YELLOW;

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
            this.shootNearestMonster();
        }
    }

    /**
     * @param g Graphics
     */
    @Override
    public void paint(Graphics g) {
        //TODO
        g.setColor(color);

        g.fillOval(getPosition().getIntX() - 22, getPosition().getIntY() - 22, 45, 45);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.RED);
        float fl[] = {12.0f};
        g2d.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 12.0f, fl, 0.0f));
        g2d.draw(new Ellipse2D.Double(this.getPosition().getIntX() - this.getRange(), this.getPosition().getIntY() - this.getRange(), 2 * this.getRange(), 2 * this.getRange()));
    }
}
