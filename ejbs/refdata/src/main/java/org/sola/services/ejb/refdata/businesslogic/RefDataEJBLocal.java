package org.sola.services.ejb.refdata.businesslogic;

import java.util.List;
import javax.ejb.Local;
import org.sola.services.common.ejbs.AbstractEJBLocal;
import org.sola.services.common.repository.entities.AbstractCodeEntity;
import org.sola.services.ejb.refdata.entities.Language;

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
    List<Language> getLanguages(String languageCode);
    
    /** 
     * @param languageCode Code (ID) of language to return. 
     * @param locale Language (locale) code, used to localize final result. 
     * If null is provided, full unlocalized string will be returned.
     * @return 
     */
    Language getLanguage(String languageCode, String locale);
    
    /**
     * Generic method to save Code entity
     * @param <T> Code entity class type
     * @param codeEntity Code entity object
     * @return 
     */
    <T extends AbstractCodeEntity> T saveCode(T codeEntity);
    
    @Override
    <T extends AbstractCodeEntity> List<T> getCodeEntityList(Class<T> codeEntityClass, String lang);
    
    /** 
     * Saves {@link Language} object
     * @param lang Language object to save
     * @return 
     */
    Language saveLanguage(Language lang);
}
