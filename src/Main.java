import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JFrame;

import java.util.PriorityQueue;

public class Main {

	// Hashmaps
	static double latMax = 0, latMin = 1000, lonMax = 0, lonMin = 1000;
	static HashMap<String, Vertex> idMap = new HashMap<String, Vertex>();
	static HashMap<Vertex, Integer> vertexMap = new HashMap<Vertex, Integer>();
	static HashMap<Integer, Vertex> indexMap = new HashMap<Integer, Vertex>();
	static int count = 0;
	// Graph
	static Graph graph;
	static ArrayList<Edge> shortestPathEdges = new ArrayList<Edge>();

	public static void main(String[] args) throws IOException {
		String[] strline = new String[4];
		String one, two, three, four;

		String input = args[0];
		String start = args[1];
		String end = args[2];
		String meridian = args[3];

		String lowercase = meridian.toLowerCase();

		FileInputStream fstream = new FileInputStream(input);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String getline;

		ArrayList<Edge> list = new ArrayList<Edge>();
		ArrayList<Integer> starting = new ArrayList<Integer>();
		ArrayList<Integer> ending = new ArrayList<Integer>();

		while ((getline = br.readLine()) != null) {
			strline = getline.split("\\s+");
			one = strline[0];
			two = strline[1];
			three = strline[2];
			four = strline[3];

			if (one.equals("i")) {
				Vertex v = new Vertex(two, Double.parseDouble(three), Double.parseDouble(four));

				if (Math.abs(Double.parseDouble(three)) > latMax) {
					latMax = Math.abs(Double.parseDouble(three));
				}
				if (Math.abs(Double.parseDouble(three)) < latMin) {
					latMin = Math.abs(Double.parseDouble(three));
				}
				if (Math.abs(Double.parseDouble(four)) > lonMax) {
					lonMax = Math.abs(Double.parseDouble(four));
				}
				if (Math.abs(Double.parseDouble(four)) < lonMin) {
					lonMin = Math.abs(Double.parseDouble(four));
				}

				idMap.put(two, v); // ID AND VERTEX
				vertexMap.put(v, count); // VERTEX AND index
				indexMap.put(count, v); // Count and vertex
				count = count + 1;

			} else if (one.equals("r")) {
				Edge e = new Edge(two, vertexMap.get(idMap.get(three)), vertexMap.get(idMap.get(four))); // HASHMAP
				list.add(e);
				starting.add(vertexMap.get(idMap.get(three)));
				ending.add(vertexMap.get(idMap.get(four)));

			} else {
				System.out.println("Error");
			}

		}

		// INSERT TO THE GRAPH

		graph = new Graph(count + 1);
		for (int i = 0; i < starting.size(); i++) {
			graph.addEdge(starting.get(i), ending.get(i));
		}
		//graph.printGraph();

		ArrayList<shortest> paths = new ArrayList<shortest>();
		ArrayList<shortest> paths2 = new ArrayList<shortest>();
		for (int i = 0; i < count; i++) {
			paths.add(new shortest(indexMap.get(i), false, Integer.MAX_VALUE, null));
		}
////==================================================================================================================================

		if (lowercase.equals("no")) {

			shortpath(paths, graph, start); // already modified the path array
			shortestpath(start, paths, end);
		}

//====================================================================================================
		// Spanning
		if (lowercase.equals("yes")) {
			shortpath(paths, graph, start); // already modified the path array

			shortestpath(start, paths, end);

			for (int i = 0; i < count; i++) {
				paths2.add(new shortest(indexMap.get(i), false, Integer.MAX_VALUE, null));
			}
			System.out.println("  ");
			System.out.println(
					"MERIDIAN PATH ======================================================================================");
			minspan(paths2, graph, start, end);
			minspanpath(start, paths2, end);
		}

		fstream.close();
		JFrame frame = new JFrame("Street Mapping");
		Canvas canvas = new Canvas(list, shortestPathEdges);
		frame.getContentPane().add(canvas);
		frame.setSize(400, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// Find the shortest path
	public static void shortpath(ArrayList<shortest> paths, Graph g, String start) {
		PriorityQueue<shortest> pQueue = new PriorityQueue<shortest>();

		Vertex v = idMap.get(start);
		//System.out.println("HEre is v:" + v.toString());
		int s = vertexMap.get(v);
		System.out.println("HEre is s:" + s);
		paths.set(s, (new shortest(v, false, 0, null)));
		// add to the priority queue
		for (shortest i : paths) {
			pQueue.add(i);
		}
		 PriorityQueue<shortest> pQueue2 = new PriorityQueue<shortest>();
		 // int c = 0;

		// MAIN partur
		while (!pQueue.isEmpty()) {
			shortest current = pQueue.poll();
			//System.out.println("popped2 up: " + current.v.toString());
			current.visited = true;

			int i = vertexMap.get(current.v);
			
			if (graph.list[i].size() > 0) {
				//System.out.println("size " + i + " " + graph.list[i].size());
				for (int j = 0; j < graph.list[i].size(); j++) {
					int value = graph.list[i].get(j);
					//System.out.println("value: " + value);
					double dist = distance(current.v.lattitude, current.v.longitude, indexMap.get(value).lattitude,
							indexMap.get(value).longitude);
					dist = current.distance + dist; // add distance
					// System.out.println("Distance: " + dist + "paths.distance " +
					// paths.get(value).distance);
					if (dist < paths.get(value).distance) {
						// update the value
						paths.get(value).distance = dist;
						paths.get(value).p = current.v; // update the parent
//						System.out.println("change2 in distance " + paths.get(value).distance);
//						System.out.println("change2 in parent " + paths.get(value).p.toString());
						
					}

				}

			}
			 while (!pQueue.isEmpty()) {
					
				 shortest out = pQueue.poll();
				 pQueue2.add(out);
				
				 }
				 pQueue = pQueue2;
//			 
//			System.out.println("Changedhere2: ");
//			for (int i1 = 0; i1 < paths.size(); i1++) {
//				System.out.println("Vertex " + paths.get(i1).v.toString() + " Visited " + paths.get(i1).visited
//						+ " Distance " + paths.get(i1).distance + " Parent " + paths.get(i1).p);
//			}
//			System.out.println(" ");

		}
	}

//	// Find the shortestpath
//	public static void shortestpath(String start, ArrayList<shortest> paths, String end) {
//
//		Vertex s = idMap.get(start);
//		Vertex e = idMap.get(end);
//
//		int sv = vertexMap.get(s);
//		int ev = vertexMap.get(e);
//		if (paths.get(ev).distance == Integer.MAX_VALUE) {
//			System.out.println("THERE IS NO PATH");
//		} else {
//			System.out.println(
//					"Distance travelled: " + paths.get(ev).v.toString() + " " + paths.get(ev).distance + " miles");
//
//			while (!paths.get(sv).v.equals(paths.get(ev).v)) {
//				System.out.println("Path: " + paths.get(ev).v.toString());
//				Vertex update = paths.get(ev).p;
//				paths.set(ev, (new shortest(update, paths.get(vertexMap.get(update)).visited,
//						paths.get(vertexMap.get(update)).distance, paths.get(vertexMap.get(update)).p)));
//			}
//		}
//
//	}

	public static void shortestpath(String start, ArrayList<shortest> paths, String end) {

		Vertex startVertex = idMap.get(start);
		Vertex endVertex = idMap.get(end);

		int startVertexID = vertexMap.get(startVertex);
		int endVertexID = vertexMap.get(endVertex);
		System.out.println(
				"Distance travelled: " + paths.get(endVertexID).v.toString() + " " + paths.get(endVertexID).distance);

		while (!paths.get(startVertexID).v.equals(paths.get(endVertexID).v)) {
			// System.out.println("Path: " + paths.get(endVertexID).v.toString());
			Vertex update = paths.get(endVertexID).p;
			System.out.println("HERE:" + vertexMap.get(update));
			shortestPathEdges.add(new Edge(vertexMap.get(paths.get(endVertexID).v), vertexMap.get(update)));
			paths.set(endVertexID, (new shortest(update, paths.get(vertexMap.get(update)).visited,
					paths.get(vertexMap.get(update)).distance, paths.get(vertexMap.get(update)).p)));
			
			
		}
		for (int i = 0; i < shortestPathEdges.size(); i++) {
			System.out.println("Arrays of edges " + shortestPathEdges.get(i).v + " " + shortestPathEdges.get(i).w);
		}

	}

//====================================================================
	public static void minspan(ArrayList<shortest> paths, Graph g, String start, String end) {
		PriorityQueue<shortest> pQueue = new PriorityQueue<shortest>();
		Vertex v = idMap.get(start);
		int s = vertexMap.get(v);
		Vertex e = idMap.get(end);

		paths.set(s, (new shortest(v, false, 0, null)));
		// add to the priority queue
		for (shortest i : paths) {
			pQueue.add(i);
		}

//		// MAIN part
		while (pQueue.size() != 0) {
			shortest current = pQueue.poll();
			// System.out.println("popped up: " + current.v.toString() );
			current.visited = true;
			int i = vertexMap.get(current.v);

			if (graph.list[i].size() > 0) {
				// System.out.println("size " + i +" " + graph.list[i].size());
				for (int j = 0; j < graph.list[i].size(); j++) {
					int value = graph.list[i].get(j);
					double dist = distance(current.v.lattitude, current.v.longitude, indexMap.get(value).lattitude,
							indexMap.get(value).longitude);
					// dist = current.distance + dist; // add distance
					if (dist < paths.get(value).distance && !paths.get(value).visited) 
					{
						// update the value
						paths.get(value).distance = dist;
						paths.get(value).p = current.v; // update the parent
					}

				}
			}
//			if (!pQueue.isEmpty()) { // Fix issue
//				shortest out = pQueue.poll();
//				pQueue.add(out);
//			}
			PriorityQueue<shortest> pQueue2 = new PriorityQueue<shortest>();
			while (!pQueue.isEmpty()) {

				shortest out = pQueue.poll();
				pQueue2.add(out);
			}
			pQueue = pQueue2;

		}


//		}
		// Print
//		for (int i = 0; i < paths.size(); i++) {
//			System.out.println("Vertex " + paths.get(i).v.toString() + " Visited " + paths.get(i).visited + " Distance "
//					+ paths.get(i).distance + " Parent " + paths.get(i).p);
//		}

		// Print the path
	}

	public static void minspanpath(String start, ArrayList<shortest> paths, String end) {

		Vertex s = idMap.get(start);
		Vertex e = idMap.get(end);

		int sv = vertexMap.get(s);
		int ev = vertexMap.get(e);
		// System.out.println("Distance travelled: " + paths.get(ev).v.toString() + " "
		// + paths.get(ev).distance + " miles");

		while (!paths.get(sv).v.equals(paths.get(ev).v)) {
			System.out.println("Path: " + paths.get(ev).v.toString());
			Vertex update = paths.get(ev).p;
			paths.set(ev, (new shortest(update, paths.get(vertexMap.get(update)).visited,
					paths.get(vertexMap.get(update)).distance, paths.get(vertexMap.get(update)).p)));
		}

	}

	// CODE from GEEKS FOR GEEKS
	public static double distance(double lat1, double lon1, double lat2, double lon2) {
// The math module contains a function 
// named toRadians which converts from 
// degrees to radians. 
		lon1 = Math.toRadians(lon1);
		lon2 = Math.toRadians(lon2);
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);

// Haversine formula  
		double dlon = lon2 - lon1;
		double dlat = lat2 - lat1;
		double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
		double c = 2 * Math.asin(Math.sqrt(a));

// Radius of earth in kilometers. Use 3956  
// for miles 
		double r = 6371;

// calculate the result 
		double round = (c * r);
		return (double) Math.round(round * 100d) / 100d; // Round to 2 decimal points
	}

// driver code 

}
