package org.jth.templates;

import java.util.Map;
import org.jth.templates.fieldoptions.*;

//Any field formatting operations will be in this class
public class FieldFormatter {

  //TODO: Complete method to check each field is correct
  public String fieldCheck(Map<String, Object> itemmap) {
    String result = "Result:" + System.lineSeparator();
    for (String key : itemmap.keySet()) {
      // Handle Unique Identifier
      boolean valid = false;
      if (key == "UNIQUE_IDENTIFIER") {
        for (UniqueIdentifiers item : UniqueIdentifiers.values()) {
          if (item.name().equals(itemmap.get("UNIQUE_IDENTIFIER"))) {
            valid = true;
          }
        }
        if (!valid) {
          result += "Invalid unique identifier" + System.lineSeparator();
        }
      }
      // Handle Unique Identifier Value
      if (key == "UNIQUEIV") {
        if (itemmap.get(key) instanceof Integer) {
          valid = true;
        } else {
          result += "Invalid unique identifier value";
        }
      }
    }
    if (result.equals("Result:")) {
      result += "There are no issues in the map";
    }
    return result;
  }
  
  //TODO: Turn map into an array corresponding to the template type
}
