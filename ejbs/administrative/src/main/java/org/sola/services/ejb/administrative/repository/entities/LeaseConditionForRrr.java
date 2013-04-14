package org.sola.services.ejb.administrative.repository.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import org.sola.services.common.repository.entities.AbstractVersionedEntity;

@Table(name = "lease_condition_for_rrr", schema = "administrative")
public class LeaseConditionForRrr extends AbstractVersionedEntity {
    
    @Id
    @Column
    private String id;
    
    @Column(name="rrr_id")
    private String rrrId;
    
    @Column(name="lease_condition_code")
    private String leaseConditionCode;
    
    @Column(name="custom_condition_text")
    private String customConditionText;
  
    public LeaseConditionForRrr(){
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

    public String getLeaseConditionCode() {
        return leaseConditionCode;
    }

    public void setLeaseConditionCode(String leaseConditionCode) {
        this.leaseConditionCode = leaseConditionCode;
    }

    public String getRrrId() {
        return rrrId;
    }

    public void setRrrId(String rrrId) {
        this.rrrId = rrrId;
    }
}
