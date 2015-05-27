/**
 * ******************************************************************************************
 * Copyright (C) 2014 - Food and Agriculture Organization of the United Nations (FAO).
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
package org.sola.services.ejb.search.repository.entities;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.sola.services.common.repository.AccessFunctions;
import org.sola.services.common.repository.CommonSqlProvider;
import org.sola.services.common.repository.entities.AbstractReadOnlyEntity;

@Entity
@Table(name = "party", schema = "party")
public class PartyPropertySearchResult extends AbstractReadOnlyEntity {

    public static final String QUERY_PARAM_NAME = "name";
     public static final String QUERY_PARAM_PARTY_ID = "partyId";
    public static final String QUERY_PARAM_TYPE_CODE = "typeCode";
    public static final String QUERY_PARAM_ROLE_TYPE_CODE = "roleTypeCode";
    public static final String SEARCH_QUERY =
            "SELECT distinct pp.id, "
            + "pp.name, "
            + "pp.last_name, "
            + "bu.name_firstpart||'/'||bu.name_lastpart   as properties, "
            + "bu.name_firstpart   as nameFirstPart, "
            + "bu.name_lastpart   as nameLastPart, "
            + "bu.id as propertyId, "
            + "(select count ('x') from application.notifiable_party_for_baunit "
            + "WHERE ( baunit_name = bu.name_firstpart||'/'||bu.name_lastpart)"
            + " AND target_party_id = pp.id and compare_strings(#{" + QUERY_PARAM_PARTY_ID + "}, party_id))>0 as selProperties, "
            + "(select count ('x') from application.notifiable_party_for_baunit "
            + "WHERE (target_party_id = pp.id  and compare_strings(#{" + QUERY_PARAM_PARTY_ID + "}, party_id))) as total, "
            + "pp.classification_code, "
            + "pp.redact_code "
            + "FROM party.party pp, "
            + "administrative.ba_unit bu, "
            + "administrative.party_for_rrr  pr, "
            + "administrative.rrr rrr "
            + "where pp.id=pr.party_id "
            + "and   pr.rrr_id=rrr.id "
            + "and   rrr.ba_unit_id= bu.id "
            + "and compare_strings(#{" + QUERY_PARAM_NAME + "}, COALESCE(pp.name, '') || ' ' "
            + "|| COALESCE(pp.last_name, '') || ' ' || COALESCE(pp.alias, ''))  "
            + "ORDER BY pp.name, pp.last_name, total desc";

    
    @Id
    @Column
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "nameFirstPart")
    private String nameFirstPart;
    @Column(name = "nameLastPart")
    private String nameLastPart;
    @Column(name = "propertyId")
    private String propertyId;
    @Column(name = "properties")
    private String properties;
    @Column(name = "selProperties")
    private boolean selProperties;
    @Column(name = "total")
    private Long total;
    @Column(name = AbstractReadOnlyEntity.CLASSIFICATION_CODE_COLUMN_NAME)
    private String classificationCode;
    @Column(name = AbstractReadOnlyEntity.REDACT_CODE_COLUMN_NAME)
    private String redactCode;
   
    
    public PartyPropertySearchResult() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSelProperties() {
        return selProperties;
    }

    public void setSelProperties(boolean selProperties) {
        this.selProperties = selProperties;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
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
    
}
