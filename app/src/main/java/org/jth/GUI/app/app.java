package org.jth.GUI.app;
import org.jth.GUI.Windows.StarterWindow;

public class app {
    public static void main(String[] args) {
        startApplication();
    }

    public static void startApplication() {
        Tracker tracker = Tracker.getInstance();
        StarterWindow starterWindow = new StarterWindow();
        tracker.addWindow("start", starterWindow);
        while (tracker.getStatus()) {
          break;
        }
    }
}
