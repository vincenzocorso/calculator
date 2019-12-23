package calculator.model.operator;

public interface BinaryOperatorStrategy extends OperatorStrategy
{
	public abstract double evaluate(double firstOperand, double secondOperand);
}