package org.sola.services.ejb.search.repository.entities;

import java.util.Date;
import javax.persistence.Column;
import org.sola.services.common.repository.CommonSqlProvider;
import org.sola.services.common.repository.entities.AbstractReadOnlyEntity;

public class ClaimSearchResult extends AbstractReadOnlyEntity {

    @Column
    private String id;
    @Column(name = "nr")
    private String nr;
    @Column(name = "lodgement_date")
    private Date lodgementDate;
    @Column(name = "challenge_expiry_date")
    private Date challengeExpiryDate;
    @Column(name = "decision_date")
    private Date decisionDate;
    @Column(name = "description")
    private String description;
    @Column(name = "challenged_claim_id")
    private String challengedClaimId;
    @Column(name = "claimant_id")
    private String claimantId;
    @Column(name = "claimant_name")
    private String claimantName;
    @Column(name = "status_code")
    private String statusCode;
    @Column(name = "status_name")
    private String statusName;

    public static final String PARAM_NAME = "claimantName";
    public static final String PARAM_USERNAME = "userName";
    public static final String PARAM_CLAIM_NUMBER = "claimNumber";
    public static final String PARAM_DESCRIPTION = "claimDescription";
    public static final String PARAM_STATUS_CODE = "statusCode";
    public static final String PARAM_DATE_FROM = "dateFrom";
    public static final String PARAM_DATE_TO = "dateTo";
    public static final String PARAM_RECORDER = "recorderName";
    public static final String PARAM_SEARCH_BY_USER = "searchByUser";
    public static final String PARAM_POINT = "pointParam";
    private static final String SELECT_PART = 
            "select c.id, c.nr, c.lodgement_date, c.challenge_expiry_date, c.decision_date, c.description, \n"
            + "c.claimant_id, (p.name || ' ' || coalesce(p.last_name, '')) as claimant_name,\n"
            + "c.challenged_claim_id, c.status_code, get_translation(cs.display_value, #{" 
            + CommonSqlProvider.PARAM_LANGUAGE_CODE + "}) as status_name\n"
            + "\n"
            + "from (opentenure.claim c inner join opentenure.claim_status cs ON c.status_code = cs.code) \n"
            + "left join opentenure.party p ON c.claimant_id = p.id\n"
            + "\n";
    
    public static final String QUERY_SEARCH_BY_POINT = SELECT_PART
            + "WHERE ST_Contains(c.mapped_geometry, ST_GeomFromText(#{" + PARAM_POINT + "}, St_SRID(c.mapped_geometry))) AND c.status_code NOT IN ('rejected','withdrawn','created')";
    
    public static final String QUERY_SEARCH_ASSIGNED_TO_USER = SELECT_PART
            + "WHERE c.assignee_name = #{" + PARAM_USERNAME + "} AND c.status_code IN ('reviewed','unmoderated') order by c.lodgement_date desc limit 100;";
    
    public static final String QUERY_SEARCH_FOR_REVIEW = SELECT_PART
            + "WHERE c.assignee_name is null AND c.status_code = 'unmoderated' and c.challenge_expiry_date <= now() order by c.lodgement_date desc limit 100;";
    
    public static final String QUERY_SEARCH_FOR_REVIEW_ALL = SELECT_PART
            + "WHERE c.status_code = 'unmoderated' and c.challenge_expiry_date <= now() order by c.lodgement_date desc limit 100;";
    
    public static final String QUERY_SEARCH_FOR_MODERATION = SELECT_PART
            + "WHERE c.assignee_name is null AND c.status_code = 'reviewed' order by c.lodgement_date desc limit 100;";
    
    public static final String QUERY_SEARCH_FOR_MODERATION_ALL = SELECT_PART
            + "WHERE c.status_code = 'reviewed' order by c.lodgement_date desc limit 100;";
    
    public static final String QUERY_SEARCH = SELECT_PART
            + "where position(lower(#{" + PARAM_NAME + "}) in lower(p.name || ' ' || COALESCE(p.last_name, ''))) > 0 and\n"
            + "position(lower(#{" + PARAM_DESCRIPTION + "}) in lower(COALESCE(c.description, ''))) > 0 and \n"
            + "position(lower(#{" + PARAM_CLAIM_NUMBER + "}) in lower(COALESCE(c.nr, ''))) > 0 and \n"
            + "(c.status_code = #{" + PARAM_STATUS_CODE + "} or #{" + PARAM_STATUS_CODE + "} = '') and \n"
            + "((c.lodgement_date between #{" + PARAM_DATE_FROM + "}::timestamp and #{" + PARAM_DATE_TO + "}::timestamp) \n"
            + "  or #{" + PARAM_DATE_FROM + "}::timestamp is null or #{" + PARAM_DATE_TO + "}::timestamp is null) and "
            + "(c.recorder_name = #{" + PARAM_RECORDER + "} or #{" + PARAM_SEARCH_BY_USER + "} = 'f') and "
            + "(c.status_code != 'created' or c.recorder_name = #{" + PARAM_RECORDER + "})"
            + "order by c.lodgement_date desc, c.nr desc limit 100;";

    public ClaimSearchResult() {
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

    public Date getLodgementDate() {
        return lodgementDate;
    }

    public void setLodgementDate(Date lodgementDate) {
        this.lodgementDate = lodgementDate;
    }

    public Date getChallengeExpiryDate() {
        return challengeExpiryDate;
    }

    public void setChallengeExpiryDate(Date challengeExpiryDate) {
        this.challengeExpiryDate = challengeExpiryDate;
    }

    public Date getDecisionDate() {
        return decisionDate;
    }

    public void setDecisionDate(Date decisionDate) {
        this.decisionDate = decisionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChallengedClaimId() {
        return challengedClaimId;
    }

    public void setChallengedClaimId(String challengedClaimId) {
        this.challengedClaimId = challengedClaimId;
    }

    public String getClaimantId() {
        return claimantId;
    }

    public void setClaimantId(String claimantId) {
        this.claimantId = claimantId;
    }

    public String getClaimantName() {
        return claimantName;
    }

    public void setClaimantName(String claimantName) {
        this.claimantName = claimantName;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
