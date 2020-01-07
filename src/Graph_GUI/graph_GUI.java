package Graph_GUI;

import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import elements.Edge;
import elements.Node;

import java.awt.Color;
import java.util.Iterator;

import algorithms.Graph_Algo;
import utils.Point3D;
import utils.StdDraw;
/*
 * @authors Jessica Blau
 */ 
public class Graph_GUI{

	public Graph_Algo ga;
	/*
	 * Default constructor
	 */
	public Graph_GUI() {
		ga = new Graph_Algo();
	}
	/*
	 * Constructor that enters this graph.
	 */
	public Graph_GUI(graph g) {	
		this.ga = new Graph_Algo();
		ga.init(g);
	}
	/*
	 * Function that draws the node. 
	 */
	public void drawNodes() {
		Iterator<node_data> nodes = ga.g.getV().iterator();
		while(nodes.hasNext()) {
			Node n = (Node) nodes.next();
			double x = n.getLocation().x();
			double y = n.getLocation().y();
			StdDraw.setPenRadius(0.05);
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.point(x,y);
			StdDraw.setPenColor(StdDraw.BLACK);
			String str = n.getKey()+"";
			StdDraw.text(x,y,str);
		}
	}
	/**
	 * Function that draws the edges.
	 */
	public void drawEdges() {
		Iterator<node_data> allNodes = ga.g.getV().iterator();
		while(allNodes.hasNext()) {
			Node n = (Node) allNodes.next();
			Iterator<edge_data> allEdgesOfNode = ga.g.getE(n.getKey()).iterator();
			while(allEdgesOfNode.hasNext()) {
				Edge edges = (Edge) allEdgesOfNode.next();
				double sx = ga.g.getNode(edges.getSrc()).getLocation().x();
				double sy = ga.g.getNode(edges.getSrc()).getLocation().y();
				double dx = ga.g.getNode(edges.getDest()).getLocation().x();
				double dy = ga.g.getNode(edges.getDest()).getLocation().y();

				StdDraw.setPenRadius(0.005);
				StdDraw.setPenColor(StdDraw.MAGENTA);

				StdDraw.line(sx,sy,dx,dy);

				StdDraw.setPenRadius(0.02);
				StdDraw.setPenColor(StdDraw.CYAN);

				double arrowX= (dx*8+sx)/9;
				double arrowY= (dy*8+sy)/9;
				StdDraw.point(arrowX,arrowY);

				String te = edges.getWeight()+"";

				StdDraw.setPenRadius(0.1);
				StdDraw.setPenColor(Color.BLACK);

				double newX= (dx*4+sx)/5;
				double newY= (dy*4+sy)/5;

				StdDraw.text(newX, newY, te);
			}
		}
	}
	/**
	 * Function that draws the graph.
	 */
	public void drawGraph() {
		try {
			if(ga.g.getV() != null) {
				StdDraw.setGui(this);
				setPageSize();
				drawEdges();
				drawNodes();
			}
		}catch(Exception e){
			throw new RuntimeException("No graph to draw");
		}
	}
	/**
	 * Function that sets the size of this page.
	 */
	private void setPageSize() {
		double xMax = 0;
		double xMin = 0;
		double yMax = 0;
		double yMin = 0;
		Iterator <node_data> nodes = ga.g.getV().iterator();
		while(nodes.hasNext()) {
			Node n = (Node)nodes.next();
			if(n.getLocation().x() > xMax) xMax = n.getLocation().x();
			else if (n.getLocation().x() < xMin) xMin = n.getLocation().x();
			if(n.getLocation().y() > yMax) yMax = n.getLocation().y();
			else if (n.getLocation().y() < yMin) yMin = n.getLocation().y();
		}

		int xCanvas = 5 * (int)(Math.abs(xMax) + Math.abs(xMin));
		int yCanvas = 5 * (int)(Math.abs(yMax) + Math.abs(yMin));

		StdDraw.setCanvasSize(xCanvas , yCanvas );
		StdDraw.setXscale(xMin - 10, xMax + 10);
		StdDraw.setYscale(yMin - 10, yMax + 10);
	}

	public static void main (String [] args) {
		Graph_GUI gg = new Graph_GUI();
		Point3D p0=new Point3D(-50,50);
		Point3D p1=new Point3D(50,50);
		Point3D p2=new Point3D(0,0);
		Point3D p3=new Point3D(0,-50);
		Point3D p4=new Point3D(0,50);

		Node a=new Node(0, p0 ,0, "", 0);
		Node b=new Node(1, p1, 0, "", 0);
		Node c=new Node(2, p2, 0, "", 0);
		Node d=new Node(3, p3, 0, "", 0);
		Node e=new Node(4, p4, 0, "", 0);


		gg.ga.g.addNode(a);
		gg.ga.g.addNode(b);
		gg.ga.g.addNode(c);
		gg.ga.g.addNode(d);
		gg.ga.g.addNode(e);

		gg.ga.g.connect(c.getKey(),a.getKey(), 1);
		gg.ga.g.connect(c.getKey(),d.getKey(), 2);
		gg.ga.g.connect(c.getKey(),b.getKey(), 3);
		gg.ga.g.connect(a.getKey(), c.getKey(), 2.5);
		gg.ga.g.connect(b.getKey(), c.getKey(), 1.5);
		gg.ga.g.connect(d.getKey(), c.getKey(), 3);
		gg.ga.g.connect(e.getKey(), a.getKey(), 5);
		gg.ga.g.connect(e.getKey(), b.getKey(), 4);


		gg.drawGraph();
	}
}
