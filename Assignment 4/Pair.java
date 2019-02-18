/**
 * This is a generic pair class
 * @author Usman Alim
 * modified by:
 * @author Eddy Qiang - CPSC 331 - T03
 * @version 1.0
 * @since April 4, 2018
 */
public class Pair<T1, T2> 
{ 
	T1 x;
	T2 y;
	/**
	 * Constructor that accepts two objects
	 * @param x first
	 * @param y second
	 */
	public Pair( T1 x, T2 y ) 
	{ 
		this.x = x;
		this.y = y;
	}
	/**
	 * This is the getter for x
	 * @return x
	 */
	public T1 getX() 
	{ 
		return x; 
	}
	/**
	 * This is the getter for y
	 * @return y
	 */
	public T2 getY() 
	{ 
		return y; 
	}
}