/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
 * Entity representing the administrative systematic_registration_listing view.
 *
 * @author soladev
 */
@Table(name = "systematic_registration_listing", schema = "administrative")
public class SysRegPubDisParcelName extends AbstractReadOnlyEntity {

    public static final String QUERY_PARAMETER_ID = "id";
    // Where clause
    public static final String QUERY_WHERE_BYID = "id = #{" + QUERY_PARAMETER_ID + "}";
    public static final String QUERY_WHERE_SEARCHBYPARTS = "name_lastpart = #{search_string}";
    
    @Id
    @Column(name = "id")
    private String id;
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
    @Column(insertable = false, updatable = false, name = "concatenated_name")
    @AccessFunctions(onSelect = "administrative.get_parcel_ownernames(ba_unit_id)")
    private String concatenatedName;
    @Column(insertable = false, updatable = false, name = "public_notification_duration")
    @AccessFunctions(onSelect = "system.get_setting('public-notification-duration')")
    private String publicNotificationDuration;
    @Column(name = "name")
    private String name;
    @Column(insertable = false, updatable = false, name = "objections")
    @AccessFunctions(onSelect = "administrative.get_objections(name_lastpart)")
    private String objections;

    public String getPublicNotificationDuration() {
        return publicNotificationDuration;
    }

    public void setPublicNotificationDuration(String publicNotificationDuration) {
        this.publicNotificationDuration = publicNotificationDuration;
    }

    public SysRegPubDisParcelName() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public BigDecimal getSize() {
        return size;
    }

    public void setSize(BigDecimal size) {
        this.size = size;
    }

    public String getBaUnitId() {
        return baUnitId;
    }

    public void setBaUnitId(String baUnitId) {
        this.baUnitId = baUnitId;
    }

    public String getConcatenatedName() {
        return concatenatedName;
    }

    public void setConcatenatedName(String concatenatedName) {
        this.concatenatedName = concatenatedName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObjections() {
        return objections;
    }

    public void setObjections(String objections) {
        this.objections = objections;
    }
}
