/* Copyrights and Licenses
 * 
 * Copyright (c) 2012-2013 by the Ministry of Justice. All rights reserved.
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
package uk.gov.moj.sdt.utils.parsing;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This is a utility class for dealing with namespaces for xml fragments.
 * 
 * @author D303894
 * 
 */
public final class XmlNamespaceUtils
{
    /**
     * Logger instance.
     */
    private static final Log LOGGER = LogFactory.getLog (XmlNamespaceUtils.class);

    /**
     * No-args constructor.
     */
    private XmlNamespaceUtils ()
    {

    }

    /**
     * Combine a number of namespace maps into a single namespace map without duplicates.
     * 
     * @param namespaceMap an individual namespace map which should be combined with any other individual namespace
     *            maps.
     * @param combinedNamespaces combined namespace map
     * @return map of individual namespace maps combined so far.
     */
    public static Map<String, String> combineNamespaces (final Map<String, String> namespaceMap,
                                                         final Map<String, String> combinedNamespaces)
    {
        final Set<String> keys = namespaceMap.keySet ();
        final Iterator<String> iter = keys.iterator ();
        while (iter.hasNext ())
        {
            final String key = iter.next ();

            // Check if we have already combined this one.
            if ( !combinedNamespaces.containsKey (key))
            {
                // Add to combined set of namespaces.
                combinedNamespaces.put (key, namespaceMap.get (key));
            }
        }

        return combinedNamespaces;
    }

    /**
     * Extract namespaces out of a piece of raw XML.
     * 
     * @param rawXml raw xml.
     * @param replacementNamespaces map containing replacement namespaces.
     * @return map containing extracted namespaces
     */
    public static Map<String, String> extractAllNamespaces (final String rawXml,
                                                            final Map<String, String> replacementNamespaces)
    {
        LOGGER.debug ("Raw xml is " + rawXml);

        final Map<String, String> namespaces = new HashMap<String, String> ();

        // Build a search pattern to find all namespaces.
        final Pattern pattern = Pattern.compile ("xmlns:(.*?)=\".*?\"");

        final Matcher matcher = pattern.matcher (rawXml);

        while (matcher.find ())
        {
            // Capture the raw XML associated with this request.
            final String namespaceKey = matcher.group (1);
            String namespaceValue = matcher.group (0);

            if (replacementNamespaces != null)
            {
                // Replace namespace value if mapping found in replacement namespaces map.
                final Set<String> keys = replacementNamespaces.keySet ();
                for (String key : keys)
                {
                    if (namespaceValue.contains (key))
                    {
                        namespaceValue = "xmlns:" + namespaceKey + "=\"" + replacementNamespaces.get (key) + "\"";
                    }
                }
            }

            LOGGER.debug ("Namespace key[" + namespaceKey + "], value[" + namespaceValue + "]");

            namespaces.put (namespaceKey, namespaceValue);
        }

        return namespaces;
    }

    /**
     * Find map of applicable namespaces for given xml fragment based on namespaces for entire xml.
     * 
     * @param xmlFragment fragment of xml to which namespaces should apply.
     * @param allNamespaces namespaces for entire xml
     * @return map of applicable namespaces.
     */
    public static Map<String, String> findMatchingNamespaces (final String xmlFragment,
                                                              final Map<String, String> allNamespaces)
    {
        LOGGER.debug ("Raw xml fragment is " + xmlFragment);

        // Clear out previous namespaces.
        final Map<String, String> matchingNamespaces = new HashMap<String, String> ();

        // Build a search pattern to find all start tag namespaces used in this xml fragment.
        final Pattern pattern = Pattern.compile ("<([a-zA-Z0-9]+?):");

        final Matcher matcher = pattern.matcher (xmlFragment);

        while (matcher.find ())
        {
            // Capture the raw XML associated with this request.
            final String namespaceKey = matcher.group (1);

            // Form the replacement string from the matched groups and the extra XML.
            LOGGER.debug ("Matching namespace[" + namespaceKey + "]");

            if ( !matchingNamespaces.containsKey (namespaceKey))
            {
                // Make sure this namespace has been defined.
                if ( !allNamespaces.containsKey (namespaceKey))
                {
                    throw new RuntimeException ("Namespace [" + namespaceKey + "] missing from incoming raw xml[" +
                            xmlFragment + "]");
                }

                // Copy namespace to the set of matching namespaces for this fragment.
                matchingNamespaces.put (namespaceKey, allNamespaces.get (namespaceKey));
            }
        }

        return matchingNamespaces;
    }

    /**
     * Adds namespaces to xml fragment.
     * 
     * @param xmlFragment xml to be decorated.
     * @param namespaces namespaces to be added.
     * @return xml with namespace
     */
    public static String addNamespaces (final String xmlFragment, final Map<String, String> namespaces)
    {
        final StringBuilder result = new StringBuilder ();

        // Locate closing bracket for first element.
        final int index = xmlFragment.indexOf ('>');

        result.append (xmlFragment.substring (0, index));

        // Add namespace details to first element
        for (String value : namespaces.values ())
        {
            result.append (" ");
            result.append (value);
        }

        result.append (xmlFragment.substring (index));

        return result.toString ();

    }
}
