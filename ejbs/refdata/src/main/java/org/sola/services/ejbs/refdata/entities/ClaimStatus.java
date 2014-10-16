package org.sola.services.ejbs.refdata.entities;

import javax.persistence.Table;
import org.sola.services.common.repository.DefaultSorter;
import org.sola.services.common.repository.entities.AbstractCodeEntity;

@Table(name = "claim_status", schema = "opentenure")
@DefaultSorter(sortString="display_value")
public class ClaimStatus extends AbstractCodeEntity {
    public ClaimStatus(){
        super();
    }
}
