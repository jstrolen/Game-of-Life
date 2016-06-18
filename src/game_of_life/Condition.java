package game_of_life;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Josef Stroleny
 */
class Condition implements ICondition {
    private final Set<Integer> born;
    private final Set<Integer> survive;

    Condition() {
        survive = new HashSet<>();
        born = new HashSet<>();
    }

    @Override
    public void setBorn(int[] born) {
        this.born.clear();
        for (int i = 0; i < born.length; i++) {
            this.born.add(born[i]);
        }
    }

    @Override
    public void setSurvive(int[] survive) {
        this.survive.clear();
        for (int i = 0; i < survive.length; i++) {
            this.survive.add(survive[i]);
        }
    }

    @Override
    public void addSurvive(int i) {
        this.survive.add(i);
    }

    @Override
    public void addBorn(int i) {
        this.born.add(i);
    }

    @Override
    public void removeSurvive(int i) {
        this.survive.remove(i);
    }

    @Override
    public void removeBorn(int i) {
        this.born.remove(i);
    }

    @Override
    public boolean isSurvive(int value) {
        return this.survive.contains(value);
    }

    @Override
    public boolean isBorn(int value) {
        return this.born.contains(value);
    }
}
