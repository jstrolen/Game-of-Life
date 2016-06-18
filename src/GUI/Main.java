package GUI;


import javax.swing.*;
import java.awt.*;

/**
 * Created by Josef Stroleny
 */
class Main extends JFrame {
	private static final long serialVersionUID = -680971580942918541L;

	public static void main(String[] args) {
		new Main();
	}
	
	private Main() {
		this.setTitle(Settings.NAME);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(Settings.INIT_WINDOW_WIDTH, Settings.INIT_WINDOW_HEIGHT);
		this.setLocationRelativeTo(null);
		init();

		this.setVisible(true);
	}

	private void init() {
		JPanel main = new JPanel();
		main.setLayout(new GridLayout(0, 2));
		MyPanel myPanel = new MyPanel();
		main.add(myPanel);
		main.add(new SettingsPanel(myPanel));
		
		this.add(main);
	}
}
