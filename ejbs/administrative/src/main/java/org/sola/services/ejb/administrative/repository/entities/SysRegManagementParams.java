/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.ejb.administrative.repository.entities;

/**
 *
 * @author RizzoM
 */
import java.util.Date;
import org.sola.services.common.repository.entities.AbstractReadOnlyEntity;

public class SysRegManagementParams extends AbstractReadOnlyEntity {

    private Date fromDate;
    private Date toDate;
    private String nameLastpart;

    public SysRegManagementParams() {
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getNameLastpart() {
        return nameLastpart;
    }

    public void setNameLastpart(String nameLastpart) {
        this.nameLastpart = nameLastpart;
    }
}
