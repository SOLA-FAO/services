package org.sola.services.ejbs.refdata.entities;

import javax.persistence.Table;
import org.sola.services.common.repository.DefaultSorter;
import org.sola.services.common.repository.entities.AbstractCodeEntity;

@Table(name = "field_constraint_type", schema = "opentenure")
@DefaultSorter(sortString="display_value")
public class FieldConstraintType extends AbstractCodeEntity {
    public static final String TYPE_DATETIME = "DATETIME";
    public static final String TYPE_DOUBLE_RANGE = "DOUBLE_RANGE";
    public static final String TYPE_INTEGER = "INTEGER";
    public static final String TYPE_INTEGER_RANGE = "INTEGER_RANGE";
    public static final String TYPE_LENGTH = "LENGTH";
    public static final String TYPE_NOT_NULL = "NOT_NULL";
    public static final String TYPE_OPTION = "OPTION";
    public static final String TYPE_REGEXP = "REGEXP";

    public FieldConstraintType() {
    }
}
