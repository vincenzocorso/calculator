package calculator.model.operator;

class ExponentiationOperator implements BinaryOperatorStrategy
{
	@Override
	public double evaluate(double firstOperand, double secondOperand)
	{
		return Math.pow(firstOperand, secondOperand);
	}

	@Override
	public String toString()
	{
		return "^";
	}
}