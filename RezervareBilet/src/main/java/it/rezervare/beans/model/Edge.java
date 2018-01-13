package it.rezervare.beans.model;

public class Edge {

    private Node source;
    private Node destination;
    private int weight;
 
    public Edge( final Node source, final Node destination, final int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Node getDestination() {
        return destination;
    }

    public Node getSource() {
        return source;
    }
    public int getWeight() {
        return weight;
    }


	public void setSource(final Node source) {
		this.source = source;
	}

	public void setDestination(final Node destination) {
		this.destination = destination;
	}

	public void setWeight(final int weight) {
		this.weight = weight;
	}
	@Override
	public String toString() {
		return source + " " + destination;
	}
}
