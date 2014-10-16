package org.sola.services.ejbs.refdata.businesslogic;

import java.util.HashMap;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.sola.common.RolesConstants;
import org.sola.services.common.ejbs.AbstractEJB;
import org.sola.services.common.repository.CommonSqlProvider;
import org.sola.services.common.repository.entities.AbstractCodeEntity;
import org.sola.services.ejbs.refdata.entities.Language;

/**
 * Implements methods to manage the claim and it's related objects
 */
@Stateless
@EJB(name = "java:global/SOLA/RefDataEJBLocal", beanInterface = RefDataEJBLocal.class)
public class RefDataEJB extends AbstractEJB implements RefDataEJBLocal {

    @Override
    @RolesAllowed({RolesConstants.ADMIN_MANAGE_REFDATA})
    public <T extends AbstractCodeEntity> T saveCode(T codeEntity) {
        return getRepository().saveEntity(codeEntity);
    }

    @Override
    public List<Language> getLanguages(String languageCode) {
        HashMap params = new HashMap();
        if (languageCode != null) {
            params.put(CommonSqlProvider.PARAM_LANGUAGE_CODE, languageCode);
        }
        return getRepository().getEntityList(Language.class, params);
    }
    
}
