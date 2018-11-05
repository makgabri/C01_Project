package org.jth.templates;

import java.util.Map;
import org.jth.templates.fieldoptions.*;

public class FieldChecker {

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
}
