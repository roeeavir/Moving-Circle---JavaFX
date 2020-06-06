import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class TitledBorder extends StackPane {
	
	public TitledBorder(String text, Node component) {
		setPadding(new Insets(10)); // Padding.
		StackPane pane = new StackPane();
		// Setting a border around the combo box (with a hole).
		pane.setStyle("-fx-padding: 10, 0, 0, 0; -fx-background-color: silver, -fx-background,"
				+ " -fx-background; -fx-background-insets: 0 , 0 11 15 11, 1;");
		Label title = new Label(text); // Setting a new label.
		pane.getChildren().add(component); // Adding the combo box to the pane.
		title.setTranslateY(-22); // Adjusting the position of the label.
		// Setting the label to bold, and making it larger.
		title.setStyle("-fx-font-weight: bold; -fx-font-size: 13px;"); 
		getChildren().addAll(pane, title); // Adding the pane and title.
	}
}
