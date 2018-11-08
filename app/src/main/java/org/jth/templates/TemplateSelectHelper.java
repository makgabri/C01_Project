package org.jth.templates;

public interface TemplateSelectHelper {

    public Object getValueFromField(Integer uniqueiv, String field);
    public Boolean updateValue(Integer uniqueiv, String field, Object value);
}
