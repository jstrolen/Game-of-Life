package game_of_life;

/**
 * Created by Josef Stroleny
 */
public interface IGameOfLife {
    void start(int speed);

    void stop();

    void nextStep();

    void clear();

    void fill();

    void random();

    void setSize(int width, int height);

    void setMap(boolean[][] map);

    void setPoint(int x, int y, boolean value);

    void switchPoint(int x, int y);

    int getGeneration();

    ICondition getCondition();

    boolean[][] getMap();

    boolean[][] getUsedCells();
}
