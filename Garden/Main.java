/**
 * Khang Minh Tran
 * Date: 2/22/2023
 * Garden Simulator - main class
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(new File(args[0]))) {  // Read in file input with line command arguments
			int row = 0;
			int col = 0;
			String line = scanner.nextLine();
			if (line.startsWith("rows:")) {
				String numberString = line.substring(6).trim();
				row = Integer.parseInt(numberString); //parse the string to row
			} else if (line.startsWith("cols:")) {
				String numberString = line.substring(6).trim();
				col = Integer.parseInt(numberString); //parse string to col
			}

			line = scanner.nextLine();
			if (line.startsWith("cols:")) {
				String numberString = line.substring(6).trim();
				col = Integer.parseInt(numberString);
			} else if (line.startsWith("cols:")) {
				String numberString = line.substring(6).trim();
				col = Integer.parseInt(numberString);

			}
			if (col > 16) { // Print an error message and exit the program if the number of columns exceeds 16
	            System.out.println("Too many plot columns.");
	            System.exit(0);
	        }

			Garden garden = new Garden(row, col); // Create a new Garden object with specified dimensions
			line = scanner.nextLine();
			while (scanner.hasNextLine()) { // Loop through each line in the input file
				line = scanner.nextLine();
				line = line.toLowerCase();

				String[] text = line.replaceAll("[\\[\\](){}]", "").toLowerCase().split("[,\\s]+");

				if (line.startsWith("plant")) { // If the line starts with 'plant', create a new Plant object based on the specified type and add it to the garden
					int inputRow = Integer.parseInt(text[1]);
					int inputCol = Integer.parseInt(text[2]);
					String type = text[3];
					switch (type) { //switch case to see if which case fit
					case "iris":
						Plant iris = new Flower();
						iris.plant(type);
						garden.addPlant(iris, inputRow, inputCol);
						break;
					case "lily":
						Plant lily = new Flower();
						lily.plant(type);
						garden.addPlant(lily, inputRow, inputCol);
						break;
					case "rose":
						Plant rose = new Flower();
						rose.plant(type);
						garden.addPlant(rose, inputRow, inputCol);
						break;
					case "daisy":
						Plant daisy = new Flower();
						daisy.plant(type);
						garden.addPlant(daisy, inputRow, inputCol);
						break;
					case "tulip":
						Plant tulip = new Flower();
						tulip.plant(type);
						garden.addPlant(tulip, inputRow, inputCol);
						break;
					case "sunflower":
						Plant sunflower = new Flower();
						sunflower.plant(type);
						garden.addPlant(sunflower, inputRow, inputCol);
						break;
					case "oak":
						Plant oak = new Tree();
						oak.plant(type);
						garden.addPlant(oak, inputRow, inputCol);
						break;
					case "willow":
						Plant willow = new Tree();
						willow.plant(type);
						garden.addPlant(willow, inputRow, inputCol);
						break;
					case "banana":
						Plant banana = new Tree();
						banana.plant(type);
						garden.addPlant(banana, inputRow, inputCol);
						break;
					case "coconut":
						Plant coconut = new Tree();
						coconut.plant(type);
						garden.addPlant(coconut, inputRow, inputCol);
						break;
					case "pine":
						Plant pine = new Tree();
						pine.plant(type);
						garden.addPlant(pine, inputRow, inputCol);
						break;
					case "garlic":
						Plant garlic = new Vegetable();
						garlic.plant(type);
						garden.addPlant(garlic, inputRow, inputCol);
						break;
					case "zucchini":
						Plant zucchini = new Vegetable();
						zucchini.plant(type);
						garden.addPlant(zucchini, inputRow, inputCol);
						break;
					case "tomato":
						Plant tomato = new Vegetable();
						tomato.plant(type);
						garden.addPlant(tomato, inputRow, inputCol);
						break;
					case "yam":
						Plant yam = new Vegetable();
						yam.plant(type);
						garden.addPlant(yam, inputRow, inputCol);
						break;
					case "lettuce":
						Plant lettuce = new Vegetable();
						lettuce.plant(type);
						garden.addPlant(lettuce, inputRow, inputCol);
						break;
					default:
						System.out.println();
					}

				} else if (line.startsWith("print")) //print the garden if line start with print
					garden.print();
				else if (line.startsWith("grow") && text.length == 2) { //grow statements for line start with grow
					int timesGrow = Integer.parseInt(text[1]);
					garden.grow(timesGrow);
				} else if (line.startsWith("grow") && text.length == 4) {
					int timesGrow = Integer.parseInt(text[1]);
					int rowGrow = Integer.parseInt(text[2]);
					int colGrow = Integer.parseInt(text[3]);
					garden.growSpecific(rowGrow, colGrow, timesGrow);
				} else if (line.startsWith("grow") && text.length == 3) {
					int timesGrow = Integer.parseInt(text[1]);
					String type = text[2];
					switch (type) {
					case "flower":
						garden.growFlower(timesGrow);
						break;
					case "tree":
						garden.growTree(timesGrow);
						break;
					case "vegetable":
						garden.growVegetable(timesGrow);
						break;
					default:
						System.out.println();
					}
				} else if (line.startsWith("harvest") && text.length == 1) { //harvest statements for line start with harvest
					garden.harvestVegetable();
				} else if (line.startsWith("harvest") && text.length == 3) {
					int harvestRow = Integer.parseInt(text[1]);
					int harvestCol = Integer.parseInt(text[2]);
					garden.harvestSpecific(harvestRow, harvestCol);
				} else if (line.startsWith("harvest") && text.length == 2) {
					String type = text[1];
					switch (type) {
					case "garlic":
						garden.harvestType("g");
						break;
					case "zucchini":
						garden.harvestType("z");
						break;
					case "tomato":
						garden.harvestType("t");
						break;
					case "yam":
						garden.harvestType("y");
						break;
					case "lettuce":
						garden.harvestType("l");
						break;
					default:
						System.out.println();
					}
				} else if (line.startsWith("pick") && text.length == 1) { //pick statements for line starts with pick
					garden.pickAll();
				} else if (line.startsWith("pick") && text.length == 3) {
					int harvestRow = Integer.parseInt(text[1]);
					int harvestCol = Integer.parseInt(text[2]);
					garden.pickSpecific(harvestRow, harvestCol);
				} else if (line.startsWith("pick") && text.length == 2) {
					String type = text[1];
					switch (type) {
					case "iris":
						garden.pickType("i");
						break;
					case "lily":
						garden.pickType("l");
						break;
					case "rose":
						garden.pickType("r");
						break;
					case "daisy":
						garden.pickType("d");
						break;
					case "tulip":
						garden.pickType("t");
						break;
					case "sunflower":
						garden.pickType("s");
						break;
					default:
						System.out.println();
					}
				} else if (line.startsWith("cut") && text.length == 1) { //cut statements for line start with cut
					garden.cutAll();
				} else if (line.startsWith("cut") && text.length == 3) {
					int harvestRow = Integer.parseInt(text[1]);
					int harvestCol = Integer.parseInt(text[2]);
					garden.cutSpecific(harvestRow, harvestCol);
				} else if (line.startsWith("cut") && text.length == 2) {
					String type = text[1];
					switch (type) {
					case "oak":
						garden.cutType("o");
						break;
					case "willow":
						garden.cutType("w");
						break;
					case "banana":
						garden.cutType("b");
						break;
					case "coconut":
						garden.cutType("c");
						break;
					case "pine":
						garden.cutType("p");
						break;
					default:
						System.out.println();
					}
				}

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}