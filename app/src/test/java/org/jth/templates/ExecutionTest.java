package org.jth.templates;

import org.jth.databaseHelper.DatabaseDriver;
import org.jth.exceptions.ConnectionFailedException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public class ExecutionTest {
    Connection conn = DatabaseDriver.connectOrCreateDatabase();

    @AfterEach
    public void setUp() throws SQLException, ConnectionFailedException {
      //TemplateDriver.clear(conn);
      conn.close();
    }
    
    @Test
    @DisplayName("Testing at first to see that no errors occur")
    public void testEmployee() {
      try {
        String path = new File("app/src/test/java/org/jth/templates/Template.xlsx").getAbsolutePath();
        Execution exe = new Execution();
        exe.execute(conn, path);
      } catch (Exception e) {
        fail("Should not throw any exception");
      }
    }
}
