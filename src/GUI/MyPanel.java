package GUI;

import game_of_life.GameOfLife;
import game_of_life.IGameOfLife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Josef Stroleny
 */
class MyPanel extends JPanel implements MouseListener {
	private static final long serialVersionUID = 5373226414681225269L;
	private final IGameOfLife game;

	private boolean showGrid;
	private boolean showUsedCells;
	private Color gridColor;
	private Color deadColor;
	private Color aliveColor;
	private Color usedCellColor;
	
	private Timer timer;
	private int speed;

	private JLabel generation;

	MyPanel(JLabel generation) {
		super.addMouseListener(this);
		this.game = new GameOfLife();

		this.game.getCondition().setSurvive(Settings.INIT_SURVIVE);
		this.game.getCondition().setBorn(Settings.INIT_BORN);

		this.speed = Settings.INIT_SPEED;
		this.showGrid = Settings.INIT_SHOW_GRID;
		this.showUsedCells = Settings.INIT_SHOW_USED_CELLS;
		this.deadColor = Settings.INIT_DEAD_COLOR;
		this.aliveColor = Settings.INIT_ALIVE_COLOR;
		this.gridColor = Settings.INIT_GRID_COLOR;
		this.usedCellColor = Settings.INIT_USED_CELL_COLOR;
		this.generation = generation;

		random();
	}

	void start() {
		stop();
		game.start(speed);
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				redraw();
			}
		}, 0, 1000 / Settings.FPS);
	}

	void stop() {
		game.stop();

		if (timer != null) {
			timer.cancel();
			timer = null;
		}
	}

	void setSpeed(int speed) {
		stop();
		this.speed = speed;
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

		//Scale ratio
		//Not working with mouse switch
		//double wRatio = (double) this.getWidth() / (partWidth * map[0].length);
		//double hRatio = (double) this.getHeight() / (partHeight * map.length);
		//g.scale(wRatio, hRatio);

		if (showUsedCells) {
			g.setColor(usedCellColor);
			boolean[][] usedCells = game.getUsedCells();
			for (int y = 0; y < map.length; y++) {
				for (int x = 0; x < map[0].length; x++) {
					if (usedCells[y][x]) g.fillRect(partWidth * x, partHeight * y, partWidth, partHeight);
				}
			}
		}

		g.setColor(aliveColor);
		for (int y = 0; y < map.length; y++) {
			for (int x = 0; x < map[0].length; x++) {
				if (map[y][x]) g.fillRect(partWidth * x, partHeight * y, partWidth, partHeight);
			}
		}

		if (showGrid) {
			g.setColor(gridColor);
			for (int y = 1; y <= map.length; y++) {
				g.drawLine(0, y * partHeight, map[0].length * partWidth, y * partHeight);
			}
			for (int x = 1; x <= map[0].length; x++) {
				g.drawLine(x * partWidth, 0, x * partWidth, map.length * partHeight);
			}
		}
		generation.setText("" + game.getGeneration());
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

	IGameOfLife getGame() {
		return game;
	}

	int getSpeed() {
		return this.speed;
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		int partWidth = this.getWidth() / game.getMap()[0].length;
		int partHeight = this.getHeight() / game.getMap().length;
		int mouseX = e.getX();
		int mouseY = e.getY();
		int posX = mouseX / partWidth;
		int posY = mouseY / partHeight;
		game.switchPoint(posX, posY);
		redraw();
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
