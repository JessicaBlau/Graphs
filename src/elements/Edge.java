package elements;


import dataStructure.edge_data;

public class Edge implements edge_data {
	private int src;
	private int dest;
	private double weight;
	private String info;
	private int tag;
	
	public Edge() {
		this.src = 0;
		this.dest = 0;
		this.weight = 0;
		this.info = "";
		this.tag = 0;
	}
	public Edge(int src,int dest,double w) {
		this.dest = dest;
		this.src = src;
		this.weight = w;
		this.tag = 0;
		this.info = "";
	}
	@Override
	public int getSrc() {
		return src;
	}

	@Override
	public int getDest() {
		return dest;
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
