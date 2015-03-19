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
package org.sola.services.ejb.application.repository.entities;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import org.sola.services.common.repository.ChildEntityList;
import org.sola.services.common.repository.ExternalEJB;
import org.sola.services.common.repository.entities.AbstractReadOnlyEntity;
import org.sola.services.common.repository.entities.AbstractVersionedEntity;
import org.sola.services.ejb.administrative.businesslogic.AdministrativeEJBLocal;
import org.sola.services.ejb.administrative.repository.entities.BaUnitBasic;
import org.sola.services.ejb.party.businesslogic.PartyEJBLocal;
import org.sola.services.ejb.party.repository.entities.Party;
import org.sola.services.ejb.source.businesslogic.SourceEJBLocal;
import org.sola.services.ejb.source.repository.entities.Source;

/**
 * Entity representing the application.objection table.
 *
 * @author soladev
 */
@Table(name = "objection", schema = "application")
public class Objection extends AbstractVersionedEntity {

    public static final String QUERY_PARAMETER_SERVICE_ID = "serviceId";
    public static final String QUERY_WHERE_BYSERVICEID = "service_id = #{" + QUERY_PARAMETER_SERVICE_ID + "} ";
    @Id
    @Column
    private String id;
    @Column(name = "service_id")
    private String serviceId;
    @Column
    private String nr;
    @Column(name = "status_code")
    private String statusCode;
    @Column(name = "lodged_date")
    private Date lodgedDate;
    @Column(name = "resolution_date")
    private Date resolutionDate;
    @Column
    private String description;
    @Column
    private String resolution;
    @Column(name = "authority_code")
    private String authorityCode;
    @Column(name = AbstractReadOnlyEntity.CLASSIFICATION_CODE_COLUMN_NAME)
    private String classificationCode;
    @Column(name = AbstractReadOnlyEntity.REDACT_CODE_COLUMN_NAME)
    private String redactCode;
    @ChildEntityList(parentIdField = "objectionId")
    private List<ObjectionComment> commentList;
    @ExternalEJB(ejbLocalClass = SourceEJBLocal.class,
            loadMethod = "getSources", saveMethod = "saveSource")
    @ChildEntityList(parentIdField = "objectionId", childIdField = "sourceId",
            manyToManyClass = ObjectionUsesSource.class)
    private List<Source> sourceList;
    @ExternalEJB(ejbLocalClass = PartyEJBLocal.class, loadMethod = "getParties")
    @ChildEntityList(parentIdField = "objectionId", childIdField = "partyId",
            manyToManyClass = ObjectionParty.class, readOnly = true)
    private List<Party> partyList;
    @ExternalEJB(ejbLocalClass = AdministrativeEJBLocal.class, loadMethod = "getSummaryBaUnits")
    @ChildEntityList(parentIdField = "objectionId", childIdField = "baUnitId",
            manyToManyClass = ObjectionProperty.class, readOnly = true)
    private List<BaUnitBasic> propertyList;

    public Objection() {
        super();
    }

    public String getId() {
        return id == null ? generateId() : id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Date getLodgedDate() {
        return lodgedDate;
    }

    public void setLodgedDate(Date lodgedDate) {
        this.lodgedDate = lodgedDate;
    }

    public Date getResolutionDate() {
        return resolutionDate;
    }

    public void setResolutionDate(Date resolutionDate) {
        this.resolutionDate = resolutionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getAuthorityCode() {
        return authorityCode;
    }

    public void setAuthorityCode(String authorityCode) {
        this.authorityCode = authorityCode;
    }

    @Override
    public String getClassificationCode() {
        return classificationCode;
    }

    @Override
    public String getRedactCode() {
        return redactCode;
    }

    public void setClassificationCode(String classificationCode) {
        this.classificationCode = classificationCode;
    }

    public void setRedactCode(String redactCode) {
        this.redactCode = redactCode;
    }

    public List<ObjectionComment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<ObjectionComment> commentList) {
        this.commentList = commentList;
    }

    public List<BaUnitBasic> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<BaUnitBasic> propertyList) {
        this.propertyList = propertyList;
    }

    public List<Source> getSourceList() {
        return sourceList;
    }

    public void setSourceList(List<Source> sourceList) {
        this.sourceList = sourceList;
    }

    public List<Party> getPartyList() {
        return partyList;
    }

    public void setPartyList(List<Party> partyList) {
        this.partyList = partyList;
    }
}
