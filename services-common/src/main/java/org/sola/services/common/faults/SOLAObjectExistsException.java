package org.sola.services.common.faults;

import org.sola.common.SOLAException;

/**
 * Used to inform that object already exists in the database.
 */
public class SOLAObjectExistsException extends SOLAException {
    public SOLAObjectExistsException(String messageCode) {
        super(messageCode);
    }
    
    public SOLAObjectExistsException(String messageCode, Object[] messageParameters) { //NOSONAR
        super(messageCode, messageParameters);
    }

    public SOLAObjectExistsException(String messageCode, Throwable cause) {
        super(messageCode, cause);
    }

    public SOLAObjectExistsException(String messageCode, Object[] messageParameters, Throwable cause) { //NOSONAR
        super(messageCode, messageParameters, cause);
    }
}
