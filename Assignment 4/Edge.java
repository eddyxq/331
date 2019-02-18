/**
 * This class is the edge of a graph
 * @author Eddy Qiang - CPSC 331 - T03
 * @version 1.0
 * @since April 4, 2018
 */
public class Edge 
{
	private int weight;
	private Vertex target;
	
    public Edge(Vertex target, int weight)
    { 
    	this.target = target; 
    	this.weight = weight; 
	}

	public Vertex getTarget() 
	{
		return target;
	}

	public int getWeight() 
	{
		return weight;
	}
}