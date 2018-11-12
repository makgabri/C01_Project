package org.jth.templates;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExecutionTest {

    @Test
    @DisplayName("Testing at first to see that no errors occur")
    public void testEmployee() {
      try {
        Execution exe = new Execution();
        exe.execute("/Users/User/Documents/GitHub/Team1/app/src/test/java/org/jth/templates/New_iCARE_Template_Comb_with_Examples.xlsx");
      } catch (Exception e) {
        fail("Should not throw any exception");
      }
    }
}
