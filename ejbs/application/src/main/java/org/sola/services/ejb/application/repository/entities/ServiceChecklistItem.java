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
package org.sola.services.ejb.application.repository.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import org.sola.services.common.repository.AccessFunctions;
import static org.sola.services.common.repository.CommonSqlProvider.PARAM_LANGUAGE_CODE;
import org.sola.services.common.repository.DefaultSorter;
import org.sola.services.common.repository.entities.AbstractVersionedEntity;

/**
 *
 * @author soladev
 */
@Table(name = "service_checklist_item", schema = "application")
@DefaultSorter(sortString = "display_order, name")
public class ServiceChecklistItem extends AbstractVersionedEntity {

    public static final String QUERY_PARAMETER_SERVICE_ID = "serviceId";
    public static final String QUERY_WHERE_BYSERVICEID = "service_id = #{" + QUERY_PARAMETER_SERVICE_ID + "} ";
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "service_id")
    private String serviceId;
    @Column(name = "checklist_item_code")
    private String itemCode;
    @Column(name = "result")
    private String result;
    @Column(name = "comment")
    private String comment;
    @AccessFunctions(onSelect = "COALESCE((SELECT get_translation(ci.display_value, #{"
            + PARAM_LANGUAGE_CODE + "})"
            + " FROM  application.checklist_item ci"
            + " WHERE checklist_item_code IS NOT NULL "
            + " AND   ci.code = checklist_item_code), name)", onChange = "#{itemDisplayValue}")
    @Column(name = "name")
    private String itemDisplayValue;
    @AccessFunctions(onSelect = "COALESCE((SELECT get_translation(ci.description, #{"
            + PARAM_LANGUAGE_CODE + "})"
            + " FROM  application.checklist_item ci "
            + " WHERE checklist_item_code IS NOT NULL "
            + " AND   ci.code = checklist_item_code), description)", onChange = "#{itemDescription}")
    @Column(name = "description")
    private String itemDescription;
    @AccessFunctions(onSelect = "COALESCE((SELECT ci.display_order "
            + " FROM application.checklist_item ci "
            + " WHERE checklist_item_code IS NOT NULL"
            + " AND   ci.code = checklist_item_code), 10000)")
    @Column(name = "display_order", insertable = false, updatable = false)
    private int itemDisplayOrder;

    public ServiceChecklistItem() {
        super();
    }

    public String getId() {
        id = id == null ? generateId() : id;
        return id;
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

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getItemDisplayValue() {
        return itemDisplayValue;
    }

    public void setItemDisplayValue(String itemDisplayValue) {
        this.itemDisplayValue = itemDisplayValue;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getItemDisplayOrder() {
        return itemDisplayOrder;
    }

    public void setItemDisplayOrder(int itemDisplayOrder) {
        this.itemDisplayOrder = itemDisplayOrder;
    }
}
