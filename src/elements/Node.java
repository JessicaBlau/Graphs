package elements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import dataStructure.edge_data;
import dataStructure.node_data;
import utils.Point3D;

public class Node implements node_data {
	private HashMap<Integer,edge_data> listEdges = new HashMap<Integer, edge_data>();
	private int key;
	private Point3D location;
	private double weight;
	private String info;
	private int tag;
	
	public Node() {
		key = 0;
		location = null;
		weight = 0;
		info = "";
		tag = 0;
	}
	
	public HashMap<Integer, edge_data> getAllEdges(){
		return listEdges;
	}
	
	public void AddThisEdge(edge_data e) {
		listEdges.putIfAbsent(this.key, e);
	}

	@Override
	public int getKey() {
		return key;
	}

	@Override
	public Point3D getLocation() {
		return location;
	}

	@Override
	public void setLocation(Point3D p) {
		this.location = p;
	}

	@Override
	public double getWeight() {
		return weight;
	}

	@Override
	public void setWeight(double w) {
		this.weight = w;
	}

	@Override
	public String getInfo() {
		return info;
	}

	@Override
	public void setInfo(String s) {
		this.info = s;
	}

	@Override
	public int getTag() {
		return tag;
	}

	@Override
	public void setTag(int t) {
		this.tag = t;
	}

}
