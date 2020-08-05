package packit;

import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ClickMe extends Application {
    
    Button btn;
    static String arg = null;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        Parameters parameters = getParameters();
        List<String> unnamed = parameters.getUnnamed();
        
        for(String s: unnamed) {
            if ("client".equals(s)) {
                arg = unnamed.get(unnamed.indexOf(s) + 1);
            }
        }
        
        btn = new Button();
        btn.setText(arg == null ? "Click me please!": arg);
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
            btn.setText(arg == null ? "Click me please!" : arg);
        }
    }
}
