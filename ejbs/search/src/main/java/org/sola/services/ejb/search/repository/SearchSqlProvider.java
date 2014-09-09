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
package org.sola.services.ejb.search.repository;

import org.apache.ibatis.jdbc.SqlBuilder;
import static org.apache.ibatis.jdbc.SqlBuilder.*;
import org.sola.common.StringUtility;
import org.sola.services.common.repository.CommonSqlProvider;
import org.sola.services.ejb.search.repository.entities.ApplicationSearchParams;
import org.sola.services.ejb.search.repository.entities.ApplicationSearchResult;
import org.sola.services.ejb.search.repository.entities.BaUnitSearchParams;
import org.sola.services.ejb.search.repository.entities.BaUnitSearchResult;
import org.sola.services.ejb.search.repository.entities.UserSearchParams;
import org.sola.services.ejb.search.repository.entities.UserSearchResult;

/**
 *
 * @author soladev
 */
public class SearchSqlProvider {

    public static final String PARAM_APPLICATION_ID = "applicationId";
    public static final String PARAM_SL_PARCEL_ID = "slParcelId";
    private static final String APPLICATION_GROUP = "application";
    private static final String SERVICE_GROUP = "service";
    private static final String RRR_GROUP = "rrr";
    private static final String PROPERTY_GROUP = "Property";
    private static final String SOURCE_GROUP = "Source";
    private static final String AGENT_GROUP = "Agent";
    private static final String CONTACT_PERSON_GROUP = "Contact person";

    private static final String CHANGE_ACTION = "changed";
    private static final String ADDED_PROPERTY = "ADDED PROPERTY: ";
    private static final String DELETED_PROPERTY = "DELETED PROPERTY: ";
    private static final String ADDED_SOURCE = "ADDED DOCUMENT: Ref#";
    private static final String DELETED_SOURCE = "REMOVED DOCUMENT: Ref#";
    private static final String ADDED_AGENT = "ADDED AGENT: ";
    private static final String DELETED_AGENT = "REMOVED AGENT: ";
    private static final String ADDED_CONTACT_PERSON = "ADDED CONTACT PERSON: ";
    private static final String DELETED_CONTACT_PERSON = "REMOVED CONTACT PERSON: ";

    public static String buildApplicationLogSql() {
        String sql;
        int sortClassifier = 1;

        // Application 
        BEGIN();
        SELECT("'" + APPLICATION_GROUP + "' AS record_group");
        SELECT("'" + APPLICATION_GROUP + "' AS record_type");
        SELECT(sortClassifier + " as sort_classifier");
        SELECT("app1.id AS record_id");
        SELECT("app1.rowversion AS record_sequence");
        SELECT("app1.nr AS nr");
        SELECT("CASE WHEN COALESCE(prev.action_code, 'null') = app1.action_code "
                + " THEN '" + CHANGE_ACTION + "' ELSE app1.action_code END AS action_code");
        SELECT("NULL::text AS notation");
        SELECT("app1.change_time");
        SELECT("(SELECT (appuser.first_name::text || ' '::text) || appuser.last_name::text"
                + " FROM system.appuser"
                + " WHERE appuser.username::text = app1.change_user::text)"
                + " AS user_fullname");
        FROM("application.application app1 "
                + " LEFT JOIN application.application_historic prev "
                + " ON app1.id = prev.id AND (app1.rowversion - 1) = prev.rowversion");
        WHERE("app1.id = #{" + PARAM_APPLICATION_ID + "}");

        sql = SQL() + " UNION ";
        sortClassifier++;

        //Application History
        BEGIN();
        SELECT("'" + APPLICATION_GROUP + "' AS record_group");
        SELECT("'" + APPLICATION_GROUP + "' AS record_type");
        SELECT(sortClassifier + " as sort_classifier");
        SELECT("app_hist.id AS record_id");
        SELECT("app_hist.rowversion AS record_sequence");
        SELECT("app_hist.nr AS nr");
        SELECT("CASE WHEN COALESCE(prev.action_code, 'null') = app_hist.action_code "
                + " THEN '" + CHANGE_ACTION + "' ELSE app_hist.action_code END AS action_code");
        SELECT("NULL::text AS notation");
        SELECT("app_hist.change_time");
        SELECT("(SELECT (appuser.first_name::text || ' '::text) || appuser.last_name::text"
                + " FROM system.appuser"
                + " WHERE appuser.username::text = app_hist.change_user::text)"
                + " AS user_fullname");
        FROM("application.application_historic app_hist "
                + " LEFT JOIN application.application_historic prev "
                + " ON app_hist.id = prev.id AND (app_hist.rowversion - 1) = prev.rowversion");
        WHERE("app_hist.id = #{" + PARAM_APPLICATION_ID + "}");

        sql = sql + SQL() + " UNION ";
        sortClassifier++;

        // Service
        BEGIN();
        SELECT("'" + SERVICE_GROUP + "' AS record_group");
        SELECT("ser1.request_type_code AS record_type");
        SELECT(sortClassifier + " as sort_classifier");
        SELECT("ser1.id AS record_id");
        SELECT("ser1.rowversion AS record_sequence");
        SELECT("ser1.service_order::text AS nr");
        SELECT("CASE WHEN COALESCE(prev.action_code, 'null') = ser1.action_code "
                + " THEN '" + CHANGE_ACTION + "' ELSE ser1.action_code END AS action_code");
        SELECT("NULL::text AS notation");
        SELECT("ser1.change_time");
        SELECT("(SELECT (appuser.first_name::text || ' '::text) || appuser.last_name::text"
                + " FROM system.appuser"
                + " WHERE appuser.username::text = ser1.change_user::text)"
                + " AS user_fullname");
        FROM("application.service ser1 "
                + " LEFT JOIN application.service_historic prev "
                + " ON ser1.id = prev.id AND (ser1.rowversion - 1) = prev.rowversion");
        WHERE("ser1.application_id = #{" + PARAM_APPLICATION_ID + "}");

        sql = sql + SQL() + " UNION ";
        sortClassifier++;

        // Service History
        BEGIN();
        SELECT("'" + SERVICE_GROUP + "' AS record_group");
        SELECT("ser_hist.request_type_code AS record_type");
        SELECT(sortClassifier + " as sort_classifier");
        SELECT("ser_hist.id AS record_id");
        SELECT("ser_hist.rowversion AS record_sequence");
        SELECT("ser_hist.service_order::text AS nr");
        SELECT("CASE WHEN COALESCE(prev.action_code, 'null') = ser_hist.action_code "
                + " THEN '" + CHANGE_ACTION + "' ELSE ser_hist.action_code END AS action_code");
        SELECT("NULL::text AS notation");
        SELECT("ser_hist.change_time");
        SELECT("(SELECT (appuser.first_name::text || ' '::text) || appuser.last_name::text"
                + " FROM system.appuser"
                + " WHERE appuser.username::text = ser_hist.change_user::text)"
                + " AS user_fullname");
        FROM("application.service ser");
        FROM("application.service_historic ser_hist"
                + " LEFT JOIN application.service_historic prev "
                + " ON ser_hist.id = prev.id AND (ser_hist.rowversion - 1) = prev.rowversion");
        WHERE("ser.application_id = #{" + PARAM_APPLICATION_ID + "}");
        WHERE("ser_hist.rowidentifier = ser.rowidentifier");

//        sql = sql + SQL() + " UNION ";
//        sortClassifier++;
//        // RRR
//        BEGIN();
//        SELECT("'" + RRR_GROUP + "' AS record_group");
//        SELECT("rrr.type_code AS record_type");
//        SELECT(sortClassifier + " as sort_classifier");
//        SELECT("rrr.id AS record_id");       
//        SELECT("rrr.rowversion AS record_sequence");
//        SELECT("rrr.nr::text AS nr");
//        SELECT("'changesMade'  AS action_code");
//        SELECT("note.notation_text AS notation");
//        SELECT("COALESCE(note.change_time, rrr.change_time) AS change_time");
//        SELECT("(SELECT (appuser.first_name::text || ' '::text) || appuser.last_name::text"
//                + " FROM system.appuser"
//                + " WHERE appuser.username::text = COALESCE(note.change_user, rrr.change_user))"
//                + " AS user_fullname");
//        FROM("application.service ser");
//        FROM("transaction.transaction tran");
//        FROM("administrative.rrr LEFT JOIN administrative.notation note ON "
//                + " rrr.id = note.rrr_id");
//        WHERE("ser.application_id = #{" + PARAM_APPLICATION_ID + "}");
//        WHERE("tran.from_service_id = ser.id");
//        WHERE("rrr.transaction_id = tran.id");
        sql = sql + SQL() + " UNION ";
        sortClassifier++;

        // Property
        BEGIN();
        SELECT("'" + PROPERTY_GROUP + "' AS record_group");
        SELECT("'" + PROPERTY_GROUP + "' AS record_type");
        SELECT(sortClassifier + " as sort_classifier");
        SELECT("prop1.id AS record_id");
        SELECT("prop1.rowversion AS record_sequence");
        SELECT("''::text AS nr");
        SELECT("replace(prop1.change_action,'i','" + ADDED_PROPERTY + "')||prop1.name_firstpart||'/'||prop1.name_lastpart AS action_code");
        SELECT("NULL::text AS notation");
        SELECT("prop1.change_time");
        SELECT("(SELECT (appuser.first_name::text || ' '::text) || appuser.last_name::text"
                + " FROM system.appuser"
                + " WHERE appuser.username::text = prop1.change_user::text)"
                + " AS user_fullname");
        FROM("application.application_property prop1 ");
        WHERE("prop1.application_id = #{" + PARAM_APPLICATION_ID + "}");

        sql = sql + SQL() + " UNION ";
        sortClassifier++;

        // Application property History
        BEGIN();
        SELECT("'" + PROPERTY_GROUP + "' AS record_group");
        SELECT("'" + PROPERTY_GROUP + "' AS record_type");
        SELECT(sortClassifier + " as sort_classifier");
        SELECT("prop_hist.id AS record_id");
        SELECT("prop_hist.rowversion AS record_sequence");
        SELECT("''::text AS nr");
        SELECT("CASE WHEN prop_hist.change_action = 'i' then replace(prop_hist.change_action,'i','" + ADDED_PROPERTY + "')||' - '|| prop_hist.name_firstpart|| '/'||prop_hist.name_lastpart"
                + "  WHEN prop_hist.change_action = 'd' then replace(prop_hist.change_action,'d','" + DELETED_PROPERTY + "')||' - '|| prop_hist.name_firstpart|| '/'||prop_hist.name_lastpart"
                + "  END AS action_code");
        SELECT("NULL::text AS notation");
        SELECT("prop_hist.change_time");
        SELECT("(SELECT (appuser.first_name::text || ' '::text) || appuser.last_name::text"
                + " FROM system.appuser"
                + " WHERE appuser.username::text = prop_hist.change_user::text)"
                + " AS user_fullname");
        FROM("application.application_property_historic prop_hist");
        WHERE("prop_hist.application_id = #{" + PARAM_APPLICATION_ID + "}");

        sql = sql + SQL() + " UNION ";
        sortClassifier++;

        // SOURCE
        BEGIN();
        SELECT("'" + SOURCE_GROUP + "' AS record_group");
        SELECT("'" + SOURCE_GROUP + "' AS record_type");
        SELECT(sortClassifier + " as sort_classifier");
        SELECT("source1.source_id AS record_id");
        SELECT("source1.rowversion AS record_sequence");
        SELECT("''::text AS nr");
        SELECT("replace(source1.change_action,'i','" + ADDED_SOURCE + "')||coalesce(source.reference_nr,'')||' - '||coalesce(source.type_code,'')   AS action_code");
        SELECT("NULL::text AS notation");
        SELECT("source1.change_time");
        SELECT("(SELECT (appuser.first_name::text || ' '::text) || appuser.last_name::text"
                + " FROM system.appuser"
                + " WHERE appuser.username::text = source1.change_user::text)"
                + " AS user_fullname");
        FROM("application.application_uses_source source1  "
                + " LEFT JOIN source.source source "
                + " ON source1.source_id = source.id ");
        WHERE("source1.application_id = #{" + PARAM_APPLICATION_ID + "}");

        sql = sql + SQL() + " UNION ";
        sortClassifier++;

        // Application Source History
        BEGIN();
        SELECT("'" + SOURCE_GROUP + "' AS record_group");
        SELECT("'" + SOURCE_GROUP + "' AS record_type");
        SELECT(sortClassifier + " as sort_classifier");
        SELECT("source1.source_id AS record_id");
        SELECT("source1.rowversion AS record_sequence");
        SELECT("''::text AS nr");
        SELECT("CASE WHEN source1.change_action = 'i' then replace(source1.change_action,'i','" + ADDED_SOURCE + "')||coalesce(source.reference_nr,'')||' - '||coalesce(source.type_code,'') "
                + "  WHEN source1.change_action = 'd' then replace(source1.change_action,'d','" + DELETED_SOURCE + "')||coalesce(source.reference_nr,'')||' - '||coalesce(source.type_code,'') "
                + "  END AS action_code");
        SELECT("NULL::text AS notation");
        SELECT("source1.change_time");
        SELECT("(SELECT (appuser.first_name::text || ' '::text) || appuser.last_name::text"
                + " FROM system.appuser"
                + " WHERE appuser.username::text = source1.change_user::text)"
                + " AS user_fullname");
        FROM("application.application_uses_source_historic source1  "
                + " LEFT JOIN source.source source "
                + " ON source1.source_id = source.id ");
        WHERE("source1.application_id = #{" + PARAM_APPLICATION_ID + "}");

        sql = sql + SQL() + " UNION ";
        sortClassifier++;

        // AGENT 
        BEGIN();

        SELECT("'" + AGENT_GROUP + "' AS record_group");
        SELECT("'" + AGENT_GROUP + "' AS record_type");
        SELECT(sortClassifier + " as sort_classifier");
        SELECT("app.agent_id AS record_id");
        SELECT("app.rowversion AS record_sequence");
        SELECT("''::text AS nr");
        SELECT("replace(party.change_action,'i','" + ADDED_AGENT + "')||' - '||party.name||' '||coalesce(party.last_name,'') AS action_code");
        SELECT("NULL::text AS notation");
        SELECT("app.change_time");
        SELECT("(SELECT (appuser.first_name::text || ' '::text) || appuser.last_name::text"
                + " FROM system.appuser"
                + " WHERE appuser.username::text = app.change_user::text)"
                + " AS user_fullname");
        FROM("application.application app");
        FROM("party.party party");
        WHERE("app.id = #{" + PARAM_APPLICATION_ID + "}");
        WHERE("app.agent_id=party.id");

        sql = sql + SQL() + " UNION ";
        sortClassifier++;

        // AGENT History
        BEGIN();

        SELECT("'" + AGENT_GROUP + "' AS record_group");
        SELECT("'" + AGENT_GROUP + "' AS record_type");
        SELECT(sortClassifier + " as sort_classifier");
        SELECT("app.agent_id AS record_id");
        SELECT("app.rowversion AS record_sequence");
        SELECT("''::text AS nr");
        SELECT("CASE WHEN (app.change_action='i') then replace(party.change_action,'i','" + ADDED_AGENT + "')||' - '||coalesce(party.name,'')||' '||coalesce(party.last_name,'')"
                + " ELSE  replace(app.change_action,app.change_action,'" + DELETED_AGENT + "')||' - '||coalesce(party.name,'')||' '||coalesce(party.last_name,'')"
                + " END AS action_code");
        SELECT("NULL::text AS notation");
        SELECT("app.change_time");
        SELECT("(SELECT (appuser.first_name::text || ' '::text) || appuser.last_name::text"
                + " FROM system.appuser"
                + " WHERE appuser.username::text = app.change_user::text)"
                + " AS user_fullname");
        FROM("application.application new_app");
        FROM("application.application_historic app"
                + " LEFT JOIN party.party party"
                + "  ON app.agent_id = party.id");
        WHERE("app.id = #{" + PARAM_APPLICATION_ID + "}");
        WHERE("app.agent_id != new_app.agent_id");
        WHERE("app.agent_id=party.id");
        WHERE("((app.rowversion - 1) = new_app.rowversion OR (app.rowversion) = new_app.rowversion)");

        sql = sql + SQL() + " UNION ";
        sortClassifier++;

        // contact_person 
        BEGIN();

        SELECT("'" + CONTACT_PERSON_GROUP + "' AS record_group");
        SELECT("'" + CONTACT_PERSON_GROUP + "' AS record_type");
        SELECT(sortClassifier + " as sort_classifier");
        SELECT("app.contact_person_id AS record_id");
        SELECT("app.rowversion AS record_sequence");
        SELECT("''::text AS nr");
        SELECT("replace(party.change_action,app.change_action,'" + ADDED_CONTACT_PERSON + "')||' - '||party.name||' '||coalesce(party.last_name,'') AS action_code");
        SELECT("NULL::text AS notation");
        SELECT("app.change_time");
        SELECT("(SELECT (appuser.first_name::text || ' '::text) || appuser.last_name::text"
                + " FROM system.appuser"
                + " WHERE appuser.username::text = app.change_user::text)"
                + " AS user_fullname");
        FROM("application.application app");
        FROM("party.party party");
        WHERE("app.id = #{" + PARAM_APPLICATION_ID + "}");
        WHERE("app.contact_person_id=party.id");

        sql = sql + SQL() + " UNION ";
        sortClassifier++;

        // contact_person History
        BEGIN();

        SELECT("'" + CONTACT_PERSON_GROUP + "' AS record_group");
        SELECT("'" + CONTACT_PERSON_GROUP + "' AS record_type");
        SELECT(sortClassifier + " as sort_classifier");
        SELECT("app.contact_person_id AS record_id");
        SELECT("app.rowversion AS record_sequence");
        SELECT("''::text AS nr");
        SELECT("CASE WHEN (app.change_action='i') then replace(party.change_action,'i','" + ADDED_CONTACT_PERSON + "')||' - '||coalesce(party.name,'')||' '||coalesce(party.last_name,'')"
                + " ELSE  replace(app.change_action,app.change_action,'" + DELETED_CONTACT_PERSON + "')||' - '||coalesce(party.name,'')||' '||coalesce(party.last_name,'')"
                + " END AS action_code");
        SELECT("NULL::text AS notation");
        SELECT("app.change_time");
        SELECT("(SELECT (appuser.first_name::text || ' '::text) || appuser.last_name::text"
                + " FROM system.appuser"
                + " WHERE appuser.username::text = app.change_user::text)"
                + " AS user_fullname");
        FROM("application.application new_app");
        FROM("application.application_historic app"
                + " LEFT JOIN party.party_historic party"
                + "  ON app.contact_person_id = party.id");
        WHERE("app.id = #{" + PARAM_APPLICATION_ID + "}");
        WHERE("app.contact_person_id != new_app.contact_person_id");
        WHERE("app.contact_person_id=party.id");
        WHERE("((app.rowversion - 1) = new_app.rowversion OR (app.rowversion) = new_app.rowversion)");

        ORDER_BY("change_time, sort_classifier, nr");

        sql = sql + SQL();

        return sql;
    }

    /**
     * Initializes the select query used to retrieve property summary details.
     */
    private static void initPropertySummaryQuery() {
        BEGIN();
        SELECT("DISTINCT prop.id");
        SELECT("prop.name");
        SELECT("prop.name_firstpart");
        SELECT("prop.name_lastpart");
        SELECT("prop.status_code");
        SELECT("prop.description");
        SELECT("prop.type_code");
        SELECT("prop.rowversion");
        SELECT("(SELECT string_agg(COALESCE(p1.name, '') || ' ' || COALESCE(p1.last_name, ''), '::::') "
                + "FROM administrative.rrr rrr1, administrative.party_for_rrr pr1, party.party p1 "
                + "WHERE rrr1.ba_unit_id = prop.id "
                + "AND rrr1.status_code = 'current' "
                + "AND pr1.rrr_id = rrr1.id "
                + "AND p1.id = pr1.party_id ) AS rightholders");
        SELECT("(SELECT string_agg(COALESCE(co1.name_firstpart, '') || ' ' || COALESCE(co1.name_lastpart, ''), '::::') "
                + "FROM administrative.ba_unit_contains_spatial_unit bas1, cadastre.cadastre_object co1 "
                + "WHERE bas1.ba_unit_id = prop.id "
                + "AND co1.id = bas1.spatial_unit_id"
                + ") AS parcels");
        SELECT("(SELECT string_agg(COALESCE(addr2.description, ''), '::::') "
                + "FROM administrative.ba_unit_contains_spatial_unit bas2, cadastre.spatial_unit_address sua2, "
                + " address.address addr2 "
                + "WHERE bas2.ba_unit_id = prop.id "
                + "AND sua2.spatial_unit_id = bas2.spatial_unit_id "
                + "AND addr2.id = sua2.address_id "
                + ") AS locality");
        SELECT("administrative.get_land_use_code(prop.id) AS land_use_code");
        SELECT("(SELECT (COALESCE(pm.name, '') || ' ' || COALESCE(pm.last_name, ''))"
                + " FROM administrative.ba_unit_as_party bap, party.party pm"
                + " WHERE bap.ba_unit_id = prop.id"
                + " AND   pm.id = bap.party_id LIMIT 1) AS prop_man");
        SELECT("administrative.get_state_land_status(prop.id) AS state_land_status_code");
        SELECT("(SELECT ba.size "
                + " FROM administrative.ba_unit_area ba "
                + " WHERE ba.ba_unit_id = prop.id"
                + " AND   ba.type_code = 'officialArea' ) AS area");
        SELECT("prop.classification_code");
        SELECT("prop.redact_code");
        FROM("administrative.ba_unit prop");
    }

    /**
     * Uses the BA Unit Search parameters to build an appropriate SQL Query.
     * This method does not inject the search parameter values into the SQL as
     * that would prevent the database from performing statement caching.
     *
     * @param params The name first part search parameter value
     * @return SQL String
     */
    public static String buildSearchBaUnitSql(BaUnitSearchParams params) {
        boolean criteriaProvided = false;
        String sql;

        initPropertySummaryQuery();

        if (params.isSearchType(BaUnitSearchParams.SEARCH_TYPE_STATE_LAND)) {
            // State Land Search
            WHERE("prop.type_code = 'stateLand'");
            WHERE("prop.name_firstpart = 'SL'");
            if (!StringUtility.isEmpty(params.getNameLastPart())) {
                // Remove any SL reference from the name
                params.setNameLastPart(params.getNameLastPart().replaceAll("SL", ""));
            }
        } else {
            WHERE("prop.type_code != 'stateLand'");
        }

        if (!StringUtility.isEmpty(params.getOwnerName()) || !StringUtility.isEmpty(params.getInterestRefNum())) {
            criteriaProvided = true;
            FROM("administrative.rrr rrr LEFT OUTER JOIN administrative.notation n ON n.rrr_id = rrr.id");
            WHERE("rrr.ba_unit_id = prop.id");
            WHERE("rrr.status_code = 'current'");
        }

        if (!StringUtility.isEmpty(params.getOwnerName())) {
            criteriaProvided = true;
            FROM("administrative.party_for_rrr pr");
            FROM("party.party p");
            WHERE("pr.rrr_id = rrr.id");
            WHERE("p.id = pr.party_id");
            WHERE("compare_strings(#{" + BaUnitSearchResult.QUERY_PARAM_OWNER_NAME + "}, "
                    + "COALESCE(p.name, '') || ' ' || COALESCE(p.last_name, '') || ' ' || COALESCE(p.alias, ''))");
        }
        if (!StringUtility.isEmpty(params.getNameFirstPart())) {
            criteriaProvided = true;
            WHERE("compare_strings(#{" + BaUnitSearchResult.QUERY_PARAM_NAME_FIRSTPART
                    + "}, COALESCE(prop.name_firstpart, ''))");
        }

        if (!StringUtility.isEmpty(params.getNameLastPart())) {
            criteriaProvided = true;
            WHERE("compare_strings(#{" + BaUnitSearchResult.QUERY_PARAM_NAME_LASTPART
                    + "}, COALESCE(prop.name_lastpart, ''))");
        }

        if (!StringUtility.isEmpty(params.getInterestRefNum())) {
            criteriaProvided = true;
            WHERE("compare_strings(#{" + BaUnitSearchResult.QUERY_PARAM_INTEREST_REF
                    + "}, COALESCE(n.reference_nr, ''))");
        }

        if (!StringUtility.isEmpty(params.getDocumentNumber())) {
            criteriaProvided = true;
            WHERE("administrative.is_linked_document(prop.id, #{" + BaUnitSearchResult.QUERY_PARAM_DOCUMENT_REF + "})");
        }

        if (!StringUtility.isEmpty(params.getParcelNumber()) || !StringUtility.isEmpty(params.getPlanNumber())
                || !StringUtility.isEmpty(params.getLandUseTypeCode()) || !StringUtility.isEmpty(params.getLocality())) {
            criteriaProvided = true;
            FROM("administrative.ba_unit_contains_spatial_unit bas");
            FROM("cadastre.cadastre_object co");
            WHERE("bas.ba_unit_id = prop.id");
            WHERE("co.id = bas.spatial_unit_id");
        }

        if (!StringUtility.isEmpty(params.getParcelNumber())) {
            criteriaProvided = true;
            WHERE("compare_strings(#{" + BaUnitSearchResult.QUERY_PARAM_PARCEL_NAME_FIRSTPART
                    + "}, COALESCE(co.name_firstpart, ''))");
        }

        if (!StringUtility.isEmpty(params.getPlanNumber())) {
            criteriaProvided = true;
            WHERE("compare_strings(#{" + BaUnitSearchResult.QUERY_PARAM_PARCEL_NAME_LASTPART
                    + "}, COALESCE(co.name_lastpart, ''))");
        }

        if (!StringUtility.isEmpty(params.getLandUseTypeCode())) {
            criteriaProvided = true;
            WHERE("co.land_use_code = #{" + BaUnitSearchResult.QUERY_PARAM_PARCEL_LAND_USE + "}");
        }

        if (!StringUtility.isEmpty(params.getLocality())) {
            criteriaProvided = true;
            FROM("cadastre.spatial_unit_address sua");
            FROM("address.address addr");
            WHERE("sua.spatial_unit_id = co.id");
            WHERE("addr.id = sua.address_id");
            WHERE("compare_strings(#{" + BaUnitSearchResult.QUERY_PARAM_LOCALITY
                    + "}, COALESCE(addr.description, ''))");
        }

        if (!StringUtility.isEmpty(params.getPropertyManager())) {
            criteriaProvided = true;
            FROM("administrative.ba_unit_as_party bap");
            FROM("party.party pman");
            WHERE("bap.ba_unit_id = prop.id");
            WHERE("pman.id = bap.party_id");
            WHERE("compare_strings(#{" + BaUnitSearchResult.QUERY_PARAM_PROPERTY_MANAGER + "}, "
                    + "COALESCE(pman.name, '') || ' ' || COALESCE(pman.last_name, '') || ' ' || COALESCE(pman.alias, ''))");
        }

        if (!StringUtility.isEmpty(params.getDescription())) {
            criteriaProvided = true;
            WHERE("compare_strings(#{" + BaUnitSearchResult.QUERY_PARAM_DESCRIPTION
                    + "}, COALESCE(prop.description, ''))");
        }

        if (!criteriaProvided) {
            // If the user has not provided any search criteria, prevent the search form
            // evaluating every property record by adding a false WHERE clause. 
            WHERE("1 = 2");
        }
        ORDER_BY(BaUnitSearchResult.QUERY_ORDER_BY + " LIMIT 100");
        sql = SQL();
        return sql;
    }

    /**
     * Creates an SQL query to retrieve the properties with required actions for
     * display for the on the SLDashboardPanel.
     *
     * @return SQL String
     */
    public static String buildPropertyToActionSql() {
        String sql;

        initPropertySummaryQuery();
        SELECT("(SELECT string_agg(COALESCE(a.nr, ''), '::::') "
                + "FROM application.application a "
                + "WHERE a.status_code IN ('lodged', 'approved', 'requisitioned') "
                + "AND a.id IN ( "
                + "   SELECT ap.application_id "
                + "   FROM application.application_property ap"
                + "   WHERE ap.ba_unit_id = prop.id "
                + "   UNION"
                + "   SELECT s.application_id "
                + "   FROM application.service s, "
                + "        transaction.transaction t "
                + "   WHERE t.id = prop.transaction_id "
                + "   AND   s.id = t.from_service_id "
                + "   AND   s.status_code IN ('lodged', 'pending')) "
                + ") AS active_jobs");
        SELECT("note.status_code AS action_status");
        SELECT("note.notation_text");
        FROM("administrative.notation note");
        WHERE("note.id IN (SELECT n.id "
                + "FROM  administrative.notation n "
                + "WHERE n.ba_unit_id = prop.id "
                + "AND   n.status_code IN ('actionReqd', 'actionReqdUrgent', 'onHold') "
                + "ORDER BY CASE n.status_code WHEN 'actionReqdUrgent' THEN 1 "
                + "                            WHEN 'actionReqd' THEN 2 "
                + "                            ELSE 3 END LIMIT 1)");
        ORDER_BY("note.status_code, prop.name_lastpart LIMIT 100");
        sql = SQL();
        return sql;
    }

    /**
     * Initializes the select query used to retrieve job summary details. This
     * query omits the assignee_name field so this can be included in the main
     * build SQL method.
     */
    private static void initJobSummaryQuery() {
        BEGIN();
        SELECT("DISTINCT app.id");
        SELECT("app.nr");
        SELECT("app.status_code AS status");
        SELECT("app.lodging_datetime");
        SELECT("app.expected_completion_date");
        SELECT("app.assigned_datetime");
        SELECT("app.assignee_id");
        SELECT("app.agent_id");
        SELECT("app.contact_person_id");
        SELECT("app.fee_paid");
        SELECT("app.rowversion");
        SELECT("app.change_user");
        SELECT("app.rowidentifier");
        SELECT("app.description");
        SELECT("(SELECT string_agg(tmp.display_value, ',') FROM "
                + "(SELECT get_translation(display_value, #{" + CommonSqlProvider.PARAM_LANGUAGE_CODE + "}) as display_value  "
                + "FROM application.service aps INNER JOIN application.request_type rt ON aps.request_type_code = rt.code  "
                + "WHERE aps.application_id = app.id ORDER BY aps.service_order) tmp) AS service_list");
        SELECT("(SELECT (COALESCE(pa.name, '') || ' ' || COALESCE(pa.last_name, ''))"
                + " FROM party.party pa"
                + " WHERE pa.id = app.agent_id ) AS agent");
        SELECT("(SELECT (COALESCE(pc.name, '') || ' ' || COALESCE(pc.last_name, ''))"
                + " FROM party.party pc"
                + " WHERE pc.id = app.contact_person_id ) AS contact_person");
        SELECT("app.classification_code");
        SELECT("app.redact_code");
        FROM("application.application app");
    }

    /**
     * Uses the Application Search parameters to build an appropriate SQL Query
     * to search for Jobs in the system. This method does not inject the search
     * parameter values into the SQL as that would prevent the database from
     * performing statement caching.
     *
     * @param params The application search parameters
     * @return SQL String
     */
    public static String buildSearchJobsSql(ApplicationSearchParams params) {
        boolean criteriaProvided = false;
        String sql;

        initJobSummaryQuery();

        SELECT("(SELECT (COALESCE(u.first_name, '') || ' ' || COALESCE(u.last_name, ''))"
                + " FROM system.appuser u"
                + " WHERE u.id = app.assignee_id ) AS assignee_name");

        if (!StringUtility.isEmpty(params.getNr())) {
            criteriaProvided = true;
            WHERE("compare_strings(#{" + ApplicationSearchResult.QUERY_PARAM_APP_NR + "}, app.nr)");
        }

        if (!StringUtility.isEmpty(params.getAgent())) {
            criteriaProvided = true;
            FROM("party.party pag");
            WHERE("pag.id = app.agent_id");
            WHERE("compare_strings(#{" + ApplicationSearchResult.QUERY_PARAM_AGENT_NAME + "}, "
                    + "COALESCE(pag.name, '') || ' ' || COALESCE(pag.last_name, '') || ' ' || COALESCE(pag.alias, ''))");
        }

        if (!StringUtility.isEmpty(params.getContactPerson())) {
            criteriaProvided = true;
            FROM("party.party pcp");
            WHERE("pcp.id = app.contact_person_id");
            WHERE("compare_strings(#{" + ApplicationSearchResult.QUERY_PARAM_CONTACT_NAME + "}, "
                    + "COALESCE(pcp.name, '') || ' ' || COALESCE(pcp.last_name, '') || ' ' || COALESCE(pcp.alias, ''))");
        }

        if (!StringUtility.isEmpty(params.getAssigneeName())) {
            criteriaProvided = true;
            FROM("system.appuser usr");
            WHERE("usr.id = app.assignee_id");
            WHERE("compare_strings(#{" + ApplicationSearchResult.QUERY_PARAM_ASSIGNEE_NAME + "}, "
                    + "COALESCE(usr.first_name, '') || ' ' || COALESCE(usr.last_name, ''))");
        }

        if (params.getFromDate() != null) {
            criteriaProvided = true;
            WHERE("app.lodging_datetime >= #{" + ApplicationSearchResult.QUERY_PARAM_FROM_LODGE_DATE + "}");
        }

        if (params.getToDate() != null) {
            criteriaProvided = true;
            WHERE("app.lodging_datetime <= #{" + ApplicationSearchResult.QUERY_PARAM_TO_LODGE_DATE + "}");
        }

        if (!StringUtility.isEmpty(params.getDocumentReference()) || !StringUtility.isEmpty(params.getDocumentNumber())) {
            criteriaProvided = true;
            FROM("application.application_uses_source aus");
            FROM("source.source src");
            WHERE("aus.application_id = app.id");
            WHERE("src.id = aus.source_id");
            if (!StringUtility.isEmpty(params.getDocumentReference())) {
                WHERE("compare_strings(#{" + ApplicationSearchResult.QUERY_PARAM_DOCUMENT_REFERENCE + "}, "
                        + "COALESCE(src.reference_nr, ''))");
            }

            if (!StringUtility.isEmpty(params.getDocumentNumber())) {
                criteriaProvided = true;
                WHERE("compare_strings(#{" + ApplicationSearchResult.QUERY_PARAM_DOCUMENT_NUMBER + "}, "
                        + "COALESCE(src.la_nr, ''))");
            }
        }

        if (!StringUtility.isEmpty(params.getDescription())) {
            criteriaProvided = true;
            WHERE("app.description IS NOT NULL");
            WHERE("compare_strings(#{" + ApplicationSearchResult.QUERY_PARAM_DESCRIPTION + "}, "
                    + "COALESCE(app.description, ''))");
        }

        if (!criteriaProvided) {
            // If the user has not provided any search criteria, prevent the search form
            // evaluating every job record by adding a false WHERE clause. 
            WHERE("1 = 2");
        }
        ORDER_BY("app.lodging_datetime DESC LIMIT 100");
        sql = SQL();
        return sql;
    }

    /**
     * Creates an SQL query to retrieve job summary details to display for the
     * Assigned Jobs table on the SLDashboardPanel. This method does not inject
     * the search parameter values into the SQL as that would prevent the
     * database from performing statement caching.
     *
     * @param allJobs Flag indicating if all jobs should be returned, or only
     * those jobs assigned to the user.
     * @return SQL String
     */
    public static String buildAssignedJobsSql(boolean allJobs) {
        String sql;

        initJobSummaryQuery();

        SELECT("(COALESCE(u.first_name, '') || ' ' || COALESCE(u.last_name, '')) AS assignee_name");
        FROM("system.appuser u");
        WHERE("app.status_code IN ('lodged', 'approved', 'requisitioned')");
        WHERE("app.assignee_id IS NOT NULL");
        WHERE("u.id = app.assignee_id");

        if (!allJobs) {
            // Only retrieve the jobs for this user
            WHERE("u.username = #{" + ApplicationSearchResult.QUERY_PARAM_USER_NAME + "}");
        }

        ORDER_BY("app.lodging_datetime DESC LIMIT 100");
        sql = SQL();
        return sql;
    }

    /**
     * Initializes the select query used to retrieve user summary details.
     */
    private static void initUserSummaryQuery() {
        BEGIN();
        SELECT("DISTINCT u.id");
        SELECT("u.username");
        SELECT("u.active");
        SELECT("u.first_name");
        SELECT("u.last_name");
        SELECT("u.description");
        SELECT("(SELECT string_agg(tmp.gname, ', ') FROM "
                + "(SELECT g.name  AS gname "
                + "FROM system.appgroup g, system.appuser_appgroup ug "
                + "WHERE g.id = ug.appgroup_id AND ug.appuser_id = u.id "
                + "ORDER BY g.name) tmp) "
                + "AS groups_list");
        SELECT("(SELECT string_agg(tmp.tname, ', ') FROM "
                + "(SELECT TRIM(COALESCE(p.name, '') || ' ' || COALESCE(p.last_name, '')) AS tname "
                + " FROM party.party p, system.appuser_team t "
                + " WHERE t.appuser_id = u.id AND p.id = t.party_id "
                + " ORDER BY tname) tmp ) "
                + " AS team_list");
        SELECT("(SELECT array_agg(tmp.tid) FROM "
                + "(SELECT TRIM(COALESCE(p.name, '') || ' ' || COALESCE(p.last_name, '')) AS tname, "
                + " p.id as tid "
                + " FROM party.party p, system.appuser_team t "
                + " WHERE t.appuser_id = u.id AND p.id = t.party_id "
                + " ORDER BY tname) tmp ) "
                + " AS team_ids");
        FROM("system.appuser u");
    }

    /**
     * Uses the User Search parameters to build an appropriate SQL Query to
     * search for Users in the system. This method does not inject the search
     * parameter values into the SQL as that would prevent the database from
     * performing statement caching.
     *
     * @param params The user search parameters
     * @return SQL String
     */
    public static String buildSearchUsersSql(UserSearchParams params) {
        boolean criteriaProvided = false;
        String sql;

        initUserSummaryQuery();

        if (!StringUtility.isEmpty(params.getUserName())) {
            criteriaProvided = true;
            WHERE("compare_strings(#{" + UserSearchResult.QUERY_PARAM_USER_NAME + "}, u.username)");
        }

        if (!StringUtility.isEmpty(params.getFirstName())) {
            criteriaProvided = true;
            WHERE("compare_strings(#{" + UserSearchResult.QUERY_PARAM_FIRST_NAME + "}, u.first_name)");
        }

        if (!StringUtility.isEmpty(params.getLastName())) {
            criteriaProvided = true;
            WHERE("compare_strings(#{" + UserSearchResult.QUERY_PARAM_LAST_NAME + "}, u.last_name)");
        }

        if (!StringUtility.isEmpty(params.getGroupId())) {
            criteriaProvided = true;
            FROM("system.appuser_appgroup grp");
            WHERE("grp.appuser_id = u.id");
            WHERE("grp.appgroup_id = #{" + UserSearchResult.QUERY_PARAM_GROUP_ID + "}");
        }

        if (!StringUtility.isEmpty(params.getTeamId())) {
            criteriaProvided = true;
            FROM("system.appuser_team team");
            WHERE("team.appuser_id = u.id");
            WHERE("team.party_id = #{" + UserSearchResult.QUERY_PARAM_TEAM_ID + "}");
        }

        if (!criteriaProvided) {
            // Return all users anyway
        }
        ORDER_BY("u.last_name, u.first_name");
        sql = SQL();
        return sql;
    }

    /**
     * Builds an appropriate SQL Query to retrieve all active users from the
     * system
     *
     * @param params The user search parameters
     * @return SQL String
     */
    public static String buildGetActiveUsersSql() {
        String sql;

        initUserSummaryQuery();
        WHERE("u.active = 't'");
        ORDER_BY("u.last_name, u.first_name");
        sql = SQL();
        return sql;
    }

    /**
     * Builds an appropriate SQL Query to retrieve the properties underlying the
     * specified State Land Parcel. Uses a spatial comparison. 
     *
     * @return SQL String
     */
    public static String buildGetUnderlyingTitles() {
        String sql;

        initPropertySummaryQuery();
        FROM("cadastre.cadastre_object co");
        FROM("cadastre.cadastre_object co_sl");
        FROM("administrative.ba_unit_contains_spatial_unit bsu");
        WHERE("co_sl.id = #{" + PARAM_SL_PARCEL_ID + "} ");
        WHERE("co_sl.type_code = 'stateLand'");
        WHERE("co_sl.geom_polygon IS NOT NULL");
        WHERE("co.geom_polygon IS NOT NULL");
        WHERE("ST_Intersects(co.geom_polygon, co_sl.geom_polygon)");
        // Don't include parcels that share a point or boundary with the state land parcel
        WHERE("NOT ST_Touches(co.geom_polygon, co_sl.geom_polygon)"); 
        WHERE("co.type_code = 'parcel'");
        WHERE("bsu.spatial_unit_id = co.id");
        WHERE("prop.id = bsu.ba_unit_id");
        sql = SQL();
        return sql;
    }

}
