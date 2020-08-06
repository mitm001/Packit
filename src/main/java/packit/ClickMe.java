package packit;

import com.sun.javafx.util.Utils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClickMe extends Application {

    private String canonicalPath;

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
        Label lbl3 = new Label(canonicalPath);
        VBox paneTop = new VBox(lbl, lbl2, lbl3);

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
        
        if (Utils.isWindows()) {
            env = System.getenv("LOCALAPPDATA");
            startScript = "/jpackage/jpackage.exe"; 
        } else if (Utils.isUnix()) {
            env = System.getProperty("user.home");
            startScript = "/jpackage/bin/jpackage";
        } else if (Utils.isMac()) {
            env = System.getProperty("user.home");
            startScript = "/jpackage.app/Contents/MacOS/jpackage";
        }
        
        path = Paths.get(env + startScript);
        parent = Paths.get(env + "/jpackage");
        try {
            canonicalPath = new File(".").getCanonicalPath();
        } catch (IOException ex) {
            Logger.getLogger(ClickMe.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
