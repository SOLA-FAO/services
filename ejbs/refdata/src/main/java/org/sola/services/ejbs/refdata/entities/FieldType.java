package org.sola.services.ejbs.refdata.entities;

import javax.persistence.Table;
import org.sola.services.common.repository.DefaultSorter;
import org.sola.services.common.repository.entities.AbstractCodeEntity;

@Table(name = "field_type", schema = "opentenure")
@DefaultSorter(sortString="display_value")
public class FieldType extends AbstractCodeEntity {
    public static final String TYPE_BOOL = "BOOL";
    public static final String TYPE_TEXT = "TEXT";
    public static final String TYPE_INTEGER = "INTEGER";
    public static final String TYPE_DECIMAL = "DECIMAL";
    public static final String TYPE_DATE = "DATE";
    
    public FieldType(){
    }
}
