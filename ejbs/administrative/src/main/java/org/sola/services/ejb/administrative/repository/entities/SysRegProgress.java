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
            + " TotParcLoaded  		varchar,"
            + "	TotRecObj  		decimal,"
            + "	TotSolvedObj  		decimal,"
            + "	TotAppPDisp  		decimal,"
            + "	TotPrepCertificate	decimal,"
            + " TotIssuedCertificate    decimal )";
            
     
    @Column(name = "block")
    private String block;
    @Column(name = "TotAppLod")
    private BigDecimal TotAppLod;
    @Column(name = "TotParcLoaded")
    private String TotParcLoaded;
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

    public String getTotParcLoaded() {
        return TotParcLoaded;
    }

    public void setTotParcLoaded(String TotParcLoaded) {
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
