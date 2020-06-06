
/*Name: Roee Aviran
ID: 316492644 */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class HW3_RoeeAviran extends Application {

	MouseAndCircle mouseAndCircle = new MouseAndCircle();

	@Override
	public void start(Stage primaryStage) throws Exception {

		// Stage 1.
		FlowPane firstPane = new FlowPane(); // Making the first pane.
		Button startButton = new Button("Mouse Point On/Off"); // Making the on/off button.
		firstPane.getChildren().add(startButton);
		firstPane.setAlignment(Pos.BASELINE_CENTER); // Aligning the pane.
		// Setting an action after the user clicks on the button.
		startButton.setOnAction(e -> HandleMouseCircle());
		firstPane.setVgap(3);
		firstPane.setHgap(3);

		primaryStage.setTitle("Control Window"); // Title of the first window.
		mouseAndCircle.setTitle("Mouse And Circle"); // Title of the second window.

		Scene sc1 = new Scene(firstPane, 300, 50); // Setting the first scene.
		primaryStage.setOnCloseRequest(e -> { // Handling a request to close the first stage.
			primaryStage.close();
			mouseAndCircle.close(); // Closing both the first and second stages.
		});

		primaryStage.setScene(sc1);
		primaryStage.show();
	}

	private void HandleMouseCircle() {
		// Stage 2
		if (mouseAndCircle.isShowing()) { // If the second stage is already active, close it.
			mouseAndCircle.close();
		} else {
			mouseAndCircle.show(); // If the second stage isn't active, activate it.
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
