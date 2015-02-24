package org.sola.services.ejb.refdata.businesslogic;

import java.util.HashMap;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.sola.common.RolesConstants;
import org.sola.services.common.ejbs.AbstractEJB;
import org.sola.services.common.repository.CommonSqlProvider;
import org.sola.services.common.repository.entities.AbstractCodeEntity;
import org.sola.services.ejb.refdata.entities.Language;

/**
 * Implements methods to manage the claim and it's related objects
 */
@Stateless
@EJB(name = "java:app/RefDataEJBLocal", beanInterface = RefDataEJBLocal.class)
public class RefDataEJB extends AbstractEJB implements RefDataEJBLocal {

    @Override
    @RolesAllowed({RolesConstants.ADMIN_MANAGE_REFDATA})
    public <T extends AbstractCodeEntity> T saveCode(T codeEntity) {
        return getRepository().saveEntity(codeEntity);
    }

    @Override
    @RolesAllowed({RolesConstants.ADMIN_MANAGE_SETTINGS})
    public Language saveLanguage(Language lang) {
        return getRepository().saveEntity(lang);
    }
    
    @Override
    public List<Language> getLanguages(String languageCode) {
        HashMap params = new HashMap();
        if (languageCode != null) {
            params.put(CommonSqlProvider.PARAM_LANGUAGE_CODE, languageCode);
        }
        params.put(CommonSqlProvider.PARAM_ORDER_BY_PART, "item_order");
        return getRepository().getEntityList(Language.class, params);
    }

    @Override
    public <T extends AbstractCodeEntity> List<T> getCodeEntityList(Class<T> codeEntityClass, String lang) {
        HashMap<String, Object> params = new HashMap<>();
        if (lang != null) {
            params.put(CommonSqlProvider.PARAM_LANGUAGE_CODE, lang);
        }
        params.put(CommonSqlProvider.PARAM_ORDER_BY_PART, "code");
        return getRepository().getEntityList(codeEntityClass, params);
    }
    
    @Override
    public Language getLanguage(String languageCode, String locale){
        HashMap params = new HashMap();
        if (locale != null) {
            params.put(CommonSqlProvider.PARAM_LANGUAGE_CODE, locale);
        }
        params.put(CommonSqlProvider.PARAM_WHERE_PART, "code = #{idValue}");
        params.put("idValue", languageCode);
        return getRepository().getEntity(Language.class, params);
    }
}
