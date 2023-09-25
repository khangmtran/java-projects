public abstract class Plant {
	protected String[][] plot; // A 2D array representing the plot of the plant

	public Plant() { // Constructor to initialize the plot
		plot = new String[5][5];
		for (int i = 0; i < plot.length; i++) {
			for (int j = 0; j < plot[i].length; j++) {
				plot[i][j] = ".";
			}
		}
	}

	public String[][] getPlot() { // Returns a copy of the plot
		String[][] copyPlot = new String[5][5];
		for (int i = 0; i < plot.length; i++) {
			for (int j = 0; j < plot[i].length; j++) {
				copyPlot[i][j] = plot[i][j];
			}
		}
		return copyPlot;
	}
	public String checkPlant() {
		if(plot[4][2] != ".")
			return plot[4][2];
		else if(plot[2][2] != ".")
			return plot[2][2];
		else if(plot[0][2] != ".")
			return plot[0][2];
		else
			return ".";
	}
	// Abstract methods
	abstract void plant(String plantName);
	abstract void grow(int times);
	abstract String getPlant();
}