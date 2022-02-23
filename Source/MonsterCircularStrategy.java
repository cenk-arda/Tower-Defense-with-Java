import java.util.Vector;

public class MonsterCircularStrategy implements IMonsterStrategy {
    //TODO

    /**
     * @param position  Vector2D
     * @param direction Vector2D
     * @return Vector2D
     */
    @Override
    public Vector2D updateDirection(Vector2D position, Vector2D direction) { //returns new direction vector according to provided current position

        direction.normalize();
        if (direction.getX() == 1.0) {
            if (position.getIntX() == 350 - Commons.MonsterSize / 2) {
                direction = new Vector2D(0.0, 1.0);
            }
        } else if (direction.getY() == 1.0) {
            if (position.getIntY() >= 350 - Commons.MonsterSize / 2) {
                direction = new Vector2D(-1.0, 0.0);
            }
        } else if (direction.getY() == -1.0) {
            if (position.getIntY() == 50 - Commons.MonsterSize / 2) {
                direction = new Vector2D(1.0, 0.0);
            }
        } else {
            direction = new Vector2D(-1.0, 0.0);
        }

        return direction;

    }
}
