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
package org.sola.services.ejb.application.repository;

import org.apache.ibatis.jdbc.SqlBuilder.*;
import static org.apache.ibatis.jdbc.SqlBuilder.*;

/**
 * Utility class that uses the MyBatis SQLBuilder API to create custom SQL
 * statements for use by the AppicationEJB.
 *
 * @author soladev
 */
public class ApplicationSqlProvider {

    public static final String PARAM_SERVICE_ID = "serviceId";
    public static final String PARAM_USERNAME = "userName";

    /**
     * Creates an Insert statement for the application_spatial_unit table based
     * on the cadastre_objects created by the application.
     */
    public static String buildInsertAppSpatialUnitSql() {
        BEGIN();
        SELECT("co.id");
        SELECT("ser.application_id");
        SELECT("#{" + PARAM_USERNAME + "}");
        FROM("application.service ser");
        FROM("transaction.transaction t");
        FROM("cadastre.cadastre_object co");
        WHERE("ser.id = #{" + PARAM_SERVICE_ID + "}");
        WHERE("t.from_service_id = ser.id");
        WHERE("co.transaction_id = t.id");
        WHERE("NOT EXISTS (SELECT asu.spatial_unit_id "
                + " FROM  application.application_spatial_unit asu "
                + " WHERE asu.spatial_unit_id = co.id "
                + " AND   asu.application_id = ser.application_id ) ");

        String sql = SQL();
        sql = "INSERT INTO application.application_spatial_unit"
                + " (spatial_unit_id, application_id, change_user) " + sql;
        return sql;
    }
}
