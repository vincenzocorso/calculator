package calculator.controller;

import calculator.model.expression.*;
import java.util.*;
import java.text.*;
import java.awt.event.*;
import javax.swing.*;

public class KeypadListener implements ActionListener
{
	private final JLabel expressionText;
	private final JLabel resultText;

	public KeypadListener(JLabel expressionText, JLabel resultText)
	{
		this.expressionText = expressionText;
		this.resultText = resultText;
	}

	private static String translateExpressionToken(String token)
	{
		switch(token)
		{
			case "x": return "*";
			case "\u00F7": return "/";
			case "\u221A": return "sqrt";
			default: return token;
		}
	}

	private static String translateExpression(String expression)
	{
		StringBuilder result = new StringBuilder();
		Scanner scanner = new Scanner(expression);
		while(scanner.hasNext())
			result.append(translateExpressionToken(scanner.next()) + " ");
		
		return result.toString().replace(',', '.');
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand())
		{
			case "Operator":
				this.expressionText.setText(this.expressionText.getText().strip() + " " + ((JButton)e.getSource()).getText() + " ");
			break;

			case "Digit":
				this.expressionText.setText(this.expressionText.getText() + ((JButton)e.getSource()).getText());
			break;

			case "+/-":
				String input = this.expressionText.getText();
				if(!input.isEmpty() && !input.endsWith(" "))
				{
					StringBuilder s = new StringBuilder(this.expressionText.getText());
					int lastSpaceIndex = s.lastIndexOf(" ");
					s.insert(lastSpaceIndex + 1, "-");
					this.expressionText.setText(s.toString());
				}
			break;

			case ",":
				this.expressionText.setText(this.expressionText.getText() + ",");
			break;

			case "CE":
				this.expressionText.setText("");
				this.resultText.setText("");
			break;

			case "=":
				try
				{
					InfixExpression expression = new InfixExpression(translateExpression(this.expressionText.getText()));
					DecimalFormat decimalFormat = new DecimalFormat("#.########");
					this.resultText.setText(decimalFormat.format(expression.evaluate()));
				}
				catch(RuntimeException ex)
				{
					this.resultText.setText("Error");
				}
			break;
		}
	}
}