package org.sola.services.ejb.search.repository.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import org.sola.services.common.repository.AccessFunctions;
import org.sola.services.common.repository.entities.AbstractReadOnlyEntity;
import static org.sola.services.ejb.search.repository.entities.ClaimSearchResult.PARAM_RECORDER;

@Table(name = "claim", schema = "opentenure")
public class ClaimSpatialSearchResult extends AbstractReadOnlyEntity {

    @Id
    @Column
    private String id;
    @Column(name = "nr")
    private String nr;
    @Column(name = "mapped_geometry")
    @AccessFunctions(onSelect = "ST_AsText(mapped_geometry)")
    private String mappedGeometry;
    @Column(name = "gps_geometry")
    @AccessFunctions(onSelect = "ST_AsText(gps_geometry)")
    private String gpsGeometry;
    @Column(name = "status_code")
    private String statusCode;
    @Column(name="rowversion")
    private int version;
    
    public static final String PARAM_ENVELOPE = "paramEnvelope";
    public static final String ENVELOPE = "LINESTRING(%s %s, %s %s)";
    
    public static final String WHERE_SEARCH_BY_BOX
            = "mapped_geometry is not null and "
            + "ST_Intersects(mapped_geometry, ST_Envelope(st_geomfromtext(#{" 
            + PARAM_ENVELOPE + "}, ST_Srid(mapped_geometry)))) and "
            + "(status_code != 'created' or recorder_name = #{" + PARAM_RECORDER + "})";
    
    public static final String WHERE_SEARCH_ALL
            = "mapped_geometry is not null and "
            + "(status_code != 'created' or recorder_name = #{" + PARAM_RECORDER + "})";

    public ClaimSpatialSearchResult() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getMappedGeometry() {
        return mappedGeometry;
    }

    public void setMappedGeometry(String mappedGeometry) {
        this.mappedGeometry = mappedGeometry;
    }

    public String getGpsGeometry() {
        return gpsGeometry;
    }

    public void setGpsGeometry(String gpsGeometry) {
        this.gpsGeometry = gpsGeometry;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
