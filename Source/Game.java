import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.util.*;

public class Game {

    private static final Game _inst = new Game();

    public static Game getInstance() {
        return _inst;
    }

    //TODO
    private GamePanel gamePanel = Display.getInstance().getGamePanel();
    private InfoPanel infoPanel = Display.getInstance().getInfoPanel();
    private boolean wave_changed;
    private static Timer timer;
    private int gold;
    private int lives;
    private int wave;
    private int monsterCount;
    private int stepNumber;
    private int kills;
    public ArrayList<Monster> aliveMonsters = new ArrayList<Monster>();
    public ITowerFactory towerFactory;
    public ArrayList<Tower> towers = new ArrayList<Tower>();

    /**
     * @param b boolean
     */
    public void setWaveChanged(boolean b) {
        wave += 1;
        this.wave_changed = true;
    }

    /**
     * @param gain int
     */
    public void incrementGold(int gain) {
        this.gold += gain;
    }

    /**
     * @param loss int
     */
    public void decrementGold(int loss) {
        this.gold -= loss;
    }

    /**
     *
     */
    public void updateMonsterCount() {
        this.monsterCount = this.aliveMonsters.size();
    }

    /**
     * @return int
     */
    public int getstepNumber() {
        return stepNumber;
    }

    /**
     * @param i int
     */
    public void incrementKill(int i) {
        this.kills += i;
    }

    /**
     *
     */
    public void decrementLives() {
        lives -= 1;
        if (lives == 0) {
            infoPanel.setCurrentWaveLabel("0");
            timer.stop();
        }
    }

    public Game() {
        //TODO
        this.gold = 25;
        this.lives = 3;
        this.wave = 0;
        this.monsterCount = wave;
        this.stepNumber = 0;
        this.kills = 0;
    }

    /**
     * @param g Graphics
     */
    public void paint(Graphics g) {
        //TODO
        String gold_label = String.valueOf(gold);
        String lives_label = String.valueOf(lives);
        String kills_label = String.valueOf(kills);
        String wave_label = String.valueOf(wave);
        infoPanel.setCurrentGoldLabel(gold_label);
        infoPanel.setCurrentLivesLabel(lives_label);
        infoPanel.setCurrentKillsLabel(kills_label);
        infoPanel.setCurrentWaveLabel(wave_label);


        for (Monster aliveMonster : this.aliveMonsters) {
            aliveMonster.paint(g);
        }
        for (Tower tower : this.towers) {
            tower.paint(g);
        }
    }

    /**
     * @param factory ITowerFactory
     * @param point   Vector2D
     */
    public void setTower(ITowerFactory factory, Vector2D point) {
        this.towerFactory = factory;
        Tower t = factory.createTower(point);
        t.setPosition(point);
        t.centralizePosition();
        boolean isOccupied = false;
        if (this.gold >= t.getCost()) {
            for (Tower tw : this.towers) {
                if (tw.getPosition().getX() == t.getPosition().getX() && tw.getPosition().getY() == t.getPosition().getY()) {
                    isOccupied = true;
                    break;
                }
            }
            if (!isOccupied) {
                decrementGold(t.getCost());
                this.towers.add(t);
            }

        }
    }

    /**
     *
     */
    public void step() {
        //TODO
        //things to be done at each step. timer calls this periodically.

        stepNumber++;
        this.updateMonsterCount();
        if (monsterCount == 0) {
            setWaveChanged(true);
        }
        if (this.wave_changed) { //new wave conditions
            Random ran = new Random();
            for (int i = 0; i < this.wave; i++) {
                double x = Commons.StartX + ran.nextDouble() * (Commons.StartWidth - Commons.MonsterSize);
                double y = Commons.StartY + ran.nextDouble() * (Commons.StartHeight - Commons.MonsterSize);
                Vector2D position = new Vector2D(x, y);
                Monster m = new Monster(100 + this.wave * 20, 1f, position);
                int strategy_no = ran.nextInt(2);
                if (strategy_no == 1) {
                    m.setDirection(new Vector2D(0.0, -1.0));
                    m.setStrategy(new MonsterCircularStrategy());
                } else {
                    m.setDirection(new Vector2D(1.0, -1.0));
                    m.setStrategy(new MonsterZigZagStrategy());
                }
                this.aliveMonsters.add(m);
            }
            this.wave_changed = false;
        }

        for (int i = 0; i < aliveMonsters.size(); i++) {
            Monster m = aliveMonsters.get(i);
            Vector2D position = m.getPosition();
            double x_index = position.getX();
            double y_index = position.getY();

            //there is a chance of changing strategy at each corner (excluding starting zone)
            Random r = new Random();
            boolean willChange = r.nextBoolean();

            if (willChange) {
                Vector2D changedDirectionCirc = null;
                Vector2D changedDirectionZig = null;
                if ((x_index > 40 && x_index < 100) && y_index > 0 && y_index < 60) {//first region
                    changedDirectionCirc = new Vector2D(1.0, 0.0);
                    changedDirectionZig = new Vector2D(1.0, -1.0);
                }

                if ((x_index > 300 && x_index < 350) && (y_index > 0 && y_index < 60)) { //second region
                    changedDirectionCirc = new Vector2D(0.0, 1.0);
                    changedDirectionZig = new Vector2D(1.0, 1.0);
                }

                if (x_index > 300 && x_index < 350 && y_index > 300 && y_index < 350) { //third region
                    changedDirectionCirc = new Vector2D(-1.0, 0.0);
                    changedDirectionZig = new Vector2D(-1.0, 1.0);
                }

                if (changedDirectionZig != null) {
                    Random ran = new Random();
                    int strategy_no = ran.nextInt(2);
                    IMonsterStrategy st = m.getStrategy();
                    if (strategy_no == 0) {
                        if (st.getClass() == MonsterZigZagStrategy.class) {
                            m.setDirection(changedDirectionCirc);
                            m.setStrategy(new MonsterCircularStrategy());
                        }
                    } else {
                        if (st.getClass() == MonsterCircularStrategy.class) {
                            m.setDirection(changedDirectionZig);
                            m.setStrategy(new MonsterZigZagStrategy());
                        }
                    }
                }
            }

            m.step();

        }
        for (Tower t : this.towers) {

            if (t.getKillNumber() == 10 && t.getGrade() == 0) {
                t.setGrade(1);
                Tower decorated = new TowerDecoratorGrade1(t);
                int a = this.towers.indexOf(t);
                this.towers.set(a, decorated);

            } else if (t.getKillNumber() == 25 && t.getGrade() == 1) {
                t.setGrade(2);
                Tower decorated = new TowerDecoratorGrade2(t);
                int a = this.towers.indexOf(t);
                this.towers.set(a, decorated);

            } else if (t.getKillNumber() == 50 && t.getGrade() == 2) {
                t.setGrade(3);
                Tower decorated = new TowerDecoratorGrade3(t);
                int a = this.towers.indexOf(t);
                this.towers.set(a, decorated);
            }

            t.step();

        }

        gamePanel.repaint();
    }

    //You can make changes

    /**
     *
     */
    public static void startGame() {
        Game.getInstance().wave = 0;
        Game.getInstance().wave_changed = true;
        Display.getInstance().setVisible(true);

        Display.getInstance().getInfoPanel().setCurrentGoldLabel("25");
        //Optional additions

        timer = new Timer(10, actionEvent -> {
            Game.getInstance().step();

            //Optional additions

        });
        timer.start();


    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Game::startGame);
    }


}
