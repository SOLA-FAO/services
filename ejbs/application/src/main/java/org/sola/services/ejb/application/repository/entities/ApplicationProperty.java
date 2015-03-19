/**
 * ******************************************************************************************
 * Copyright (C) 2015 - Food and Agriculture Organization of the United Nations (FAO).
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 *    1. Redistributions of source code must retain the above copyright notice,this list
 *       of conditions and the following disclaimer.
 *    2. Redistributions in binary form must reproduce the above copyright notice,this list
 *       of conditions and the following disclaimer in the documentation and/or other
 *       materials provided with the distribution.
 *    3. Neither the name of FAO nor the names of its contributors may be used to endorse or
 *       promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT
 * SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,STRICT LIABILITY,OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * *********************************************************************************************
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.ejb.application.repository.entities;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import org.sola.services.common.repository.AccessFunctions;
import org.sola.services.common.repository.entities.AbstractVersionedEntity;

/**
 * Entity representing the application.application_property table.
 *
 * @author soladev
 */
@Table(name = "application_property", schema = "application")
public class ApplicationProperty extends AbstractVersionedEntity {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "application_id")
    private String applicationId;
    @Column(name = "name_firstpart")
    private String nameFirstpart;
    @Column(name = "name_lastpart")
    private String nameLastpart;
    @Column(name = "area")
    private BigDecimal area;
    @Column(name = "total_value")
    private BigDecimal totalValue;
    @Column(name = "verified_exists")
    private boolean verifiedExists;
    @Column(name = "verified_location")
    private boolean verifiedLocation;
    @Column(name = "ba_unit_id")
    private String baUnitId;
    @Column(insertable = false, updatable = false, name = "state_land_status_code")
    @AccessFunctions(onSelect = "administrative.get_state_land_status(ba_unit_id)")
    private String stateLandStatusCode;
    @Column(insertable = false, updatable = false, name = "land_use_code")
    @AccessFunctions(onSelect = "administrative.get_land_use_code(ba_unit_id)")
    private String landUseCode;
    @Column(insertable = false, updatable = false, name = "prop_man")
    @AccessFunctions(onSelect = "(SELECT (COALESCE(pm.name, '') || ' ' || COALESCE(pm.last_name, ''))"
                + " FROM administrative.ba_unit_as_party bap, party.party pm"
                + " WHERE bap.ba_unit_id = application.application_property.ba_unit_id"
                + " AND   pm.id = bap.party_id LIMIT 1)")
    private String propertyManager;
    @Column(insertable = false, updatable = false, name = "locality")
    @AccessFunctions(onSelect = "(SELECT string_agg(COALESCE(addr2.description, ''), '::::') "
                + "FROM administrative.ba_unit_contains_spatial_unit bas2, cadastre.spatial_unit_address sua2, "
                + " address.address addr2 "
                + "WHERE bas2.ba_unit_id = application.application_property.ba_unit_id "
                + "AND sua2.spatial_unit_id = bas2.spatial_unit_id "
                + "AND addr2.id = sua2.address_id )")
    private String locality;

    public ApplicationProperty() {
        super();
    }

    public String getId() {
        id = id == null ? generateId() : id;
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public String getBaUnitId() {
        return baUnitId;
    }

    public void setBaUnitId(String baUnitId) {
        this.baUnitId = baUnitId;
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

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public boolean isVerifiedExists() {
        return verifiedExists;
    }

    public void setVerifiedExists(boolean verifiedExists) {
        this.verifiedExists = verifiedExists;
    }

    public boolean isVerifiedLocation() {
        return verifiedLocation;
    }

    public void setVerifiedLocation(boolean verifiedLocation) {
        this.verifiedLocation = verifiedLocation;
    }

    public String getStateLandStatusCode() {
        return stateLandStatusCode;
    }

    public void setStateLandStatusCode(String stateLandStatusCode) {
        this.stateLandStatusCode = stateLandStatusCode;
    }

    public String getLandUseCode() {
        return landUseCode;
    }

    public void setLandUseCode(String landUseCode) {
        this.landUseCode = landUseCode;
    }

    public String getPropertyManager() {
        return propertyManager;
    }

    public void setPropertyManager(String propertyManager) {
        this.propertyManager = propertyManager;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }
}
