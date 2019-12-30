package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


import org.junit.jupiter.api.Test;

import elements.Edge;
import elements.Node;
import dataStructure.*;

public class DGraphTest {

	@Test
	void testGetNode() {
		HashMap<Integer,node_data> graph = new HashMap<Integer, node_data>();
		graph.put(1, new Node(1));
		graph.put(2, new Node(2));
		graph.put(5, new Node(5));
		graph.put(7, new Node(7));
		DGraph test = new DGraph();
		test.setGraph(graph);
		System.out.println("\nThe test of getting a node:");
		if(test.getNode(3) != graph.get(3)) {
			fail(test.getNode(3)+" shoud equal "+ graph.get(3));
		}
		else {
			System.out.println("\nPassed test");
		}
	}

	@Test
	void testGetEdge() {
		HashMap<Integer,node_data> graph = new HashMap<Integer, node_data>();
		Edge temp = new Edge(5,3,0);
		Node tempp = new Node(5);
		tempp.AddThisEdge(temp);
		graph.put(1, new Node(1));
		graph.put(tempp.getKey(), tempp);
		graph.put(3, new Node(3));
		graph.put(7, new Node(7));
		DGraph test = new DGraph();
		test.setGraph(graph);
		System.out.println("\nThe test of getting an edge:");
		if(test.getEdge(5, 3) != temp) {
			fail(temp+" should equal "+test.getEdge(2,3));
		}
		else {
			System.out.println("\nPassed test");
		}
	}

	@Test
	void testAddNode() { 
		HashMap<Integer, node_data> graph = new HashMap<Integer,node_data>();
		Edge temp = new Edge(5,3,0);
		Node tempp = new Node(5);
		tempp.AddThisEdge(temp);
		graph.put(1, new Node(1));
		graph.put(tempp.getKey(), tempp);
		graph.put(3, new Node(3));
		graph.put(7, new Node(7));
		DGraph test = new DGraph();
		test.setGraph(graph);
		test.addNode(new Node(8));
		System.out.println("\nThe test of adding a node:");
		if(!test.getGraph().containsKey(8)) {
			fail(test.getNode(8)+" should equal "+graph.get(8));
		}
		else {
			System.out.println("\nPassed test");
		}
	}

	@Test
	void testConnect(){
		HashMap<Integer, node_data> graph = new HashMap<Integer,node_data>();
		Edge temp = new Edge(5,3,0);
		Node tempp = new Node(5);
		Edge temppp = new Edge(1.2);
		tempp.AddThisEdge(temppp);
		tempp.AddThisEdge(temp);
		graph.put(1, new Node(1));
		graph.put(tempp.getKey(), tempp);
		graph.put(3, new Node(3));
		graph.put(7, new Node(7));
		DGraph test = new DGraph();
		test.setGraph(graph);
		test.connect(1, 7, 1.2);
		System.out.println("\nThe test of making sure an edge was connected:");
		if(test.getEdge(1, 7).getWeight() != 1.2) {
			fail(test.getEdge(1, 7)+" should equal "+tempp.getWeight());
		}
		else {
			System.out.println("\nPassed test");
		}
	}

	@Test
	void testGetV() {
		HashMap<Integer,node_data> graph = new HashMap<Integer, node_data>();
		Collection<node_data> temps = new ArrayList<node_data>();
		Edge temp = new Edge(5,3,0);
		Node tempp = new Node(5);
		tempp.AddThisEdge(temp);
		graph.put(1, new Node(1));
		graph.put(tempp.getKey(), tempp);
		graph.put(3, new Node(3));
		graph.put(7, new Node(7));
		DGraph test = new DGraph();
		test.setGraph(graph);
		temps = test.getV();
		System.out.println("\nThe test of recieving a collection of the values (the nodes in the graph:");
		System.out.println("\n"+temps);
		System.out.println("\nshould be the same elemets above.");
		for (node_data node : temps) {
			System.out.println(node);
		}
	}

	@Test
	void testGetE() {
		HashMap<Integer,node_data> graph = new HashMap<Integer, node_data>();
		Collection<edge_data> temps = new ArrayList<edge_data>();
		Edge temp = new Edge(5,3,0);
		Edge temppp= new Edge(1,2,1.2);
		Node tempp = new Node(5);
		tempp.AddThisEdge(temp);
		tempp.AddThisEdge(temppp);
		graph.put(1, new Node(1));
		graph.put(tempp.getKey(), tempp);
		graph.put(3, new Node(3));
		graph.put(7, new Node(7));
		DGraph test = new DGraph();
		test.setGraph(graph);
		temps = test.getE(5);
		System.out.println("\nThe test of recieving a collection of the values (the edges of this node):");
		System.out.println("\n"+temps);
		System.out.println("\nshould be the same elemets above.");
		for (edge_data edge : temps) {
			System.out.println(edge);
		}
	}

	@Test
	void testRemoveNode() {
		HashMap<Integer,node_data> graph = new HashMap<Integer, node_data>();
		Edge temp = new Edge(5,3,0);
		Edge temppp= new Edge(1,2,1.2);
		Node tempp = new Node(5);
		tempp.AddThisEdge(temp);
		tempp.AddThisEdge(temppp);
		graph.put(1, new Node(1));
		graph.put(tempp.getKey(), tempp);
		graph.put(3, new Node(3));
		graph.put(7, new Node(7));
		DGraph test = new DGraph();
		test.setGraph(graph);
		test.removeNode(5);
		System.out.println("\nThe test of removing a node from a graph:");
		if(graph.containsValue(tempp)) {
			fail("\nThe graph still contains this node.");
		}
		else {
			System.out.println("\nNode removed succesfully.");
		}
	}
	
	@Test
	void testRemoveEdge() {
		HashMap<Integer,node_data> graph = new HashMap<Integer, node_data>();
		Edge temp = new Edge(5,2,0);
		Edge temppp= new Edge(5,2,1.2);
		Node tempp = new Node(5);
		tempp.AddThisEdge(temp);
		tempp.AddThisEdge(temppp);
		graph.put(1, new Node(1));
		graph.put(tempp.getKey(), tempp);
		graph.put(2, new Node(2));
		graph.put(7, new Node(7));
		DGraph test = new DGraph();
		test.setGraph(graph);
		System.out.println("\nThe test of removing an edge:");
		if(test.getE(5).contains(temppp)) {
			fail("\nThe graph still contains this edge.");
		}
		else {
			System.out.println("\nThe Edge was removed succesfully.");
		}
	}
	
	@Test
	void testNodeSize() {
		HashMap<Integer,node_data> graph = new HashMap<Integer, node_data>();
		Edge temp = new Edge(5,2,0);
		Edge temppp= new Edge(5,2,1.2);
		Node tempp = new Node(5);
		tempp.AddThisEdge(temp);
		tempp.AddThisEdge(temppp);
		graph.put(1, new Node(1));
		graph.put(tempp.getKey(), tempp);
		graph.put(2, new Node(2));
		graph.put(7, new Node(7));
		DGraph test = new DGraph();
		test.setGraph(graph);
		System.out.println("\nThe test to see the amount of nodes in this graph:");
		if(test.nodeSize() != 4) {
			fail("\nThe amont of nodes should be 4 not"+ test.nodeSize());
		}
		else {
			System.out.println("\nPassed test");
		}
	}
	
	@Test
	void testEdgeSize() {
		HashMap<Integer,node_data> graph = new HashMap<Integer, node_data>();
		Edge temp = new Edge(5,2,0);
		Edge temppp= new Edge(5,2,1.2);
		Node tempp = new Node(5);
		tempp.AddThisEdge(temp);
		tempp.AddThisEdge(temppp);
		graph.put(1, new Node(1));
		graph.put(tempp.getKey(), tempp);
		graph.put(2, new Node(2));
		graph.put(7, new Node(7));
		DGraph test = new DGraph();
		test.setGraph(graph);
		System.out.println("\nThe test of the amount of edges in this graph:");
		if(test.edgeSize() != 2) {
			fail("\nThe amount of edges shoud be 2 not "+test.edgeSize());
		}
		else {
			System.out.println("\nPassed test");
		}
	}
	
	@Test
	void testGetMC() {
		HashMap<Integer,node_data> graph = new HashMap<Integer, node_data>();
		Edge temp = new Edge(5,2,0);
		Edge temppp= new Edge(5,2,1.2);
		Node tempp = new Node(5);
		tempp.AddThisEdge(temp);
		tempp.AddThisEdge(temppp);
		graph.put(1, new Node(1));
		graph.put(tempp.getKey(), tempp);
		graph.put(2, new Node(2));
		graph.put(7, new Node(7));
		DGraph test = new DGraph();
		test.setGraph(graph);
		test.removeEdge(5, 2);
		test.addNode(new Node());
		System.out.println("\nThe test of changes in this graph:");
		if(test.getMC() != 2) {
			fail("\nThe amout of changes are not correct should equal 2 not"+test.getMC());
		}
		else {
			System.out.println("\nPassed test");
		}
	}
}
