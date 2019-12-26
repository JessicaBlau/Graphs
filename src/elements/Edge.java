package elements;

import dataStructure.edge_data;

public class Edge implements edge_data {
	private Node src;
	private Node dest;
	private double weight;
	private String info;
	private int tag;
	
	public Edge() {
		this.src = null;
		this.dest = null;
		
	}
	@Override
	public int getSrc() {
		return src.getKey();
	}

	@Override
	public int getDest() {
		return dest.getKey();
	}

	@Override
	public double getWeight() {
		return weight;
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
