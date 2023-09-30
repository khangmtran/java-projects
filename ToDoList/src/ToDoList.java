import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Khang Tran ToDoList class contains a list of string items. The list
 *         is implemented as an ObservableList, allowing listeners to track
 *         changes to the list. It provides functionality to add, remove, and
 *         move items within the list. The class also overrides default
 *         serialization methods to handle the custom serialization of the
 *         ObservableList, ensuring that the list is properly stored and
 *         retrieved.
 */
public class ToDoList implements Serializable {

	private static final long serialVersionUID = 1L;
	// transient observable list to store items, transient ensures it is not
	// serialized.
	private transient ObservableList<String> list;

	/**
	 * Constructor initializes the list as an observable array list.
	 */
	public ToDoList() {
		list = FXCollections.observableArrayList();
	}

	/**
	 * Custom serialization method
	 * 
	 * @param oos
	 * @throws IOException
	 */
	private void writeObject(ObjectOutputStream oos) throws IOException {
		oos.defaultWriteObject();// Perform default serialization for non-transient objects.
		oos.writeObject(new ArrayList<>(list));// Write the list to the ObjectOutputStream as an ArrayList.
	}

	/**
	 * Custom serialization method
	 * 
	 * @param ois
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
		ois.defaultReadObject(); // Perform default deserialization for non-transient objects.

		// Read the list from the ObjectInputStream and assign it back to the list
		// variable.
		list = FXCollections.observableList((List<String>) ois.readObject());
	}

	/**
	 * method to add an item to the list
	 * 
	 * @param item
	 */
	public void add(String item) {
		list.add(item);
	}

	/**
	 * method to remove an item from the list
	 * 
	 * @param index
	 */
	public void remove(int index) {
		list.remove(index);
	}

	/**
	 * method to move an item to the top of the list
	 * 
	 * @param index
	 * @param item
	 */
	public void moveTop(int index, String item) {
		list.remove(index);
		list.add(0, item);
	}

	/**
	 * method to move an item to the bottom of the list
	 * 
	 * @param index
	 * @param item
	 */
	public void moveBottom(int index, String item) {
		list.remove(index);
		list.add(list.size(), item);
	}

	/**
	 * method to raise the item one position up in the list
	 * 
	 * @param index
	 * @param item
	 */
	public void raise(int index, String item) {
		String copyString = list.get((index - 1));
		list.set((index - 1), item);
		list.set(index, copyString);
	}

	/**
	 * method to lower the item one position down in the list
	 * 
	 * @param index
	 * @param item
	 */
	public void lower(int index, String item) {
		String copyString = list.get((index + 1));
		list.set((index + 1), item);
		list.set(index, copyString);
	}

	/**
	 * getter method
	 * 
	 * @return the observable list
	 */
	public ObservableList<String> getList() {
		return list;
	}
}
