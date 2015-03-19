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

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import org.sola.services.common.repository.ChildEntity;
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
 * Entity representing the application.notify table.
 *
 * @author soladev
 */
@Table(name = "notify", schema = "application")
public class Notify extends AbstractVersionedEntity {

    public static final String QUERY_PARAMETER_SERVICE_ID = "serviceId";
    public static final String QUERY_WHERE_BYSERVICEID = "service_id = #{" + QUERY_PARAMETER_SERVICE_ID + "} ";
    @Id
    @Column
    private String id;
    @Column(name = "service_id")
    private String serviceId;
    @Column(name = "party_id")
    private String partyId;
    @Column(name = "relationship_type_code")
    private String relationshipTypeCode;
    @Column
    private String description;
    @Column(name = AbstractReadOnlyEntity.CLASSIFICATION_CODE_COLUMN_NAME)
    private String classificationCode;
    @Column(name = AbstractReadOnlyEntity.REDACT_CODE_COLUMN_NAME)
    private String redactCode;
    @ExternalEJB(ejbLocalClass = SourceEJBLocal.class,
            loadMethod = "getSources", saveMethod = "saveSource")
    @ChildEntityList(parentIdField = "notifyId", childIdField = "sourceId",
            manyToManyClass = NotifyUsesSource.class)
    private List<Source> sourceList;
    @ExternalEJB(ejbLocalClass = PartyEJBLocal.class,
            loadMethod = "getParty", saveMethod = "saveParty")
    @ChildEntity(childIdField = "partyId")
    private Party party;
    @ExternalEJB(ejbLocalClass = AdministrativeEJBLocal.class, loadMethod = "getSummaryBaUnits")
    @ChildEntityList(parentIdField = "notifyId", childIdField = "baUnitId",
            manyToManyClass = NotifyProperty.class, readOnly = true)
    private List<BaUnitBasic> propertyList;

    public Notify() {
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

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public String getRelationshipTypeCode() {
        return relationshipTypeCode;
    }

    public void setRelationshipTypeCode(String relationshipTypeCode) {
        this.relationshipTypeCode = relationshipTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<Source> getSourceList() {
        return sourceList;
    }

    public void setSourceList(List<Source> sourceList) {
        this.sourceList = sourceList;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public List<BaUnitBasic> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<BaUnitBasic> propertyList) {
        this.propertyList = propertyList;
    }
}
