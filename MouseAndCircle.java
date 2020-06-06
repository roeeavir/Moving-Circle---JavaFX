
/*Name: Roee Aviran
ID: 316492644 */

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MouseAndCircle extends Stage {

	private String[] colors = { "Red", "Blue", "Purple", "Green", "Yellow" };

	public MouseAndCircle() {

		setOnCloseRequest(e -> { // Preventing the ability to close the second stage from the second stage's
									// window.
			e.consume();
		});
		Circle circle = new Circle(100, 100, 50); // Initializing a circle.
		BorderPane secondPane = new BorderPane(); // Making the second pane.
		secondPane.getChildren().add(circle);
		secondPane.setPrefSize(500, 600); // Setting an initial size from the window.
		secondPane.setPadding(new Insets(10)); // Padding.

		ComboBox<String> fillColors = new ComboBox<String>();
		ComboBox<String> strokeColors = new ComboBox<String>();

		ObservableList<String> items = FXCollections.observableArrayList(colors); // Initialize an observable list.

		fillColors.getItems().addAll(items); // Setting the items into the combo box.
		strokeColors.getItems().addAll(items); // Setting the items into the combo box.

		fillColors.setValue(items.get(0)); // Setting the initialized shown color in the combo box.
		strokeColors.setValue(items.get(0));// Setting the initialized shown color in the combo box.

		circle.setFill(Color.valueOf(fillColors.getValue())); // The first color of the circle.
		circle.setStroke(Color.valueOf(strokeColors.getValue())); // The first stroke of the circle.

		HBox bottomPane = new HBox(20); // Making another pane for the bottom pane.
		bottomPane.setAlignment(Pos.CENTER); // Aligning the pane.
		bottomPane.setPadding(new Insets(10)); // Padding.

		// Setting the labels and combo boxes inside a stack pane.
		TitledBorder comboPane1 = new TitledBorder("Circle Color", fillColors);
		TitledBorder comboPane2 = new TitledBorder("Circle Frame", strokeColors);
		
		// Adding the stack panes into the bottom pane.
		bottomPane.getChildren().add(comboPane1);
		bottomPane.getChildren().add(comboPane2);

		secondPane.setBottom(bottomPane);

		fillColors.setOnAction(e -> setFill(fillColors, circle)); // Handling the request of the combo box.
		strokeColors.setOnAction(e -> setStroke(strokeColors, circle)); // Handling the request of the combo box.

		Text text = new Text(); // New text.

		/*
		 * Making the text "unclickable" to prevent it from getting in the way of the
		 * mouse's actions with the combo boxes.
		 */
		text.setDisable(true);

		// Animation sequence for the circle every 5 seconds.
		Timeline animation = new Timeline(new KeyFrame(Duration.millis(5000), e -> {
			changePos(circle); // A method for changing the position of the circle.
			/*
			 * A method for checking to see if the mouse pointer is in the circle, after the
			 * circle has changed position.
			 */
			mouseStatus(text.getX(), text.getY(), circle, text);
		}));

		// Setting the animation to repeat itself indefinitely.
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play(); // Playing the animation.

		/*
		 * A method for checking to see if the mouse pointer is in the circle, after the
		 * mouse has moved.
		 */
		secondPane.setOnMouseMoved(e -> {
			mouseStatus(e.getX(), e.getY(), circle, text);
			text.setX(e.getX());
			text.setY(e.getY());
		});
		/*
		 * A method for checking to see if the mouse pointer is in the circle, after the
		 * mouse has been dragged.
		 */
		secondPane.setOnMouseDragged(e -> {
			mouseStatus(e.getX(), e.getY(), circle, text);
			text.setX(e.getX());
			text.setY(e.getY());
		});

		secondPane.getChildren().add(text);

		Scene sc2 = new Scene(secondPane); // Setting the second scene.
		this.setScene(sc2);
	}

	private void mouseStatus(double x, double y, Circle circle, Text text) {
		/*
		 * An equation for comparing the mouse's coordinates to the circle's radius, and
		 * setting the right text for it.
		 */
		if (Math.sqrt(Math.pow(x - circle.getCenterX(), 2) + Math.pow(y - circle.getCenterY(), 2)) <= 50) {
			text.setText("Mouse point is in the circle ");
		} else {
			text.setText("Mouse point is not in the circle ");
		}

	}

	private void changePos(Circle circle) {
		if (circle.getCenterY() == 100) { // Checking the Y coordinates of the circle.
			circle.setCenterY(300); // Setting the Y coordinates of the circle to 300.
		} else {
			circle.setCenterY(100); // Setting the Y coordinates of the circle to 100.
		}
	}

	private void setStroke(ComboBox<String> strokeColors, Circle circle) {
		// Setting the wanted color of the circle's fill.
		circle.setStroke(Color.valueOf(strokeColors.getValue()));

	}

	private void setFill(ComboBox<String> fillColors, Circle circle) {
		// Setting the wanted color of the circle's stroke.
		circle.setFill(Color.valueOf(fillColors.getValue()));

	}

}
