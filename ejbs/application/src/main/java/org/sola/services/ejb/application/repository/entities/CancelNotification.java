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
@Table(name = "cancel_notification", schema = "application")
public class CancelNotification extends AbstractReadOnlyEntity {

    public static final String QUERY_PARAM_NAME = "baunitName";
    public static final String QUERY_PARAM_SERVICE = "serviceId";
    public static final String QUERY_PARAM_PARTY = "partyId";
    public static final String QUERY_PARAM_TARGET_PARTY = "targetPartyId";
    public static final String QUERY_WHERE_SEARCHBY_SERVICE = " compare_strings(#{" + QUERY_PARAM_SERVICE + "}, cancel_service_id)";
    public static final String QUERY_WHERE_SEARCHBY_PARTY = " (compare_strings(#{" + QUERY_PARAM_PARTY + "}, party_id)"
            + " or compare_strings(#{" + QUERY_PARAM_TARGET_PARTY + "}, target_party_id) )"
            + " and compare_strings(#{" + QUERY_PARAM_SERVICE + "}, cancel_service_id)";
    
    
    @Column(name = "partyName")
    private String partyName;
    @Column(name = "partyLastName")
    private String partyLastName;
    @Column(name = "targetpartyName")
    private String targetpartyName;
    @Column(name = "targetpartyLastName")
    private String targetpartyLastName;
    @Column(name = "party_id")
    private String partyId;
    @Column(name = "target_party_id")
    private String targetPartyId;
    @Column(name = "baunit_name")
    private String baunitName;
    @Column(name = "service_id")
    private String serviceId;

    public String getBaunitName() {
        return baunitName;
    }

    public void setBaunitName(String baunitName) {
        this.baunitName = baunitName;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public String getPartyLastName() {
        return partyLastName;
    }

    public void setPartyLastName(String partyLastName) {
        this.partyLastName = partyLastName;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getTargetPartyId() {
        return targetPartyId;
    }

    public void setTargetPartyId(String targetPartyId) {
        this.targetPartyId = targetPartyId;
    }

    public String getTargetpartyLastName() {
        return targetpartyLastName;
    }

    public void setTargetpartyLastName(String targetpartyLastName) {
        this.targetpartyLastName = targetpartyLastName;
    }

    public String getTargetpartyName() {
        return targetpartyName;
    }

    public void setTargetpartyName(String targetpartyName) {
        this.targetpartyName = targetpartyName;
    }
}
