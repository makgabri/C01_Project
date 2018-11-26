package org.jth.cscc01.app;

import org.jth.GUI.Windows.StarterWindow;
import org.jth.GUI.app.Tracker;

public class App {
    public static void main(String[] args) {
        startApplication();
    }
    public static void startApplication() {
        Tracker tracker = Tracker.getInstance();
        StarterWindow starterWindow = new StarterWindow();
        tracker.addWindow("start", starterWindow);
    }
}
