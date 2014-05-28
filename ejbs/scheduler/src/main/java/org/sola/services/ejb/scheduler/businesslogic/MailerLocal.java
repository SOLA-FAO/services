package org.sola.services.ejb.scheduler.businesslogic;

import javax.ejb.Local;

@Local
public interface MailerLocal {
    void init();
}
