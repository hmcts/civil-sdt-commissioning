/* Copyrights and Licenses
 *
 * Copyright (c) 2012-2014 by the Ministry of Justice. All rights reserved.
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met:
 * - Redistributions of source code must retain the above copyright notice, this list of conditions
 * and the following disclaimer.
 * - Redistributions in binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other materials
 * provided with the distribution.
 * - All advertising materials mentioning features or use of this software must display the
 * following acknowledgment: "This product includes Money Claims OnLine."
 * - Products derived from this software may not be called "Money Claims OnLine" nor may
 * "Money Claims OnLine" appear in their names without prior written permission of the
 * Ministry of Justice.
 * - Redistributions of any form whatsoever must retain the following acknowledgment: "This
 * product includes Money Claims OnLine."
 * This software is provided "as is" and any expressed or implied warranties, including, but
 * not limited to, the implied warranties of merchantability and fitness for a particular purpose are
 * disclaimed. In no event shall the Ministry of Justice or its contributors be liable for any
 * direct, indirect, incidental, special, exemplary, or consequential damages (including, but
 * not limited to, procurement of substitute goods or services; loss of use, data, or profits;
 * or business interruption). However caused any on any theory of liability, whether in contract,
 * strict liability, or tort (including negligence or otherwise) arising in any way out of the use of this
 * software, even if advised of the possibility of such damage.
 *
 * $Id: $
 * $LastChangedRevision: $
 * $LastChangedDate: $
 * $LastChangedBy: $ */
package uk.gov.moj.sdt.producers.comx.cache;

import org.springframework.stereotype.Component;
import uk.gov.moj.sdt.domain.GlobalParameter;
import uk.gov.moj.sdt.domain.api.IDomainObject;
import uk.gov.moj.sdt.domain.api.IGlobalParameter;
import uk.gov.moj.sdt.domain.cache.api.ICacheable;
import uk.gov.moj.sdt.services.cache.api.IGlobalParametersCache;

/**
 * Mock implementation of the GlobalParametersCache.
 *
 * @author d130680
 *
 */
@Component("GlobalParametersCache")
public class MockGlobalParametersCache implements ICacheable, IGlobalParametersCache
{

    @SuppressWarnings ("unchecked")
    @Override
    public <D extends IDomainObject> D
            getValue (final Class<D> domainType, final String key)
    {

        if (IGlobalParameter.ParameterKey.DATA_RETENTION_PERIOD.name ().equals (key))
        {
            final IGlobalParameter globalParameter = new GlobalParameter ();
            globalParameter.setName (IGlobalParameter.ParameterKey.DATA_RETENTION_PERIOD.name ());
            globalParameter.setValue ("90");

            return (D) globalParameter;
        }
        if (IGlobalParameter.ParameterKey.CONTACT_DETAILS.name ().equals (key))
        {
            final IGlobalParameter globalParameter = new GlobalParameter ();
            globalParameter.setName (IGlobalParameter.ParameterKey.CONTACT_DETAILS.name ());
            globalParameter.setValue ("TBD");

            return (D) globalParameter;
        }

        return null;
    }

}
