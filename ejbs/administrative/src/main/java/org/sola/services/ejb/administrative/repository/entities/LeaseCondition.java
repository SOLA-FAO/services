package org.sola.services.ejb.administrative.repository.entities;

import javax.persistence.Table;
import org.sola.services.common.repository.DefaultSorter;
import org.sola.services.common.repository.entities.AbstractCodeEntity;

@Table(name = "lease_condition", schema = "administrative")
@DefaultSorter(sortString="display_value")
public class LeaseCondition extends AbstractCodeEntity {
    public LeaseCondition(){
        super();
    }
}
