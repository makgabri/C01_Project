package org.jth.templates;

import org.jth.databaseHelper.DatabaseDriver;
import org.jth.exceptions.ConnectionFailedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;

public class ExecutionTest {

    @BeforeEach
    public void setUp() throws SQLException, ConnectionFailedException {
      //TemplateDriver.clear(DatabaseDriver.connectOrCreateDatabase(), "EMPLOYMENT_RELATED_SERVICES");
    }
    
    @Test
    @DisplayName("Testing at first to see that no errors occur")
    public void testEmployee() {
      try {
        Execution exe = new Execution();
        exe.execute("/home/gabrian/University/CSCC01/Team1/app/src/test/java/org/jth/templates/New_iCARE_Template_Comb_with_Examples.xlsx");
      } catch (Exception e) {
        fail("Should not throw any exception");
      }
    }
}
