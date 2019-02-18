import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Collections;
/**
 * This class is a graph that calculates shortest paths
 * @author Eddy Qiang - CPSC 331 - T03
 * @version 1.0
 * @since April 4, 2018
 */
public class Graph
{
	/**
	 * This method contains the logic for Dijkstra's Shortest Path Algorithm
	 * @param source The starting vertex
	 */
    public void dijkstrasAlgorithm(Vertex source)
    {
    	//set distance to itself to 0
        source.setMinDistance(0);
        //add to queue
        PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
      	queue.add(source);
      	//Dijkstra's algorithm
      	while (!queue.isEmpty()) 
      	{
      		//dequeue vertex from queue with minimal dist(u)
      		Vertex u = queue.poll();
      		//for all v adjacent to u that are also in queue
            for (Edge e : u.getAdjEdges())
            {
                Vertex v = e.getTarget();
                double weight = e.getWeight();
                double distance = u.getMinDistance() + weight;
                //if shorter distance found, update min distance
				if (distance < v.getMinDistance()) 
				{
				    queue.remove(v);
				    v.setMinDistance(distance) ;
				    v.setPrevious(u);
				    queue.add(v);
				}
            }
        }
    }
    /**
     * This method calculates and returns the shortest path as a array list
     * @param target The ending vertex
     * @return shortestPath A list containing the shortest path 
     */
    public ArrayList<Integer> generatePathList(Vertex target)
    {      
        ArrayList<Integer> shortestPath = new ArrayList<Integer>();
        //add each vertex in the shortest path to the list
        for (Vertex v = target; v != null; v = v.getPrevious())
        {
        	shortestPath.add(Integer.parseInt(v.getNodeNumber()));            
        }
        Collections.reverse(shortestPath);
        return shortestPath;
    }
}