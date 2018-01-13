package it.rezervare.beans.model;

public class Node {

	private Integer id;
	private String airportName;
    public Node() {
    	
    }
    public Node(final String name) {
        this.airportName = name;
    }

	public String getName() {
		return airportName;
	}

	public void setName(final String name) {
		this.airportName = name;
	}
	@Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Node other = (Node) obj;
        if (airportName == null) {
            if (other.airportName != null)
                return false;
        } else if (!airportName.equals(other.airportName)) 
            return false;
        return true;
    }
	@Override
	public String toString() {
		return "Node [airportName=" + airportName + "] id =[" + id + "]" ;
	}
	public Integer getId() {
		return id;
	}
	public void setId(final Integer id) {
		this.id = id;
	}

}
