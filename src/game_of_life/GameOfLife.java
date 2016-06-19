package game_of_life;

import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Josef Stroleny
 */
public class GameOfLife implements IGameOfLife, Runnable {
	private boolean[][] map;
	private boolean[][] hlpMap;
	private ICondition condition;
	private final Random random;
	private int generation;
	private Timer timer;
	private int index;
	private int maxIndex;
	private boolean stop;

	public GameOfLife() {
		this.random = new Random();
		this.condition = new Condition();
		setSize(Settings.INIT_WIDTH, Settings.INIT_HEIGHT);
	}

	@Override
	public void nextStep() {
		this.index = 0;
		this.maxIndex = this.map.length * this.map[0].length;
		hlpMap = new boolean[map.length][map[0].length];

		Thread[] threads = new Thread[4];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(this);
		}
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (Exception e) {}
		}
		nextGeneration();
		this.map = hlpMap;
	}

	private synchronized int getWork() {
		if (stop) return -1;
		if (index < maxIndex) return index++;
		else return -1;
	}

	@Override
	public void run() {
		int index;
		while((index = getWork()) >= 0 && !stop) {
			int x = index % this.map[0].length;
			int y = index / this.map[0].length;
			int neighbours = countNeighbours(x, y);
			if (map[y][x]) {
				hlpMap[y][x] = condition.isSurvive(neighbours);
			} else {
				hlpMap[y][x] = condition.isBorn(neighbours);
			}
		}
	}

	private int countNeighbours(int x, int y) {
		if (stop) return -1;
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

	@Override
	public void start(int speed) {
		stop();
		stop = false;
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				nextStep();
			}
		}, 0, 1000 / speed);
	}

	@Override
	public void stop() {
		stop = true;
		if (timer != null) {
			timer.cancel();
			timer = null;
		}
	}

	@Override
	public void clear() {
		stop();
		for (int i = 0; i < this.map.length; i++) {
			Arrays.fill(this.map[i], false);
		}
		restartGenerations();
	}

	@Override
	public void fill() {
		stop();
		for (int i = 0; i < this.map.length; i++) {
			Arrays.fill(this.map[i], true);
		}
		restartGenerations();
	}

	@Override
	public void random() {
		stop();
		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[i].length; j++) {
				this.map[i][j] = random.nextBoolean();
			}
		}
		restartGenerations();
	}

	@Override
	public void setSize(int width, int height) {
		stop();
		this.map = new boolean[height][width];
		restartGenerations();
	}

	@Override
	public void setMap(boolean[][] map) {
		stop();
		this.map = map;
		restartGenerations();
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

	private void restartGenerations() {
		generation = 0;
	}

	private void nextGeneration() { this.generation++; }

	@Override
	public int getGeneration() {
		return this.generation;
	}
}
