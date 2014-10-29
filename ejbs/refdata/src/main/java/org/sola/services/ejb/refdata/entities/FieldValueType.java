package org.sola.services.ejb.refdata.entities;

import javax.persistence.Table;
import org.sola.services.common.repository.DefaultSorter;
import org.sola.services.common.repository.entities.AbstractCodeEntity;

@Table(name = "field_value_type", schema = "opentenure")
@DefaultSorter(sortString="display_value")
public class FieldValueType extends AbstractCodeEntity {
    public static final String TYPE_BOOL = "BOOL";
    public static final String TYPE_TEXT = "TEXT";
    public static final String TYPE_NUMBER = "NUMBER";
    
    public FieldValueType(){
    }
}
