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
public class SysRegGender extends AbstractReadOnlyEntity {

    public static final String PARAMETER_GENDER = "gender";
    public static final String QUERY_PARAMETER_QUERY = "query";
    public static final String QUERY_GETQUERY = "select * from administrative.get_parcel_ownergender(#{"
            + PARAMETER_GENDER + "}, #{" + QUERY_PARAMETER_QUERY + "}) "
            + " as SysRegGender  ("
            + " parcel    varchar,"
            + " total     decimal,"
            + " totFem    decimal,"
            + "	totMale   decimal,"
            + "	totMixed  decimal,"
            + "	totJoint  decimal,"
            + "	totEntity decimal,"
            + "	totNull   decimal)";
            
    
     
  @Column(name = "parcel")
  private String parcel;
  @Column(name = "total")
  private BigDecimal total;
  @Column(name = "totFem")
  private BigDecimal totFem;
  @Column(name = "totMale")
  private BigDecimal totMale;
  @Column(name = "totMixed")
  private BigDecimal totMixed;
  @Column(name = "totJoint")
  private BigDecimal totJoint;
  @Column(name = "totEntity")
  private BigDecimal totEntity;
  @Column(name = "totNull")
  private BigDecimal totNull;
    
    
    public SysRegGender() {
        super();
    }

    public String getParcel() {
        return parcel;
    }

    public void setParcel(String parcel) {
        this.parcel = parcel;
    }

    public BigDecimal getTotEntity() {
        return totEntity;
    }

    public void setTotEntity(BigDecimal totEntity) {
        this.totEntity = totEntity;
    }

    public BigDecimal getTotFem() {
        return totFem;
    }

    public void setTotFem(BigDecimal totFem) {
        this.totFem = totFem;
    }

    public BigDecimal getTotJoint() {
        return totJoint;
    }

    public void setTotJoint(BigDecimal totJoint) {
        this.totJoint = totJoint;
    }

    public BigDecimal getTotMale() {
        return totMale;
    }

    public void setTotMale(BigDecimal totMale) {
        this.totMale = totMale;
    }

    public BigDecimal getTotMixed() {
        return totMixed;
    }

    public void setTotMixed(BigDecimal totMixed) {
        this.totMixed = totMixed;
    }

    public BigDecimal getTotNull() {
        return totNull;
    }

    public void setTotNull(BigDecimal totNull) {
        this.totNull = totNull;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
