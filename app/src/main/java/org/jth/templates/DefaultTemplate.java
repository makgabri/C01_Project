package org.jth.templates;

import java.util.ArrayList;
import java.util.HashMap;

public class DefaultTemplate {

    private HashMap<String,String> map;
    private Boolean containsMandatoryFields;

    public DefaultTemplate () {
        this.map = new HashMap<String, String>();
        this.containsMandatoryFields = false;
    }

    /**
     * Inserts list of items into map
     * @param fieldType list of templates that need upload.
     * @param data list of data corresponding to fieldtype.
     * @return void
     */
     public void insertItems(ArrayList<String> fieldType, ArrayList<String> data) {
         for (int i = 0; i < fieldType.size(); i++) {
             this.map.put(fieldType.get(i), data.get(i));
         }
     }

     /**
     * Validates and checks if hashmap contains all mandatory fields
     * @return void
     */
     public void validate() {
         for (MandatoryFields field : MandatoryFields.values()) {
             if (map.get(field) == null) {
                 return;
             }
         }
         this.containsMandatoryFields = true;
     }

     /**
     * @return whether this object contains all mandatory fields
     */
     public Boolean containsMandatory () {
         return this.containsMandatoryFields;
     }
     
     public HashMap<String, String> getMap() {
       return this.map;
     }
}