public class Vegetable extends Plant {
	public Vegetable() { // Constructor calls the superclass constructor
		super();
	}

	@Override
	void plant(String plantName) {  // Method to plant 
		if (plot[0][2] == ".")
			plot[0][2] = plantName.substring(0, 1);//take only the inital of the plant

	}

	@Override
	void grow(int timesgrow) { // Method to grow the vegetable
		if (plot[0][2] != ".")
			assistGrow(timesgrow); //help with how to grow
	}

	void assistGrow(int times) {  // Method to assist in growing the vegetable 
		boolean check = true;
		int start = 1;
		while (check) {

			if (plot[4][2] == ".") {
				plot[start++][2] = plot[0][2];
				times--;
			} else
				times--;

			if (times == 0)
				check = false;
		}
	}

	void harvest() { // Remove vegetables with dots
		for (int i = 0; i < plot.length; i++) {
			for (int j = 0; j < plot[i].length; j++) {
				plot[i][j] = ".";
			}
		}
	}

	@Override
	String getPlant() { //method to get the name
		if (plot[0][2] != ".")
			return plot[0][2];
		return ".";
	}

	
}
