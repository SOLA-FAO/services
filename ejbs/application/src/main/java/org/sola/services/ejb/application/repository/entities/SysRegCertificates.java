/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.ejb.application.repository.entities;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import org.sola.services.common.repository.AccessFunctions;
import org.sola.services.common.repository.Localized;
import org.sola.services.common.repository.entities.AbstractReadOnlyEntity;

/**
 * Entity representing the application systematic_registration_certificates
 * view.
 *
 * @author soladev
 */
@Table(name = "systematic_registration_certificates", schema = "application")
public class SysRegCertificates extends AbstractReadOnlyEntity {

    public static final String QUERY_PARAMETER_NR = "nr";
    // Where clause
    public static final String QUERY_WHERE_BYNR = "nr = #{" + QUERY_PARAMETER_NR  + "} "
            + "AND compare_strings(#{search_string}, name_lastpart)";

    /**
     * WHERE clause to return current CO's based on search string compared to
     * last part
     */
    public static final String QUERY_WHERE_SEARCHBYPARTS = "compare_strings(#{search_string}, name_lastpart)";
//    @Id
//    @Column(name = "id")
//    private String id;
    @Column(name = "nr")
    private String nr;
//    @Column(name = "application_status")
//    private String application_status;
    @Column(name = "name_firstpart")
    private String nameFirstpart;
    @Column(name = "name_lastpart")
    private String nameLastpart;
//    @Column(name = "size")
//    private BigDecimal size;
//    @Localized
//    @Column(name = "land_use_code")
//    private String landUsecode;
    @Column(name = "ba_unit_id")
    private String baUnitId;
//    @Column(insertable = false, updatable = false, name = "concatenated_name")
//    @AccessFunctions(onSelect = "administrative.get_parcel_ownernames(ba_unit_id)")
//    private String concatenatedName;
//    @Column(name = "name")
//    private String name;
//    @Localized
//    @Column(name = "type_code")
//    private String typeCode;
//    @Column(name = "creation_date")
//    private Date creationDate;

//    public Date getCreationDate() {
//        return creationDate;
//    }
//
//    public void setCreationDate(Date creationDate) {
//        this.creationDate = creationDate;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getTypeCode() {
//        return typeCode;
//    }
//
//    public void setTypeCode(String typeCode) {
//        this.typeCode = typeCode;
//    }
//    
//    
//    
//    public String getApplication_status() {
//        return application_status;
//    }
//
//    public void setApplication_status(String application_status) {
//        this.application_status = application_status;
//    }

    public String getBaUnitId() {
        return baUnitId;
    }

    public void setBaUnitId(String baUnitId) {
        this.baUnitId = baUnitId;
    }

//    public String getConcatenatedName() {
//        return concatenatedName;
//    }
//
//    public void setConcatenatedName(String concatenatedName) {
//        this.concatenatedName = concatenatedName;
//    }

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

//    public String getLandUsecode() {
//        return landUsecode;
//    }
//
//    public void setLandUsecode(String landUsecode) {
//        this.landUsecode = landUsecode;
//    }
//
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

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

//    public BigDecimal getSize() {
//        return size;
//    }
//
//    public void setSize(BigDecimal size) {
//        this.size = size;
//    }
}
