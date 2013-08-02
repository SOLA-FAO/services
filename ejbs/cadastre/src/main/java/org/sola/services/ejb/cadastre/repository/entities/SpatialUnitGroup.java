/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.ejb.cadastre.repository.entities;

import java.io.Serializable;
import java.util.HashMap;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import org.sola.services.common.repository.AccessFunctions;
import org.sola.services.common.repository.RepositoryUtility;
import org.sola.services.common.repository.entities.AbstractVersionedEntity;
import org.sola.services.ejb.system.br.Result;
import org.sola.services.ejb.system.businesslogic.SystemEJBLocal;

/**
 *
 * @author Elton Manoku
 */
@Table(name = "spatial_unit_group", schema = "cadastre")
public class SpatialUnitGroup extends AbstractVersionedEntity {

    public static String WHERE_CONDITION = "hierarchy_level = #{hierarchy_level} "
            + " and ST_Intersects(st_transform(geom, #{srid}), ST_SetSRID(ST_GeomFromWKB(#{filtering_geometry}), #{srid}))";
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "hierarchy_level")
    private Integer hierarchyLevel;
    @Column(name = "label")
    private String label;
    @Column(name = "name")
    private String name;
    @Column(name = "geom")
    @AccessFunctions(onSelect = "st_asewkb(geom)",
    onChange = "get_geometry_with_srid(#{geom})")
    private byte[] geom;

    public SpatialUnitGroup() {
        super();
    }

    public String getId() {
        id = id == null ? generateId() : id;
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getGeom() {
        return geom;
    }

    public void setGeom(byte[] geom) {
        this.geom = geom.clone();
    }

    public Integer getHierarchyLevel() {
        return hierarchyLevel;
    }

    public void setHierarchyLevel(Integer hierarchyLevel) {
        this.hierarchyLevel = hierarchyLevel;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void preSave() {
        setName(generateName());
        super.preSave();
    }

    private String generateName() {
        String result = "";
        SystemEJBLocal systemEJB = RepositoryUtility.tryGetEJB(SystemEJBLocal.class);
        if (systemEJB != null) {
            HashMap<String, Serializable> parameters = new HashMap<String, Serializable>();
            parameters.put("geom_v", getGeom());
            parameters.put("hierarchy_level_v", getHierarchyLevel());
            parameters.put("label_v", getLabel());
            Result newNumberResult = systemEJB.checkRuleGetResultSingle(
                    "generate-spatial-unit-group-name", parameters);
            if (newNumberResult != null && newNumberResult.getValue() != null) {
                result = newNumberResult.getValue().toString();
            }
        }
        return result;
    }
}
