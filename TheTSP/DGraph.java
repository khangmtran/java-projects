
/**
 * @author Khang Tran
 * date : 4/28/2023
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class DGraph<K, V> {
	/**
	 * 
	 * Internal class node
	 *
	 */
	private class Node {
		K key;
		V value;
		List<Edge> adjList = new ArrayList<>();

		/**
		 * constructor
		 * 
		 * @param key
		 * @param value
		 */
		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	/**
	 * A map that stores the nodes in the graph
	 */
	Map<K, Node> nodes = new HashMap<>();

	/**
	 * Adds a new node with the given key and value to the graph.
	 * 
	 * @param key
	 * @param value
	 */
	void addNode(K key, V value) {
		nodes.put(key, new Node(key, value));
	}

	/**
	 * Adds a new directed edge from the node
	 * 
	 * @param k1
	 * @param k2
	 * @param w
	 */
	void addEdge(K k1, K k2, double w) {
		Node u = nodes.get(k1);
		Node v = nodes.get(k2);
		u.adjList.add(new Edge(v, w));

	}

	/**
	 * Computes the TSP using heuristic algorithm
	 * 
	 * 
	 * @param startKey
	 * @param path
	 * @return the approximate cost of the TSP heuristic
	 */
	public double tspHeuristic(K startKey, List<K> path) {
		Node startNode = nodes.get(startKey);
		LinkedList<Node> queue = new LinkedList<>();
		queue.add(startNode);
		Set<Node> discovered = new HashSet<>();
		discovered.add(startNode);
		double tspCost = 0.0;
		while (queue.size() != 0) {
			Node current = queue.pollFirst();
			path.add(current.key);
			double minEdgeWeight = Double.MAX_VALUE;
			Node minNode = null;
			for (Edge adj : current.adjList) {
				if (!discovered.contains(adj.sink) && adj.weight < minEdgeWeight) {
					minEdgeWeight = adj.weight;
					minNode = adj.sink;
				}
			}
			if (minNode != null) {
				discovered.add(minNode);
				queue.add(minNode);
				tspCost += minEdgeWeight;
			} else {
				for (Edge e : current.adjList) {
					if (e.sink == startNode) {
						tspCost += e.weight;
					}
				}
			}

		}
		return tspCost;
	}

	/**
	 * Computes the TSP using backtrack algorithm
	 * @param startKey
	 * @param path
	 * @return the cost of TSP backtrack
	 */
	public double tspBacktracking(K startKey, List<K> path) {
		List<K> currPath = new ArrayList<>();
		currPath.add(startKey);
		Node startNode = nodes.get(startKey);
		if (startNode == null)
			return -1.0;
		Set<Node> visited = new HashSet<>();

		double currCost = 0.0;
		double tspCost = Double.MAX_VALUE;
		List<K> minPath = new ArrayList<>();
		return tspBacktracking(startNode, startKey, visited, currCost, tspCost, minPath, path, currPath);

	}

	/**
	 * A recursive helper function for the tspBacktracking function that performs the backtracking algorithm.
	 * @param current
	 * @param startKey
	 * @param visited
	 * @param currCost
	 * @param tspCost
	 * @param minPath
	 * @param path
	 * @param currPath
	 * @return cost 
	 */
	public double tspBacktracking(Node current, K startKey, Set<Node> visited, double currCost, double tspCost,
			List<K> minPath, List<K> path, List<K> currPath) {
		visited.add(current);
		if (visited.size() == nodes.size()) {
			for (Edge e : current.adjList) {
				if (e.sink.key == startKey) {
					currCost += e.weight;
				}
			}
			if (currCost < tspCost) {
				tspCost = currCost;
				minPath.clear();
				minPath.addAll(currPath);
				path.clear();
				path.addAll(minPath);
			}
		} else {
			for (Edge e : current.adjList) {
				Node v = e.sink;
				double w = e.weight;
				if (!visited.contains(v)) {
					visited.add(v);
					currPath.add(v.key);
					currCost += w;
					tspCost = tspBacktracking(v, startKey, visited, currCost, tspCost, minPath, path, currPath);
					currCost -= w;
					currPath.remove(currPath.size() - 1);
					visited.remove(v);
				}
			}
		}
		return tspCost;
	}

	/**
	 * my own variation
	 * @param startKey
	 * @param path
	 * @return the cost for my own variation
	 */
	public double tspMine(K startKey, List<K> path) {
		Node startNode = nodes.get(startKey);
		LinkedList<Node> queue = new LinkedList<>();
		queue.add(startNode);
		Set<Node> discovered = new HashSet<>();
		discovered.add(startNode);
		double tspCost = 0.0;
		Node current;
		double minEdgeWeight;
		Node minNode;

		while (queue.size() != 0) {
			int count = 1;
			while (count < 3) {
				current = queue.pollFirst();
				path.add(current.key);

				minEdgeWeight = Double.MAX_VALUE;
				minNode = null;
				for (Edge e : current.adjList) {
					if (!discovered.contains(e.sink) && e.weight < minEdgeWeight) {
						minEdgeWeight = e.weight;
						minNode = e.sink;

					}
				}

				if (minNode != null) {
					discovered.add(minNode);
					queue.add(minNode);
					tspCost += minEdgeWeight;

				} else {

					for (Edge e : current.adjList) {
						if (e.sink == startNode) {
							tspCost += e.weight;
						}

					}
					break;
				}

				count++;
			}
			if (!queue.isEmpty()) {
				List<Double> contain = new ArrayList<>();
				double first = Integer.MAX_VALUE;
				double second = Integer.MAX_VALUE;
				Node n = null;

				current = queue.pollFirst();
				path.add(current.key);

				minEdgeWeight = Double.MAX_VALUE;
				minNode = null;
				for (Edge e : current.adjList) {
					if (!discovered.contains(e.sink) && e.weight < minEdgeWeight) {
						minEdgeWeight = e.weight;
						contain.add(minEdgeWeight);
						n = e.sink;
					}
				}
				Collections.sort(contain);

				if (contain.size() >= 2) {
					for (int i = 0; i < contain.size(); i++) {
						double c = contain.get(i);
						if (c < first) {
							second = first;
							first = c;
						} else if (c < second && c != first) {
							second = c;
						}
					}
				} else {
					minNode = n;
				}
				for (Edge e : current.adjList) {
					if (e.weight == second) {

						minNode = e.sink;
					}
				}
				if (minNode != null) {
					discovered.add(minNode);
					queue.add(minNode);
					tspCost += minEdgeWeight;
				} else {
					for (Edge e : current.adjList) {
						if (e.sink == startNode) {
							tspCost += e.weight;

						}
					}
				}
			}

		}

		return tspCost;
	}

	/**
	 * Class Edge
	 *
	 */
	private class Edge {
		Node sink;
		double weight;

		/**
		 * constructor
		 * @param v
		 * @param w
		 */
		Edge(Node v, double w) {
			sink = v;
			weight = w;
		}
	}
}