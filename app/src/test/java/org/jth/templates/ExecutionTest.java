package org.jth.templates;

import org.jth.databaseHelper.DatabaseDriver;
import org.jth.exceptions.ConnectionFailedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.sql.SQLException;

public class ExecutionTest {

    @BeforeEach
    public void setUp() throws SQLException, ConnectionFailedException {
      TemplateDriver.clear(DatabaseDriver.connectOrCreateDatabase());
    }
    
    @Test
    @DisplayName("Testing at first to see that no errors occur")
    public void testEmployee() {
      try {
        String path = new File("app/src/test/java/org/jth/templates/Template.xlsx").getAbsolutePath();
        Execution exe = new Execution();
        exe.execute(path);
      } catch (Exception e) {
        fail("Should not throw any exception");
      }
    }
}
