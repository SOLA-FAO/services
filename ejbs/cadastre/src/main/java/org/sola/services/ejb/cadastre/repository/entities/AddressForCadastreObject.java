package org.sola.services.ejb.cadastre.repository.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import org.sola.services.common.repository.entities.AbstractVersionedEntity;

/**
 * Entity representing the cadastre.spatial_unit_address association table.
 */
@Table(name = "spatial_unit_address", schema = "cadastre")
public class AddressForCadastreObject extends AbstractVersionedEntity {
    @Id
    @Column(name = "spatial_unit_id")
    private String cadastreObjectId;
    
    @Id
    @Column(name = "address_id")
    private String addressId;
    
    public AddressForCadastreObject(){
        super();
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getCadastreObjectId() {
        return cadastreObjectId;
    }

    public void setCadastreObjectId(String cadastreObjectId) {
        this.cadastreObjectId = cadastreObjectId;
    }
}
