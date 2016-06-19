package game_of_life;

/**
 * Created by Josef Stroleny
 */
public interface ICondition {
    void setBorn(int[] born);

    void setSurvive(int[] survive);

    void addSurvive(int i);

    void addBorn(int i);

    void removeSurvive(int i);

    void removeBorn(int i);

    boolean isSurvive(int value);

    boolean isBorn(int value);
}
