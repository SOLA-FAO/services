package org.sola.services.ejb.search.repository.entities;

import org.sola.services.common.repository.entities.AbstractReadOnlyEntity;

public class ClaimSpatialSearchParams extends AbstractReadOnlyEntity {
    String minX;
    String minY;
    String maxX;
    String maxY;
    int limit;
    
    public ClaimSpatialSearchParams(){
        super();
    }

    public String getMinX() {
        return minX;
    }

    public void setMinX(String minX) {
        this.minX = minX;
    }

    public String getMinY() {
        return minY;
    }

    public void setMinY(String minY) {
        this.minY = minY;
    }

    public String getMaxX() {
        return maxX;
    }

    public void setMaxX(String maxX) {
        this.maxX = maxX;
    }

    public String getMaxY() {
        return maxY;
    }

    public void setMaxY(String maxY) {
        this.maxY = maxY;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
