/**
 * This class represents a node in a binary expression tree
 * expression into a binary expression tree
 * @author Eddy Qiang - CPSC 331 - T03
 * @version 1.0
 * @since Mar 20, 2018
 */
public class ExpTreeNode 
{
	String el;
	ExpTreeNode left, right;
	
	ExpTreeNode(String e) 
	{ 
		this(e, null, null);
	}
	
	ExpTreeNode( String e, ExpTreeNode l, ExpTreeNode r ) 
	{
		el = new String(e); 
		left = l;
		right = r;
	} 
}