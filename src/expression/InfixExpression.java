package calculator.model.expression;
import calculator.model.operator.*;
import java.util.*;
import stack.*;

public class InfixExpression extends Expression implements Evaluable
{
	public InfixExpression(String infixExpression)
	throws NullPointerException
	{
		super(infixExpression);
	}

	public PostfixExpression convertToPostfixExpression()
	{
		StringBuilder postfixExpression = new StringBuilder();
		CustomStack<String> stack = new ArrayListStack<>();
		Scanner scanner = new Scanner(this.expression);
		scanner.useLocale(Locale.ENGLISH);
		while(scanner.hasNext())
		{
			if(scanner.hasNextDouble())
				postfixExpression.append(scanner.nextDouble() + " ");
			else
			{
				String token = scanner.next();
				if(token.equals("("))
					stack.push(token);
				else if(OperatorFactory.isOperator(token))
				{
					if(!stack.isEmpty() && stack.peek().equals("("))
						stack.push(token);
					else
					{
						while(!stack.isEmpty() && !stack.peek().equals("(") &&
						OperatorFactory.compareOperators(token, stack.peek()) > 0)
							postfixExpression.append(stack.pop() + " ");
						
						stack.push(token);
					}
				}
				else if(token.equals(")"))
				{
					while(!stack.peek().equals("("))
						postfixExpression.append(stack.pop() + " ");
				}
			}
		}

		while(!stack.isEmpty())
			postfixExpression.append(stack.pop() + " ");
		
		return new PostfixExpression(postfixExpression.toString());
	}

	@Override
	public double evaluate()
	{
		return this.convertToPostfixExpression().evaluate();
	}
}