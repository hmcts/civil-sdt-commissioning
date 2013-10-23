/* Copyrights and Licenses
 * 
 * Copyright (c) 2013 by the Ministry of Justice. All rights reserved.
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
 * $Id$
 * $LastChangedRevision$
 * $LastChangedDate$
 * $LastChangedBy$ */
package uk.gov.moj.sdt.enricher;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;

import uk.gov.moj.sdt.utils.SdtContext;

/**
 * Tests for {@link SubmitQueryEnricher}.
 * 
 * @author d276205
 * 
 */
public class GenericEnricherTest
{
    /**
     * Logger.
     */
    private static final Log LOGGER = LogFactory.getLog (GenericEnricherTest.class);

    /**
     * Subject for test.
     */
    private GenericEnricher enricher;

    /**
     * Setup.
     */
    @Before
    public void setUp ()
    {
        enricher = new GenericEnricher ();
    }

    /**
     * Test submit query enrichment.
     */
    @Test
    public void testSubmitQueryEnrichmentSuccess ()
    {
        LOGGER.debug ("test for subimt query response enrichment");

        enricher.setInsertionTag ("results");
        enricher.setParentTag ("submitQueryResponse");
        
        SdtContext.getContext ().setRawOutXml ("<record></record>");
        final String result = enricher.enrichXml ("<ns1:submitQueryResponse><ns2:results/></ns1:submitQueryResponse>");
        final String expected = "<ns1:submitQueryResponse><ns2:results>" +
        		"<record></record></ns2:results></ns1:submitQueryResponse>";
        Assert.assertEquals(expected, result);

    }
    
    /**
     * Test individual request enrichment.
     */
    @Test
    //CHECKSTYLE:OFF
    public void testIndRequestEnrichment(){
        enricher.setInsertionTag ("targetAppDetail");
        enricher.setParentTag ("individualRequest");
        

        SdtContext.getContext ().setRawOutXml ("<claim></claim>");
        
        final String requestHeader = "<ind:header><ind:sdtRequestId>?</ind:sdtRequestId><ind:targetAppCustomerId>?</ind:targetAppCustomerId><ind:requestType>?</ind:requestType></ind:header>";
        final String result = enricher.enrichXml ("<ns1:individualRequest>"+
         requestHeader+"<ind:targetAppDetail/></ns1:individualRequest>");
        final String expected = "<ns1:individualRequest>" + requestHeader + "<ind:targetAppDetail>" +
                "<claim></claim></ind:targetAppDetail></ns1:individualRequest>";
        Assert.assertEquals(expected, result);
    }
    //CHECKSTYLE:ON
    
    /**
     * Test Submit query parsing.
     */
    @Test
    //CHECKSTYLE:OFF
    public void testSubmitQueryParse(){
        enricher.setInsertionTag ("criterion");
        enricher.setParentTag ("individualRequest");
        

        SdtContext.getContext ().setRawInXml ("<sub:criteria><sub:criterion criteriaType=\"mcolDefenceCriteria\"><sub:mcolDefenceCriteria><quer:fromDate>2013-08-01</quer:fromDate><quer:toDate>2013-08-31</quer:toDate></sub:mcolDefenceCriteria></sub:criterion></sub:criteria>");
        
        final String result = enricher.parse ();
        final String expected = "<sub:mcolDefenceCriteria><quer:fromDate>2013-08-01</quer:fromDate><quer:toDate>2013-08-31</quer:toDate></sub:mcolDefenceCriteria>";
        Assert.assertEquals(expected, result);
    }
    //CHECKSTYLE:ON
}
