package packit;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ClickMe extends Application {
    
    Button btn;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        btn = new Button();
        btn.setText("Click me please!");
        btn.setOnAction(e -> buttonClick());
        
        BorderPane pane = new BorderPane();
        pane.setCenter(btn);
        
        Scene scene = new Scene(pane, 300, 250);
        
        stage.setScene(scene);
        stage.setTitle("The clickme app");
        stage.show();
    }

    private void buttonClick() {
        if ("Click me please!".equals(btn.getText())) {
            btn.setText("You clicked me!");
        } else {
            btn.setText("Click me please!");
        }
    }
}
