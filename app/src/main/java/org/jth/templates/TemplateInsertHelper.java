package org.jth.templates;

import java.util.Map;

public interface TemplateInsertHelper {

    public boolean insertUniqueIV(Integer UniqueIdentifierValue);
    
    public boolean insertTemplateItems(Integer uniqueidentifiervalue,
        Map<String, String> itemmap);
    
}
