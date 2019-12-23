package calculator.view;

import calculator.view.component.*;
import calculator.controller.*;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.border.*;

public final class CalculatorGUI extends JFrame
{
	private final JPanel topPanel = new JPanel();
	private final JPanel screenPanel = new JPanel();
	private final JLabel expressionText = new JLabel();
	private final JLabel resultText = new JLabel();

	private final JPanel bottomPanel = new JPanel();

	private final JButton[] keypadButtons =
	{
		new CalculatorGreyButton("\u221A"), new CalculatorGreyButton("^"), new CalculatorGreyButton("CE", new Color(255, 145, 0)), new CalculatorWhiteButton("\u00F7"),
		new CalculatorGreyButton("7"), new CalculatorGreyButton("8"), new CalculatorGreyButton("9"), new CalculatorWhiteButton("x"),
		new CalculatorGreyButton("4"), new CalculatorGreyButton("5"), new CalculatorGreyButton("6"), new CalculatorWhiteButton("-"),
		new CalculatorGreyButton("1"), new CalculatorGreyButton("2"), new CalculatorGreyButton("3"), new CalculatorWhiteButton("+"),
		new CalculatorGreyButton("+/-"), new CalculatorGreyButton("0"), new CalculatorGreyButton(","), new CalculatorOrangeButton("=")
	};
	private final String[] keypadActions =
	{
		"Operator", "Operator", "CE", "Operator",
		"Digit", "Digit", "Digit", "Operator",
		"Digit", "Digit", "Digit", "Operator",
		"Digit", "Digit", "Digit", "Operator",
		"+/-", "Digit", ",", "="
	};

	private CalculatorGUI()
	{
		this.setupContentPane();
		this.setupCalculatorScreen();
		this.setupKeypad();
	}

	private void setupContentPane()
	{
		Container contentPane = this.getContentPane();
		
		contentPane.add(this.topPanel, BorderLayout.NORTH);
		this.topPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
		this.topPanel.setBorder(new EmptyBorder(14, 15, 25, 15));
		this.topPanel.setPreferredSize(new Dimension(400, 150));
		this.topPanel.setBackground(Color.BLACK);

		contentPane.add(this.bottomPanel, BorderLayout.CENTER);
		this.bottomPanel.setLayout(new GridLayout(5, 4, 0, 0));
	}

	private void setupKeypad()
	{
		Dimension defaultButtonDimension = new Dimension(100, 100);
		Font defaultButtonFont = new Font("Arial", Font.PLAIN, 40);
		for(int i = 0; i < this.keypadButtons.length; i++)
		{
			this.bottomPanel.add(this.keypadButtons[i]);
			this.keypadButtons[i].setPreferredSize(defaultButtonDimension);
			this.keypadButtons[i].setFont(defaultButtonFont);
			this.keypadButtons[i].setBorderPainted(false);
			this.keypadButtons[i].setActionCommand(this.keypadActions[i]);
			this.keypadButtons[i].addActionListener(new KeypadListener(this.expressionText, this.resultText));
		}
	}

	private void setupCalculatorScreen()
	{
		this.topPanel.add(this.screenPanel);
		this.screenPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 11));
		this.screenPanel.setPreferredSize(new Dimension(370, 100));
		this.screenPanel.setBackground(Color.BLACK);
			
		this.screenPanel.add(this.expressionText);
		this.expressionText.setPreferredSize(new Dimension(370, 22));
		this.expressionText.setFont(new Font("Arial", Font.PLAIN, 20));
		this.expressionText.setForeground(Color.WHITE);
		this.expressionText.setHorizontalAlignment(SwingConstants.RIGHT);

		this.screenPanel.add(this.resultText);
		this.resultText.setPreferredSize(new Dimension(370, 67));
		this.resultText.setFont(new Font("Arial", Font.PLAIN, 60));
		this.resultText.setForeground(new Color(255, 87, 0));
		this.resultText.setHorizontalAlignment(SwingConstants.RIGHT);
	}

	public static void createAndShowGUI()
	{
		CalculatorGUI frame = new CalculatorGUI();
		frame.setUndecorated(true);
		frame.setSize(new Dimension(400, 650));
		frame.setShape(new RoundRectangle2D.Double(0, 0, frame.getWidth(), frame.getHeight(), 100, 100));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args)
	{
		createAndShowGUI();
	}
}

class CalculatorGreyButton extends JGradientButton
{
	public CalculatorGreyButton(String text, Color foregroundColor)
	{
		super(text, new Color(113, 113, 113, 217), new Color(0, 0, 0, 217));
		this.setForeground(foregroundColor);
	}

	public CalculatorGreyButton(String text)
	{
		this(text, Color.WHITE);
	}
}

class CalculatorWhiteButton extends JButton
{
	public CalculatorWhiteButton(String text)
	{
		super(text);
		this.setForeground(Color.BLACK);
		this.setBackground(Color.WHITE);
	}
}

class CalculatorOrangeButton extends JGradientButton
{
	public CalculatorOrangeButton(String text)
	{
		super(text, new Color(255, 88, 0), new Color(255, 166, 0));
		this.setForeground(Color.WHITE);
	}
}