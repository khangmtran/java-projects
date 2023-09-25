public class Flower extends Plant {
	public Flower() { // Constructor calls the superclass constructor
		super();
	}

	@Override
	void plant(String plantName) { // Plant a flower at the center of the plot

		if (plot[2][2] == ".")
			plot[2][2] = plantName.substring(0, 1); //take only the inital of the plant

	}

	@Override
	void grow(int timesgrow) { // Grow the flower 

		if (plot[2][2] != ".")
			assistGrow(timesgrow); //help with how to grow

	}

	void assistGrow(int times) {  // Helper method to spread the flower
		boolean check = true;
		while (check) {
			if (plot[2][1] == ".") {
				plot[2][1] = plot[2][2];
				plot[2][3] = plot[2][2];
				plot[1][2] = plot[2][2];
				plot[3][2] = plot[2][2];
				times--;
			} else if (plot[1][1] == ".") {
				plot[2][0] = plot[2][2];
				plot[2][4] = plot[2][2];
				plot[1][1] = plot[2][2];
				plot[1][3] = plot[2][2];
				plot[3][1] = plot[2][2];
				plot[3][3] = plot[2][2];
				plot[0][2] = plot[2][2];
				plot[4][2] = plot[2][2];
				times--;
			} else if (plot[1][0] == ".") {
				plot[1][0] = plot[2][2];
				plot[1][4] = plot[2][2];
				plot[3][0] = plot[2][2];
				plot[3][4] = plot[2][2];
				plot[0][1] = plot[2][2];
				plot[0][3] = plot[2][2];
				plot[4][1] = plot[2][2];
				plot[4][3] = plot[2][2];
				times--;
			} else if (plot[0][0] == ".") {
				plot[0][0] = plot[2][2];
				plot[0][4] = plot[2][2];
				plot[4][0] = plot[2][2];
				plot[4][4] = plot[2][2];
				times--;
			} else
				times--;
			if (times == 0)
				check = false;
		}
	}

	void pick() { // Remove flowers with dots
		for (int i = 0; i < plot.length; i++) {
			for (int j = 0; j < plot[i].length; j++) {
				plot[i][j] = ".";
			}
		}
	}

	@Override
	String getPlant() { //method to get the name
		if (plot[2][2] != ".")
			return plot[2][2];
		return ".";
	}

	
}