package calculator.model.expression;
import calculator.model.operator.*;
import java.util.*;
import stack.*;

public class PostfixExpression extends Expression implements Evaluable
{
	public PostfixExpression(String postfixExpression)
	throws NullPointerException
	{
		super(postfixExpression);
	}

	@Override
	public double evaluate()
	{
		CustomStack<Double> operands = new ArrayListStack<>();
		Scanner scanner = new Scanner(this.expression);
		scanner.useLocale(Locale.ENGLISH);
		while(scanner.hasNext())
		{
			if(scanner.hasNextDouble())
				operands.push(scanner.nextDouble());
			else
			{
				String token = scanner.next();
				if(OperatorFactory.isUnaryOperator(token))
					operands.push(OperatorFactory.getUnaryOperator(token).evaluate(operands.pop()));
				else if(OperatorFactory.isBinaryOperator(token))
				{
					double secondOperand = operands.pop();
					double result = OperatorFactory.getBinaryOperator(token).evaluate(operands.pop(), secondOperand);
					operands.push(result);
				}
			}
		}

		return operands.pop();
	}
}