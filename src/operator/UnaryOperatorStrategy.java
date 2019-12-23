package calculator.model.operator;

public interface UnaryOperatorStrategy extends OperatorStrategy
{
	public abstract double evaluate(double operand);
}