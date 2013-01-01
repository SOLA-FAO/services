/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.ejb.administrative.repository.entities;

import java.math.BigDecimal;
import javax.persistence.Column;

import org.sola.services.common.repository.entities.AbstractReadOnlyEntity;

/**
 *
 * @author RizzoM
 */
public class SysRegManagement extends AbstractReadOnlyEntity {

    public static final String PARAMETER_FROM = "fromDate";
    public static final String PARAMETER_TO = "toDate";
    public static final String QUERY_PARAMETER_LASTPART = "nameLastpart";

//        public static final String QUERY_GETQUERY = "select * from administrative.getsysregmanagement(#{" + 
//            PARAMETER_FROM + "}, #{" + PARAMETER_TO + "}, #{" + QUERY_PARAMETER_LASTPART + "}) "
//            + " as SysRegManagementReport(application decimal, spatial decimal,"
//                + " completed decimal, approved decimal, archived decimal,"
//                + " parcelnotification decimal, parcelcompletion decimal, "
//                + " parcelapproved decimal, sizeapproved decimal, "
//                + " parcelapprovedresidential decimal, sizeapprovedresidential decimal, "
//                + " parcelapprovedcommercial decimal, sizeapprovedcommercial decimal, "
//                + " parcelapprovedindustrial decimal, sizeapprovedindustrial decimal, "
//                + " parcelapprovedagricultural decimal,  sizeapprovedagricultural decimal, "
//                + " objection decimal) ";
          
            public static final String QUERY_GETQUERY = "select * from administrative.getsysregmanagement(#{" + 
            PARAMETER_FROM + "}, #{" + PARAMETER_TO + "}, #{" + QUERY_PARAMETER_LASTPART + "}) "
            + " as SysRegManagementReport(counter integer, descr  varchar) ";
    
    
//    @Column(name = "application")
//    private BigDecimal application;
//    @Column(name = "spatial")
//    private BigDecimal spatial;
//    @Column(name = "completed")
//    private BigDecimal completed;
//    @Column(name = "approved")
//    private BigDecimal approved;
//    @Column(name = "archived")
//    private BigDecimal archived;
//    @Column(name = "parcelnotification")
//    private BigDecimal parcelnotification;
//    @Column(name = "parcelcompletion")
//    private BigDecimal parcelcompletion;
//    @Column(name = "parcelapproved")
//    private BigDecimal parcelapproved;
//    @Column(name = "sizeapproved")
//    private BigDecimal sizeapproved;
//    @Column(name = "parcelapprovedresidential")
//    private BigDecimal parcelapprovedresidential;
//    @Column(name = "sizeapprovedresidential")
//    private BigDecimal sizeapprovedresidential;
//    @Column(name = "parcelapprovedcommercial")
//    private BigDecimal parcelapprovedcommercial;
//    @Column(name = "sizeapprovedcommercial")
//    private BigDecimal sizeapprovedcommercial;
//    @Column(name = "parcelapprovedindustrial")
//    private BigDecimal parcelapprovedindustrial;
//    @Column(name = "sizeapprovedindustrial")
//    private BigDecimal sizeapprovedindustrial;
//    @Column(name = "parcelapprovedagricultural")
//    private BigDecimal parcelapprovedagricultural;
//    @Column(name = "sizeapprovedagricultural")
//    private BigDecimal sizeapprovedagricultural;
//    @Column(name = "objection")
//    private BigDecimal objection;
      
    @Column(name="counter")
    private Integer counter;
    @Column(name="descr")
    private String  descr;
         
    public SysRegManagement() {
        super();
    }
    
    
    
//    public BigDecimal getApplication() {
//        return application;
//    }
//
//    public void setApplication(BigDecimal application) {
//        this.application = application;
//    }
//
//    public BigDecimal getApproved() {
//        return approved;
//    }
//
//    public void setApproved(BigDecimal approved) {
//        this.approved = approved;
//    }
//
//    public BigDecimal getArchived() {
//        return archived;
//    }
//
//    public void setArchived(BigDecimal archived) {
//        this.archived = archived;
//    }
//
//    public BigDecimal getCompleted() {
//        return completed;
//    }
//
//    public void setCompleted(BigDecimal completed) {
//        this.completed = completed;
//    }
//
//    public BigDecimal getObjection() {
//        return objection;
//    }
//
//    public void setObjection(BigDecimal objection) {
//        this.objection = objection;
//    }
//
//    public BigDecimal getParcelapproved() {
//        return parcelapproved;
//    }
//
//    public void setParcelapproved(BigDecimal parcelapproved) {
//        this.parcelapproved = parcelapproved;
//    }
//
//    public BigDecimal getParcelapprovedagricultural() {
//        return parcelapprovedagricultural;
//    }
//
//    public void setParcelapprovedagricultural(BigDecimal parcelapprovedagricultural) {
//        this.parcelapprovedagricultural = parcelapprovedagricultural;
//    }
//
//    public BigDecimal getParcelapprovedcommercial() {
//        return parcelapprovedcommercial;
//    }
//
//    public void setParcelapprovedcommercial(BigDecimal parcelapprovedcommercial) {
//        this.parcelapprovedcommercial = parcelapprovedcommercial;
//    }
//
//    public BigDecimal getParcelapprovedindustrial() {
//        return parcelapprovedindustrial;
//    }
//
//    public void setParcelapprovedindustrial(BigDecimal parcelapprovedindustrial) {
//        this.parcelapprovedindustrial = parcelapprovedindustrial;
//    }
//
//    public BigDecimal getParcelapprovedresidential() {
//        return parcelapprovedresidential;
//    }
//
//    public void setParcelapprovedresidential(BigDecimal parcelapprovedresidential) {
//        this.parcelapprovedresidential = parcelapprovedresidential;
//    }
//
//    public BigDecimal getParcelcompletion() {
//        return parcelcompletion;
//    }
//
//    public void setParcelcompletion(BigDecimal parcelcompletion) {
//        this.parcelcompletion = parcelcompletion;
//    }
//
//    public BigDecimal getParcelnotification() {
//        return parcelnotification;
//    }
//
//    public void setParcelnotification(BigDecimal parcelnotification) {
//        this.parcelnotification = parcelnotification;
//    }
//
//    public BigDecimal getSizeapproved() {
//        return sizeapproved;
//    }
//
//    public void setSizeapproved(BigDecimal sizeapproved) {
//        this.sizeapproved = sizeapproved;
//    }
//
//    public BigDecimal getSizeapprovedagricultural() {
//        return sizeapprovedagricultural;
//    }
//
//    public void setSizeapprovedagricultural(BigDecimal sizeapprovedagricultural) {
//        this.sizeapprovedagricultural = sizeapprovedagricultural;
//    }
//
//    public BigDecimal getSizeapprovedcommercial() {
//        return sizeapprovedcommercial;
//    }
//
//    public void setSizeapprovedcommercial(BigDecimal sizeapprovedcommercial) {
//        this.sizeapprovedcommercial = sizeapprovedcommercial;
//    }
//
//    public BigDecimal getSizeapprovedindustrial() {
//        return sizeapprovedindustrial;
//    }
//
//    public void setSizeapprovedindustrial(BigDecimal sizeapprovedindustrial) {
//        this.sizeapprovedindustrial = sizeapprovedindustrial;
//    }
//
//    public BigDecimal getSizeapprovedresidential() {
//        return sizeapprovedresidential;
//    }
//
//    public void setSizeapprovedresidential(BigDecimal sizeapprovedresidential) {
//        this.sizeapprovedresidential = sizeapprovedresidential;
//    }
//
//    public BigDecimal getSpatial() {
//        return spatial;
//    }
//
//    public void setSpatial(BigDecimal spatial) {
//        this.spatial = spatial;
//    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
}
