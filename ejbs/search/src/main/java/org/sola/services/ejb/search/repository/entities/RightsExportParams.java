package org.sola.services.ejb.search.repository.entities;

import java.util.Date;
import org.sola.services.common.repository.entities.AbstractEntity;

public class RightsExportParams extends AbstractEntity{
    
    private Date dateFrom;
    private Date dateTo;
    private String rightTypeCode;
    
    public RightsExportParams(){
        super();
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getRightTypeCode() {
        return rightTypeCode;
    }

    public void setRightTypeCode(String rightTypeCode) {
        this.rightTypeCode = rightTypeCode;
    }
}
