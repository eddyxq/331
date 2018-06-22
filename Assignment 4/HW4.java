import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
/**
 * This class is the main driver for the program
 * @author Eddy Qiang - CPSC 331 - T03
 * @version 1.0
 * @since April 4, 2018
 */
public class HW4 
{
	public static void main(String[] args)
	{
		//accept inputs from commandline
		String option = args[0];
		String mazeFile = args[1];
		String queryFile = args[2];
		ArrayList<int[]> queries = new ArrayList<int[]>();
		int numOfQueries = 0;
		//read inputs from query file and store in list
        try (BufferedReader br = new BufferedReader(new FileReader(queryFile))) 
		{  
        	String line;
		    while ((line = br.readLine()) != null) 
		    {
		    	String[] queryLine = line.split(" ");
		    	int[] q = new int[2];
		    	q[0] = Integer.parseInt(queryLine[0]);
		    	q[1] = Integer.parseInt(queryLine[1]);
		    	queries.add(q);
		    	numOfQueries += 1;
		    }
		} 
		catch (IOException e) 
		{
			System.out.println("ERROR: File not found");
		}
        //create frame for gui
		JFrame f = new JFrame("Shortest Paths --" + option);
        f.addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });        
        //get the maze dimensions for gui
        int mazeDimensions = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(mazeFile))) 
		{  
		    mazeDimensions = Integer.parseInt(br.readLine());
		} 
		catch (IOException e) 
		{
			System.out.println("ERROR: File not found");
		}
        MazeVisualizer applet = new MazeVisualizer(mazeDimensions);
        //loop once for each query
        for(int r = 0; r < numOfQueries; r++)
        {
        	int[] temp = queries.get(r);
        	//subtracted 1 from node number to get it lined up with its index number
        	int start = temp[0]-1;
        	int end = temp[1]-1;
        	//create a hash map to hold the node data
        	Map<String, Vertex> map = new HashMap<String, Vertex>();
			Graph maze = new Graph();
			int size = 0;
	        //read from maze file and store the vertices in a hash map
	        try (BufferedReader br = new BufferedReader(new FileReader(mazeFile))) 
			{  
			    String line;
			    String mazeDimension = br.readLine();
			    //size is equal to dimensions squared
			    size = Integer.parseInt(mazeDimension)*Integer.parseInt(mazeDimension);
			    //maps each key node number to a actual vertex 
		        for(int i = 1; i <= size; i++)
		        {
		        	map.put(i+"", new Vertex(i+""));
		        }
			    //extract the data from each line in text file
			    while ((line = br.readLine()) != null) 
			    {
			    	String[] elements = line.split("\t");
			    	String sourceVertex = elements[0];
			    	String targetVertex = elements[1];
			    	int weight = Integer.parseInt(elements[2]);
			    	//set weights to 0 if the unweighted option was selected
			    	if(option.equals("unweighted"))
			    	{
			    		weight = 1;
			    	}
			    	Vertex tempV = map.get(sourceVertex);
			    	tempV.getAdjEdges().add(new Edge(map.get(targetVertex), weight));
			    	//update hash map
			    	map.put(sourceVertex, tempV);
			    	//add edges to gui
			    	applet.addEdge(Integer.parseInt(sourceVertex), Integer.parseInt(targetVertex));
			    }
			} 
			catch (IOException e) 
			{
				System.out.println("ERROR: File not found");
			}
	        //copy mapped data to list
	        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	        for(int i = 1; i <= size; i++)
	        {
	        	vertices.add(map.get(i+""));
	        }
	        //perform Dijkstra's calculation from the starting node
			maze.dijkstrasAlgorithm(vertices.get(start));
	    	//get the shortest path
			ArrayList<Integer> path = maze.generatePathList(vertices.get(end));
			//display to console for debugging purposes, uncomment line below for debugging
	        //System.out.println("path going from " + vertices.get(start).getNodeNumber() + " to " + vertices.get(end).getNodeNumber() + " is " + path);
	        //display the shortest path in a random color on the gui	
	        applet.addPath(path);
        }   
        //set gui frame settings
        f.getContentPane().add("Center", applet);
        applet.init();
        f.pack();
        f.setBackground(Color.WHITE);
        f.setSize(new Dimension(512,512));
        f.setVisible(true);
	}
}