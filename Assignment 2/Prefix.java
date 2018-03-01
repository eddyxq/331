import java.util.Scanner;
/**
 * This class evaluates prefix expressions
 * @author Eddy Qiang - CPSC 331 - T03
 * @version 1.0
 * @since February 28, 2018
 */
public class Prefix 
{
	public static int evaluate(Scanner sc)
	{
		// return 0 if reached end
		if (!sc.hasNext())
			return 0;
		// check if next character is a operand, if so return it
		if (sc.hasNextInt())
			return sc.nextInt();
		// otherwise it must be a operator
		else
		{
			String letter = sc.next();
			// a and b are operands
			int a = evaluate(sc);
			int b = evaluate(sc);
			// the return value
			int result = 0;
			// determine what operator was scanned
			switch (letter)
			{
			case "+":
				result = a + b;
				break;
			case "-":
				result = a - b;
				break;
			case "*":
				result = a * b;
				break;
			case "/":
				result = a / b;
				break;
			}
			return result;	
		}
	}
}