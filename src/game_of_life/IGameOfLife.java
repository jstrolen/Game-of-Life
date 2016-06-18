package game_of_life;

/**
 * Created by Josef Stroleny
 */
public interface IGameOfLife {

    void nextStep();

    void clear();

    void fill();

    void random();

    void setSize(int width, int height);

    void setMap(boolean[][] map);

    void setPoint(int x, int y, boolean value);

    void setCondition(Condition condition);

    int getGeneration();

    ICondition getCondition();

    boolean[][] getMap();
}
