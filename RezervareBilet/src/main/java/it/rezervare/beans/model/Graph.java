package it.rezervare.beans.model;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Graph {
	private final Map<String, LinkedHashSet<String>> map = new HashMap<>();

    public void addEdge(final String node1, final String node2) {
        LinkedHashSet<String> adjacent = map.get(node1);
        if(adjacent==null) {
            adjacent = new LinkedHashSet<>();
            map.put(node1, adjacent);
        }
        adjacent.add(node2);
    }

    public void addTwoWayVertex(final String node1, final String node2) {
        addEdge(node1, node2);
        addEdge(node2, node1);
    }

    public boolean isConnected(final String node1, final String node2) {
        final Set<String> adjacent = map.get(node1);
        if(adjacent==null) {
            return false;
        }
        return adjacent.contains(node2);
    }

    public LinkedList<String> adjacentNodes(final String last) {
        final LinkedHashSet<String> adjacent = map.get(last);
        if(adjacent==null) {
            return new LinkedList<>();
        }
        return new LinkedList<String>(adjacent);
    }

	@Override
	public String toString() {
		return "Graph [map=" + map + "]";
	}
}