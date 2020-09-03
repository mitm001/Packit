package jpack;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.commons.lang3.SystemUtils;

public class ClickMe extends Application {

    private Path parent;
    private Path path;
    Button btn;
    String arg = null;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        configurePreferences();
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
        
        Label lbl = new Label(path.toString());
        Label lbl2 = new Label(parent.toString());
        VBox paneTop = new VBox(lbl, lbl2);

        BorderPane pane = new BorderPane();
        pane.setCenter(btn);
        
        pane.setTop(paneTop);
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
    
    /**
     * Method to set the registry settings for testing.
     */
    private void configurePreferences() {
        String appDir = null;
        String startCfg = null;
        String appLauncher = null;
        String launcherDir = null;
        
        if (SystemUtils.IS_OS_WINDOWS) {
            appDir = System.getenv("LOCALAPPDATA") + "/packit/app";
            startCfg = "packit.cfg"; 
        } else if (SystemUtils.IS_OS_LINUX) {
            appDir = System.getProperty("user.home") + "/packit/lib/app/";
            startCfg = "packit";
        } else if (SystemUtils.IS_OS_MAC) {
            appDir = System.getProperty("user.home") + "/packit.app/Contents/app/";
            startCfg = "packit";
        }
        
        path = Paths.get(appDir + startCfg);
        parent = Paths.get(appDir);
                
    }
}
