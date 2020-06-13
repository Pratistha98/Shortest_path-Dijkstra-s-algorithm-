
public class Vertex {
	String Vertex_id; 
	double lattitude; 
	double longitude;
	
	public Vertex (String Vertex_id, double lattitude, double longitude ){
		
		this.Vertex_id = Vertex_id;
		this.lattitude = lattitude; 
		this.longitude = longitude;
	}
	public String  toString() {
		return "ID: " + Vertex_id;
	}
}
