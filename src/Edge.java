
public class Edge {

	String Edge_Id;
	int v; 
	int w;
	double distance;
	public Edge (String Edge_Id, int v, int w){
		this.Edge_Id = Edge_Id; 
		this.v = v; 
		this.w = w;
	}
	public Edge ( int v, int w){
	
		this.v = v; 
		this.w = w;

	}
}
