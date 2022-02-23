import java.awt.*;

public class MonsterPoisonState extends MonsterState {
    int remaining;

    /**
     * @param m Monster
     */
    public MonsterPoisonState(Monster m) {
        super(m);
        this.remaining = 3;
    }

    //TODO

    /**
     *
     */
    @Override
    public void update() {
        //TODO
        if (remaining > 0) {

            if (remaining == 3) {
                mon.decrementHealth(5);
                if (mon.getHealth() <= 0) {
                    Game.getInstance().incrementKill(1);
                    Game.getInstance().incrementGold(mon.getReward());

                    Game.getInstance().aliveMonsters.remove(mon);
                }
            }
            remaining--;
        }
        if (remaining == 0) {
            mon.setMonsterState(null);
        }
    }

    /**
     * @param g Graphics
     */
    @Override
    public void paint(Graphics g) {
        //TODO
        if (remaining > 0) {
            g.setColor(Color.GREEN);
            g.fillRect(mon.getPosition().getIntX(), mon.getPosition().getIntY(), Commons.MonsterSize, Commons.MonsterSize);
        }
    }
}
