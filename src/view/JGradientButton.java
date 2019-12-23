package calculator.view.component;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class JGradientButton extends JButton
{
	protected Color color1;
	protected Color color2;

	public JGradientButton(String text, Color color1, Color color2)
	{
		super(text);
		this.color1 = color1;
		this.color2 = color2;
		this.setContentAreaFilled(false);
		this.setFocusPainted(false);
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g.create();
		GradientPaint gradient = new GradientPaint(new Point2D.Double(0,0), color1,
									new Point2D.Double(this.getWidth(), this.getHeight()), color2);
		g2.setPaint(gradient);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2.dispose();
		super.paintComponent(g);
	}
}