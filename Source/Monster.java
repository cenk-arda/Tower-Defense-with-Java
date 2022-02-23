import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Monster extends Entity {
    //TODO
    private int health;
    private double speed;
    private final static int reward = 10;
    private Vector2D position; //top left corner
    private Vector2D direction;
    private IMonsterStrategy strategy;
    private int stepNum;
    private MonsterState monsterState = null;

    public Monster(int health, double speed, Vector2D position) {
        this.health = health;
        this.speed = speed;
        this.position = position;
        this.stepNum = 0;
    }

    /**
     * @return MonsterState
     */
    public MonsterState getMonsterState() {
        return monsterState;
    }

    /**
     * @param monsterState MonsterState
     */
    public void setMonsterState(MonsterState monsterState) {
        this.monsterState = monsterState;
    }

    /**
     * @return IMonsterStrategy
     */
    public IMonsterStrategy getStrategy() {
        return strategy;
    }

    /**
     * @param strategy IMonsterStrategy
     */
    public void setStrategy(IMonsterStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * @param direction Vector2D
     */
    public void setDirection(Vector2D direction) {
        this.direction = direction;
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
     * @return int
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * @return double
     */
    public double getSpeed() {
        return this.speed;
    }

    /**
     * @param sp double
     */
    public void setSpeed(double sp) {
        this.speed = sp;
    }

    /**
     * @return int
     */
    public int getReward() {
        return reward;
    }

    /**
     * @param i int
     */
    public void decrementHealth(int i) {
        health -= i;
    }

    /**
     *
     */
    @Override
    public void step() {
        //TODO
        //game has made some choice : random(0,1) to determine this monster's strategy.
        //circular strategy or zigzag strategy. monster does not know which. :
        //according to state, change its speed or health

        if (speed < 1) {
            this.stepNum++;

            if (stepNum >= 1 / speed) {
                this.direction = this.strategy.updateDirection(this.position, this.direction);
                this.setPosition(this.getPosition().add(this.direction));
                stepNum = 0;
            }
        }

        if (speed >= 1) {
            for (int i = 0; i < speed; i++) {
                this.direction = this.strategy.updateDirection(this.position, this.direction);
                this.setPosition(this.getPosition().add(this.direction));
            }
        }

        if (this.position.getIntX() == 100 - this.speed + 1 && this.position.getY() >= 300 && this.direction.getX() == -1.0) {
            Game.getInstance().decrementLives();
            Game.getInstance().aliveMonsters.remove(this);
        }

        if (this.monsterState != null) {
            this.monsterState.update();
        }


    }

    /**
     * @param g Graphics
     */
    @Override
    public void paint(Graphics g) {
        //TODO
        g.setColor(Color.ORANGE);
        g.fillRect(this.getPosition().getIntX(), this.getPosition().getIntY(), Commons.MonsterSize, Commons.MonsterSize);

        g.setColor(Color.WHITE);
        int offset = 0;
        char[] data = String.valueOf(this.getHealth()).toCharArray();
        int length = data.length;
        g.drawChars(data, offset, length, this.getPosition().getIntX() + Commons.MonsterSize / 5, this.getPosition().getIntY() + Commons.MonsterSize / 2);
        if (this.monsterState != null) this.monsterState.paint(g);
    }
}
