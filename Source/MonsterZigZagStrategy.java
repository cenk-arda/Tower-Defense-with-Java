public class MonsterZigZagStrategy implements IMonsterStrategy {
    //TODO

    /**
     * @param position  Vector2D
     * @param direction Vector2D
     * @return Vector2D
     */
    @Override
    public Vector2D updateDirection(Vector2D position, Vector2D direction) {

        //midpoint of monster's square
        int monster_size = Commons.MonsterSize;

        int left_side = position.getIntX();

        int bottom_side = position.getIntY() + monster_size;
        int top_side = position.getIntY();
        int right_side = left_side + monster_size;

        int direction_x = direction.getIntX();
        int direction_y = direction.getIntY();


        if (direction_x == 1.0 && direction_y == -1.0) {
            if (right_side == 100 && bottom_side > 100) {
                direction = new Vector2D(-1.0, -1.0);
            } else if (top_side == 0) {
                direction = new Vector2D(1.0, 1.0);
            }

        } else if (direction_x == -1.0 && direction_y == -1.0) {
            if (left_side == 0.0) {
                direction = new Vector2D(1.0, -1.0);
            } else if (top_side == 300 && right_side > 100) {
                direction = new Vector2D(-1.0, 1.0);
            }
        } else if (direction_x == 1.0 && direction_y == 1.0) {
            if (bottom_side == 100 && left_side <= 300) {
                direction = new Vector2D(1.0, -1.0);
            } else if (right_side == 400) {
                direction = new Vector2D(-1.0, 1.0);
            }

        } else { // direction -1,1
            if (left_side == 300 && top_side < 300) {
                direction = new Vector2D(1.0, 1.0);
            } else if (bottom_side == 400) {
                direction = new Vector2D(-1.0, -1.0);
            }
        }

        return direction;
    }
}
