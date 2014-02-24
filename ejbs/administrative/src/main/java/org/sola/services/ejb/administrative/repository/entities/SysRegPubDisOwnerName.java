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
package org.sola.services.ejb.administrative.repository.entities;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import org.sola.services.common.repository.AccessFunctions;
import org.sola.services.common.repository.Localized;
import org.sola.services.common.repository.entities.AbstractReadOnlyEntity;

/**
 * Entity representing the administrative sys_reg_owner_name view.
 *
 *
 * @author soladev
 */
@Table(name = "sys_reg_owner_name", schema = "administrative")
public class SysRegPubDisOwnerName extends AbstractReadOnlyEntity {

    public static final String QUERY_PARAMETER_ID = "id";
    // Where clause
    public static final String QUERY_WHERE_BYID = "id = #{" + QUERY_PARAMETER_ID + "}";
    public static final String QUERY_WHERE_SEARCHBYPARTS = "name_lastpart = #{search_string}";
    
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "value")
    private String value;
    @Column(name = "name_firstpart")
    private String nameFirstpart;
    @Column(name = "name_lastpart")
    private String nameLastpart;
    @Column(name = "size")
    private BigDecimal size;
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
    @Column(insertable = false, updatable = false, name = "public_notification_duration")
    @AccessFunctions(onSelect = "system.get_setting('public-notification-duration')")
    private String publicNotificationDuration;
    @Column(insertable = false, updatable = false, name = "objections")
    @AccessFunctions(onSelect = "administrative.get_objections(name_lastpart)")
    private String objections;

    public String getPublicNotificationDuration() {
        return publicNotificationDuration;
    }

    public void setPublicNotificationDuration(String publicNotificationDuration) {
        this.publicNotificationDuration = publicNotificationDuration;
    }

    public SysRegPubDisOwnerName() {
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

    public String getObjections() {
        return objections;
    }

    public void setObjections(String objections) {
        this.objections = objections;
    }

    public BigDecimal getSize() {
        return size;
    }

    public void setSize(BigDecimal size) {
        this.size = size;
    }
}
