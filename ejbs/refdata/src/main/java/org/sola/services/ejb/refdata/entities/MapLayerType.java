/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sola.services.ejb.refdata.entities;

import javax.persistence.Table;
import org.sola.services.common.repository.DefaultSorter;
import org.sola.services.common.repository.entities.AbstractCodeEntity;

/**
 * Entity representing the system.config_map_layer_type code table.
 */
@Table(name = "config_map_layer_type", schema = "system")
@DefaultSorter(sortString="display_value")
public class MapLayerType extends AbstractCodeEntity {
    public MapLayerType(){
        super();
    }
}
