package it.rezervare.beans.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.rezervare.beans.model.Edge;
import it.rezervare.beans.model.Graphs;
import it.rezervare.beans.model.Node;

@SuppressWarnings("unused")
public class DijkstraAlgorithm {
	private final  List<Node> nodes;
    private final  List<Edge> edges;
    private Set<Node> settledNodes;
    private Set<Node> unSettledNodes;
    private Map<Node, Node> predecessors;
    private Map<Node, Integer> distance;

    public DijkstraAlgorithm(final Graphs graph) {
        // create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList<Node>(graph.getNodes());
        this.edges = new ArrayList<Edge>(graph.getEdges());
    }

    public void execute(final Node source) {
        settledNodes = new HashSet<Node>();
        unSettledNodes = new HashSet<Node>();
        distance = new HashMap<Node, Integer>();
        predecessors = new HashMap<Node, Node>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            final Node node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }
    private void findMinimalDistances(final Node node) {
    	System.out.println("Enter findMinimalDistances() for node = ["+node+"]");
        final List<Node> adjacentNodes = getNeighbors(node);
        for (final Node target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }
        System.out.println("Exit findMinimalDistances()");
    }
 
    private int getDistance(final Node node, final Node target) {
    	final int distance = 0;
        for (final Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && edge.getDestination().equals(target)) {
                return edge.getWeight();
            }
        }
        return distance;
    }

    private List<Node> getNeighbors(final Node node) {
        final List<Node> neighbors = new ArrayList<Node>();
        for (final Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    private Node getMinimum(final Set<Node> Nodees) {
        Node minimum = null;
        for (final Node Node : Nodees) {
            if (minimum == null) {
                minimum = Node;
            } else {
                if (getShortestDistance(Node) < getShortestDistance(minimum)) {
                    minimum = Node;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(final Node Node) {
        return settledNodes.contains(Node);
    }

    private int getShortestDistance(final Node destination) {
        final Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    public LinkedList<Node> getPath(final Node target) {
    	System.out.println("ENTER getPath with target = ["+target+"] predecessors = ["+predecessors+"]");
        final LinkedList<Node> path = new LinkedList<Node>();
        Node step = target;
        // check if a path exists
        
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        System.out.println("EXIT getPath with path = ["+target+"]");
        return path;
    }
}
