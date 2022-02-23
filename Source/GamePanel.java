import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class GamePanel extends JPanel {
    private ITowerFactory factory;

    public GamePanel() {
        this.setBackground(Color.DARK_GRAY);

        this.setFocusable(true); //For keyboard and mouse actions
        this.requestFocus();

        //Optional
        //Can be used to add Towers
        //Remove if not used
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.isControlDown()) { // Ice

                    double x = getMousePosition().getX();
                    double y = getMousePosition().getY();
                    factory = new TowerIceFactory();
                    Vector2D coordinates = new Vector2D(x, y);
                    Game.getInstance().setTower(factory, coordinates);

                } else if (e.isAltDown()) { // Poison

                    double x = getMousePosition().getX();
                    double y = getMousePosition().getY();
                    factory = new TowerPoisonFactory();
                    Vector2D coordinates = new Vector2D(x, y);
                    Game.getInstance().setTower(factory, coordinates);

                } else if (e.isAltGraphDown()) { // Regular

                    double x = getMousePosition().getX();
                    double y = getMousePosition().getY();
                    factory = new TowerRegularFactory();
                    Vector2D coordinates = new Vector2D(x, y);
                    Game.getInstance().setTower(factory, coordinates);
                }

                //Optional

            }
        });


    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Commons.GamePanelWidth, Commons.GameHeight);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        //Optional
        //You can make changes to the visuals
        //This is just an example
        g.setColor(Color.CYAN);
        g.fillRect(Commons.StartX, Commons.StartY, Commons.StartWidth, Commons.StartHeight);

        g.setColor(Color.WHITE);
        g.drawChars("Start Zone".toCharArray(), 0, 10, Commons.StartX, Commons.StartY + 12);
        g.setColor(Color.WHITE);
        g.drawRect(Commons.TowerZoneX, Commons.TowerZoneY, Commons.TowerZoneWidth, Commons.TowerZoneHeight);


        //Optional
        //Maybe some additional Drawings


        //Draw Grid Lines
        g.setColor(Color.WHITE);

        for (int i = 1; i < 4; i++) {
            g.drawLine(Commons.TowerZoneX, Commons.TowerZoneY + (Commons.TowerZoneDivideLength * i),
                    Commons.TowerZoneX + Commons.TowerZoneWidth,
                    Commons.TowerZoneY + (Commons.TowerZoneDivideLength * i));
            g.drawLine(Commons.TowerZoneX + (Commons.TowerZoneDivideLength * i), Commons.TowerZoneY,
                    Commons.TowerZoneX + (Commons.TowerZoneDivideLength * i),
                    Commons.TowerZoneY + Commons.TowerZoneHeight);
        }

        //TODO
        g.setColor(Color.PINK);
        g.fillRect(Commons.StartX, Commons.StartY, Commons.MonsterSize, Commons.MonsterSize);
        //Maybe some additional Drawings
        Game.getInstance().paint(g);
    }
}
