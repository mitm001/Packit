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
        String env = null;
        String startScript = null;
        
        if (SystemUtils.IS_OS_WINDOWS) {
            env = System.getenv("LOCALAPPDATA") + "/jpackage/";
            startScript = "jpackage.exe"; 
        } else if (SystemUtils.IS_OS_LINUX) {
            env = System.getProperty("user.home") + "/jpackage/bin/";
            startScript = "jpackage";
        } else if (SystemUtils.IS_OS_MAC) {
            env = System.getProperty("user.home") + "/jpackage.app/Contents/MacOS/";
            startScript = "jpackage";
        }
        
        path = Paths.get(env + startScript);
        parent = Paths.get(env);
        
        //Distribution registry
//        Preferences pref = Preferences.userRoot().node("clent").node("dist");
//        pref = null;
//        pref = Preferences.userRoot().node("polygeddon").node("dist").node("updater");
//        pref.remove("startscript");
//        pref.put("startscript", "C:/Users/Robert/Documents/Client/Updater/bin/updater_startup.bat");
//        pref.remove("parent");
//        pref.put("parent", "C:/Users/Robert/Documents/Client");
//        pref = null;
//        pref = Preferences.userRoot().node("polygeddon").node("dist").node("client");
//        pref.remove("startscript");
//        pref.put("startscript", "C:/Users/Robert/Documents/Client/bin/client_startup.bat");
        
    }
}
