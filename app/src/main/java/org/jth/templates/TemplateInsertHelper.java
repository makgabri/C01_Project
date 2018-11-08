package org.jth.templates;

import java.sql.SQLException;
import java.util.Map;

public interface TemplateInsertHelper {

    public boolean insertTemplateItems(Integer uniqueidentifiervalue,
        Map<String, String> itemmap) throws Exception, SQLException;
    
}
