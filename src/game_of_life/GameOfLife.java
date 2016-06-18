package game_of_life;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Josef Stroleny
 */
public class GameOfLife implements IGameOfLife {
	private boolean[][] map;
	private ICondition condition;
	private final Random random;
	private int generation;

	public GameOfLife() {
		this.random = new Random();
		this.condition = new Condition();
		setSize(Settings.INIT_WIDTH, Settings.INIT_HEIGHT);
	}

	private int countNeighbours(int x, int y) {
		int neighbours = 0;

		int startPosX = (x - 1 < 0) ? x : x - 1;
		int startPosY = (y - 1 < 0) ? y : y - 1;
		int endPosX = (x + 1 > this.map[0].length - 1) ? x : x + 1;
		int endPosY = (y + 1 > this.map.length - 1) ? y : y + 1;

		for (int col = startPosY; col <= endPosY; col++) {
			for (int row = startPosX; row <= endPosX; row++) {
				if ((row != x || col != y) && this.map[col][row]) neighbours++;
			}
		}
		return neighbours;
	}

	private void restartGenerations() {
		generation = 0;
	}
	private void nextGeneration() { this.generation++; }

	@Override
	public void nextStep() {
		boolean[][] hlp = new boolean[map.length][map[0].length];

		for (int y = 0; y < map.length; y++) {
			for (int x = 0; x < map[0].length; x++) {
				int neighbours = countNeighbours(x, y);
				if (map[y][x]) {
					hlp[y][x] = condition.isSurvive(neighbours);
				} else{
					hlp[y][x] = condition.isBorn(neighbours);
				}
			}
		}
		nextGeneration();
		this.map = hlp;
	}

	@Override
	public int getGeneration() {
		return this.generation;
	}

	@Override
	public void clear() {
		for (int i = 0; i < this.map.length; i++) {
			Arrays.fill(this.map[i], false);
		}
		restartGenerations();
	}

	@Override
	public void fill() {
		for (int i = 0; i < this.map.length; i++) {
			Arrays.fill(this.map[i], true);
		}
		restartGenerations();
	}

	@Override
	public void random() {
		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[i].length; j++) {
				this.map[i][j] = random.nextBoolean();
			}
		}
		restartGenerations();
	}

	@Override
	public void setSize(int width, int height) {
		this.map = new boolean[height][width];
		restartGenerations();
	}

	@Override
	public void setMap(boolean[][] map) {
		this.map = map;
		restartGenerations();
	}

	@Override
	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	@Override
	public void setPoint(int x, int y, boolean value) {
		if (map.length > y) {
			if (map[y].length > x) map[y][x] = value;
		}
	}

	@Override
	public ICondition getCondition() {
		return this.condition;
	}

	@Override
	public boolean[][] getMap() {
		return this.map;
	}


}
