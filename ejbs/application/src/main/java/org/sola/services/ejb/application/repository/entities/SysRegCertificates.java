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
@Table(name = "systematic_registration_certificates", schema = "application")
public class SysRegCertificates extends AbstractReadOnlyEntity {

    public static final String QUERY_PARAMETER_NR = "nr";
    // Where clause
//    public static final String QUERY_WHERE_BYNR = "nr = #{" + QUERY_PARAMETER_NR  + "} "
//            + "AND compare_strings(#{search_string}, name_lastpart)";
     
    public static final String QUERY_SELECT = "  distinct(ba_unit_id), nr, name_firstpart, name_lastpart";
    
    public static final String QUERY_WHERE_BYNR = " nr = #{" + QUERY_PARAMETER_NR  + "} "
             + "AND compare_strings(#{search_string}, name_lastpart)"; 
    /**
     * WHERE clause to return current CO's based on search string compared to
     * last part
     */
//    public static final String QUERY_WHERE_SEARCHBYPARTS = "compare_strings(#{search_string}, name_lastpart)";
    
    public static final String QUERY_WHERE_SEARCHBYPARTS = " compare_strings(#{search_string}, name_lastpart)"; 
    
    @Column(name = "nr")
    private String nr;
    @Column(name = "name_firstpart")
    private String nameFirstpart;
    @Column(name = "name_lastpart")
    private String nameLastpart;
    @Column(name = "ba_unit_id")
    private String baUnitId;

    
    
    public String getBaUnitId() {
        return baUnitId;
    }

    public void setBaUnitId(String baUnitId) {
        this.baUnitId = baUnitId;
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

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

}
