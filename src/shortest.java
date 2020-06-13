
public class shortest implements Comparable<Object> {
	
	Vertex v; 
	Boolean visited;
	double distance;
	Vertex p;
	
	public shortest (Vertex v, Boolean visited, double distance, Vertex p) {
		this.v = v; 
		this.visited = visited; 
		this.distance = distance; 
		this.p = p;
	}



	@Override
	public int compareTo (Object a) {
		// TODO Auto-generated method stub
		shortest o = (shortest) a; //CAST
		//System.out.println("Compared");
		if(this.distance < o.distance) {
			return -1; 
		}
		if (this.distance > o.distance) {
			return 1;
		}
		else
			return 0;
	}
	
}
