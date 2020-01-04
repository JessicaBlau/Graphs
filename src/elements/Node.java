package elements;


import java.util.HashMap;

import dataStructure.edge_data;
import dataStructure.node_data;
import utils.Point3D;

public class Node implements node_data {
	private HashMap<Integer,edge_data> listEdges = new HashMap<Integer, edge_data>();// hashmap that contains the edges of this node with the key of their destination.
	private int counterEdges = 0;
	private int key;
	private Point3D location;
	private double weight;
	private String info;
	private int tag;
	/**
	 * 
	 */
	public Node() {
		key = 0;
		location = null;
		weight = 0;
		info = "";
		tag = 0;
	}
	/**
	 * 
	 * @param key
	 * @param location
	 * @param weight
	 * @param info
	 * @param tag
	 */
	public Node(int key, Point3D location, double weight, String info, int tag) {
		this.info = info;
		this.key = key;
		this.location = location;
		this.weight = weight;
		this.tag = tag;
	}
	/**
	 * 
	 * @param key
	 */
	public Node(int key) {
		this.key = key;
		this.info = "";
		this.location = null;
		this.weight = 0;
		this.tag = 0;
	}
	/**
	 * 
	 * @return
	 */
	public int getNumberOfEdges() {
		return counterEdges;
	}
	/**
	 * 
	 * @return
	 */
	public HashMap<Integer, edge_data> getAllEdges(){
		return listEdges;
	}
	/**
	 * 
	 * @param e
	 */
	public void AddThisEdge(edge_data e) {
		counterEdges++;
		listEdges.putIfAbsent(e.getDest(), e);
	}
	/**
	 * 
	 */
	@Override
	public int getKey() {
		return key;
	}
	/**
	 * 
	 */
	@Override
	public Point3D getLocation() {
		return location;
	}
	/**
	 * 
	 */
	@Override
	public void setLocation(Point3D p) {
		this.location = p;
	}
	/**
	 * 
	 */
	@Override
	public double getWeight() {
		return weight;
	}
	/**
	 * 
	 */
	@Override
	public void setWeight(double w) {
		this.weight = w;
	}
	/**
	 * 
	 */
	@Override
	public String getInfo() {
		return info;
	}
	/**
	 * 
	 */
	@Override
	public void setInfo(String s) {
		this.info = s;
	}
	/**
	 * 
	 */
	@Override
	public int getTag() {
		return tag;
	}
	/**
	 * 
	 */
	@Override
	public void setTag(int t) {
		this.tag = t;
	}

}
