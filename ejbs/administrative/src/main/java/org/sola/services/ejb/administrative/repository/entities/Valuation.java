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
import org.sola.services.common.repository.ChildEntityList;
import org.sola.services.common.repository.ExternalEJB;
import org.sola.services.common.repository.Redact;
import org.sola.services.common.repository.entities.AbstractReadOnlyEntity;
import org.sola.services.common.repository.entities.AbstractVersionedEntity;
import org.sola.services.ejb.source.businesslogic.SourceEJBLocal;
import org.sola.services.ejb.source.repository.entities.Source;

/**
 * Entity representing the administrative.ba_unit_type code table.
 *
 * @author soladev
 */
@Table(name = "valuation", schema = "administrative")
public class Valuation extends AbstractVersionedEntity {

    @Id
    @Column(name = "id")
    String id;
    @Column(name = "nr")
    String nr;
    @Column(name = "ba_unit_id")
    String baUnitId;
    @Redact(minClassification = RolesConstants.CLASSIFICATION_CONFIDENTIAL)
    @Column(name = "amount")
    BigDecimal amount;
    @Column(name = "valuation_date")
    Date valuationDate;
    @Column(name = "type_code")
    String typeCode;
    @ExternalEJB(ejbLocalClass = SourceEJBLocal.class,
            loadMethod = "getSources", saveMethod = "saveSource")
    @ChildEntityList(parentIdField = "valuationId", childIdField = "sourceId",
            manyToManyClass = SourceDescribesRrr.class)
    private List<Source> sourceList;
    String description;
    @Column(name = "transaction_id", updatable = false)
    String transcationId;
    @Column(name = AbstractReadOnlyEntity.CLASSIFICATION_CODE_COLUMN_NAME)
    String classificationCode;
    @Column(name = AbstractReadOnlyEntity.REDACT_CODE_COLUMN_NAME)
    String redactCode;

    public Valuation() {
        super();
    }
}
