package calculator.model.operator;

class SquareRootOperator implements UnaryOperatorStrategy
{
	@Override
	public double evaluate(double operand)
	{
		return Math.sqrt(operand);
	}

	@Override
	public String toString()
	{
		return "sqrt";
	}
}