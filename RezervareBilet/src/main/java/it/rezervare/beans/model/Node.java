package it.rezervare.beans.model;

public class Node {

	private String airportName;
    public Node() {
    	
    }
    public Node(String name) {
        this.airportName = name;
    }

	public String getName() {
		return airportName;
	}

	public void setName(String name) {
		this.airportName = name;
	}
	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node other = (Node) obj;
        if (airportName == null) {
            if (other.airportName != null)
                return false;
        } else if (!airportName.equals(other.airportName))
            return false;
        return true;
    }
	@Override
	public String toString() {
		return "Node [airportName=" + airportName + "]";
	}

}
