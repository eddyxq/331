import java.util.Scanner;
import java.util.ArrayList;
import java.text.ParseException;
/**
 * This class is a parser that parses an arithmetic 
 * expression into a binary expression tree
 * @author Eddy Qiang - CPSC 331 - T03
 * @version 1.0
 * @since Mar 20, 2018
 */
public class ExpressionTree 
{
	private ExpTreeNode root;    
	private int nextToken;			  
	ArrayList<String> list = new ArrayList<String>();
	/**
	 * This method creates a parse tree from an expression
	 * @param line String containing the expression
	 * @throws ParseException If an error occurs during parsing,
     * the location of the error is included in the thrown exception
	 */
	public void parse(String line) throws ParseException 
	{
		initializeList(line);
		root = expression();
	}
	/**
	 * This method evaluates the expression tree and returns a integer result
	 * @return The result as an integer
	 */
	public int evaluate() 
	{ 
		return new Prefix().evaluate(new Scanner(prefix()));
	}
	/**
	 * This method returns a post-fix representation of the expression
	 * @return The post-fix expression as a string
	 */
	public String postfix() 
	{
		return generateExpression(root, "postOrder");
	}
	/**
	 * This method returns a prefix representation of the expression
	 * @return The prefix expression as a string
	 */
	public String prefix() 
	{
		return generateExpression(root, "preOrder");
	}
	/**
	 * This method parses a expression
	 * @return Tree node
	 * @throws ParseException If an error occurs during parsing,
     * the location of the error is included in the thrown exception
	 */
	private ExpTreeNode expression() throws ParseException
	{
		String oper;	
		ExpTreeNode p1, p2;
		p1 = term(); // calls method that parses a term
		while (list.get(nextToken).equals("+") || list.get(nextToken).equals("-"))
		{
			oper = list.get(nextToken);
			nextToken += 1;
			p2 = term(); // need to parse another term
			p1 = new ExpTreeNode(oper, p1, p2); // left associative
		}
		return p1;
	}
	/**
	 * This method parses a term
	 * @return Tree node
	 * @throws ParseException If an error occurs during parsing,
	 * the location of the error is included in the thrown exception
	 */
	private ExpTreeNode term() throws ParseException
	{
		if (nextToken <= list.size() - 1) 
		{			
			String oper;
			ExpTreeNode p1, p2;
			p1 = factor(); // calls method that parses a factor
			while (list.get(nextToken).equals("*") || list.get(nextToken).equals("/"))
			{
				oper = list.get(nextToken);
				nextToken += 1;
				p2 = factor();
				p1 = new ExpTreeNode(oper, p1, p2);
			}
			return  p1;
		}
		else 
		{
			throw new ParseException("Error parsing " + "\"" + "\"" + " at string[" + nextToken + "]",0);
		}
	}
	/**
	 * This method parses a factor
	 * @return Tree node
	 * @throws ParseException If an error occurs during parsing,
	 * the location of the error is included in the thrown exception
	 */  
	private ExpTreeNode factor() throws ParseException 
	{
		ExpTreeNode p1;
		if (isDigit(list.get(nextToken)))
		{
			p1 = new ExpTreeNode(list.get(nextToken));
			if (nextToken < list.size() - 1)
			{
				nextToken += 1;
			}					
		 	return p1;
		}
		else 
		{
			nextToken += 1;
			p1 = expression(); // calls method that parses a expression
			if (list.get(nextToken).equals(")") && nextToken < list.size() - 1)
			{
				nextToken += 1;	
				return p1;
			}
			else 
			{ 
				throw new ParseException("Error parsing " + "\"" + list.get(nextToken) + "\"" + " at string[" + nextToken + "]",0);
			}	
		}
	}
	/**
	 * This method traverses the parse tree and returns a expression
	 * @param node The root
	 * @return The expression as a string
	 */
	private String generateExpression(ExpTreeNode node, String order)
	{
		if (node != null) 
		{
			String a = generateExpression(node.left, order);
			String b = generateExpression(node.right, order);
			return visit(node, a, b, order);
		}
		return "";
	}
	/**
	 * This method loads the input expression into an array list
	 * @param line The input
	 */
	private void initializeList(String line)
	{
		nextToken = 0;
		int i = 0;
		Scanner sc = new Scanner(line);
		while(sc.hasNext())
		{
			list.add(i, sc.next());
			i += 1;
		}		
		sc.close();
	}
	/**
	 * This method checks if a string contains all digits
	 * @param s The string to be checked 
	 * @return True if string is all digits, false otherwise
	 */
	private boolean isDigit(String str)
	{
		try 
		{
			Double.parseDouble(str);
			return true;
		}
		catch (NumberFormatException NFEexception) 
		{ 
			return false; 
		}
	}
	/**
	 * "This method generates expression from VLR traversal
	 * @param node The tree
	 * @param a A string
	 * @param b A string
	 * @param traversal A string indicating the order of traversal
	 */
	private String visit(ExpTreeNode node, String a, String b, String traversal)
	{
		String output = node.el;
		if (a.length() > 0 && (b.length() > 0))
		{
			return traversal == "preOrder" ? output + " " + a + " " + b : a + " " + b + " " + output;
		}
		if (a.length() > 0 && !(b.length() > 0))
		{
			return traversal == "preOrder" ? output + " " + b : b + " " + output;
		}
		if (!(a.length() > 0) && b.length() > 0) 
		{
			return traversal == "preOrder" ? output + " " + a : a + " " + output;
		}
		return output;
	}
	/**
	 * This class is from assignment 2, I am using it here to evaluate expressions
	 */
	private class Prefix 
	{
		/**
		 * This method evaluates an express
		 * @param sc The scanner containing input string
		 * @return result The evaluated expression as a integer
		 */
		public int evaluate(Scanner sc)
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
}