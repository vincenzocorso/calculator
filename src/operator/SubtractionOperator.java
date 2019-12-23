package calculator.model.operator;

class SubtractionOperator implements BinaryOperatorStrategy
{
	@Override
	public double evaluate(double firstOperand, double secondOperand)
	{
		return firstOperand - secondOperand;
	}

	@Override
	public String toString()
	{
		return "-";
	}
}