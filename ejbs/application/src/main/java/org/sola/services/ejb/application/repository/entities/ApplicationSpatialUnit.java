package org.sola.services.ejb.application.repository.entities;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import org.sola.services.common.repository.entities.AbstractVersionedEntity;

/**
 * Entity representing the application.spatial_unit_id association table (i.e many to 
 * many table) between application and cadastre.spatial_unit. 
 */
@Table(schema = "application", name = "application_spatial_unit")
public class ApplicationSpatialUnit extends AbstractVersionedEntity {
    @Id
    @Column(name = "application_id")
    private String applicationId;
    
    @Id
    @Column(name = "spatial_unit_id")
    private String spatialUnitId;

    public ApplicationSpatialUnit() {
        super();
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }


    public String getSpatialUnitId() {
        return spatialUnitId;
    }

    public void setSpatialUnitId(String spatialUnitId) {
        this.spatialUnitId = spatialUnitId;
    }
}
