package calculator.model.operator;
import java.util.*;

public abstract class OperatorFactory
{
	private static final List<UnaryOperatorStrategy> unaryOperators = Arrays.asList
	(
		new SquareRootOperator()
	);

	private static final List<BinaryOperatorStrategy> binaryOperators = Arrays.asList
	(
		new ExponentiationOperator(), new DivisionOperator(), new MultiplicationOperator(),
		new SubtractionOperator(), new AdditionOperator()
	);

	private OperatorFactory() {}

	public static boolean isUnaryOperator(String operator)
	{
		for(UnaryOperatorStrategy o : OperatorFactory.unaryOperators)
			if(o.toString().equals(operator))
				return true;
		
		return false;
	}

	public static boolean isBinaryOperator(String operator)
	{
		for(BinaryOperatorStrategy o : OperatorFactory.binaryOperators)
			if(o.toString().equals(operator))
				return true;

		return false;
	}

	public static boolean isOperator(String operator)
	{
		if(OperatorFactory.isBinaryOperator(operator) || OperatorFactory.isUnaryOperator(operator))
			return true;

		return false;
	}

	public static int compareOperators(String firstOperator, String secondOperator)
	throws IllegalOperatorException
	{
		OperatorStrategy operator1 = OperatorFactory.getOperator(firstOperator);
		OperatorStrategy operator2 = OperatorFactory.getOperator(secondOperator);

		if(operator1 instanceof UnaryOperatorStrategy)
		{
			if(operator2 instanceof BinaryOperatorStrategy)
				return -1;
			else
				return 0;
		}
		else
		{
			if(operator2 instanceof BinaryOperatorStrategy)
			{
				int firstIndex = OperatorFactory.binaryOperators.indexOf(operator1);
				int secondIndex = OperatorFactory.binaryOperators.indexOf(operator2);

				if(firstIndex < secondIndex)
					return -1;
				else if(firstIndex > secondIndex)
					return 1;
				else
					return 0;
			}
			else
				return 1;
		}
	}

	public static UnaryOperatorStrategy getUnaryOperator(String operator)
	throws IllegalOperatorException
	{
		for(UnaryOperatorStrategy o : OperatorFactory.unaryOperators)
			if(o.toString().equals(operator))
				return o;
		
		throw new IllegalOperatorException();
	}

	public static BinaryOperatorStrategy getBinaryOperator(String operator)
	throws IllegalOperatorException
	{
		for(BinaryOperatorStrategy o : OperatorFactory.binaryOperators)
			if(o.toString().equals(operator))
				return o;
		
		throw new IllegalOperatorException();
	}

	public static OperatorStrategy getOperator(String operator)
	throws IllegalOperatorException
	{
		if(OperatorFactory.isUnaryOperator(operator))
			return OperatorFactory.getUnaryOperator(operator);
		else
			return OperatorFactory.getBinaryOperator(operator);
	}
}

class IllegalOperatorException extends RuntimeException
{
	public IllegalOperatorException() {}

	public IllegalOperatorException(String msg)
	{
		super(msg);
	}
}