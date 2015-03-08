package org.sola.services.ejb.system.repository.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import org.sola.services.common.repository.ChildEntityList;
import org.sola.services.common.repository.DefaultSorter;
import org.sola.services.common.repository.entities.AbstractEntity;

@Table(name = "query", schema = "system")
@DefaultSorter(sortString = "name")
public class Query extends AbstractEntity {
    @Id
    @Column
    private String name;
    
    @Column
    private String sql;
    
    @Column
    private String description;
    
    @ChildEntityList(parentIdField = "queryName")
    private List<QueryField> fields;
    
    public Query(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<QueryField> getFields() {
        return fields;
    }

    public void setFields(List<QueryField> fields) {
        this.fields = fields;
    }
}
