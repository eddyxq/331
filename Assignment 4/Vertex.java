import java.util.ArrayList;
/**
 * This class is the vertex of a graph
 * @author Eddy Qiang - CPSC 331 - T03
 * @version 1.0
 * @since April 4, 2018
 */
public class Vertex implements Comparable<Vertex>
{
	//initial unvisited vertex distance is set to infinity
	private double minDistance = Double.POSITIVE_INFINITY;
    private String nodeNumber;
    private Vertex previous;
    private ArrayList<Edge> adjEdges = new ArrayList<Edge>();
    /**
     * This constructor accepts a string that numbers the vertex
     * @param num The vertex number
     */
    public Vertex(String num) 
    { 
        nodeNumber = num; 
    }
    /**
     * This is the getter for minDistance
     * @return The min distance
     */
    public double getMinDistance() 
	{
		return minDistance;
	}
    /**
     * This is the setter for minDistance
     * @param minDistance The min distance
     */
	public void setMinDistance(double minDistance) 
	{
		this.minDistance = minDistance;
	}
	/**
	 * This is the getter for adjEdges
	 * @return The list of adjacent edges
	 */
	public ArrayList<Edge> getAdjEdges() 
	{
		return adjEdges;
	}
	/**
	 * This is the getter for previous
	 * @return The previous vertex
	 */
	public Vertex getPrevious() 
	{
		return previous;
	}
	/**
	 * This is the setter for previous
	 * @param previous The previous vertex
	 */
	public void setPrevious(Vertex previous) 
	{
		this.previous = previous;
	}
	/**
	 * This is the getter for node number
	 * @return The node number
	 */
	public String getNodeNumber() 
	{
		return nodeNumber;
	}
	/**
	 * This method compares vertex min distances
	 * @param v The vertex being compared with
	 * @return The comparison 
	 */
	public int compareTo(Vertex v)
    {
        return Double.compare(getMinDistance(), v.getMinDistance());
    }
}