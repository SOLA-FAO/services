package org.sola.services.ejb.system.repository.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import org.sola.services.common.repository.DefaultSorter;
import org.sola.services.common.repository.Localized;
import org.sola.services.common.repository.entities.AbstractEntity;

@Table(name = "query_field", schema = "system")
@DefaultSorter(sortString = "index_in_query")
public class QueryField extends AbstractEntity {
    @Id
    @Column(name="query_name")
    private String queryName;
    
    @Id
    @Column(name="index_in_query")
    private int indexInQuery;
    
    @Column
    private String name;
    
    @Column(name="display_value")
    @Localized
    private String displayValue;
    
    public QueryField(){
        
    }

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public int getIndexInQuery() {
        return indexInQuery;
    }

    public void setIndexInQuery(int indexInQuery) {
        this.indexInQuery = indexInQuery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }
}
