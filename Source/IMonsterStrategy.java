public interface IMonsterStrategy {
    /**
     * @param position  Vector2D
     * @param direction Vector2D
     * @return Vector2D
     */
    Vector2D updateDirection(Vector2D position, Vector2D direction);
}
