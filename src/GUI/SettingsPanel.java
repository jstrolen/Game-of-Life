package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Josef Stroleny
 */
class SettingsPanel extends JPanel {
	private static final long serialVersionUID = -1624527071656166845L;
	private final MyPanel myPanel;

	SettingsPanel(MyPanel myPanel) {
		super();
		this.myPanel = myPanel;
		this.setLayout(new GridLayout(0, 1));
		this.setBackground(Color.WHITE);
		
		//SIZES
		JPanel size = new JPanel();
		size.setBackground(Color.WHITE);
		size.setLayout(new GridLayout(0, 2));
		//Height
		JLabel label = new JLabel("Height: ");
		size.add(label);
		JSlider height = new JSlider(Settings.MIN_WIDTH, Settings.MAX_WIDTH, myPanel.getGame().getMap()[0].length);
		height.setBackground(Color.WHITE);
		height.setMajorTickSpacing(100);
		height.setMinorTickSpacing(50);
		height.setPaintTicks(true);
		height.setPaintLabels(true);
		height.addChangeListener(e ->
				SettingsPanel.this.myPanel.setGameSize(((JSlider) e.getSource()).getValue(),
						SettingsPanel.this.myPanel.getGame().getMap().length));
		size.add(height);
		//Width
		label = new JLabel("Width: ");
		size.add(label);
		JSlider width = new JSlider(Settings.MIN_HEIGHT, Settings.MAX_HEIGHT, myPanel.getGame().getMap().length);
		width.setBackground(Color.WHITE);
		width.setMajorTickSpacing(100);
		width.setMinorTickSpacing(50);
		width.setPaintTicks(true);
		width.setPaintLabels(true);
		width.addChangeListener(e ->
				SettingsPanel.this.myPanel.setGameSize(SettingsPanel.this.myPanel.getGame().getMap()[0].length,
				((JSlider) e.getSource()).getValue()));
		size.add(width);
		JPanel hlpPanel = new JPanel();
		hlpPanel.setBackground(Color.WHITE);
		hlpPanel.add(size);
		this.add(hlpPanel);
		
		//BUTTONS
		JPanel buttons = new JPanel();
		buttons.setBackground(Color.WHITE);
		buttons.setLayout(new GridLayout(0, 3));
		//Clear
		JButton button = new JButton("Clear");
		button.addActionListener(e -> SettingsPanel.this.myPanel.clear());
		buttons.add(button);
		//Fill
		button = new JButton("Fill");
		button.addActionListener(e -> SettingsPanel.this.myPanel.fill());
		buttons.add(button);
		//Random
		button = new JButton("Random");
		button.addActionListener(e -> SettingsPanel.this.myPanel.random());
		buttons.add(button);
		hlpPanel = new JPanel();
		hlpPanel.setBackground(Color.WHITE);
		hlpPanel.add(buttons);
		this.add(hlpPanel);
		
		//Survive
		label = new JLabel("Survive: ");
		JPanel survive = new JPanel();
		survive.setBackground(Color.WHITE);
		survive.setLayout(new GridLayout(1, 0));
		for (int i = 0; i <= 8; i++) {
			final int number = i;
			final JCheckBox box = new JCheckBox("" + number);
			box.setBackground(Color.WHITE);
			box.setSelected(this.myPanel.getGame().getCondition().isSurvive(number));
			box.addActionListener(e -> {
					if (box.isSelected()) SettingsPanel.this.myPanel.getGame().getCondition().addSurvive(number);
					else SettingsPanel.this.myPanel.getGame().getCondition().removeSurvive(number);
			});
			survive.add(box);
		}
		hlpPanel = new JPanel();
		hlpPanel.setBackground(Color.WHITE);
		hlpPanel.add(label);
		hlpPanel.add(survive);
		this.add(hlpPanel);

		//Born
		label = new JLabel("Born: ");
		JPanel born = new JPanel();
		born.setBackground(Color.WHITE);
		born.setLayout(new GridLayout(1, 0));
		for (int i = 0; i <= 8; i++) {
			final int number = i;
			final JCheckBox box = new JCheckBox("" + number);
			box.setBackground(Color.WHITE);
			box.setSelected(this.myPanel.getGame().getCondition().isBorn(number));
			box.addActionListener(e -> {
					if (box.isSelected()) SettingsPanel.this.myPanel.getGame().getCondition().addBorn(number);
					else SettingsPanel.this.myPanel.getGame().getCondition().removeBorn(number);
			});
			born.add(box);
		}
		hlpPanel = new JPanel();
		hlpPanel.setBackground(Color.WHITE);
		hlpPanel.add(label);
		hlpPanel.add(born);
		this.add(hlpPanel);

		
		//SPEED
		JSlider speed = new JSlider(Settings.MIN_SPEED, Settings.MAX_SPEED, myPanel.getSpeed());
		speed.setBackground(Color.WHITE);
		speed.setMajorTickSpacing(100);
		speed.setMinorTickSpacing(20);
		speed.setPaintTicks(true);
		speed.setPaintLabels(true);
		speed.addChangeListener(e -> SettingsPanel.this.myPanel.setSpeed(((JSlider) e.getSource()).getValue()));
		size.add(height);
		hlpPanel = new JPanel();
		hlpPanel.setBackground(Color.WHITE);
		hlpPanel.add(speed);
		this.add(hlpPanel);
		
		//CONTROLLERS
		JPanel controllers = new JPanel();
		controllers.setBackground(Color.WHITE);
		controllers.setLayout(new GridLayout(1, 0));

		button = new JButton("Stop");
		button.setBackground(Color.WHITE);
		button.addActionListener(e -> SettingsPanel.this.myPanel.stop());
		controllers.add(button);
		button = new JButton("Start");
		button.setBackground(Color.WHITE);
		button.addActionListener(e -> SettingsPanel.this.myPanel.start());
		controllers.add(button);
		button = new JButton("Step");
		button.setBackground(Color.WHITE);
		button.addActionListener(e -> SettingsPanel.this.myPanel.step());
		controllers.add(button);
		hlpPanel = new JPanel();
		hlpPanel.setBackground(Color.WHITE);
		hlpPanel.add(controllers);
		this.add(hlpPanel);
	}

}
