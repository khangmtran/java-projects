import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Khang Tran Graphical User Interface for a ToDo lsit application using
 *         JavaFX Use ListView with ObservableList to manipulate a graphical
 *         view of a list
 */
public class ToDoListGUI extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	// Declaring GUI components and ToDoList object.
	private VBox pane;
	private VBox innerVbox;
	private HBox hbox;
	private ListView<String> listView;
	private ToDoList list;
	private Button saveButton = new Button("Save Current list");
	private Button topButton = new Button("Top");
	private Button botButton = new Button("Bottom");
	private Button raiseButton = new Button("Raise");
	private Button lowerButton = new Button("Lower");
	private Button removeButton = new Button("Remove");
	private TextField textField = new TextField("");
	private Label label = new Label("Enter a new ToDo");

	@Override
	public void start(Stage stage) {
		// Setting up the GUI layout and registering handlers.
		layoutGUI();
		setAlert();
		registerHandlers();

		// Put the pane in a sized Scene and show the GUI
		Scene scene = new Scene(pane, 700, 500);
		stage.setScene(scene);
		stage.show();
		// Handling window close event.
		// Allow users to save the current list of ToDos or leave the list unchanged
		// from startup
		stage.setOnCloseRequest(event -> {
			Alert alertClose = new Alert(AlertType.CONFIRMATION);
			alertClose.setHeaderText("Click cancel to not save any changes");
			alertClose.setContentText("Click OK to save the current ToDo list");
			Optional<ButtonType> result = alertClose.showAndWait();
			if (result.get() == ButtonType.OK) {
				try {
					FileOutputStream bytesToDisk = new FileOutputStream("objects.ser");
					ObjectOutputStream outFile = new ObjectOutputStream(bytesToDisk);
					outFile.writeObject(list);
					outFile.close();
					Platform.exit();
					System.exit(0);
				} catch (IOException ioe) {
					System.out.println("Writing objects failed");
					ioe.printStackTrace();
					Platform.exit();
					System.exit(0);
				}
			} else {
				return;
			}
		});
	}

	/**
	 * Method to layout GUI components
	 */
	private void layoutGUI() {
		pane = new VBox();
		hbox = new HBox();
		innerVbox = new VBox();
		setButton();
		pane.setPadding(new Insets(10, 30, 10, 30));
		pane.setSpacing(10);
		hbox.setSpacing(35);
		list = new ToDoList();
		listView = new ListView<>();
		listView.setItems(list.getList());
		hbox.getChildren().addAll(topButton, botButton, raiseButton, lowerButton, removeButton);
		pane.getChildren().addAll(label, textField, saveButton);
		innerVbox.getChildren().add(listView);
		pane.getChildren().addAll(innerVbox, hbox);

	}

	/**
	 * Method to set the initial alert for loading persisted to-do list Allow users
	 * to start with a saved list of ToDos stored in a serialized file or start with
	 * an empty list of ToDos
	 */
	private void setAlert() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Click cancel to start with an empty list");
		alert.setContentText("Click OK to start with the persistent ToDo List");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			try {
				FileInputStream rawBytes = new FileInputStream("objects.ser");
				ObjectInputStream inFile = new ObjectInputStream(rawBytes);
				ToDoList readFile = (ToDoList) inFile.readObject();
				inFile.close();
				list.getList().clear();
				list.getList().addAll(readFile.getList());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			return;
		}
	}

	/**
	 * Method to configure the buttons.
	 */
	private void setButton() {
		topButton.setMinWidth(100);
		botButton.setMinWidth(100);
		raiseButton.setMinWidth(100);
		lowerButton.setMinWidth(100);
		removeButton.setMinWidth(100);
		saveButton.setDefaultButton(true);
	}

	/**
	 * Method to register event handlers for buttons.
	 */
	private void registerHandlers() {
		saveButton.setOnAction((event) -> {
			list.add(textField.getText());
			textField.clear();
		});
		topButton.setOnAction((event) -> {
			int index = listView.getSelectionModel().getSelectedIndex();
			list.moveTop(index, listView.getSelectionModel().getSelectedItem());
		});
		botButton.setOnAction((event) -> {
			int index = listView.getSelectionModel().getSelectedIndex();
			list.moveBottom(index, listView.getSelectionModel().getSelectedItem());
		});
		raiseButton.setOnAction((event) -> {
			int index = listView.getSelectionModel().getSelectedIndex();
			list.raise(index, listView.getSelectionModel().getSelectedItem());
		});
		lowerButton.setOnAction((event) -> {
			int index = listView.getSelectionModel().getSelectedIndex();
			list.lower(index, listView.getSelectionModel().getSelectedItem());
		});
		removeButton.setOnAction((event) -> {
			int index = listView.getSelectionModel().getSelectedIndex();
			list.remove(index);
		});

	}
}
