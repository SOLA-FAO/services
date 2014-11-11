package org.sola.services.ejb.system.repository.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import org.sola.services.common.repository.DefaultSorter;
import org.sola.services.common.repository.entities.AbstractEntity;

@Table(name = "email", schema = "system")
@DefaultSorter(sortString = "time_to_send")
public class EmailTask extends AbstractEntity {

    @Id
    @Column
    String id;

    @Column
    String recipient;
    
    @Column(name="recipient_name")
    String recipientName;
    
    @Column
    String cc;
    
    @Column
    String bcc;
    
    @Column
    String subject;
    
    @Column
    String body;
    
    @Column
    byte[] attachment;
    
    @Column(name = "attachment_name")
    String attachmentName;
    
    @Column(name="attachment_mime_type")
    String attachmentMimeType;
    
    @Column(name = "time_to_send")
    Date timeToSend;
    
    @Column
    int attempt;
    
    @Column()
    String error;
    
    public static final String WHERE_BY_NOW = "time_to_send <= now()";
            
    public EmailTask() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getAttachmentMimeType() {
        return attachmentMimeType;
    }

    public void setAttachmentMimeType(String attachmentMimeType) {
        this.attachmentMimeType = attachmentMimeType;
    }

    public Date getTimeToSend() {
        return timeToSend;
    }

    public void setTimeToSend(Date timeToSend) {
        this.timeToSend = timeToSend;
    }

    public int getAttempt() {
        return attempt;
    }

    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
