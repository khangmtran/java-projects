public class Tree extends Plant {
	public Tree() { // Constructor calls the superclass constructor
		super();
	}

	@Override
	void plant(String plantName) {  // Method to plant new tree

		if (plot[4][2] == ".")
			plot[4][2] = plantName.substring(0, 1);//take only the inital of the plant

	}

	@Override
	void grow(int timesgrow) { //method to grow the tree with assist from another method
		if (plot[4][2] != ".") 
			assistGrow(timesgrow); //help with how to grow

	}

	void assistGrow(int times) { // Helper method to assist the tree growth
		boolean check = true;
		int start = 3;
		while (check) {

			if (plot[0][2] == ".") {
				plot[start--][2] = plot[4][2];
				times--;
			} else
				times--;

			if (times == 0)
				check = false;
		}
	}

	void cut() { // Remove trees with dots
		for (int i = 0; i < plot.length; i++) {
			for (int j = 0; j < plot[i].length; j++) {
				plot[i][j] = ".";
			}
		}
	}

	@Override
	String getPlant() { //method to get the name
		if (plot[4][2] != ".")
			return plot[2][2];
		return ".";
	}

	
}