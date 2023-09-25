public class Garden {
	private int row; // rows of garden
	private int col; // columns of garden
	private Plant[][] gardenArray; // 2D array of type plants in the garden

	public Garden(int row, int col) {// constructor to create a new garden object
		this.row = row;
		this.col = col;
		this.gardenArray = new Plant[row][col];

	}

//getters and setters
	public void setRow(int row) {
		this.row = row;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public void growSpecific(int row, int col, int times) { // method to grow a specific plant at a given location

		System.out.println("> GROW " + times + " (" + row + "," + col + ")");
		System.out.println();

		if (row < 0 || row >= gardenArray.length || col < 0 || col >= gardenArray[row].length) // check if the given location is valid
			System.out.println("Can't grow there.");
		else if (gardenArray[row][col] != null ) {// check if there is a plant at the given location
			gardenArray[row][col].grow(times);
		} else {
			System.out.println("Can't grow there.");
		}

	}

	public void grow(int times) { // method to grow all plants in the garden

		System.out.println("> GROW " + times);
		System.out.println();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (gardenArray[i][j] == null)
					continue;
				if (gardenArray[i][j] != null) {
					gardenArray[i][j].grow(times);
				}
			}
		}
	}

	public void growTree(int times) { // method to grow all trees in the garden

		System.out.println("> GROW " + times + " tree");
		System.out.println();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (gardenArray[i][j] == null)
					continue;
				if (gardenArray[i][j] != null && gardenArray[i][j] instanceof Tree) {
					gardenArray[i][j].grow(times);
				}
			}
		}
	}

	public void growVegetable(int times) { // method to grow all vegetables in the garden

		System.out.println("> GROW " + times + " vegetable");
		System.out.println();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (gardenArray[i][j] == null)
					continue;
				if (gardenArray[i][j] != null && gardenArray[i][j] instanceof Vegetable) {
					gardenArray[i][j].grow(times);
				}
			}
		}
	}

	public void growFlower(int times) { // method to grow all flowers in the garden

		System.out.println("> GROW " + times + " flower");
		System.out.println();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (gardenArray[i][j] == null)
					continue;
				if (gardenArray[i][j] != null && gardenArray[i][j] instanceof Flower) {
					gardenArray[i][j].grow(times);
				}
			}
		}
	}

	public void harvestVegetable() { // method to harvest all vegetables in the garden

		System.out.println("> HARVEST");
		System.out.println();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (gardenArray[i][j] == null)
					continue;
				else if (gardenArray[i][j] instanceof Vegetable) {
					Vegetable v = (Vegetable) gardenArray[i][j];
					v.harvest();
				}
			}
		}
	}

	public void harvestSpecific(int row, int col) { // method to harvest a vegetable at a given location in the garden

		System.out.println("> HARVEST " + "(" + row + "," + col + ")");
		System.out.println();
		if (row < 0 || row >= gardenArray.length || col < 0 || col >= gardenArray[row].length) // check if the given location is valid
			System.out.println("Can't harvest there.");
		else if (gardenArray[row][col] instanceof Vegetable) {
			Vegetable v = (Vegetable) gardenArray[row][col];
			v.harvest();
		} else {
			System.out.println("Can't harvest there.");
		}

	}

	public void harvestType(String type) { // harvest the vegetable with a specific type/name

		System.out.println("> HARVEST " + type);
		System.out.println();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (gardenArray[i][j] == null)
					continue;
				else if ( gardenArray[i][j] instanceof Vegetable) {
					Vegetable v = (Vegetable) gardenArray[i][j];
					if (v.checkPlant().equals(type))
						v.harvest();

				}
			}
		}

	}

	public void pickAll() { // remove all flowers

		System.out.println("> PICK");
		System.out.println();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (gardenArray[i][j] == null)
					continue;
				else if (gardenArray[i][j] instanceof Flower) {
					Flower f = (Flower) gardenArray[i][j];
					f.pick();

				}
			}
		}
	}

	public void pickSpecific(int row, int col) { // remove a flower from a given location

		System.out.println("> PICK " + "(" + row + "," + col + ")");
		System.out.println();
		if (row < 0 || row >= gardenArray.length || col < 0 || col >= gardenArray[row].length) // check if the given location is valid
			System.out.println("Can't pick there.");
		 else if (gardenArray[row][col] instanceof Flower) {
			Flower f = (Flower) gardenArray[row][col];
			f.pick();
		} else {
			System.out.println("Can't pick there.");
		}

	}

	public void pickType(String type) { // remove only flower of specific type/name

		System.out.println("> PICK " + type);
		System.out.println();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (gardenArray[i][j] == null)
					continue;
				else if (gardenArray[i][j] instanceof Flower) {
					Flower f = (Flower) gardenArray[i][j];
					if (f.checkPlant().equals(type))
						f.pick();

				}
			}
		}
	}

	public void cutAll() { // remove all tree

		System.out.println("> CUT");
		System.out.println();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (gardenArray[i][j] == null)
					continue;
				else if ( gardenArray[i][j] instanceof Tree) {
					Tree t = (Tree) gardenArray[i][j];
					t.cut();
				}
			}
		}
	}

	public void cutSpecific(int row, int col) { // remove a tree at a given location

		System.out.println("> CUT " + "(" + row + "," + col + ")");
		System.out.println();
		if (row < 0 || row >= gardenArray.length || col < 0 || col >= gardenArray[row].length) // check if the given location is valid
			System.out.println("Can't cut there.");
		 else if ( gardenArray[row][col] instanceof Tree) {
			Tree t = (Tree) gardenArray[row][col];
			t.cut();
		} else {
			System.out.println("Can't cut there.");
		}

	}

	public void cutType(String type) { // remove tree with given name

		System.out.println("> CUT " + type);
		System.out.println();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (gardenArray[i][j] == null)
					continue;
				else if (gardenArray[i][j] instanceof Tree) {
					Tree t = (Tree) gardenArray[i][j];
					if (t.checkPlant().equals(type))
						t.cut();

				}
			}
		}
	}

	public void addPlant(Plant plant, int r, int c) { // add a plant to the garden with given row and column
		gardenArray[r][c] = plant;
	}

	public void print() { // method to print the garden
		System.out.println("> PRINT");

		String[][] copyGarden = new String[gardenArray.length * 5][gardenArray[0].length * 5];

		for (int i = 0; i < copyGarden.length; i++) {
			for (int j = 0; j < copyGarden[0].length; j++) {
				copyGarden[i][j] = ".";
			}
		}

		for (int i = 0; i < gardenArray.length; i++) {
			for (int j = 0; j < gardenArray[0].length; j++) {
				Plant plant = gardenArray[i][j];
				if (plant != null) {
					String[][] plot = plant.getPlot();
					for (int x = 0; x < plot.length; x++) {
						for (int y = 0; y < plot[0].length; y++) {
							if (!plot[x][y].equals(".")) {
								int row = i * 5 + x;
								int col = j * 5 + y;
								copyGarden[row][col] = plot[x][y];
							}
						}
					}
				}
			}
		}
		for (int i = 0; i < copyGarden.length; i++) {
			for (int j = 0; j < copyGarden[0].length; j++) {
				System.out.print(copyGarden[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}