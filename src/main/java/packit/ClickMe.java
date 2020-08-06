package packit;

import com.sun.javafx.util.Utils;
import java.util.List;
import java.util.prefs.Preferences;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ClickMe extends Application {

    private String env;
    
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
        
        Label lbl = new Label(env);
        
        BorderPane pane = new BorderPane();
        pane.setCenter(btn);
        pane.setTop(lbl);
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
        
        if (Utils.isWindows()) {
            env = System.getenv("LOCALAPPDATA");
//            env = System.getProperty("user.home");
        } else if (Utils.isUnix()) {

        } else if (Utils.isMac()) {
            
        }
        
        //Distribution registry
//        Preferences pref = Preferences.userRoot().node("polygeddon").node("dist");
//        pref = null;
//        pref = Preferences.userRoot().node("polygeddon").node("dist").node("updater");
//        pref.remove("startscript");
//        pref.put("startscript", "C:/Users/Robert/Documents/PolyGameClient/Updater/bin/updater_startup.bat");
//        pref.remove("parent");
//        pref.put("parent", "C:/Users/Robert/Documents/PolyGameClient");
//        pref = null;
//        pref = Preferences.userRoot().node("polygeddon").node("dist").node("client");
//        pref.remove("startscript");
//        pref.put("startscript", "C:/Users/Robert/Documents/PolyGameClient/bin/client_startup.bat");
        
    }
}
