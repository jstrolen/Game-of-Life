package GUI;

import java.awt.*;

/**
 * Created by Josef Stroleny
 */
class Settings {
    static final String NAME = "Game of Life";
    static final int INIT_WINDOW_WIDTH = 1200;
    static final int INIT_WINDOW_HEIGHT = 800;

    static final boolean INIT_SHOW_GRID = true;
    static final Color INIT_ALIVE_COLOR = Color.BLUE;
    static final Color INIT_DEAD_COLOR = Color.WHITE;
    static final Color INIT_GRID_COLOR = Color.DARK_GRAY;

    static final int MIN_SPEED = 1;
    static final int MAX_SPEED = 501;
    static final int INIT_SPEED = 50;

    static final int MIN_WIDTH = 1;
    static final int MAX_WIDTH = 401;

    static final int MIN_HEIGHT = 1;
    static final int MAX_HEIGHT = 401;

    static final int[] INIT_BORN = new int[]{3};
    static final int[] INIT_SURVIVE = new int[]{2, 3};
}
