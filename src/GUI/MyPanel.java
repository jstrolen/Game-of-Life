package GUI;

import game_of_life.GameOfLife;
import game_of_life.IGameOfLife;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Josef Stroleny
 */
class MyPanel extends JPanel {
	private static final long serialVersionUID = 5373226414681225269L;
	private final IGameOfLife game;

	private boolean showGrid;
	private Color gridColor;
	private Color deadColor;
	private Color aliveColor;
	
	private Timer timer;
	private int speed;

	MyPanel() {
		this.game = new GameOfLife();
		this.game.getCondition().setSurvive(Settings.INIT_SURVIVE);
		this.game.getCondition().setBorn(Settings.INIT_BORN);

		this.speed = Settings.INIT_SPEED;
		this.showGrid = Settings.INIT_SHOW_GRID;
		this.deadColor = Settings.INIT_DEAD_COLOR;
		this.aliveColor = Settings.INIT_ALIVE_COLOR;
		this.gridColor = Settings.INIT_GRID_COLOR;

		random();
	}

	void start() {
		stop();
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				game.nextStep();
				redraw();
			}
		}, 0, speed);
	}

	void stop() {
		if (timer != null) {
			timer.cancel();
			timer = null;
		}
	}

	void step() {
		stop();
		game.nextStep();
		redraw();
	}

	private void redraw() {
		this.invalidate();
		this.repaint();
	}
	
	@Override
	public void paint(Graphics g0) {
		Graphics2D g = (Graphics2D) g0;
		g.setColor(deadColor);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		boolean[][] map = game.getMap();

		int partWidth = this.getWidth() / map[0].length;
		int partHeight = this.getHeight() / map.length;
		
		double wRatio = (double) this.getWidth() / (partWidth * map[0].length);
		double hRatio = (double) this.getHeight() / (partHeight * map.length);
		g.scale(wRatio, hRatio);

		g.setColor(aliveColor);
		for (int y = 0; y < map.length; y++) {
			for (int x = 0; x < map[0].length; x++) {
				if (map[y][x]) g.fillRect(partWidth * x, partHeight * y, partWidth, partHeight);
			}
		}

		if (showGrid) {
			g.setColor(gridColor);
			for (int y = 1; y <= map.length; y++) {
				g.drawLine(0, y * partHeight, this.getWidth(), y * partHeight);
			}
			for (int x = 1; x <= map[0].length; x++) {
				g.drawLine(x * partWidth, 0, x * partWidth, this.getHeight());
			}
		}
	}

	void setSpeed(int speed) {
		this.speed = speed;
		if (timer != null) start();
	}

	IGameOfLife getGame() {
		return game;
	}

	int getSpeed() {
		return this.speed;
	}


	void setGameSize(int width, int height) {
		stop();
		game.setSize(width, height);
		game.random();
		redraw();
	}

	void random() {
		stop();
		game.random();
		redraw();
	}

	void clear() {
		stop();
		game.clear();
		redraw();
	}

	void fill() {
		stop();
		game.fill();
		redraw();
	}
}
