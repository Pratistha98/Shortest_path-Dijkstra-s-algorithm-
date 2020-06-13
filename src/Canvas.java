import javax.swing.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Canvas extends JPanel {
	private ArrayList<edgeLocation> edges;
	private ArrayList<edgeLocation> list;
	public Canvas(ArrayList<Edge> edges, ArrayList<Edge> list) {
		this.edges = minMax(edges);
		this.list = minMax(list);
	}
	public class edgeLocation {
		double lat1, lon1, lat2, lon2;
		edgeLocation(double lat1, double lon1, double lat2, double lon2) {
			this.lat1 = lat1;
			this.lon1 = lon1;
			this.lat2 = lat2;
			this.lon2 = lon2;
		}
	}
	public ArrayList<edgeLocation> minMax(ArrayList<Edge> edges){
		ArrayList<edgeLocation> normalEdges = new ArrayList<edgeLocation>();
		for(Edge edge : edges) {
			double lat1 = Math.abs(Main.indexMap.get(edge.v).lattitude);
			double lat2 = Math.abs(Main.indexMap.get(edge.w).lattitude);
			double lon1 = Math.abs(Main.indexMap.get(edge.v).longitude);
			double lon2 = Math.abs(Main.indexMap.get(edge.w).longitude);
			
			//System.out.printf("NON-NORMAL LON1: %f, LAT1: %f, LON2: %f, LAT2: %f%n", lon1, lat1, lon2, lat2);
			
			lat1 = (lat1 - Main.latMin)/(Main.latMax - Main.latMin);
			lat2 = (lat2 - Main.latMin)/(Main.latMax - Main.latMin);
			lon1 = (lon1 - Main.lonMin)/(Main.lonMax - Main.lonMin);
			lon2 = (lon2 - Main.lonMin)/(Main.lonMax - Main.lonMin);
			
			normalEdges.add(new edgeLocation(lat1, lon1, lat2, lon2));
		}
		return normalEdges;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paint2D((Graphics2D) g);
	}
	public void paint2D(Graphics2D g2) {
		System.out.println("painting");
		double scale = Math.min(getWidth(), getHeight());
		for(edgeLocation edge: edges) {
			//System.out.printf("LON1: %f, LAT1: %f, LON2: %f, LAT2: %f%n", edge.lon1, edge.lat1, edge.lon2, edge.lat2);

			g2.drawLine((int)(edge.lon1 * scale), (int)(edge.lat1 * scale), (int)(edge.lon2 * scale), (int)(edge.lat2 * scale));
		}
		g2.setColor(Color.RED);
		g2.setStroke(new BasicStroke(5));
		for(edgeLocation edge: list) {
			//System.out.printf("LON1: %f, LAT1: %f, LON2: %f, LAT2: %f%n", edge.lon1, edge.lat1, edge.lon2, edge.lat2);

			g2.drawLine((int)(edge.lon1 * scale), (int)(edge.lat1 * scale), (int)(edge.lon2 * scale), (int)(edge.lat2 * scale));
		}
	}
}
