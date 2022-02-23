public abstract class MonsterState implements IPaintable {
    //TODO
    Monster mon;

    /**
     * @param m Monster
     */
    public MonsterState(Monster m) {
        this.mon = m;
    }

    /**
     *
     */
    public abstract void update();
}
