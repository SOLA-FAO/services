/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.ejb.search.spatial;

/**
 * A query that is used for navigation purposes for layers of type
 * pojo_public_display
 * 
 * @author Elton Manoku
 */
public class QueryForPublicDisplayMap extends QueryForNavigation {
    private String nameLastPart;

    public String getNameLastPart() {
        return nameLastPart;
    }

    public void setNameLastPart(String nameLastPart) {
        this.nameLastPart = nameLastPart;
    }
    
}
