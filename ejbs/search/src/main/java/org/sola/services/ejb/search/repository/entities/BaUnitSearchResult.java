/**
 * ******************************************************************************************
 * Copyright (C) 2014 - Food and Agriculture Organization of the United Nations
 * (FAO). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,this
 * list of conditions and the following disclaimer. 2. Redistributions in binary
 * form must reproduce the above copyright notice,this list of conditions and
 * the following disclaimer in the documentation and/or other materials provided
 * with the distribution. 3. Neither the name of FAO nor the names of its
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT,STRICT LIABILITY,OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING
 * IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * *********************************************************************************************
 */
package org.sola.services.ejb.search.repository.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import org.sola.services.common.repository.entities.AbstractReadOnlyEntity;

public class BaUnitSearchResult extends AbstractReadOnlyEntity {

    public static final String QUERY_PARAM_NAME_FIRSTPART = "nameFirstPart";
    public static final String QUERY_PARAM_NAME_LASTPART = "nameLastPart";
    public static final String QUERY_PARAM_OWNER_NAME = "ownerName";
    public static final String QUERY_PARAM_INTEREST_REF = "interestRef";
    public static final String QUERY_PARAM_PARCEL_NAME_FIRSTPART = "parcelNameFirstpart";
    public static final String QUERY_PARAM_PARCEL_NAME_LASTPART = "parcelNameLastpart";
    public static final String QUERY_PARAM_PARCEL_LAND_USE = "parcelLandUse";
    public static final String QUERY_PARAM_LOCALITY = "locality";
    public static final String QUERY_PARAM_DOCUMENT_REF = "documentRef";
    public static final String QUERY_PARAM_PROPERTY_MANAGER = "propManager";
    public static final String QUERY_PARAM_DESCRIPTION = "description";
    public static final String QUERY_ORDER_BY = "prop.name_firstpart, prop.name_lastpart";

    @Id
    @Column
    private String id;
    @Column
    private String name;
    @Column(name = "name_firstpart")
    private String nameFirstPart;
    @Column(name = "name_lastpart")
    private String nameLastPart;
    @Column(name = "status_code")
    private String statusCode;
    @Column
    private String rightholders;
    @Column
    private String description;
    @Column(name = "land_use_code")
    private String landUseCode;
    @Column
    private String parcels;
    @Column
    private String locality;
    @Column(name = "prop_man")
    private String propertyManager;
    @Column(name = "type_code")
    private String typeCode;
    @Column(name = "active_jobs")
    private String activeJobs;
    @Column(name = "action_status")
    private String actionStatusCode;
    @Column(name = "notation_text")
    private String notationText;

    public BaUnitSearchResult() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameFirstPart() {
        return nameFirstPart;
    }

    public void setNameFirstPart(String nameFirstPart) {
        this.nameFirstPart = nameFirstPart;
    }

    public String getNameLastPart() {
        return nameLastPart;
    }

    public void setNameLastPart(String nameLastPart) {
        this.nameLastPart = nameLastPart;
    }

    public String getRightholders() {
        return rightholders;
    }

    public void setRightholders(String rightholders) {
        this.rightholders = rightholders;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLandUseCode() {
        return landUseCode;
    }

    public void setLandUseCode(String landUseCode) {
        this.landUseCode = landUseCode;
    }

    public String getParcels() {
        return parcels;
    }

    public void setParcels(String parcels) {
        this.parcels = parcels;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getPropertyManager() {
        return propertyManager;
    }

    public void setPropertyManager(String propertyManager) {
        this.propertyManager = propertyManager;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getActiveJobs() {
        return activeJobs;
    }

    public void setActiveJobs(String activeJobs) {
        this.activeJobs = activeJobs;
    }

    public String getActionStatusCode() {
        return actionStatusCode;
    }

    public void setActionStatusCode(String actionStatusCode) {
        this.actionStatusCode = actionStatusCode;
    }

    public String getNotationText() {
        return notationText;
    }

    public void setNotationText(String notationText) {
        this.notationText = notationText;
    }
}
