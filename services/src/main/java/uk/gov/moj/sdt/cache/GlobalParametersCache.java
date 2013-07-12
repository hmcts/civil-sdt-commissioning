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

package uk.gov.moj.sdt.cache;

import uk.gov.moj.sdt.utils.cache.AbstractCacheControl;

/**
 * Cache bean for the Global parameters.
 * 
 * @author Manoj Kulkarni
 * 
 */
public final class GlobalParametersCache extends AbstractCacheControl
{
    @Override
    public String getValue (final String paramName)
    {
        // Should cache be discarded?
        if (this.uncacheRequired ())
        {
            this.resetCache ();
        }

        // This object should be a singleton but play safe and only let one instance at a time refresh the cache.
        synchronized (this.getCacheMap ())
        {
            if (this.getCacheMap () == null)
            {
                loadCache ();
            }
        }

        // Get the value of the named parameter.
        return this.getCacheMap ().get (paramName);
    }

    @Override
    protected void loadCache ()
    {
        // TODO: Call the Global parameters Dao and fetch the list of IGlobalParameter domain object
    }
}
