/**
 * ******************************************************************************************
 * Copyright (C) 2012 - Food and Agriculture Organization of the United Nations
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
package org.sola.services.ejb.search.repository.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import org.sola.services.common.repository.entities.AbstractReadOnlyEntity;

public class PropertyVerifier extends AbstractReadOnlyEntity {

    public static final String QUERY_PARAM_FIRST_PART = "firstPart";
    public static final String QUERY_PARAM_LAST_PART = "lastPart";
    public static final String QUERY_PARAM_APPLICATION_NUMBER = "applicationNumber";
    public static final String QUERY_VERIFY_SQL =
            "SELECT bcs.ba_unit_id AS id, (COUNT(co.id)>0) AS has_location, "
            + "(SELECT COALESCE(string_agg(nr, ','), '') FROM application.application a "
            + "INNER JOIN application.application_property ap on a.id = ap.application_id "
            + "WHERE a.status_code = 'lodged' and a.nr!=#{" + QUERY_PARAM_APPLICATION_NUMBER + "} "
            + "AND ap.name_firstpart = #{" + QUERY_PARAM_FIRST_PART + "} "
            + "AND ap.name_lastpart = #{" + QUERY_PARAM_LAST_PART + "}) "
            + "AS applications_where_found "
            + "FROM cadastre.cadastre_object co "
            + "LEFT JOIN administrative.ba_unit_contains_spatial_unit bcs "
            + "ON co.id = bcs.spatial_unit_id "
            + "WHERE co.name_firstpart = #{" + QUERY_PARAM_FIRST_PART + "} "
            + "AND co.name_lastpart = #{" + QUERY_PARAM_LAST_PART + "} "
            + "GROUP BY bcs.ba_unit_id";
    @Id
    @Column
    private String id;
    @Column(name = "has_location")
    private boolean hasLocation;
    @Column(name = "applications_where_found")
    private String applicationsWhereFound;

    public PropertyVerifier() {
        super();
    }

    public String getApplicationsWhereFound() {
        return applicationsWhereFound;
    }

    public void setApplicationsWhereFound(String applicationsWhereFound) {
        this.applicationsWhereFound = applicationsWhereFound;
    }

    public boolean isHasLocation() {
        return hasLocation;
    }

    public void setHasLocation(boolean hasLocation) {
        this.hasLocation = hasLocation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
