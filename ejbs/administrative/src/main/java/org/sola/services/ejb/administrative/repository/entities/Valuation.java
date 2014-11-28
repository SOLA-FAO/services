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
package org.sola.services.ejb.administrative.repository.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import org.sola.common.RolesConstants;
import org.sola.services.common.LocalInfo;
import org.sola.services.common.repository.ChildEntity;
import org.sola.services.common.repository.ChildEntityList;
import org.sola.services.common.repository.ExternalEJB;
import org.sola.services.common.repository.Redact;
import org.sola.services.common.repository.entities.AbstractReadOnlyEntity;
import org.sola.services.common.repository.entities.AbstractVersionedEntity;
import org.sola.services.ejb.source.businesslogic.SourceEJBLocal;
import org.sola.services.ejb.source.repository.entities.Source;

/**
 * Entity representing the administrative.valuation table.
 *
 * @author soladev
 */
@Table(name = "valuation", schema = "administrative")
public class Valuation extends AbstractVersionedEntity {

    public static final String QUERY_PARAMETER_TRANSACTION_ID = "transactionId";
    public static final String QUERY_WHERE_BYTRANSACTIONID = "transaction_id = #{" + QUERY_PARAMETER_TRANSACTION_ID + "} ";

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "nr")
    private String nr;
    @Column(name = "ba_unit_id")
    private String baUnitId;
    @Redact(minClassification = RolesConstants.CLASSIFICATION_CONFIDENTIAL)
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "valuation_date")
    private Date valuationDate;
    @Column(name = "type_code")
    private String typeCode;
    @ExternalEJB(ejbLocalClass = SourceEJBLocal.class,
            loadMethod = "getSources", saveMethod = "saveSource")
    @ChildEntityList(parentIdField = "valuationId", childIdField = "sourceId",
            manyToManyClass = SourceDescribesValuation.class)
    private List<Source> sourceList;
     @ChildEntity(childIdField = "baUnitId", readOnly=true)
    private BaUnitBasic baUnitBasic;
    @Column(name = "source")
    private String source;
    @Column(name = "description")
    private String description;
    @Column(name = "transaction_id", updatable = false)
    private String transactionId;
    @Column(name = AbstractReadOnlyEntity.CLASSIFICATION_CODE_COLUMN_NAME)
    private String classificationCode;
    @Column(name = AbstractReadOnlyEntity.REDACT_CODE_COLUMN_NAME)
    private String redactCode;

    ; 

    public Valuation() {
        super();
    }

    public String getId() {
        id = id == null ? generateId() : id;
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the nr
     */
    public String getNr() {
        return nr;
    }

    /**
     * @param nr the nr to set
     */
    public void setNr(String nr) {
        this.nr = nr;
    }

    /**
     * @return the baUnitId
     */
    public String getBaUnitId() {
        return baUnitId;
    }

    /**
     * @param baUnitId the baUnitId to set
     */
    public void setBaUnitId(String baUnitId) {
        this.baUnitId = baUnitId;
    }

    /**
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * @return the valuationDate
     */
    public Date getValuationDate() {
        return valuationDate;
    }

    /**
     * @param valuationDate the valuationDate to set
     */
    public void setValuationDate(Date valuationDate) {
        this.valuationDate = valuationDate;
    }

    /**
     * @return the typeCode
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * @param typeCode the typeCode to set
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    /**
     * @return the sourceList
     */
    public List<Source> getSourceList() {
        return sourceList;
    }

    /**
     * @param sourceList the sourceList to set
     */
    public void setSourceList(List<Source> sourceList) {
        this.sourceList = sourceList;
    }

    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the transcationId
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * @param transcationId the transcationId to set
     */
    public void setTransactionId(String transcationId) {
        this.transactionId = transcationId;
    }

    /**
     * @return the classificationCode
     */
    @Override
    public String getClassificationCode() {
        return classificationCode;
    }

    /**
     * @param classificationCode the classificationCode to set
     */
    public void setClassificationCode(String classificationCode) {
        this.classificationCode = classificationCode;
    }

    /**
     * @return the redactCode
     */
    @Override
    public String getRedactCode() {
        return redactCode;
    }

    /**
     * @param redactCode the redactCode to set
     */
    public void setRedactCode(String redactCode) {
        this.redactCode = redactCode;
    }

    public BaUnitBasic getBaUnitBasic() {
        return baUnitBasic;
    }

    public void setBaUnitBasic(BaUnitBasic baUnitBasic) {
        this.baUnitBasic = baUnitBasic;
    }
    
   
    
    @Override
    public void preSave() {
        if (this.isNew()) {
            setTransactionId(LocalInfo.getTransactionId());
        }
        super.preSave();
    }

}
