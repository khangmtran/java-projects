/**
 * @author Khang Tran
 * date 4/28/2023
 * The Travelling Salesman Problem
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		DGraph<Integer, Double> graph = new DGraph<>();
		List<Integer> path = new ArrayList<>();
		try (Scanner scanner = new Scanner(new File(args[0]))) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line.startsWith("%")) {
					continue;
				} else {
					String[] text = line.split("\\s+");
					int node = Integer.parseInt(text[0]);
					int node1 = Integer.parseInt(text[1]);
					if (node == node1) {
						for (int i = 1; i <= node; i++) {
							graph.addNode(i, null);
						}
					}
					double weight = Double.parseDouble(text[2]);
					graph.addEdge(node, node1, weight);
					if (!scanner.hasNextLine()) {
						if (args[1].equals("HEURISTIC")) {
							double a = Double.parseDouble(String.format("%.1f", graph.tspHeuristic(1, path)));
							System.out.print("cost = " + a + ", ");
							System.out.println("visitOrder = " + path);

						} else if (args[1].equals("BACKTRACK")) {
							double a = Double.parseDouble(String.format("%.1f", graph.tspBacktracking(1, path)));
							System.out.print("cost = " + a + ", ");
							System.out.println("visitOrder = " + path);
							
						} else if (args[1].equals("MINE")) {
							double a = Double.parseDouble(String.format("%.1f", graph.tspMine(1, path)));
							System.out.print("cost = " + a + ", ");
							System.out.println("visitOrder = " + path);

						} else {
							long startTime = System.nanoTime();
							double tspCost = graph.tspHeuristic(1, path);
							long endTime = System.nanoTime();
							float duration = (endTime - startTime) / 1000000.0f;
							System.out.println("heuristic: cost = " + duration + " milliseconds");

							long startTime2 = System.nanoTime();
							double tspCost2 = graph.tspMine(1, path);
							long endTime2 = System.nanoTime();
							float duration2 = (endTime2 - startTime2) / 1000000.0f;
							System.out.println("mine: cost = " + duration2 + " milliseconds");

							long startTime1 = System.nanoTime();
							double tspCost1 = graph.tspBacktracking(1, path);
							long endTime1 = System.nanoTime();
							float duration1 = (endTime1 - startTime1) / 1000000.0f;
							System.out.println("backtrack: cost = " + duration1 + " milliseconds");

						}
					}
				}
			}
		}
	}

}
