/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.ejb.cadastre.repository.entities;

import java.io.Serializable;

/**
 *
 * @author Elton Mmanoku
 */

public class NewCadastreObjectIdentifier implements Serializable {
   private String firstPart;
   private String lastPart;

    public String getFirstPart() {
        return firstPart;
    }

    public void setFirstPart(String firstPart) {
        this.firstPart = firstPart;
    }

    public String getLastPart() {
        return lastPart;
    }

    public void setLastPart(String lastPart) {
        this.lastPart = lastPart;
    }
   
}
