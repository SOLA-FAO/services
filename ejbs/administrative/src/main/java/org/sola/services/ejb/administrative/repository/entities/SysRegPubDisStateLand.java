/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.ejb.administrative.repository.entities;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import org.sola.services.common.repository.AccessFunctions;
import org.sola.services.common.repository.Localized;
import org.sola.services.common.repository.entities.AbstractReadOnlyEntity;

/**
 * Entity representing the administrative sys_reg_state_land view. 
 *
 * @author soladev
 */
@Table(name = "sys_reg_state_land", schema = "administrative")
public class SysRegPubDisStateLand extends AbstractReadOnlyEntity {

    public static final String QUERY_PARAMETER_ID = "id";
    // Where clause
    public static final String QUERY_WHERE_BYID = "id = #{" + QUERY_PARAMETER_ID + "}";
    /**
     * WHERE clause to return current CO's based on search string compared to
     * last part
     */
    public static final String QUERY_WHERE_SEARCHBYPARTS = "compare_strings(#{search_string}, name_lastpart)";
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "value")
    private String value;
    @Column(name = "name_firstpart")
    private String nameFirstpart;
    @Column(name = "name_lastpart")
    private String nameLastpart;
    @Localized
    @Column(name = "land_use_code")
    private String landUsecode;
    @Column(name = "ba_unit_id")
    private String baUnitId;
    @Column(name = "residential")
    private BigDecimal residential;
    @Column(name = "commercial")
    private BigDecimal commercial;
    @Column(name = "agricultural")
    private BigDecimal agricultural;
    @Column(name = "industrial")
    private BigDecimal industrial;

    public SysRegPubDisStateLand() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getAgricultural() {
        return agricultural;
    }

    public void setAgricultural(BigDecimal agricultural) {
        this.agricultural = agricultural;
    }

    public BigDecimal getCommercial() {
        return commercial;
    }

    public void setCommercial(BigDecimal commercial) {
        this.commercial = commercial;
    }

    public BigDecimal getIndustrial() {
        return industrial;
    }

    public void setIndustrial(BigDecimal industrial) {
        this.industrial = industrial;
    }

    public BigDecimal getResidential() {
        return residential;
    }

    public void setResidential(BigDecimal residential) {
        this.residential = residential;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLandUsecode() {
        return landUsecode;
    }

    public void setLandUsecode(String landUsecode) {
        this.landUsecode = landUsecode;
    }

    public String getNameFirstpart() {
        return nameFirstpart;
    }

    public void setNameFirstpart(String nameFirstpart) {
        this.nameFirstpart = nameFirstpart;
    }

    public String getNameLastpart() {
        return nameLastpart;
    }

    public void setNameLastpart(String nameLastpart) {
        this.nameLastpart = nameLastpart;
    }

    public String getBaUnitId() {
        return baUnitId;
    }

    public void setBaUnitId(String baUnitId) {
        this.baUnitId = baUnitId;
    }

}
