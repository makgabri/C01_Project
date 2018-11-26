package org.jth.GUI.app;

import java.util.HashMap;

public class Tracker {

    private HashMap<String, Object> windowHolder;
    
    // Declare instance, only one instance should exist at a time
    private static Tracker instance = null;
    
    // Constructor
    private Tracker() {
        this.windowHolder = new HashMap<String, Object>();
    }
    
    /**
     * Gets the instance
     * @return - instance of tracker
     */
    public static Tracker getInstance() {
        if (instance == null) {
            instance = new Tracker();
        }
        return instance;
    }
    
    /**
     * Adds the window object to array holder
     * @param window - window object to be added to holder
     */
    public void addWindow(String name, Object window) {
        windowHolder.put(name, window);
    }
    
    public Object getWindow(String name) {
        return windowHolder.get(name);
    }
}
