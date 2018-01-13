package it.rezervare.beans.model;

import java.util.List;


public class Graphs {
	private List<Node> nodes;
    private List<Edge> edges;
    
    public Graphs(final List<Node> nodes, final List<Edge> edges) {
    	this.nodes = nodes;
    	this.edges = edges;
    }
	public List<Edge> getEdges() {
		return edges; 
	}
	public void setEdges(final List<Edge> edges) {
		this.edges = edges;
	}
	public List<Node> getNodes() {
		return nodes;
	}
	public void setNodes(final List<Node> nodes) {
		this.nodes = nodes;
	}
	@Override 
	public String toString() {
		return "Graphs [nodes=" + nodes + ", edges=" + edges + "] \n\n";
	}
	
}
