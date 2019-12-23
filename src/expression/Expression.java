package calculator.model.expression;

public abstract class Expression implements Evaluable
{
	protected final String expression;

	public Expression(String expression)
	throws NullPointerException
	{
		if(expression == null)
			throw new NullPointerException();
		
		this.expression = expression;
	}

	public abstract double evaluate();

	@Override
	public String toString()
	{
		return expression;
	}
}