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
@Table(name = "notifiable_party_for_baunit", schema = "administrative")
public class NotifiablePartySearchResult extends AbstractReadOnlyEntity {

    public static final String QUERY_PARAM_NAME = "name";
    public static final String QUERY_PARAM_SERVICE = "service";
    public static final String SEARCH_QUERY =
            " SELECT distinct pp.id ,"
            + " pp.name, "
            + " pp.last_name,"
            + " tpp.id targetpartyId, "
            + " tpp.name targetpartyName, "
            + " tpp.last_name targetpartyLastname, "
            + " npbu.baunit_name as properties, "
            + " npbu.party_id, "
            + " npbu.target_party_id,  "
            + "(select count ('x') from administrative.notifiable_party_for_baunit "
            + " WHERE ( baunit_name = npbu.baunit_name"
            + " AND party_id = npbu.party_id"
            + " AND target_party_id = npbu.target_party_id"
            + " AND npbu.cancel_service_id = #{" + QUERY_PARAM_SERVICE + "} )"
//            + " AND status='x')"
            + " )>0 as selProperties,"
            + " gpp.id groupPartyId,   "
            + " gpp.name groupPartyName,   "
            + " gpp.last_name groupPartyLastName "
            + " FROM party.party pp,"
            + " party.party tpp, "
            + " administrative.notifiable_party_for_baunit npbu,"
            + " party.party gpp,"
            + " party.group_party gp  "
            + " where (pp.id=npbu.party_id "
            + " and tpp.id=npbu.target_party_id)"
            + " and (compare_strings(#{" + QUERY_PARAM_NAME + "}, COALESCE(pp.name, '') || ' ' || COALESCE(pp.last_name, '') || ' ' || COALESCE(pp.alias, '')) "
            + " or compare_strings(#{" + QUERY_PARAM_NAME + "}, COALESCE(tpp.name, '') || ' ' || COALESCE(tpp.last_name, '') || ' ' || COALESCE(tpp.alias, '')) )"
            + " and npbu.service_id in (select from_service_id from transaction.transaction where status_code = 'approved')"
            + " and  (gpp.id=gp.id)"
            + " and (pp.id in (select pm.party_id from party.party_member pm where pm.group_id = gp.id))"
            + " and (tpp.id in (select pm.party_id from party.party_member pm where pm.group_id = gp.id))"
            + " and (npbu.cancel_service_id = #{" + QUERY_PARAM_SERVICE + "} or npbu.cancel_service_id is null)"
            + " ORDER BY pp.name, pp.last_name";

    
    @Id
    @Column
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "targetPartyName")
    private String targetPartyName;
    @Column(name = "targetPartyLastName")
    private String targetPartyLastName;
    @Column(name = "targetPartyId")
    private String targetPartyId;
    @Column(name = "properties")
    private String properties;
    @Column(name = "selProperties")
    private boolean selProperties;
    @Column(name = "groupPartyName")
    private String groupPartyName;
    @Column(name = "groupPartyLastName")
    private String groupPartyLastName;
    @Column(name = "groupPartyId")
    private String groupPartyId;
    
   
    
    public NotifiablePartySearchResult() {
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


    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getTargetPartyId() {
        return targetPartyId;
    }

    public void setTargetPartyId(String targetPartyId) {
        this.targetPartyId = targetPartyId;
    }

    public String getTargetPartyLastName() {
        return targetPartyLastName;
    }

    public void setTargetPartyLastName(String targetPartyLastName) {
        this.targetPartyLastName = targetPartyLastName;
    }

    public String getTargetPartyName() {
        return targetPartyName;
    }

    public void setTargetPartyName(String targetPartyName) {
        this.targetPartyName = targetPartyName;
    }

    public String getGroupPartyId() {
        return groupPartyId;
    }

    public void setGroupPartyId(String groupPartyId) {
        this.groupPartyId = groupPartyId;
    }

    public String getGroupPartyLastName() {
        return groupPartyLastName;
    }

    public void setGroupPartyLastName(String groupPartyLastName) {
        this.groupPartyLastName = groupPartyLastName;
    }

    public String getGroupPartyName() {
        return groupPartyName;
    }

    public void setGroupPartyName(String groupPartyName) {
        this.groupPartyName = groupPartyName;
    }
}
