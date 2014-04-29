package org.sola.services.common.faults;

import java.util.List;
import org.sola.common.SOLAException;

/**
 * OpenTenure exception of missing attachments on the claim
 */
public class OTMissingAttachmentsException extends SOLAException {
    List<String> attachments;

    public List<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<String> attachments) {
        this.attachments = attachments;
    }
    
    public OTMissingAttachmentsException(String messageCode, List<String> attachments) {
        super(messageCode);
        this.attachments = attachments;
    }
    
    public OTMissingAttachmentsException(String messageCode, Throwable cause) {
        super(messageCode, cause);
    }
}