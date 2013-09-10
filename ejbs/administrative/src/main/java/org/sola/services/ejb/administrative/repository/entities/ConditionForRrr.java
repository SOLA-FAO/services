package org.sola.services.ejb.administrative.repository.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import org.sola.services.common.repository.entities.AbstractVersionedEntity;

@Table(name = "condition_for_rrr", schema = "administrative")
public class ConditionForRrr extends AbstractVersionedEntity {
    
    @Id
    @Column
    private String id;
    
    @Column(name="rrr_id")
    private String rrrId;
    
    @Column(name="condition_code")
    private String conditionCode;
    
    @Column(name="custom_condition_text")
    private String customConditionText;
  
    @Column(name="condition_quantity")
    private int conditionQuantity;
    
    @Column(name="condition_unit")
    private String conditionUnit;
    
    public ConditionForRrr(){
        super();
    }

    public String getCustomConditionText() {
        return customConditionText;
    }

    public void setCustomConditionText(String customConditionText) {
        this.customConditionText = customConditionText;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConditionCode() {
        return conditionCode;
    }

    public void setConditionCode(String conditionCode) {
        this.conditionCode = conditionCode;
    }

    public int getConditionQuantity() {
        return conditionQuantity;
    }

    public void setConditionQuantity(int conditionQuantity) {
        this.conditionQuantity = conditionQuantity;
    }

    public String getConditionUnit() {
        return conditionUnit;
    }

    public void setConditionUnit(String conditionUnit) {
        this.conditionUnit = conditionUnit;
    }

    public String getRrrId() {
        return rrrId;
    }

    public void setRrrId(String rrrId) {
        this.rrrId = rrrId;
    }
}
