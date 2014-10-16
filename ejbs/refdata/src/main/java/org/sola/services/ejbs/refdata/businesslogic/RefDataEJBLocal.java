package org.sola.services.ejbs.refdata.businesslogic;

import java.util.List;
import javax.ejb.Local;
import org.sola.services.common.ejbs.AbstractEJBLocal;
import org.sola.services.common.repository.entities.AbstractCodeEntity;
import org.sola.services.ejbs.refdata.entities.Language;

/**
 * Local interface for the {@linkplain RefDataEJB}
 */
@Local
public interface RefDataEJBLocal extends AbstractEJBLocal {
    /**
     * Returns list of {@link Language}s
     * @param languageCode Language (locale) code, used to localize final result. 
     * If null is provided, full unlocalized string will be returned.
     * @return 
     */
    public List<Language> getLanguages(String languageCode);
    
    /**
     * Generic method to save Code entity
     * @param <T> Code entity class type
     * @param codeEntity Code entity object
     * @return 
     */
    public <T extends AbstractCodeEntity> T saveCode(T codeEntity);
}
