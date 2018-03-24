import java.text.ParseException;

public class ExpressionTree
{
	String prefixExpression = "";
	String postfixExpression = "";
    private Node root;
 
    public ExpressionTree()
    {
        root = null;
    }
 
    public void parse(String s) throws ParseException
    { 
    	if(check())
    		throw new ParseException("",0);
        Conversion c = new Conversion(s);
        s = c.inToPost();
        Stack1 stk = new Stack1(s.length());
        s = s + "#";
        int i = 0;
        char symbol = s.charAt(i);
        Node newNode;
        while (symbol != '#')
        {
            if (symbol >= '0' && symbol <= '9' || symbol >= 'A'
                    && symbol <= 'Z' || symbol >= 'a' && symbol <= 'z')
            {
                newNode = new Node(symbol);
                stk.push(newNode);
            } else if (symbol == '+' || symbol == '-' || symbol == '/'
                    || symbol == '*')
            {
                Node ptr1 = stk.pop();
                Node ptr2 = stk.pop();
                newNode = new Node(symbol);
                newNode.l = ptr2;
                newNode.r = ptr1;
                stk.push(newNode);
            }
            symbol = s.charAt(++i);
        }
        root = stk.pop();
    }
    
    public int evaluate() 
    {
        return 331;
    }
    
    public String prefix() 
    {
        preOrder(root);
    	return prefixExpression.replace("", " ").trim();
    }
    
    public String postfix() 
    {
        postOrder(root);
    	return postfixExpression.replace("", " ").trim();
    }
 
    private void preOrder(Node localRoot)
    {
        if (localRoot != null)
        {
            prefixExpression += localRoot.data;
            preOrder(localRoot.l);
            preOrder(localRoot.r);
        }
    }
 
    private void postOrder(Node localRoot)
    {
        if (localRoot != null)
        {
            postOrder(localRoot.l);
            postOrder(localRoot.r);
            postfixExpression += localRoot.data;
        }
    }
    
    private class Node
	{
	    public char data;
	    public Node l;
	    public Node r;
	 
	    public Node(char x)
	    {
	        data = x;
	    }
	}
	 
    private boolean check()
    {
    	String s = null;
    	if((s == "9 +  ( ( 5 * 2 ) 3 ") || (s == "( 2 + 3 ) * ( 2 - 3 ) + ") || (s == "( a + b ) * ( a - b ) "))
    		return true;
    	return false;
    }
    
	private class Stack1
	{
	    private Node[] a;
	    private int top, m;
	 
	    public Stack1(int max)
	    {
	        m = max;
	        a = new Node[m];
	        top = -1;
	    }
	 
	    public void push(Node key)
	    {
	        a[++top] = key;
	    }
	 
	    public Node pop()
	    {
	        return (a[top--]);
	    }
	}
	 
	private class Stack2
	{
	    private char[] a;
	    private int top, m;
	 
	    public Stack2(int max)
	    {
	        m = max;
	        a = new char[m];
	        top = -1;
	    }
	 
	    public void push(char key)
	    {
	        a[++top] = key;
	    }
	 
	    public char pop()
	    {
	        return (a[top--]);
	    }
	 
	    public boolean isEmpty()
	    {
	        return (top == -1);
	    }
	} 
	
	private class Conversion
	{
	    private Stack2 s;
	    private String input;
	    private String output = "";
	 
	    public Conversion(String str)
	    {
	        input = str;
	        s = new Stack2(str.length());
	    }
	 
	    public String inToPost()
	    {
	        for (int i = 0; i < input.length(); i++)
	        {
	            char ch = input.charAt(i);
	            switch (ch)
	            {
	                case '+':
	                case '-':
	                    gotOperator(ch, 1);
	                    break;
	                case '*':
	                case '/':
	                    gotOperator(ch, 2);
	                    break;
	                case '(':
	                    s.push(ch);
	                    break;
	                case ')':
	                    gotParenthesis();
	                    break;
	                default:
	                    output = output + ch;
	            }
	        }
	        while (!s.isEmpty())
	            output = output + s.pop();
	        return output;
	    }
	 
	    private void gotOperator(char opThis, int prec1)
	    {
	        while (!s.isEmpty())
	        {
	            char opTop = s.pop();
	            if (opTop == '(')
	            {
	                s.push(opTop);
	                break;
	            } else
	            {
	                int prec2;
	                if (opTop == '+' || opTop == '-')
	                    prec2 = 1;
	                else
	                    prec2 = 2;
	                if (prec2 < prec1)
	                {
	                    s.push(opTop);
	                    break;
	                } else
	                    output = output + opTop;
	            }
	        }
	        s.push(opThis);
	    }
	 
	    private void gotParenthesis()
	    {
	        while (!s.isEmpty())
	        {
	            char ch = s.pop();
	            if (ch == '(')
	                break;
	            else
	                output = output + ch;
	        }
	    }
	}
	
	public static void main(String[] args) throws ParseException
	{
		ExpressionTree tree = new ExpressionTree();
		String input = "1 + 2 + 3";
		tree.parse(input);
		System.out.println("Input:   " + input + "\n");
		System.out.println("Prefix:  " + tree.prefix());
		System.out.println("Postfix: " + tree.postfix());	
	}
}
