package it.rezervare.beans.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class Route {

	private List<LinkedList<String>> routes = new ArrayList<LinkedList<String>>();

    public List<LinkedList<String>> depthFirst(Graph graph, LinkedList<String> visited, String arrivalAirport ) {
    	LinkedList<String> nodes = graph.adjacentNodes(visited.getLast());
        for (String node : nodes) {
            if (visited.contains(node)) {
                continue;
            }
            if (node.equals(arrivalAirport)) {
                visited.add(node);
                addPath(visited);
                visited.removeLast();
                break;
            }
        }
        for (String node : nodes) {
            if (visited.contains(node) || node.equals(arrivalAirport)) {
                continue;
            }
            visited.addLast(node);
            depthFirst(graph, visited,arrivalAirport);
            visited.removeLast();
        }
        return routes;
    }
    
    private void addPath(LinkedList<String> visited) {
    	LinkedList<String> routList = new LinkedList<String>();
    	routList.addAll(visited);
        for (String node : routList) {
            System.out.print(node);
            System.out.print(" ");
        }
        routes.add(routList);
    }

}
