/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.ejb.transaction.repository.entities;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 *
 * @author Elton Manoku
 */
@Table(name = "transaction", schema = "transaction")
public class TransactionBulk extends TransactionBasic {
    
    @Column(name = "is_bulk_operation", updatable= false)
    private boolean isBulkOperation;

    public TransactionBulk(){
        super();
    }
    
    public boolean isIsBulkOperation() {
        return isBulkOperation;
    }

    public void setIsBulkOperation(boolean isBulkOperation) {
        this.isBulkOperation = isBulkOperation;
    }

    @Override
    public void preSave() {
        super.preSave();
        if (this.isNew()){
            setIsBulkOperation(true);
        }
    }
        
}
