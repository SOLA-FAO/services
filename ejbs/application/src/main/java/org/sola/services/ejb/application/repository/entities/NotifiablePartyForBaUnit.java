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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.ejb.application.repository.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import org.sola.services.common.repository.AccessFunctions;
import org.sola.services.common.repository.CommonSqlProvider;
import org.sola.services.common.repository.entities.AbstractVersionedEntity;

/**
 * Entity representing the administrative.party_for_rrr association table.
 *
 * @author soladev
 */
@Table(name = "notifiable_party_for_baunit", schema = "application")
public class NotifiablePartyForBaUnit extends AbstractVersionedEntity {

    public static final String QUERY_PARAM_NAME = "name";
    public static final String QUERY_PARAM_PARTY = "partyId";
    public static final String QUERY_PARAM_TARGET_PARTY = "targetPartyId";
    public static final String QUERY_PARAM_APPLICATION = "application";
    public static final String QUERY_PARAM_SERVICE = "service";
    public static final String QUERY_ORDER_BY = " party_id ";
    public static final String QUERY_WHERE_ALL = " "
            + "party_id = #{" + QUERY_PARAM_PARTY + "}"
            + " and target_party_id = #{" + QUERY_PARAM_TARGET_PARTY + "}"
            + " and baunit_name = #{" + QUERY_PARAM_NAME + "}"
            + " and "
            + " application_id = #{" + QUERY_PARAM_APPLICATION + "}"
            + " and "
            + " service_id = #{" + QUERY_PARAM_SERVICE + "}";
    public static final String QUERY_WHERE_APPLICATION_SERVICE = " "
            + " application_id = #{" + QUERY_PARAM_APPLICATION + "}"
            + " and "
            + " service_id = #{" + QUERY_PARAM_SERVICE + "}";
    public static final String QUERY_WHERE_APPLICATION_PROPERTY = " "
            + " baunit_name = #{" + QUERY_PARAM_NAME + "}"
            + " and "
            + " target_party_id in (select pp.id"
            + " from administrative.ba_unit bu,"
            + " party.party pp,"
            + " administrative.party_for_rrr  pr,"
            + " administrative.rrr rrr"
            + " where pp.id=pr.party_id"
            + " and   pr.rrr_id=rrr.id"
            + " and   rrr.ba_unit_id= bu.id"
            + "	and bu.name_firstpart||'/'||bu.name_lastpart = #{" + QUERY_PARAM_NAME + "}) "
            + " and status != 'x'"
            + " and service_id in (select from_service_id from transaction.transaction where status_code = 'approved')";
    public static final String QUERY_WHERE_APPLICATION = " "
            + " application_id = #{" + QUERY_PARAM_APPLICATION + "}";
    public static final String QUERY_WHERE_PARTY_PROPERTY = " "
            + "party_id = #{" + QUERY_PARAM_PARTY + "}"
            + " and target_party_id = #{" + QUERY_PARAM_TARGET_PARTY + "}"
            + " and baunit_name = #{" + QUERY_PARAM_NAME + "}";
    @Id
    @Column(name = "party_id")
    private String partyId;
    @Id
    @Column(name = "target_party_id")
    private String targetPartyId;
    @Id
    @Column(name = "baunit_name")
    private String baunitName;
    @Column(name = "status")
    private String statusParty;
    @AccessFunctions(onSelect = " (SELECT id "
    + "  FROM administrative.ba_unit bu "
    + "  WHERE bu.name = baunit_name) ")
    @Column(name = "baunit_id", insertable = false, updatable = false)
    private String baunitId;
    @Column(name = "application_id")
    private String applicationId;
    @Column(name = "service_id")
    private String serviceId;
    @Column(name = "cancel_service_id")
    private String cancelServiceId;
    @Column(name = "notifyId")
    private String notifyId;
    @Column(name = "notifyTargetId")
    private String notifyTargetId;

    public NotifiablePartyForBaUnit() {
        super();
    }

    public String getBaunitId() {
        return baunitId;
    }

    public void setBaunitId(String baunitId) {
        this.baunitId = baunitId;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public String getStatusParty() {
        return statusParty;
    }

    public void setStatusParty(String statusParty) {
        this.statusParty = statusParty;
    }

    public String getTargetPartyId() {
        return targetPartyId;
    }

    public void setTargetPartyId(String targetPartyId) {
        this.targetPartyId = targetPartyId;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getBaunitName() {
        return baunitName;
    }

    public void setBaunitName(String baunitName) {
        this.baunitName = baunitName;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getCancelServiceId() {
        return cancelServiceId;
    }

    public void setCancelServiceId(String cancelServiceId) {
        this.cancelServiceId = cancelServiceId;
    }

    public String getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

    public String getNotifyTargetId() {
        return notifyTargetId;
    }

    public void setNotifyTargetId(String notifyTargetId) {
        this.notifyTargetId = notifyTargetId;
    }
}
