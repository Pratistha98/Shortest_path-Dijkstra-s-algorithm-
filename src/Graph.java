import java.util.LinkedList;

public class Graph {
    int vertex;
    LinkedList<Integer> list[];

    public Graph(int vertex) {
        this.vertex = vertex;
        list = new LinkedList[vertex];
        for (int i = 0; i <vertex ; i++) {
            list[i] = new LinkedList<>();
        }
    }

    public void addEdge(int source, int destination){

        //add edge
        list[source].addFirst(destination);

        //add back edge ((for undirected)
        list[destination].addFirst(source);
    }

    public void printGraph(){
        for (int i = 0; i <vertex ; i++) {
            if(list[i].size()>0) {
                System.out.print("Vertex " + i + " is connected to: ");
                for (int j = 0; j < list[i].size(); j++) {
                    System.out.print(list[i].get(j) + " ");
                }
                System.out.println();
            }
        }
    }


}
//import java.util.*;
//
//public class Graph {
//
//        HashMap<Object, LinkedList<Object>> adjList = new HashMap();
//        HashMap<Object, Integer>  indexes = new HashMap<>();
//        int index = -1;
//
//        public Graph(ArrayList<Object> vertices) {
//            for (int i = 0; i <vertices.size() ; i++) {
//                Object vertex = vertices.get(i);
//                LinkedList<Object> list = new LinkedList<>();
//                adjList.put(vertex, list);
//                indexes.put(vertex, ++index);
//            }
//        }
//
//        public void addEdge(int source, int destination) {
//            //add forward edge
//            LinkedList<Object> list;
//            list = adjList.get(source);
//            list.addFirst(destination);
//            adjList.put(source, list);
//            list = adjList.get(destination);
//            adjList.put(destination, list);
//        }
//
////        public void DFS(){
////            int vertices = adjList.size();
////            boolean [] visited = new boolean[vertices];
////
////            for (Map.Entry<Object, LinkedList<Object>> entry : adjList.entrySet()) {
////                Object source = entry.getKey();
////                if(!visited[indexes.get(source)]){
////                    DFSUtil(source, visited);
////                }
//////                System.out.println("Key = " +  +
//////                        ", Value = " + entry.getValue());
////            }
////        }
//
////        public void DFSUtil(Object source, boolean[] visited){
////
////            //mark this visited
////            visited[indexes.get(source)] = true;
////
////            System.out.print(source + " ");
////            LinkedList<Object> list = adjList.get(source);
////            for (int i = 0; i <list.size() ; i++) {
////                Object destination = list.get(i);
////                if(!visited[indexes.get(destination)])
////                    DFSUtil(destination,visited);
////            }
////        }
//
//        public void printGraph() {
//            Set<Object> set = adjList.keySet();
//            Iterator<Object> iterator = set.iterator();
//
//            while(iterator.hasNext()){
//                Object vertex = iterator.next();
//                System.out.print("Vertex " + vertex + " is connected to: ");
//                LinkedList<Object> list = adjList.get(vertex);
//                for (int i = 0; i <list.size() ; i++) {
//                    System.out.print(list.get(i) + " ");
//                }
//                System.out.println();
//            }
//        }
//    }
//
////    public static void main(String[] args) {
////        ArrayList<Object> vertices = new ArrayList<>();
////        vertices.add('A');
////        vertices.add('B');
////        vertices.add('C');
////        vertices.add('D');
////        vertices.add('E');
////        vertices.add('F');
////        vertices.add('G');
////        Graph1 graph = new Graph1(vertices);
////        graph.addEdge('A', 'B');
////        graph.addEdge('A', 'C');
////        graph.addEdge('B', 'D');
////        graph.addEdge('B', 'E');
////        graph.addEdge('C', 'D');
////        graph.addEdge('D', 'E');
////        graph.addEdge('G', 'E');
////        graph.addEdge('A', 'G');
////        graph.printGraph();
////        System.out.println("---------------Depth First Traversal------------");
////        graph.DFS();
////    }
//
////import java.io.File;
////import java.util.ArrayList;
////import java.util.HashMap;
////import java.util.LinkedList;
////import java.util.Scanner;
////
////public class Graph {
////	 HashMap<Object, LinkedList<Object>> adjList = new HashMap<>();
////     HashMap<Object, Integer>  indexes = new HashMap<>();
////	private int vertexCount;
////	boolean directed; // false for undirected graphs, true for directed
////	//boolean adj[][];
////	// static int ade[][];
////	static int edgeCount;
////	int index = -1;
////
////	public Graph(int numVerticies, boolean isDirected) {
////		directed = isDirected;
////		vertexCount = numVerticies;
////		//adj = new boolean[numVerticies][numVerticies];
////
//////		for (int i = 0; i < numVerticies; i++) {
//////			for (int j = 0; j < numVerticies; j++) {
//////				adj[i][j] = false;
//////			}
//////		}
////        for (int i = 0; i < vertexCount ; i++) {
////           // Object vertex = vertices.get(i);
////            LinkedList<Object> list = new LinkedList<>();
////            adjList.put(vertex, list);
////            indexes.put(vertex, ++index);
////        }
////		edgeCount = 0;
////	}
////
////	public boolean isDirected() {
////		return directed; // your code here
////	}
////
////	public int vertices() {
////		return vertexCount; // return the number of vertices
////	}
////
////	public int edges() {
////		return edgeCount; // return number of edges
////	}
////
////	public void insert(Edge e) { // your code here
////		if (directed) {
////			if (!adj[e.v][e.w]) { // For directed
////				adj[e.v][e.w] = true;
////				edgeCount += 1;
////			}
////		} else {
////			if (!adj[e.v][e.w] || !adj[e.w][e.v]) // For undirected
////				adj[e.v][e.w] = true;
////			adj[e.w][e.v] = true;
////			edgeCount += 1;
////		}
////	}
////
////	public void delete(Edge e) { // your code here
////		if (directed) {
////			if (adj[e.v][e.w]) { // For directed
////				adj[e.v][e.w] = false;
////				edgeCount -= 1;
////			}
////		} else {
////			if (adj[e.v][e.w] || adj[e.w][e.v]) { // For undirected
////				adj[e.v][e.w] = false;
////				adj[e.w][e.v] = false;
////				edgeCount -= 1;
////			}
////		}
////	}
////
////	public boolean connected(int node1, int node2) {
////		return adj[node1][node2]; // are they connected?
////	}
////
////	public AdjList getAdjList(int vertex) {
////		return new AdjArray(vertex); // your code here
////	}
////
////	// Show
////	public void show() {
////		for (int s = 0; s < vertices(); s++) {
////			System.out.print(s + ": ");
////			AdjList A = getAdjList(s);
////			for (int t = A.begin(); !A.end(); t = A.next()) // use of iterator
////				System.out.print(t + " ");
////			System.out.println();
////		}
////	}
////
////	// =============ADDEED FUNCTIONS==========================
////
////	
////	public HashMap<Integer, Vertex> unweighted(int s) {
////		HashMap<Integer, Vertex> hashmap = new HashMap<>();
////
////		for (int i = 0; i < vertices(); i++) {
////			hashmap.put(i, new Vertex(Integer.MAX_VALUE, false, null, i));
////		}
////		hashmap.put(s, new Vertex(0, false, null, s));
////
////		for (int currdist = 0; currdist < vertices(); currdist++) {
////			for (int i = 0; i < vertices(); i++) {
////				Vertex v = hashmap.get(i);
////				if (!v.known && (v.distance == currdist)) {
////					v.known = true;
////					AdjList A = getAdjList(v.id);
////					for (int t = A.begin(); !A.end(); t = A.next()) {
////						if (t != -1) {
////							Vertex w = hashmap.get(t);
////							if (w.distance == Integer.MAX_VALUE) {
////								w.distance = currdist + 1;
////								w.path = v;
////							}
////						}
////					}
////
////				}
////			}
////		}
////		return hashmap;
////	}
////
////	public void shortestpath(int s, HashMap<Integer, Vertex> hashmap, int d) {
////
////		Vertex v = hashmap.get(s);
////		Vertex w = hashmap.get(d);
////
////		System.out.println("Distance: " + w.distance);
////		while (!v.equals(w)) {
////			System.out.println("Path: " + w.id);
////			w = w.path;
////		}
////
////	}
////
////// =========================================================
////	public class AdjArray implements AdjList {
////
////		private int v; // what vertex we are interested in
////		private int i; // so we can keep track of where we are
////
////		public AdjArray(int v) {
////			// write the code for the constructors
////			// save the value of the vertex passed in
////			// (that will be where the iterator starts)
////			// start the “i” counter at negative one
////			this.v = v;
////			this.i = -1;
////		}
////
////		@Override
////		public int next() {
////			// perhaps the trickiest method
////			// use a for loop to advance the value of “i”
////			// “for (++i; i < vertices(); i++)”
////			// and search the appropriate row return the index
////			// of the next true value found
////			// “if (connected(v,i) == true) return i;”
////			// if the loop completes without finding anything return -1
////			for (++i; i < vertices(); i++) {
////				if (connected(v, i) == true)
////					return i;
////			}
////			return -1;
////		}
////
////		@Override
////		public int begin() {
////			i = -1;
////			return next();
////			// reset “i” back to negative one
////			// return the value of a call to “next”
////		}
////
////		@Override
////		public boolean end() {
////			return i > vertices();
////			// if “i” is less than the number of vertices return false
////		}
////	}
////
////}

//
//public class Graph {
//
//	private int vertexCount;
//	boolean directed; // false for undirected graphs, true for directed
//	boolean adj[][];
//	// static int ade[][];
//	static int edgeCount;
//
//	public Graph(int numVerticies, boolean isDirected) {
//		directed = isDirected;
//		vertexCount = numVerticies;
//		adj = new boolean[numVerticies][numVerticies];
//
//		for (int i = 0; i < numVerticies; i++) {
//			for (int j = 0; j < numVerticies; j++) {
//				adj[i][j] = false;
//			}
//		}
//		edgeCount = 0;
//	}
//
//	public boolean isDirected() {
//		return directed; // your code here
//	}
//
//	public int vertices() {
//		return vertexCount; // return the number of vertices
//	}
//
//	public int edges() {
//		return edgeCount; // return number of edges
//	}
//
//	public void insert(Edge e) { // your code here
//		if (directed) {
//			if (!adj[e.v][e.w]) { // For directed
//				adj[e.v][e.w] = true;
//				edgeCount += 1;
//			}
//		} else {
//			if (!adj[e.v][e.w] || !adj[e.w][e.v]) // For undirected
//				adj[e.v][e.w] = true;
//			adj[e.w][e.v] = true;
//			edgeCount += 1;
//		}
//	}
//
//	public void delete(Edge e) { // your code here
//		if (directed) {
//			if (adj[e.v][e.w]) { // For directed
//				adj[e.v][e.w] = false;
//				edgeCount -= 1;
//			}
//		} else {
//			if (adj[e.v][e.w] || adj[e.w][e.v]) { // For undirected
//				adj[e.v][e.w] = false;
//				adj[e.w][e.v] = false;
//				edgeCount -= 1;
//			}
//		}
//	}
//
//	public boolean connected(int node1, int node2) {
//		return adj[node1][node2]; // are they connected?
//	}
//
//	public AdjList getAdjList(int vertex) {
//		return new AdjArray(vertex); // your code here
//	}
//
//	// Show
//	public void show() {
//		for (int s = 0; s < vertices(); s++) {
//			System.out.print(s + ": ");
//			AdjList A = getAdjList(s);
//			for (int t = A.begin(); !A.end(); t = A.next()) // use of iterator
//				System.out.print(t + " ");
//			System.out.println();
//		}
//	}
//
//	// =============ADDEED FUNCTIONS==========================
//
//
//
//// =========================================================
//	public class AdjArray implements AdjList {
//
//		private int v; // what vertex we are interested in
//		private int i; // so we can keep track of where we are
//
//		public AdjArray(int v) {
//			// write the code for the constructors
//			// save the value of the vertex passed in
//			// (that will be where the iterator starts)
//			// start the “i” counter at negative one
//			this.v = v;
//			this.i = -1;
//		}
//
//		@Override
//		public int next() {
//			// perhaps the trickiest method
//			// use a for loop to advance the value of “i”
//			// “for (++i; i < vertices(); i++)”
//			// and search the appropriate row return the index
//			// of the next true value found
//			// “if (connected(v,i) == true) return i;”
//			// if the loop completes without finding anything return -1
//			for (++i; i < vertices(); i++) {
//				if (connected(v, i) == true)
//					return i;
//			}
//			return -1;
//		}
//
//		@Override
//		public int begin() {
//			i = -1;
//			return next();
//			// reset “i” back to negative one
//			// return the value of a call to “next”
//		}
//
//		@Override
//		public boolean end() {
//			return i > vertices();
//			// if “i” is less than the number of vertices return false
//		}
//	}
//
//}
