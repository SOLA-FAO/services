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
public class SysRegProgress extends AbstractReadOnlyEntity {

    public static final String PARAMETER_FROM = "fromDate";
    public static final String PARAMETER_TO = "toDate";
    public static final String QUERY_PARAMETER_LASTPART = "nameLastpart";
    public static final String QUERY_GETQUERY = "select * from administrative.getsysregprogress(#{"
            + PARAMETER_FROM + "}, #{" + PARAMETER_TO + "}, #{" + QUERY_PARAMETER_LASTPART + "}) "
            + " as SysRegStatusReport(block varchar,"
            + "	TotAppLod  		decimal,"
            + " TotParcLoaded  		decimal,"
            + "	TotRecObj  		decimal,"
            + "	TotSolvedObj  		decimal,"
            + "	TotAppPDisp  		decimal,"
            + "	TotPrepCertificate	decimal,"
            + " TotIssuedCertificate    decimal )";
            
//        block  			varchar;	
//       	TotAppLod		decimal:=0 ;	
//        TotParcLoaded		decimal:=0 ;	
//        TotRecObj		decimal:=0 ;	
//        TotSolvedObj		decimal:=0 ;	
//        TotAppPDisp		decimal:=0 ;	
//        TotPrepCertificate      decimal:=0 ;	
//        TotIssuedCertificate	decimal:=0 ;	


     
    @Column(name = "block")
    private String block;
    @Column(name = "TotAppLod")
    private BigDecimal TotAppLod;
    @Column(name = "TotParcLoaded")
    private BigDecimal TotParcLoaded;
    @Column(name = "TotRecObj")
    private BigDecimal TotRecObj;
    @Column(name = "TotSolvedObj")
    private BigDecimal TotSolvedObj;
    @Column(name = "TotAppPDisp")
    private BigDecimal TotAppPDisp;
    @Column(name = "TotPrepCertificate")
    private BigDecimal TotPrepCertificate;
    @Column(name = "TotIssuedCertificate")
    private BigDecimal TotIssuedCertificate;
    
    public SysRegProgress() {
        super();
    }

    public BigDecimal getTotAppLod() {
        return TotAppLod;
    }

    public void setTotAppLod(BigDecimal TotAppLod) {
        this.TotAppLod = TotAppLod;
    }

    public BigDecimal getTotAppPDisp() {
        return TotAppPDisp;
    }

    public void setTotAppPDisp(BigDecimal TotAppPDisp) {
        this.TotAppPDisp = TotAppPDisp;
    }

    public BigDecimal getTotIssuedCertificate() {
        return TotIssuedCertificate;
    }

    public void setTotIssuedCertificate(BigDecimal TotIssuedCertificate) {
        this.TotIssuedCertificate = TotIssuedCertificate;
    }

    public BigDecimal getTotParcLoaded() {
        return TotParcLoaded;
    }

    public void setTotParcLoaded(BigDecimal TotParcLoaded) {
        this.TotParcLoaded = TotParcLoaded;
    }

    public BigDecimal getTotPrepCertificate() {
        return TotPrepCertificate;
    }

    public void setTotPrepCertificate(BigDecimal TotPrepCertificate) {
        this.TotPrepCertificate = TotPrepCertificate;
    }

    public BigDecimal getTotRecObj() {
        return TotRecObj;
    }

    public void setTotRecObj(BigDecimal TotRecObj) {
        this.TotRecObj = TotRecObj;
    }

    public BigDecimal getTotSolvedObj() {
        return TotSolvedObj;
    }

    public void setTotSolvedObj(BigDecimal TotSolvedObj) {
        this.TotSolvedObj = TotSolvedObj;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }
}
