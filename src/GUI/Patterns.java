package GUI;

/**
 * Created by Josef Stroleny
 */
class Patterns {
    public static boolean[][] block() {
        boolean[][] map = new boolean[100][100];
        map[24][24] = true; map[24][25] = true;
        map[25][24] = true; map[25][25] = true;
        return map;
    }
}
