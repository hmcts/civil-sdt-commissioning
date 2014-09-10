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

package uk.gov.moj.sdt.utils.parser;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import uk.gov.moj.sdt.utils.SdtUnitTestBase;
import uk.gov.moj.sdt.utils.parsing.XmlNamespaceUtils;

/**
 * A base test class that compares XML file output.
 * 
 * @author Robin Compston
 */
public class XmlNamespaceUtilsTest extends SdtUnitTestBase
{
    /**
     * Constructs a new {@link XmlNamespaceUtilsTest}.
     * 
     * @param testName name of this test.
     */
    public XmlNamespaceUtilsTest (final String testName)
    {
        super (testName);
    }

    /**
     * Test the extraction of single namespace values.
     */
    @Test
    public void testSingleNamespace ()
    {
        // Define text raw xml.
        final String xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +

                "<beans xmlns=\"http://www.springframework.org/schema/beans\""
                        + "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
                        + "    xsi:schemaLocation=\"http://www.springframework.org/schema/beans"
                        + "       http://www.springframework.org/schema/beans/spring-beans-3.1.xsd"
                        + "       http://www.springframework.org/schema/aop"
                        + "       http://www.springframework.org/schema/aop/spring-aop-2.5.xsd\">" +

                        "   <!-- Note all ids should be based on fully qualified names (interfaces where"
                        + "       this is not ambiguous) and all classes should have an interface. -->" +

                        "   <bean id=\"uk.gov.moj.sdt.utils.mbeans.api.ISdtMetricsMBean\" "
                        + "class=\"uk.gov.moj.sdt.utils.mbeans.SdtMetricsMBean\" />" +

                        "</beans>";

        final Map<String, String> map = XmlNamespaceUtils.extractAllNamespaces (xml, null);

        Assert.assertEquals ("Missing namespace", "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"",
                map.get ("xsi"));
        Assert.assertEquals ("Missing namespace", "xmlns=\"http://www.springframework.org/schema/beans\"",
                map.get (XmlNamespaceUtils.DEFAULT_NAMESPACE));
        Assert.assertEquals ("Incorrect number of namespaces", 2, map.size ());
    }

    /**
     * Test the extraction of single namespace values with embedded comments in XML.
     */
    @Test
    public void testComments ()
    {
        // Define text raw xml.
        final String xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +

                "<beans xmlns=\"http://www.springframework.org/schema/beans\""
                        + "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
                        + "    xsi:schemaLocation=\"http://www.springframework.org/schema/beans"
                        + "       http://www.springframework.org/schema/beans/spring-beans-3.1.xsd"
                        + "       http://www.springframework.org/schema/aop"
                        + "       http://www.springframework.org/schema/aop/spring-aop-2.5.xsd\">" +

                        "   <!-- Note all ids should be based on fully qualified names (interfaces where"
                        + "       this is not ambiguous) and all classes should have an interface. -->" +

                        "   <bean id=\"uk.gov.moj.sdt.utils.mbeans.api.ISdtMetricsMBean\" "
                        + "class=\"uk.gov.moj.sdt.utils.mbeans.SdtMetricsMBean\" />" +

                        "</beans>";

        final Map<String, String> map = XmlNamespaceUtils.extractAllNamespaces (xml, null);

        Assert.assertEquals ("Missing namespace", "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"",
                map.get ("xsi"));
        Assert.assertEquals ("Missing namespace", "xmlns=\"http://www.springframework.org/schema/beans\"",
                map.get (XmlNamespaceUtils.DEFAULT_NAMESPACE));
        Assert.assertEquals ("Incorrect number of namespaces", 2, map.size ());
    }

    /**
     * Test the extraction of multiple namespace values.
     */
    @Test
    public void testMultipleNamespace ()
    {
        // Define text raw xml.
        final String xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +

                "<beans xmlns=\"http://www.springframework.org/schema/beans\""
                        + "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
                        + "xmlns:aop=\"http://www.springframework.org/schema/aop\""
                        + "    xsi:schemaLocation=\"http://www.springframework.org/schema/beans"
                        + "       http://www.springframework.org/schema/beans/spring-beans-3.1.xsd"
                        + "       http://www.springframework.org/schema/aop"
                        + "       http://www.springframework.org/schema/aop/spring-aop-2.5.xsd\">" +

                        "   <!-- Note all ids should be based on fully qualified names (interfaces where"
                        + "       this is not ambiguous) and all classes should have an interface. -->" +

                        "   <bean id=\"uk.gov.moj.sdt.utils.mbeans.api.ISdtMetricsMBean\" "
                        + "class=\"uk.gov.moj.sdt.utils.mbeans.SdtMetricsMBean\" />" +

                        "</beans>";

        final Map<String, String> map = XmlNamespaceUtils.extractAllNamespaces (xml, null);

        Assert.assertEquals ("Missing namespace", "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"",
                map.get ("xsi"));
        Assert.assertEquals ("Missing namespace", "xmlns:aop=\"http://www.springframework.org/schema/aop\"",
                map.get ("aop"));
        Assert.assertEquals ("Missing namespace", "xmlns=\"http://www.springframework.org/schema/beans\"",
                map.get (XmlNamespaceUtils.DEFAULT_NAMESPACE));
        Assert.assertEquals ("Incorrect number of namespaces", 3, map.size ());
    }

    /**
     * Test the application of a single namespace to xml fragment.
     */
    @Test
    public void testMatchingSingleNamespace ()
    {
        // Define text raw xml.
        final String xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +

                "<beans xmlns=\"http://www.springframework.org/schema/beans\""
                        + "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
                        + "xmlns:aop=\"http://www.springframework.org/schema/aop\""
                        + "    xsi:schemaLocation=\"http://www.springframework.org/schema/beans"
                        + "       http://www.springframework.org/schema/beans/spring-beans-3.1.xsd"
                        + "       http://www.springframework.org/schema/aop"
                        + "       http://www.springframework.org/schema/aop/spring-aop-2.5.xsd\">" +

                        "   <!-- Note all ids should be based on fully qualified names (interfaces where"
                        + "       this is not ambiguous) and all classes should have an interface. -->" +

                        "   <bean id=\"uk.gov.moj.sdt.utils.mbeans.api.ISdtMetricsMBean\" "
                        + "class=\"uk.gov.moj.sdt.utils.mbeans.SdtMetricsMBean\" />" +

                        "   <xsi:some-tag some-attribute=\"some value\">"
                        + "       <xsi:some-other-tag some-attribute=\"some value\"/>"
                        + "       <xsi:some-other-tag some-attribute=\"some value\">" + "       </xsi:some-other-tag>"
                        + "   </xsi:some-tag>" + "</beans>";

        final Map<String, String> allNamespaces = XmlNamespaceUtils.extractAllNamespaces (xml, null);

        final Map<String, String> map =
                XmlNamespaceUtils.findMatchingNamespaces ("   <xsi:some-tag some-attribute=\"some value\">"
                        + "       <xsi:some-other-tag some-attribute=\"some value\"/>"
                        + "       <xsi:some-other-tag some-attribute=\"some value\">" + "       </xsi:some-other-tag>"
                        + "   </xsi:some-tag>", allNamespaces);

        Assert.assertEquals ("Missing namespace", "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"",
                map.get ("xsi"));
        Assert.assertEquals ("Missing namespace", "xmlns=\"http://www.springframework.org/schema/beans\"",
                map.get (XmlNamespaceUtils.DEFAULT_NAMESPACE));
        Assert.assertEquals ("Incorrect number of namespaces matching fragment", 2, map.size ());
    }

    /**
     * Test the application of multiple namespaces to xml fragment.
     */
    @Test
    public void testMatchingMultipleNamespace ()
    {
        // Define text raw xml.
        final String xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +

                "<beans xmlns=\"http://www.springframework.org/schema/beans\""
                        + "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
                        + "xmlns:aop=\"http://www.springframework.org/schema/aop\""
                        + "xmlns:abc=\"http://www.springframework.org/schema/abc\""
                        + "    xsi:schemaLocation=\"http://www.springframework.org/schema/beans"
                        + "       http://www.springframework.org/schema/beans/spring-beans-3.1.xsd"
                        + "       http://www.springframework.org/schema/aop"
                        + "       http://www.springframework.org/schema/aop/spring-aop-2.5.xsd\">"
                        + "       http://www.springframework.org/schema/abc"
                        + "       http://www.springframework.org/schema/abc/spring-abc-2.5.xsd\">"

                        + "   <!-- Note all ids should be based on fully qualified names (interfaces where"
                        + "       this is not ambiguous) and all classes should have an interface. -->" +

                        "   <bean id=\"uk.gov.moj.sdt.utils.mbeans.api.ISdtMetricsMBean\" "
                        + "class=\"uk.gov.moj.sdt.utils.mbeans.SdtMetricsMBean\" />" +

                        "   <xsi:some-tag some-attribute=\"some value\">"
                        + "       <aop:some-other-tag some-attribute=\"some value\"/>"
                        + "       <aop:some-other-tag some-attribute=\"some value\">" + "       </aop:some-other-tag>"
                        + "   </xsi:some-tag>" + "</beans>";

        final Map<String, String> allNamespaces = XmlNamespaceUtils.extractAllNamespaces (xml, null);

        final Map<String, String> map =
                XmlNamespaceUtils.findMatchingNamespaces ("   <xsi:some-tag some-attribute=\"some value\">"
                        + "       <aop:some-other-tag some-attribute=\"some value\"/>"
                        + "       <aop:some-other-tag some-attribute=\"some value\">" + "       </aop:some-other-tag>"
                        + "   </xsi:some-tag>", allNamespaces);

        Assert.assertEquals ("Missing fragment namespace", "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"",
                map.get ("xsi"));
        Assert.assertEquals ("Missing fragment namespace", "xmlns:aop=\"http://www.springframework.org/schema/aop\"",
                map.get ("aop"));
        Assert.assertEquals ("Missing namespace", "xmlns=\"http://www.springframework.org/schema/beans\"",
                map.get (XmlNamespaceUtils.DEFAULT_NAMESPACE));
        Assert.assertEquals ("Incorrect number of namespaces matching fragment", 3, map.size ());
    }

    /**
     * Test the application of multiple namespaces to xml fragment with comments containing colons which look like
     * namespaces.
     */
    @Test
    public void testCommentsWithColons ()
    {
        // Define text raw xml.
        final String xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +

                "<beans xmlns=\"http://www.springframework.org/schema/beans\""
                        + "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
                        + "xmlns:aop=\"http://www.springframework.org/schema/aop\""
                        + "xmlns:abc=\"http://www.springframework.org/schema/abc\""
                        + "    xsi:schemaLocation=\"http://www.springframework.org/schema/beans"
                        + "       http://www.springframework.org/schema/beans/spring-beans-3.1.xsd"
                        + "       http://www.springframework.org/schema/aop"
                        + "       http://www.springframework.org/schema/aop/spring-aop-2.5.xsd\">"
                        + "       http://www.springframework.org/schema/abc"
                        + "       http://www.springframework.org/schema/abc/spring-abc-2.5.xsd\">"

                        + "   <!-- Note all ids should be based on fully qualified names (interfaces where"
                        + "       this is not ambiguous) and all classes should have an interface. -->" +

                        "   <bean id=\"uk.gov.moj.sdt.utils.mbeans.api.ISdtMetricsMBean\" "
                        + "class=\"uk.gov.moj.sdt.utils.mbeans.SdtMetricsMBean\" />" +

                        "   <xsi:some-tag some-attribute=\"some value\">"
                        + "       <aop:some-other-tag some-attribute=\"some value\"/>"
                        + "       <aop:some-other-tag some-attribute=\"some value\">" + "       </aop:some-other-tag>"
                        + "   </xsi:some-tag>" + "</beans>";

        final Map<String, String> allNamespaces = XmlNamespaceUtils.extractAllNamespaces (xml, null);

        final Map<String, String> map =
                XmlNamespaceUtils.findMatchingNamespaces ("   <xsi:some-tag some-attribute=\"some value\">"
                        + "       <!--Optional:--><aop:some-other-tag some-attribute=\"some value\"/>"
                        + "<!--Optional:--><aop:some-other-tag some-attribute=\"some value\">"
                        + "       </aop:some-other-tag>" + "   </xsi:some-tag>", allNamespaces);

        Assert.assertEquals ("Missing fragment namespace", "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"",
                map.get ("xsi"));
        Assert.assertEquals ("Missing fragment namespace", "xmlns:aop=\"http://www.springframework.org/schema/aop\"",
                map.get ("aop"));
        Assert.assertEquals ("Missing namespace", "xmlns=\"http://www.springframework.org/schema/beans\"",
                map.get (XmlNamespaceUtils.DEFAULT_NAMESPACE));
        Assert.assertEquals ("Incorrect number of namespaces matching fragment", 3, map.size ());
    }

    /**
     * Test the reporting of a missing namespace.
     */
    @Test
    public void testMissingNamespace ()
    {
        // Define text raw xml.
        final String xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +

                "<beans xmlns=\"http://www.springframework.org/schema/beans\""
                        + "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
                        + "    xsi:schemaLocation=\"http://www.springframework.org/schema/beans"
                        + "       http://www.springframework.org/schema/beans/spring-beans-3.1.xsd"
                        + "       http://www.springframework.org/schema/aop"
                        + "       http://www.springframework.org/schema/aop/spring-aop-2.5.xsd\">" +

                        "   <!-- Note all ids should be based on fully qualified names (interfaces where"
                        + "       this is not ambiguous) and all classes should have an interface. -->" +

                        "   <bean id=\"uk.gov.moj.sdt.utils.mbeans.api.ISdtMetricsMBean\" "
                        + "class=\"uk.gov.moj.sdt.utils.mbeans.SdtMetricsMBean\" />" +

                        "   <xsi:some-tag some-attribute=\"some value\">"
                        + "       <aop:some-other-tag some-attribute=\"some value\"/>"
                        + "       <aop:some-other-tag some-attribute=\"some value\">" + "       </aop:some-other-tag>"
                        + "   </xsi:some-tag>" + "</beans>";

        final Map<String, String> allNamespaces = XmlNamespaceUtils.extractAllNamespaces (xml, null);

        try
        {
            @SuppressWarnings ("unused") final Map<String, String> map =
                    XmlNamespaceUtils.findMatchingNamespaces ("   <xsi:some-tag some-attribute=\"some value\">"
                            + "       <aop:some-other-tag some-attribute=\"some value\"/>"
                            + "       <aop:some-other-tag some-attribute=\"some value\">"
                            + "       </aop:some-other-tag>" + "   </xsi:some-tag>", allNamespaces);

            Assert.fail ("Failed to throw expected RuntimeException due to missing tag namespace.");
        }
        catch (final RuntimeException e)
        {
            Assert.assertEquals ("Unrecognised exception message:",
                    e.getMessage (),
                    // CHECKSTYLE:OFF
                    "Namespace [aop] missing from incoming raw xml[   <xsi:some-tag some-attribute=\"some value\">       <aop:some-other-tag some-attribute=\"some value\"/>       <aop:some-other-tag some-attribute=\"some value\">       </aop:some-other-tag>   </xsi:some-tag>]");
            // CHECKSTYLE:ON
        }
    }

    /**
     * Test the combining of individual namespaces.
     */
    @Test
    public void testCombinedNamespace ()
    {
        final Map<String, String> map1 = new HashMap<String, String> ();
        final Map<String, String> map2 = new HashMap<String, String> ();

        // Setup individual maps.
        map1.put ("xsi", "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
        map2.put ("xsi", "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
        map2.put ("aop", "xmlns:aop=\"http://www.springframework.org/schema/aop\"");

        Map<String, String> combinedMap = new HashMap<String, String> ();
        // Combine together the two maps.
        combinedMap = XmlNamespaceUtils.combineNamespaces (map1, combinedMap);
        combinedMap = XmlNamespaceUtils.combineNamespaces (map2, combinedMap);

        Assert.assertEquals ("Missing combined namespace", "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"",
                combinedMap.get ("xsi"));
        Assert.assertEquals ("Missing combined namespace", "xmlns:aop=\"http://www.springframework.org/schema/aop\"",
                combinedMap.get ("aop"));
        Assert.assertEquals ("Incorrect number of namespaces matching fragment", combinedMap.size (), 2);
    }

    /**
     * Test addition of namespace to xml.
     */
    @Test
    public void testAddNamespaces ()
    {

        // Define text raw xml.
        final String xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +

                "<beans xmlns=\"http://www.springframework.org/schema/beans\""
                        + "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
                        + "xmlns:aop=\"http://www.springframework.org/schema/aop\""
                        + "    xsi:schemaLocation=\"http://www.springframework.org/schema/beans"
                        + "       http://www.springframework.org/schema/beans/spring-beans-3.1.xsd"
                        + "       http://www.springframework.org/schema/aop"
                        + "       http://www.springframework.org/schema/aop/spring-aop-2.5.xsd\">" +

                        "   <!-- Note all ids should be based on fully qualified names (interfaces where"
                        + "       this is not ambiguous) and all classes should have an interface. -->" +

                        "   <bean id=\"uk.gov.moj.sdt.utils.mbeans.api.ISdtMetricsMBean\" "
                        + "class=\"uk.gov.moj.sdt.utils.mbeans.SdtMetricsMBean\" />" +

                        "   <xsi:some-tag some-attribute=\"some value\">"
                        + "       <aop:some-other-tag some-attribute=\"some value\"/>"
                        + "       <aop:some-other-tag some-attribute=\"some value\">" + "       </aop:some-other-tag>"
                        + "   </xsi:some-tag>" + "</beans>";

        final Map<String, String> allNamespaces = XmlNamespaceUtils.extractAllNamespaces (xml, null);

        String xmlFragment =
                "   <!--Comment--><xsi:some-tag some-attribute=\"some value\">"
                        + "       <aop:some-other-tag some-attribute=\"some value\"/>"
                        + "       <aop:some-other-tag some-attribute=\"some value\">" + "       </aop:some-other-tag>"
                        + "   </xsi:some-tag>";

        final Map<String, String> matched = XmlNamespaceUtils.findMatchingNamespaces (xmlFragment, allNamespaces);

        // Remove namespaces to facilitate adding new namespaces.
        xmlFragment = XmlNamespaceUtils.removeEmbeddedNamespaces (xmlFragment);

        // Translate any embedded default namespaces to their target application equivalent.
        xmlFragment = XmlNamespaceUtils.translateDefaultNamespaces (xmlFragment, null);

        final String result = XmlNamespaceUtils.addNamespaces (xmlFragment, matched);

        // CHECKSTYLE:OFF
        final String expected =
                "   <!--Comment--><xsi:some-tag xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" some-attribute=\"some value\">"
                        + "       <aop:some-other-tag xmlns:aop=\"http://www.springframework.org/schema/aop\" some-attribute=\"some value\"/>"
                        + "       <aop:some-other-tag xmlns:aop=\"http://www.springframework.org/schema/aop\" some-attribute=\"some value\">"
                        + "       </aop:some-other-tag>" + "   </xsi:some-tag>";

        Assert.assertEquals ("Generated xml fragment is incorrect", expected, result);

        // CHECKSTYLE:ON

    }

    /**
     * Test addition of namespace to xml with default namespace to be applied from higher level. Default namespace from
     * the bulkRequest tag should be added to the mcolClaim tag.
     */
    @Test
    public void testDefaultNamespace ()
    {
        // CHECKSTYLE:OFF

        // Define text raw xml.
        final String xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                        + "<soap:Envelope "
                        + "xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" "
                        + "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
                        + "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">"
                        + "    <soap:Body>"
                        + "        <bulkRequest "
                        + "            xmlns:base=\"http://ws.sdt.moj.gov.uk/2013/mcol/BaseSchema\" "
                        + "            xmlns:bulk=\"http://ws.sdt.moj.gov.uk/2013/mcol/BulkRequestSchema\" "
                        + "            xmlns:jud=\"http://ws.sdt.moj.gov.uk/2013/mcol/JudgmentSchema\" "
                        + "            xmlns:clmupd=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimStatusUpdateSchema\" "
                        + "            xmlns:clm=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\" "
                        + "            xmlns:wnt=\"http://ws.sdt.moj.gov.uk/2013/mcol/WarrantSchema\" "
                        + "            xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BulkRequestSchema\">"
                        + "            <header>"
                        + "                <sdtCustomerId>12341541</sdtCustomerId>"
                        + "                <targetApplicationId>mcol</targetApplicationId>"
                        + "                <requestCount>1</requestCount>"
                        + "                <customerReference>CUST000000003</customerReference>"
                        + "            </header>"
                        + "            <requests>"
                        + "                <request requestType=\"mcolClaim\" requestId=\"CUST000000003\">"
                        + "                    <mcolClaim>"
                        + "                        <clm:claimantReference>Custref000001</clm:claimantReference>"
                        + "                        <clm:defendant1>"
                        + "                            <clm:name>A &amp; Bclmname</clm:name>"
                        + "                            <clm:address>"
                        + "                                <line1 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">defaddrline1</line1>"
                        + "                                <line2 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">defaddrline2</line2>"
                        + "                                <line3 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">defaddrline3</line3>"
                        + "                                <line4 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Beds</line4>"
                        + "                                <postcode xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">KT22 7LP</postcode>"
                        + "                            </clm:address>"
                        + "                        </clm:defendant1>"
                        + "                        <clm:sendParticularsSeparately>false</clm:sendParticularsSeparately>"
                        + "                        <clm:reserveRightToClaimInterest>false</clm:reserveRightToClaimInterest>"
                        + "                        <clm:claimAmount>1490000</clm:claimAmount>"
                        + "                        <clm:solicitorCost>10000</clm:solicitorCost>"
                        + "                        <clm:particulars>The claimant claims some for goods</clm:particulars>"
                        + "                        <clm:particulars>supplied and/or services rendered by the</clm:particulars>"
                        + "                        <clm:particulars>claimant at the request of the defendant as</clm:particulars>"
                        + "                        <clm:particulars>set out in the invoices and/or statements</clm:particulars>"
                        + "                        <clm:particulars>particular lines </clm:particulars>"
                        + "                        <clm:particulars>statements sent by the claimant to the</clm:particulars>"
                        + "                        <clm:particulars>defendant with interest pursuant to contract</clm:particulars>"
                        + "                        <clm:particulars>or under S69 County Courts Act 1999 or under</clm:particulars>"
                        + "                        <clm:particulars>S1 of the Late Payment of Commercial Debts</clm:particulars>"
                        + "                        <clm:particulars>(Interest) Act 1998 from the due date of</clm:particulars>"
                        + "                        <clm:particulars>each invoice to the date hereof at 48.0%</clm:particulars>"
                        + "                        <clm:particulars>being £132.68 and further until payment or</clm:particulars>"
                        + "                        <clm:particulars>Judgment at a daily rate of £10.20.  The</clm:particulars>"
                        + "                        <clm:particulars>some stuff</clm:particulars>"
                        + "                        <clm:particulars>some more particulars</clm:particulars>"
                        + "                        <clm:sotSignature>"
                        + "                            <flag xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">true</flag>"
                        + "                            <name xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Joe Doe</name>"
                        + "                        </clm:sotSignature>" + "                    </mcolClaim>"
                        + "                </request>" + "            </requests>" + "        </bulkRequest>"
                        + "    </soap:Body>" + "</soap:Envelope> ";

        // Setup translation from SDT to MCOL namespace for non-generic
        final Map<String, String> replacementNamespaces = new HashMap<String, String> ();
        replacementNamespaces.put ("http://ws.sdt.moj.gov.uk/2013/sdt/BulkRequestSchema",
                "http://ws.sdt.moj.gov.uk/2013/sdt/targetApp/IndvRequestSchema");

        final Map<String, String> allNamespaces = XmlNamespaceUtils.extractAllNamespaces (xml, replacementNamespaces);

        String xmlFragment =
                "                    <mcolClaim>"
                        + "                        <clm:claimantReference>Custref000001</clm:claimantReference>"
                        + "                        <clm:defendant1>"
                        + "                            <clm:name>A &amp; Bclmname</clm:name>"
                        + "                            <clm:address>"
                        + "                                <line1 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\" >defaddrline1</line1>"
                        + "                                <line2 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\" some-attribute='some value' >defaddrline2</line2>"
                        + "                                <line3 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">defaddrline3</line3>"
                        + "                                <line4 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Beds</line4>"
                        + "                                <postcode xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">KT22 7LP</postcode>"
                        + "                            </clm:address>"
                        + "                        </clm:defendant1>"
                        + "                        <clm:sendParticularsSeparately>false</clm:sendParticularsSeparately>"
                        + "                        <clm:reserveRightToClaimInterest>false</clm:reserveRightToClaimInterest>"
                        + "                        <clm:claimAmount>1490000</clm:claimAmount>"
                        + "                        <clm:solicitorCost>10000</clm:solicitorCost>"
                        + "                        <clm:particulars>The claimant claims some for goods</clm:particulars>"
                        + "                        <clm:particulars>supplied and/or services rendered by the</clm:particulars>"
                        + "                        <clm:particulars>claimant at the request of the defendant as</clm:particulars>"
                        + "                        <clm:particulars>set out in the invoices and/or statements</clm:particulars>"
                        + "                        <clm:particulars>particular lines </clm:particulars>"
                        + "                        <clm:particulars>statements sent by the claimant to the</clm:particulars>"
                        + "                        <clm:particulars>defendant with interest pursuant to contract</clm:particulars>"
                        + "                        <clm:particulars>or under S69 County Courts Act 1999 or under</clm:particulars>"
                        + "                        <clm:particulars>S1 of the Late Payment of Commercial Debts</clm:particulars>"
                        + "                        <clm:particulars>(Interest) Act 1998 from the due date of</clm:particulars>"
                        + "                        <clm:particulars>each invoice to the date hereof at 48.0%</clm:particulars>"
                        + "                        <clm:particulars>being £132.68 and further until payment or</clm:particulars>"
                        + "                        <clm:particulars>Judgment at a daily rate of £10.20.  The</clm:particulars>"
                        + "                        <clm:particulars>some stuff</clm:particulars>"
                        + "                        <clm:particulars>some more particulars</clm:particulars>"
                        + "                        <clm:sotSignature>"
                        + "                            <flag xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">true</flag>"
                        + "                            <name xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Joe Doe</name>"
                        + "                        </clm:sotSignature>" + "                    </mcolClaim>";
        // CHECKSTYLE:ON

        final Map<String, String> matched = XmlNamespaceUtils.findMatchingNamespaces (xmlFragment, allNamespaces);

        // Remove namespaces to facilitate adding new namespaces.
        xmlFragment = XmlNamespaceUtils.removeEmbeddedNamespaces (xmlFragment);

        // Translate any embedded default namespaces to their target application equivalent.
        xmlFragment = XmlNamespaceUtils.translateDefaultNamespaces (xmlFragment, replacementNamespaces);

        final String result = XmlNamespaceUtils.addNamespaces (xmlFragment, matched);

        // CHECKSTYLE:OFF
        final String expected =
                "                    <mcolClaim xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/targetApp/IndvRequestSchema\">"
                        + "                        <clm:claimantReference xmlns:clm=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">Custref000001</clm:claimantReference>"
                        + "                        <clm:defendant1 xmlns:clm=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">"
                        + "                            <clm:name xmlns:clm=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">A &amp; Bclmname</clm:name>"
                        + "                            <clm:address xmlns:clm=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">"
                        + "                                <line1 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\" >defaddrline1</line1>"
                        + "                                <line2 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\" some-attribute='some value' >defaddrline2</line2>"
                        + "                                <line3 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">defaddrline3</line3>"
                        + "                                <line4 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Beds</line4>"
                        + "                                <postcode xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">KT22 7LP</postcode>"
                        + "                            </clm:address>"
                        + "                        </clm:defendant1>"
                        + "                        <clm:sendParticularsSeparately xmlns:clm=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">false</clm:sendParticularsSeparately>"
                        + "                        <clm:reserveRightToClaimInterest xmlns:clm=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">false</clm:reserveRightToClaimInterest>"
                        + "                        <clm:claimAmount xmlns:clm=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">1490000</clm:claimAmount>"
                        + "                        <clm:solicitorCost xmlns:clm=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">10000</clm:solicitorCost>"
                        + "                        <clm:particulars xmlns:clm=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">The claimant claims some for goods</clm:particulars>"
                        + "                        <clm:particulars xmlns:clm=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">supplied and/or services rendered by the</clm:particulars>"
                        + "                        <clm:particulars xmlns:clm=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">claimant at the request of the defendant as</clm:particulars>"
                        + "                        <clm:particulars xmlns:clm=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">set out in the invoices and/or statements</clm:particulars>"
                        + "                        <clm:particulars xmlns:clm=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">particular lines </clm:particulars>"
                        + "                        <clm:particulars xmlns:clm=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">statements sent by the claimant to the</clm:particulars>"
                        + "                        <clm:particulars xmlns:clm=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">defendant with interest pursuant to contract</clm:particulars>"
                        + "                        <clm:particulars xmlns:clm=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">or under S69 County Courts Act 1999 or under</clm:particulars>"
                        + "                        <clm:particulars xmlns:clm=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">S1 of the Late Payment of Commercial Debts</clm:particulars>"
                        + "                        <clm:particulars xmlns:clm=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">(Interest) Act 1998 from the due date of</clm:particulars>"
                        + "                        <clm:particulars xmlns:clm=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">each invoice to the date hereof at 48.0%</clm:particulars>"
                        + "                        <clm:particulars xmlns:clm=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">being £132.68 and further until payment or</clm:particulars>"
                        + "                        <clm:particulars xmlns:clm=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">Judgment at a daily rate of £10.20.  The</clm:particulars>"
                        + "                        <clm:particulars xmlns:clm=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">some stuff</clm:particulars>"
                        + "                        <clm:particulars xmlns:clm=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">some more particulars</clm:particulars>"
                        + "                        <clm:sotSignature xmlns:clm=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">"
                        + "                            <flag xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">true</flag>"
                        + "                            <name xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Joe Doe</name>"
                        + "                        </clm:sotSignature>" + "                    </mcolClaim>";

        Assert.assertEquals ("Generated xml fragment is incorrect", expected, result);

        // CHECKSTYLE:ON

    }

    /**
     * Test addition of namespace to xml with default namespace to be applied from higher level. Ensure default
     * namespace is only applied to to the topmost tag encountered in the hierarchy by providing a second unprefixed tag
     * without a default namespace ('name') to which it should not be added.
     */
    @Test
    public void testDefaultNamespaceTopLevelTagOnly ()
    {
        // CHECKSTYLE:OFF

        // Define text raw xml.
        final String xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                        + "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:cla=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\" xmlns:bas=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\" xmlns:jud=\"http://ws.sdt.moj.gov.uk/2013/mcol/JudgmentSchema\" xmlns:cla1=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimStatusUpdateSchema\" xmlns:war=\"http://ws.sdt.moj.gov.uk/2013/mcol/WarrantSchema\">"
                        + "  <soap:Header/>"
                        + "  <soap:Body>"
                        + "    <bulkRequest xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BulkRequestSchema\">"
                        + "      <header>"
                        + "        <sdtCustomerId>12341544</sdtCustomerId>"
                        + "        <targetApplicationId>mcol</targetApplicationId>"
                        + "        <requestCount>1</requestCount>"
                        + "        <customerReference>S3Namespace03</customerReference>"
                        + "      </header>"
                        + "      <requests>"
                        + "        <!--1 to 2000 repetitions:-->"
                        + "        <request requestType=\"mcolclaim\" requestId=\"S3Namespace03\">"
                        + "          <!--You have a CHOICE of the next 5 items at this level-->"
                        + "          <mcolClaim>"
                        + "            <!--Optional:-->"
                        + "            <cla:claimantReference>Style02</cla:claimantReference>"
                        + "            <!--Optional:-->"
                        + "            <claimant xmlns=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">"
                        + "              <name>Mr John Wayne</name>"
                        + "              <address>"
                        + "                <line1>Address 1</line1>"
                        + "                <line2>Address 2</line2>"
                        + "                <!--Optional:-->"
                        + "                <line3>Address 3</line3>"
                        + "                <!--Optional:-->"
                        + "                <line4>Address 4</line4>"
                        + "                <!--Optional:-->"
                        + "                <postcode>KT22 7LP</postcode>"
                        + "              </address>"
                        + "            </claimant>"
                        + "            <cla:defendant1>"
                        + "              <cla:name>Mr Bruce Willis</cla:name>"
                        + "              <cla:address>"
                        + "                <line1 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 1</line1>"
                        + "                <line2 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 2</line2>"
                        + "                <!--Optional:-->"
                        + "                <line3 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 3</line3>"
                        + "                <!--Optional:-->"
                        + "                <line4 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 4</line4>"
                        + "                <postcode xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">KT22 7LP</postcode>"
                        + "              </cla:address>" + "            </cla:defendant1>"
                        + "            <!--Optional:-->"
                        + "            <defendant2 xmlns=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">"
                        + "              <name>Mrs Helen Mirram</name>" + "              <address>"
                        + "                <bas:line1>Addy 11</bas:line1>"
                        + "                <bas:line2>Addy 21</bas:line2>" + "                <!--Optional:-->"
                        + "                <bas:line3>Addy 31</bas:line3>" + "                <!--Optional:-->"
                        + "                <bas:line4>Addy 41</bas:line4>"
                        + "                <bas:postcode>KT22 7LP</bas:postcode>" + "              </address>"
                        + "            </defendant2>"
                        + "            <cla:sendParticularsSeparately>true</cla:sendParticularsSeparately>"
                        + "            <cla:reserveRightToClaimInterest>false</cla:reserveRightToClaimInterest>"
                        + "            <cla:claimAmount>450000</cla:claimAmount>" + "            <!--Optional:-->"
                        + "            <cla:solicitorCost>4000</cla:solicitorCost>"
                        + "            <!--1 to 24 repetitions:-->"
                        + "            <cla:particulars>testing 123</cla:particulars>"
                        + "            <cla:sotSignature>" + "              <bas:flag>true</bas:flag>"
                        + "              <bas:name>richard</bas:name>" + "            </cla:sotSignature>"
                        + "          </mcolClaim>" + "        </request>" + "      </requests>" + "    </bulkRequest>"
                        + "  </soap:Body>" + "</soap:Envelope> ";

        // Setup translation from SDT to MCOL namespace for non-generic
        final Map<String, String> replacementNamespaces = new HashMap<String, String> ();
        replacementNamespaces.put ("http://ws.sdt.moj.gov.uk/2013/sdt/BulkRequestSchema",
                "http://ws.sdt.moj.gov.uk/2013/sdt/targetApp/IndvRequestSchema");

        final Map<String, String> allNamespaces = XmlNamespaceUtils.extractAllNamespaces (xml, replacementNamespaces);

        String xmlFragment =
                "          <mcolClaim>"
                        + "            <!--Optional:-->"
                        + "            <cla:claimantReference>Style02</cla:claimantReference>"
                        + "            <!--Optional:-->"
                        + "            <claimant xmlns=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">"
                        + "              <name>Mr John Wayne</name>"
                        + "              <address>"
                        + "                <line1>Address 1</line1>"
                        + "                <line2>Address 2</line2>"
                        + "                <!--Optional:-->"
                        + "                <line3>Address 3</line3>"
                        + "                <!--Optional:-->"
                        + "                <line4>Address 4</line4>"
                        + "                <!--Optional:-->"
                        + "                <postcode>KT22 7LP</postcode>"
                        + "              </address>"
                        + "            </claimant>"
                        + "            <cla:defendant1>"
                        + "              <cla:name>Mr Bruce Willis</cla:name>"
                        + "              <cla:address>"
                        + "                <line1 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 1</line1>"
                        + "                <line2 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 2</line2>"
                        + "                <!--Optional:-->"
                        + "                <line3 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 3</line3>"
                        + "                <!--Optional:-->"
                        + "                <line4 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 4</line4>"
                        + "                <postcode xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">KT22 7LP</postcode>"
                        + "              </cla:address>" + "            </cla:defendant1>"
                        + "            <!--Optional:-->"
                        + "            <defendant2 xmlns=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">"
                        + "              <name>Mrs Helen Mirram</name>" + "              <address>"
                        + "                <bas:line1>Addy 11</bas:line1>"
                        + "                <bas:line2>Addy 21</bas:line2>" + "                <!--Optional:-->"
                        + "                <bas:line3>Addy 31</bas:line3>" + "                <!--Optional:-->"
                        + "                <bas:line4>Addy 41</bas:line4>"
                        + "                <bas:postcode>KT22 7LP</bas:postcode>" + "              </address>"
                        + "            </defendant2>"
                        + "            <cla:sendParticularsSeparately>true</cla:sendParticularsSeparately>"
                        + "            <cla:reserveRightToClaimInterest>false</cla:reserveRightToClaimInterest>"
                        + "            <cla:claimAmount>450000</cla:claimAmount>" + "            <!--Optional:-->"
                        + "            <cla:solicitorCost>4000</cla:solicitorCost>"
                        + "            <!--1 to 24 repetitions:-->"
                        + "            <cla:particulars>testing 123</cla:particulars>"
                        + "            <cla:sotSignature>" + "              <bas:flag>true</bas:flag>"
                        + "              <bas:name>richard</bas:name>" + "            </cla:sotSignature>"
                        + "          </mcolClaim>";

        // CHECKSTYLE:ON

        final Map<String, String> matched = XmlNamespaceUtils.findMatchingNamespaces (xmlFragment, allNamespaces);

        // Remove namespaces to facilitate adding new namespaces.
        xmlFragment = XmlNamespaceUtils.removeEmbeddedNamespaces (xmlFragment);

        // Translate any embedded default namespaces to their target application equivalent.
        xmlFragment = XmlNamespaceUtils.translateDefaultNamespaces (xmlFragment, replacementNamespaces);

        final String result = XmlNamespaceUtils.addNamespaces (xmlFragment, matched);

        // CHECKSTYLE:OFF
        final String expected =
                "          <mcolClaim xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/targetApp/IndvRequestSchema\">            <!--Optional:-->            <cla:claimantReference xmlns:cla=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">Style02</cla:claimantReference>            <!--Optional:-->            <claimant xmlns=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">              <name>Mr John Wayne</name>              <address>                <line1>Address 1</line1>                <line2>Address 2</line2>                <!--Optional:-->                <line3>Address 3</line3>                <!--Optional:-->                <line4>Address 4</line4>                <!--Optional:-->                <postcode>KT22 7LP</postcode>              </address>            </claimant>            <cla:defendant1 xmlns:cla=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">              <cla:name xmlns:cla=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">Mr Bruce Willis</cla:name>              <cla:address xmlns:cla=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">                <line1 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 1</line1>                <line2 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 2</line2>                <!--Optional:-->                <line3 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 3</line3>                <!--Optional:-->                <line4 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 4</line4>                <postcode xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">KT22 7LP</postcode>              </cla:address>            </cla:defendant1>            <!--Optional:-->            <defendant2 xmlns=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">              <name>Mrs Helen Mirram</name>              <address>                <bas:line1 xmlns:bas=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 11</bas:line1>                <bas:line2 xmlns:bas=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 21</bas:line2>                <!--Optional:-->                <bas:line3 xmlns:bas=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 31</bas:line3>                <!--Optional:-->                <bas:line4 xmlns:bas=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 41</bas:line4>                <bas:postcode xmlns:bas=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">KT22 7LP</bas:postcode>              </address>            </defendant2>            <cla:sendParticularsSeparately xmlns:cla=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">true</cla:sendParticularsSeparately>            <cla:reserveRightToClaimInterest xmlns:cla=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">false</cla:reserveRightToClaimInterest>            <cla:claimAmount xmlns:cla=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">450000</cla:claimAmount>            <!--Optional:-->            <cla:solicitorCost xmlns:cla=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">4000</cla:solicitorCost>            <!--1 to 24 repetitions:-->            <cla:particulars xmlns:cla=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">testing 123</cla:particulars>            <cla:sotSignature xmlns:cla=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">              <bas:flag xmlns:bas=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">true</bas:flag>              <bas:name xmlns:bas=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">richard</bas:name>            </cla:sotSignature>          </mcolClaim>";

        Assert.assertEquals ("Generated xml fragment is incorrect", expected, result);

        // CHECKSTYLE:ON

    }

    /**
     * Test addition of namespace to xml with default namespace to be applied from higher level for multiple tags at the
     * same level. Ensure default namespace is only applied to to the topmost tag encountered in the hierarchy by
     * providing a second unprefixed tag without a default namespace ('name') to which it should not be added.
     */
    @Test
    public void testMultipleDefaultNamespaceTopLevelTagOnly ()
    {
        // CHECKSTYLE:OFF

        // Define text raw xml.
        final String xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                        + "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:cla=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\" xmlns:bas=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\" xmlns:jud=\"http://ws.sdt.moj.gov.uk/2013/mcol/JudgmentSchema\" xmlns:cla1=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimStatusUpdateSchema\" xmlns:war=\"http://ws.sdt.moj.gov.uk/2013/mcol/WarrantSchema\">"
                        + "  <soap:Header/>"
                        + "  <soap:Body>"
                        + "    <bulkRequest xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BulkRequestSchema\">"
                        + "      <header>"
                        + "        <sdtCustomerId>12341544</sdtCustomerId>"
                        + "        <targetApplicationId>mcol</targetApplicationId>"
                        + "        <requestCount>2</requestCount>"
                        + "        <customerReference>S3Namespace03</customerReference>"
                        + "      </header>"
                        + "      <requests>"
                        + "        <!--1 to 2000 repetitions:-->"
                        + "        <request requestType=\"mcolclaim\" requestId=\"S3Namespace02\">"
                        + "          <!--You have a CHOICE of the next 5 items at this level-->"
                        + "          <mcolClaim>"
                        + "            <!--Optional:-->"
                        + "            <cla:claimantReference>Style02</cla:claimantReference>"
                        + "            <!--Optional:-->"
                        + "            <claimant xmlns=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">"
                        + "              <name>Mr John Wayne</name>"
                        + "              <address>"
                        + "                <line1>Address 1</line1>"
                        + "                <line2>Address 2</line2>"
                        + "                <!--Optional:-->"
                        + "                <line3>Address 3</line3>"
                        + "                <!--Optional:-->"
                        + "                <line4>Address 4</line4>"
                        + "                <!--Optional:-->"
                        + "                <postcode>KT22 7LP</postcode>"
                        + "              </address>"
                        + "            </claimant>"
                        + "            <cla:defendant1>"
                        + "              <cla:name>Mr Bruce Willis</cla:name>"
                        + "              <cla:address>"
                        + "                <line1 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 1</line1>"
                        + "                <line2 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 2</line2>"
                        + "                <!--Optional:-->"
                        + "                <line3 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 3</line3>"
                        + "                <!--Optional:-->"
                        + "                <line4 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 4</line4>"
                        + "                <postcode xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">KT22 7LP</postcode>"
                        + "              </cla:address>"
                        + "            </cla:defendant1>"
                        + "            <!--Optional:-->"
                        + "            <defendant2 xmlns=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">"
                        + "              <name>Mrs Helen Mirram</name>"
                        + "              <address>"
                        + "                <bas:line1>Addy 11</bas:line1>"
                        + "                <bas:line2>Addy 21</bas:line2>"
                        + "                <!--Optional:-->"
                        + "                <bas:line3>Addy 31</bas:line3>"
                        + "                <!--Optional:-->"
                        + "                <bas:line4>Addy 41</bas:line4>"
                        + "                <bas:postcode>KT22 7LP</bas:postcode>"
                        + "              </address>"
                        + "            </defendant2>"
                        + "            <cla:sendParticularsSeparately>true</cla:sendParticularsSeparately>"
                        + "            <cla:reserveRightToClaimInterest>false</cla:reserveRightToClaimInterest>"
                        + "            <cla:claimAmount>450000</cla:claimAmount>"
                        + "            <!--Optional:-->"
                        + "            <cla:solicitorCost>4000</cla:solicitorCost>"
                        + "            <!--1 to 24 repetitions:-->"
                        + "            <cla:particulars>testing 123</cla:particulars>"
                        + "            <cla:sotSignature>"
                        + "              <bas:flag>true</bas:flag>"
                        + "              <bas:name>richard</bas:name>"
                        + "            </cla:sotSignature>"
                        + "          </mcolClaim>"
                        + "        </request>"
                        + "        <request requestType=\"mcolclaim\" requestId=\"S3Namespace03\">"
                        + "          <!--You have a CHOICE of the next 5 items at this level-->"
                        + "          <mcolClaim>"
                        + "            <!--Optional:-->"
                        + "            <cla:claimantReference>Style02</cla:claimantReference>"
                        + "            <!--Optional:-->"
                        + "            <claimant xmlns=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">"
                        + "              <name>Mr John Wayne</name>"
                        + "              <address>"
                        + "                <line1>Address 1</line1>"
                        + "                <line2>Address 2</line2>"
                        + "                <!--Optional:-->"
                        + "                <line3>Address 3</line3>"
                        + "                <!--Optional:-->"
                        + "                <line4>Address 4</line4>"
                        + "                <!--Optional:-->"
                        + "                <postcode>KT22 7LP</postcode>"
                        + "              </address>"
                        + "            </claimant>"
                        + "            <cla:defendant1>"
                        + "              <cla:name>Mr Bruce Willis</cla:name>"
                        + "              <cla:address>"
                        + "                <line1 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 1</line1>"
                        + "                <line2 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 2</line2>"
                        + "                <!--Optional:-->"
                        + "                <line3 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 3</line3>"
                        + "                <!--Optional:-->"
                        + "                <line4 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 4</line4>"
                        + "                <postcode xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">KT22 7LP</postcode>"
                        + "              </cla:address>" + "            </cla:defendant1>"
                        + "            <!--Optional:-->"
                        + "            <defendant2 xmlns=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">"
                        + "              <name>Mrs Helen Mirram</name>" + "              <address>"
                        + "                <bas:line1>Addy 11</bas:line1>"
                        + "                <bas:line2>Addy 21</bas:line2>" + "                <!--Optional:-->"
                        + "                <bas:line3>Addy 31</bas:line3>" + "                <!--Optional:-->"
                        + "                <bas:line4>Addy 41</bas:line4>"
                        + "                <bas:postcode>KT22 7LP</bas:postcode>" + "              </address>"
                        + "            </defendant2>"
                        + "            <cla:sendParticularsSeparately>true</cla:sendParticularsSeparately>"
                        + "            <cla:reserveRightToClaimInterest>false</cla:reserveRightToClaimInterest>"
                        + "            <cla:claimAmount>450000</cla:claimAmount>" + "            <!--Optional:-->"
                        + "            <cla:solicitorCost>4000</cla:solicitorCost>"
                        + "            <!--1 to 24 repetitions:-->"
                        + "            <cla:particulars>testing 123</cla:particulars>"
                        + "            <cla:sotSignature>" + "              <bas:flag>true</bas:flag>"
                        + "              <bas:name>richard</bas:name>" + "            </cla:sotSignature>"
                        + "          </mcolClaim>" + "        </request>" + "      </requests>" + "    </bulkRequest>"
                        + "  </soap:Body>" + "</soap:Envelope> ";

        // Setup translation from SDT to MCOL namespace for non-generic
        final Map<String, String> replacementNamespaces = new HashMap<String, String> ();
        replacementNamespaces.put ("http://ws.sdt.moj.gov.uk/2013/sdt/BulkRequestSchema",
                "http://ws.sdt.moj.gov.uk/2013/sdt/targetApp/IndvRequestSchema");

        final Map<String, String> allNamespaces = XmlNamespaceUtils.extractAllNamespaces (xml, replacementNamespaces);

        String xmlFragment =
                "          <mcolClaim>"
                        + "            <!--Optional:-->"
                        + "            <cla:claimantReference>Style02</cla:claimantReference>"
                        + "            <!--Optional:-->"
                        + "            <claimant xmlns=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">"
                        + "              <name>Mr John Wayne</name>"
                        + "              <address>"
                        + "                <line1>Address 1</line1>"
                        + "                <line2>Address 2</line2>"
                        + "                <!--Optional:-->"
                        + "                <line3>Address 3</line3>"
                        + "                <!--Optional:-->"
                        + "                <line4>Address 4</line4>"
                        + "                <!--Optional:-->"
                        + "                <postcode>KT22 7LP</postcode>"
                        + "              </address>"
                        + "            </claimant>"
                        + "            <cla:defendant1>"
                        + "              <cla:name>Mr Bruce Willis</cla:name>"
                        + "              <cla:address>"
                        + "                <line1 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 1</line1>"
                        + "                <line2 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 2</line2>"
                        + "                <!--Optional:-->"
                        + "                <line3 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 3</line3>"
                        + "                <!--Optional:-->"
                        + "                <line4 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 4</line4>"
                        + "                <postcode xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">KT22 7LP</postcode>"
                        + "              </cla:address>" + "            </cla:defendant1>"
                        + "            <!--Optional:-->"
                        + "            <defendant2 xmlns=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">"
                        + "              <name>Mrs Helen Mirram</name>" + "              <address>"
                        + "                <bas:line1>Addy 11</bas:line1>"
                        + "                <bas:line2>Addy 21</bas:line2>" + "                <!--Optional:-->"
                        + "                <bas:line3>Addy 31</bas:line3>" + "                <!--Optional:-->"
                        + "                <bas:line4>Addy 41</bas:line4>"
                        + "                <bas:postcode>KT22 7LP</bas:postcode>" + "              </address>"
                        + "            </defendant2>"
                        + "            <cla:sendParticularsSeparately>true</cla:sendParticularsSeparately>"
                        + "            <cla:reserveRightToClaimInterest>false</cla:reserveRightToClaimInterest>"
                        + "            <cla:claimAmount>450000</cla:claimAmount>" + "            <!--Optional:-->"
                        + "            <cla:solicitorCost>4000</cla:solicitorCost>"
                        + "            <!--1 to 24 repetitions:-->"
                        + "            <cla:particulars>testing 123</cla:particulars>"
                        + "            <cla:sotSignature>" + "              <bas:flag>true</bas:flag>"
                        + "              <bas:name>richard</bas:name>" + "            </cla:sotSignature>"
                        + "          </mcolClaim>";

        // CHECKSTYLE:ON

        final Map<String, String> matched = XmlNamespaceUtils.findMatchingNamespaces (xmlFragment, allNamespaces);

        // Remove namespaces to facilitate adding new namespaces.
        xmlFragment = XmlNamespaceUtils.removeEmbeddedNamespaces (xmlFragment);

        // Translate any embedded default namespaces to their target application equivalent.
        xmlFragment = XmlNamespaceUtils.translateDefaultNamespaces (xmlFragment, replacementNamespaces);

        final String result = XmlNamespaceUtils.addNamespaces (xmlFragment, matched);

        // CHECKSTYLE:OFF
        final String expected =
                "          <mcolClaim xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/targetApp/IndvRequestSchema\">            <!--Optional:-->            <cla:claimantReference xmlns:cla=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">Style02</cla:claimantReference>            <!--Optional:-->            <claimant xmlns=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">              <name>Mr John Wayne</name>              <address>                <line1>Address 1</line1>                <line2>Address 2</line2>                <!--Optional:-->                <line3>Address 3</line3>                <!--Optional:-->                <line4>Address 4</line4>                <!--Optional:-->                <postcode>KT22 7LP</postcode>              </address>            </claimant>            <cla:defendant1 xmlns:cla=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">              <cla:name xmlns:cla=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">Mr Bruce Willis</cla:name>              <cla:address xmlns:cla=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">                <line1 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 1</line1>                <line2 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 2</line2>                <!--Optional:-->                <line3 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 3</line3>                <!--Optional:-->                <line4 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 4</line4>                <postcode xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">KT22 7LP</postcode>              </cla:address>            </cla:defendant1>            <!--Optional:-->            <defendant2 xmlns=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">              <name>Mrs Helen Mirram</name>              <address>                <bas:line1 xmlns:bas=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 11</bas:line1>                <bas:line2 xmlns:bas=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 21</bas:line2>                <!--Optional:-->                <bas:line3 xmlns:bas=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 31</bas:line3>                <!--Optional:-->                <bas:line4 xmlns:bas=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 41</bas:line4>                <bas:postcode xmlns:bas=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">KT22 7LP</bas:postcode>              </address>            </defendant2>            <cla:sendParticularsSeparately xmlns:cla=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">true</cla:sendParticularsSeparately>            <cla:reserveRightToClaimInterest xmlns:cla=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">false</cla:reserveRightToClaimInterest>            <cla:claimAmount xmlns:cla=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">450000</cla:claimAmount>            <!--Optional:-->            <cla:solicitorCost xmlns:cla=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">4000</cla:solicitorCost>            <!--1 to 24 repetitions:-->            <cla:particulars xmlns:cla=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">testing 123</cla:particulars>            <cla:sotSignature xmlns:cla=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">              <bas:flag xmlns:bas=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">true</bas:flag>              <bas:name xmlns:bas=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">richard</bas:name>            </cla:sotSignature>          </mcolClaim>";

        Assert.assertEquals ("Generated xml fragment is incorrect", expected, result);

        // CHECKSTYLE:ON
    }

    /**
     * Test addition of namespace to xml with default namespace to be applied from higher level. Ensure all instance of
     * embedded default namespaces that qualify for translation are translated. In this case mcolClaim already has a
     * default embedded namespace which must be translated.
     */
    @Test
    public void testDefaultNamespaceTranslateDefaultEmbedded ()
    {
        // CHECKSTYLE:OFF

        // Define text raw xml.
        final String xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                        + "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:cla=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\" xmlns:bas=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\" xmlns:jud=\"http://ws.sdt.moj.gov.uk/2013/mcol/JudgmentSchema\" xmlns:cla1=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimStatusUpdateSchema\" xmlns:war=\"http://ws.sdt.moj.gov.uk/2013/mcol/WarrantSchema\">"
                        + "  <soap:Header/>"
                        + "  <soap:Body>"
                        + "    <bulkRequest xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BulkRequestSchema\">"
                        + "      <header>"
                        + "        <sdtCustomerId>12341544</sdtCustomerId>"
                        + "        <targetApplicationId>mcol</targetApplicationId>"
                        + "        <requestCount>1</requestCount>"
                        + "        <customerReference>S3Namespace01</customerReference>"
                        + "      </header>"
                        + "      <requests xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BulkRequestSchema\">"
                        + "        <!--1 to 2000 repetitions:-->"
                        + "        <request requestType=\"mcolclaim\" requestId=\"S3Namespace01\">"
                        + "          <!--You have a CHOICE of the next 5 items at this level-->"
                        + "          <mcolClaim xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BulkRequestSchema\">"
                        + "            <!--Optional:-->"
                        + "            <cla:claimantReference>Style01</cla:claimantReference>"
                        + "            <!--Optional:-->"
                        + "            <claimant xmlns=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">"
                        + "              <name>Mr John Wayne</name>"
                        + "              <address>"
                        + "                <line1>Address 1</line1>"
                        + "                <line2>Address 2</line2>"
                        + "                <!--Optional:-->"
                        + "                <line3>Address 3</line3>"
                        + "                <!--Optional:-->"
                        + "                <line4>Address 4</line4>"
                        + "                <!--Optional:-->"
                        + "                <postcode>KT22 7LP</postcode>"
                        + "              </address>"
                        + "            </claimant>"
                        + "            <cla:defendant1>"
                        + "              <cla:name>Mr Bruce Willis</cla:name>"
                        + "              <cla:address>"
                        + "                <line1 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 1</line1>"
                        + "                <line2 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 2</line2>"
                        + "                <!--Optional:-->"
                        + "                <line3 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 3</line3>"
                        + "                <!--Optional:-->"
                        + "                <line4 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 4</line4>"
                        + "                <postcode xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">KT22 7LP</postcode>"
                        + "              </cla:address>" + "            </cla:defendant1>"
                        + "            <!--Optional:-->"
                        + "            <defendant2 xmlns=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">"
                        + "              <name>Mrs Helen Mirram</name>" + "              <address>"
                        + "                <bas:line1>Addy 11</bas:line1>"
                        + "                <bas:line2>Addy 21</bas:line2>" + "                <!--Optional:-->"
                        + "                <bas:line3>Addy 31</bas:line3>" + "                <!--Optional:-->"
                        + "                <bas:line4>Addy 41</bas:line4>"
                        + "                <bas:postcode>KT22 7LP</bas:postcode>" + "              </address>"
                        + "            </defendant2>"
                        + "            <cla:sendParticularsSeparately>true</cla:sendParticularsSeparately>"
                        + "            <cla:reserveRightToClaimInterest>false</cla:reserveRightToClaimInterest>"
                        + "            <cla:claimAmount>450000</cla:claimAmount>" + "            <!--Optional:-->"
                        + "            <cla:solicitorCost>4000</cla:solicitorCost>"
                        + "            <!--1 to 24 repetitions:-->"
                        + "            <cla:particulars>testing 123</cla:particulars>"
                        + "            <cla:sotSignature>" + "              <bas:flag>true</bas:flag>"
                        + "              <bas:name>richard</bas:name>" + "            </cla:sotSignature>"
                        + "          </mcolClaim>" + "        </request>" + "      </requests>" + "    </bulkRequest>"
                        + "  </soap:Body>" + "</soap:Envelope> ";

        // Setup translation from SDT to MCOL namespace for non-generic
        final Map<String, String> replacementNamespaces = new HashMap<String, String> ();
        replacementNamespaces.put ("http://ws.sdt.moj.gov.uk/2013/sdt/BulkRequestSchema",
                "http://ws.sdt.moj.gov.uk/2013/sdt/targetApp/IndvRequestSchema");

        final Map<String, String> allNamespaces = XmlNamespaceUtils.extractAllNamespaces (xml, replacementNamespaces);

        String xmlFragment =
                "          <mcolClaim xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BulkRequestSchema\">"
                        + "            <!--Optional:-->"
                        + "            <cla:claimantReference>Style01</cla:claimantReference>"
                        + "            <!--Optional:-->"
                        + "            <claimant xmlns=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">"
                        + "              <name>Mr John Wayne</name>"
                        + "              <address>"
                        + "                <line1>Address 1</line1>"
                        + "                <line2>Address 2</line2>"
                        + "                <!--Optional:-->"
                        + "                <line3>Address 3</line3>"
                        + "                <!--Optional:-->"
                        + "                <line4>Address 4</line4>"
                        + "                <!--Optional:-->"
                        + "                <postcode>KT22 7LP</postcode>"
                        + "              </address>"
                        + "            </claimant>"
                        + "            <cla:defendant1>"
                        + "              <cla:name>Mr Bruce Willis</cla:name>"
                        + "              <cla:address>"
                        + "                <line1 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 1</line1>"
                        + "                <line2 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 2</line2>"
                        + "                <!--Optional:-->"
                        + "                <line3 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 3</line3>"
                        + "                <!--Optional:-->"
                        + "                <line4 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 4</line4>"
                        + "                <postcode xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">KT22 7LP</postcode>"
                        + "              </cla:address>" + "            </cla:defendant1>"
                        + "            <!--Optional:-->"
                        + "            <defendant2 xmlns=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">"
                        + "              <name>Mrs Helen Mirram</name>" + "              <address>"
                        + "                <bas:line1>Addy 11</bas:line1>"
                        + "                <bas:line2>Addy 21</bas:line2>" + "                <!--Optional:-->"
                        + "                <bas:line3>Addy 31</bas:line3>" + "                <!--Optional:-->"
                        + "                <bas:line4>Addy 41</bas:line4>"
                        + "                <bas:postcode>KT22 7LP</bas:postcode>" + "              </address>"
                        + "            </defendant2>"
                        + "            <cla:sendParticularsSeparately>true</cla:sendParticularsSeparately>"
                        + "            <cla:reserveRightToClaimInterest>false</cla:reserveRightToClaimInterest>"
                        + "            <cla:claimAmount>450000</cla:claimAmount>" + "            <!--Optional:-->"
                        + "            <cla:solicitorCost>4000</cla:solicitorCost>"
                        + "            <!--1 to 24 repetitions:-->"
                        + "            <cla:particulars>testing 123</cla:particulars>"
                        + "            <cla:sotSignature>" + "              <bas:flag>true</bas:flag>"
                        + "              <bas:name>richard</bas:name>" + "            </cla:sotSignature>"
                        + "          </mcolClaim>";

        // CHECKSTYLE:ON

        final Map<String, String> matched = XmlNamespaceUtils.findMatchingNamespaces (xmlFragment, allNamespaces);

        // Remove namespaces to facilitate adding new namespaces.
        xmlFragment = XmlNamespaceUtils.removeEmbeddedNamespaces (xmlFragment);

        // Translate any embedded default namespaces to their target application equivalent.
        xmlFragment = XmlNamespaceUtils.translateDefaultNamespaces (xmlFragment, replacementNamespaces);

        final String result = XmlNamespaceUtils.addNamespaces (xmlFragment, matched);

        // CHECKSTYLE:OFF
        final String expected =
                "          <mcolClaim xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/targetApp/IndvRequestSchema\">            <!--Optional:-->            <cla:claimantReference xmlns:cla=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">Style01</cla:claimantReference>            <!--Optional:-->            <claimant xmlns=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">              <name>Mr John Wayne</name>              <address>                <line1>Address 1</line1>                <line2>Address 2</line2>                <!--Optional:-->                <line3>Address 3</line3>                <!--Optional:-->                <line4>Address 4</line4>                <!--Optional:-->                <postcode>KT22 7LP</postcode>              </address>            </claimant>            <cla:defendant1 xmlns:cla=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">              <cla:name xmlns:cla=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">Mr Bruce Willis</cla:name>              <cla:address xmlns:cla=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">                <line1 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 1</line1>                <line2 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 2</line2>                <!--Optional:-->                <line3 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 3</line3>                <!--Optional:-->                <line4 xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 4</line4>                <postcode xmlns=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">KT22 7LP</postcode>              </cla:address>            </cla:defendant1>            <!--Optional:-->            <defendant2 xmlns=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">              <name>Mrs Helen Mirram</name>              <address>                <bas:line1 xmlns:bas=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 11</bas:line1>                <bas:line2 xmlns:bas=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 21</bas:line2>                <!--Optional:-->                <bas:line3 xmlns:bas=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 31</bas:line3>                <!--Optional:-->                <bas:line4 xmlns:bas=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">Addy 41</bas:line4>                <bas:postcode xmlns:bas=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">KT22 7LP</bas:postcode>              </address>            </defendant2>            <cla:sendParticularsSeparately xmlns:cla=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">true</cla:sendParticularsSeparately>            <cla:reserveRightToClaimInterest xmlns:cla=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">false</cla:reserveRightToClaimInterest>            <cla:claimAmount xmlns:cla=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">450000</cla:claimAmount>            <!--Optional:-->            <cla:solicitorCost xmlns:cla=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">4000</cla:solicitorCost>            <!--1 to 24 repetitions:-->            <cla:particulars xmlns:cla=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">testing 123</cla:particulars>            <cla:sotSignature xmlns:cla=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\">              <bas:flag xmlns:bas=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">true</bas:flag>              <bas:name xmlns:bas=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\">richard</bas:name>            </cla:sotSignature>          </mcolClaim>";

        Assert.assertEquals ("Generated xml fragment is incorrect", expected, result);

        // CHECKSTYLE:ON
    }

    /**
     * Test addition of namespace to xml.
     */
    @Test
    public void testAddNamespacesSingleQuote ()
    {

        // Define text raw xml.
        final String xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +

                "<beans xmlns=\"http://www.springframework.org/schema/beans\""
                        + "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
                        + "xmlns:aop='http://www.springframework.org/schema/aop'"
                        + "    xsi:schemaLocation=\"http://www.springframework.org/schema/beans"
                        + "       http://www.springframework.org/schema/beans/spring-beans-3.1.xsd"
                        + "       http://www.springframework.org/schema/aop"
                        + "       http://www.springframework.org/schema/aop/spring-aop-2.5.xsd\">" +

                        "   <!-- Note all ids should be based on fully qualified names (interfaces where"
                        + "       this is not ambiguous) and all classes should have an interface. -->" +

                        "   <bean id=\"uk.gov.moj.sdt.utils.mbeans.api.ISdtMetricsMBean\" "
                        + "class=\"uk.gov.moj.sdt.utils.mbeans.SdtMetricsMBean\" />" +

                        "   <xsi:some-tag some-attribute=\"some value\">"
                        + "       <aop:some-other-tag some-attribute=\"some value\"/>"
                        + "       <aop:some-other-tag some-attribute=\"some value\">" + "       </aop:some-other-tag>"
                        + "   </xsi:some-tag>" + "</beans>";

        final Map<String, String> allNamespaces = XmlNamespaceUtils.extractAllNamespaces (xml, null);

        String xmlFragment =
                "   <!--Comment--><xsi:some-tag some-attribute=\"some value\">"
                        + "       <aop:some-other-tag some-attribute=\"some value\"/>"
                        + "       <aop:some-other-tag some-attribute=\"some value\">" + "       </aop:some-other-tag>"
                        + "   </xsi:some-tag>";

        final Map<String, String> matched = XmlNamespaceUtils.findMatchingNamespaces (xmlFragment, allNamespaces);

        // Remove namespaces to facilitate adding new namespaces.
        xmlFragment = XmlNamespaceUtils.removeEmbeddedNamespaces (xmlFragment);

        // Translate any embedded default namespaces to their target application equivalent.
        xmlFragment = XmlNamespaceUtils.translateDefaultNamespaces (xmlFragment, null);

        final String result = XmlNamespaceUtils.addNamespaces (xmlFragment, matched);

        // CHECKSTYLE:OFF
        final String expected =
                "   <!--Comment--><xsi:some-tag xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" some-attribute=\"some value\">"
                        + "       <aop:some-other-tag xmlns:aop='http://www.springframework.org/schema/aop' some-attribute=\"some value\"/>"
                        + "       <aop:some-other-tag xmlns:aop='http://www.springframework.org/schema/aop' some-attribute=\"some value\">"
                        + "       </aop:some-other-tag>" + "   </xsi:some-tag>";

        Assert.assertEquals ("Generated xml fragment is incorrect", expected, result);

        // CHECKSTYLE:ON

    }

    /**
     * Test addition of namespace to xml which already has namespaces present. These must be removed first.
     */
    @Test
    public void testAddNamespacesAlreadyPresentNoSpaces ()
    {

        // Define text raw xml.
        final String xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +

                "<beans xmlns=\"http://www.springframework.org/schema/beans\""
                        + "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
                        + "xmlns:aop=\"http://www.springframework.org/schema/aop\""
                        + "    xsi:schemaLocation=\"http://www.springframework.org/schema/beans"
                        + "       http://www.springframework.org/schema/beans/spring-beans-3.1.xsd"
                        + "       http://www.springframework.org/schema/aop"
                        + "       http://www.springframework.org/schema/aop/spring-aop-2.5.xsd\">" +

                        "   <!-- Note all ids should be based on fully qualified names (interfaces where"
                        + "       this is not ambiguous) and all classes should have an interface. -->" +

                        "   <bean id=\"uk.gov.moj.sdt.utils.mbeans.api.ISdtMetricsMBean\" "
                        + "class=\"uk.gov.moj.sdt.utils.mbeans.SdtMetricsMBean\" />" +

                        "   <xsi:some-tag some-attribute=\"some value\">"
                        + "       <aop:some-other-tag some-attribute=\"some value\"/>"
                        + "       <aop:some-other-tag some-attribute=\"some value\">" + "       </aop:some-other-tag>"
                        + "   </xsi:some-tag>" + "</beans>";

        final Map<String, String> allNamespaces = XmlNamespaceUtils.extractAllNamespaces (xml, null);

        String xmlFragment =
                "   <!--Comment--><xsi:some-tag   xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">"
                        + "<aop:some-other-tag some-attribute=\"some value\"/>"
                        + "       <aop:some-other-tag some-attribute=\"some value\">" + "       </aop:some-other-tag>"
                        + "   </xsi:some-tag>";

        final Map<String, String> matched = XmlNamespaceUtils.findMatchingNamespaces (xmlFragment, allNamespaces);

        // Remove namespaces to facilitate adding new namespaces.
        xmlFragment = XmlNamespaceUtils.removeEmbeddedNamespaces (xmlFragment);

        // Translate any embedded default namespaces to their target application equivalent.
        xmlFragment = XmlNamespaceUtils.translateDefaultNamespaces (xmlFragment, null);

        final String result = XmlNamespaceUtils.addNamespaces (xmlFragment, matched);

        // CHECKSTYLE:OFF
        final String expected =
                "   <!--Comment--><xsi:some-tag xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">"
                        + "<aop:some-other-tag xmlns:aop=\"http://www.springframework.org/schema/aop\" some-attribute=\"some value\"/>"
                        + "       <aop:some-other-tag xmlns:aop=\"http://www.springframework.org/schema/aop\" some-attribute=\"some value\">"
                        + "       </aop:some-other-tag>" + "   </xsi:some-tag>";

        Assert.assertEquals ("Generated xml fragment is incorrect", expected, result);

        // CHECKSTYLE:ON

    }

    /**
     * Test addition of namespace to xml where namespaces already present on request (which CLX puts there) and with no
     * attributes on request.
     */
    @Test
    public void testAddNamespacesAlreadyPresentNoAttributes ()
    {
        // Define text raw xml.
        final String xml =
                "<bul:bulkRequest xmlns:bul=\"http://ws.sdt.moj.gov.uk/2013/sdt/BulkRequestSchema\""
                        + "    xmlns:cla=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\" "
                        + "    xmlns:bas=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\""
                        + "    xmlns:jud=\"http://ws.sdt.moj.gov.uk/2013/mcol/JudgmentSchema\""
                        + "    xmlns:cla1=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimStatusUpdateSchema\""
                        + "    xmlns:war=\"http://ws.sdt.moj.gov.uk/2013/mcol/WarrantSchema\">" + "    <bul:header>"
                        + "        <bul:sdtCustomerId>12345678</bul:sdtCustomerId>"
                        + "        <bul:targetApplicationId>mcol</bul:targetApplicationId>"
                        + "        <bul:requestCount>1</bul:requestCount>"
                        + "        <bul:customerReference>1</bul:customerReference>" + "    </bul:header>"
                        + "    <bul:requests>" + "        <bul:request requestType=\"mcolClaimStatusUpdate\""
                        + "            requestId=\"1-1\">" + "            <bul:mcolClaimStatusUpdate>"
                        + "                <cla1:claimNumber>claim123</cla1:claimNumber>"
                        + "                <cla1:defendantId>1</cla1:defendantId>"
                        + "                <cla1:notificationType>MP</cla1:notificationType>"
                        + "                <cla1:paidInFullDate>2012-01-01</cla1:paidInFullDate>"
                        + "            </bul:mcolClaimStatusUpdate>" + "        </bul:request>" + "    </bul:requests>"
                        + "</bul:bulkRequest>";

        final Map<String, String> allNamespaces = XmlNamespaceUtils.extractAllNamespaces (xml, null);

        String xmlFragment =
                "       <bul:mcolClaimStatusUpdate > " + "  <cla1:claimNumber>claim123</cla1:claimNumber>"
                        + "<cla1:defendantId>1</cla1:defendantId>"
                        + "<cla1:notificationType>MP</cla1:notificationType>"
                        + "<cla1:paidInFullDate>2012-01-01</cla1:paidInFullDate>" + "</bul:mcolClaimStatusUpdate>";

        final Map<String, String> matched = XmlNamespaceUtils.findMatchingNamespaces (xmlFragment, allNamespaces);

        // Remove namespaces to facilitate adding new namespaces.
        xmlFragment = XmlNamespaceUtils.removeEmbeddedNamespaces (xmlFragment);

        // Translate any embedded default namespaces to their target application equivalent.
        xmlFragment = XmlNamespaceUtils.translateDefaultNamespaces (xmlFragment, null);

        final String result = XmlNamespaceUtils.addNamespaces (xmlFragment, matched);

        final String expected =
                "       <bul:mcolClaimStatusUpdate "
                        + "xmlns:bul=\"http://ws.sdt.moj.gov.uk/2013/sdt/BulkRequestSchema\" >"
                        + "   <cla1:claimNumber xmlns:cla1=\"http://ws.sdt.moj.gov.uk/2013/mcol/"
                        + "ClaimStatusUpdateSchema\"" + ">claim123</cla1:claimNumber>"
                        + "<cla1:defendantId xmlns:cla1=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimStatusUpdateSchema\""
                        + ">1</cla1:defendantId>"
                        + "<cla1:notificationType xmlns:cla1=\"http://ws.sdt.moj.gov.uk/2013/mcol/"
                        + "ClaimStatusUpdateSchema\">MP</cla1:notificationType>"
                        + "<cla1:paidInFullDate xmlns:cla1=\"http://ws.sdt.moj.gov.uk/2013/mcol/"
                        + "ClaimStatusUpdateSchema\">2012-01-01</cla1:paidInFullDate>" + "</bul:mcolClaimStatusUpdate>";

        Assert.assertEquals ("Generated xml fragment is incorrect", expected, result);
    }

    /**
     * Test addition of namespace to xml where namespaces already present on request (which CLX puts there) and with
     * attributes on request.
     */
    @Test
    public void testAddNamespacesAlreadyPresentAndAttributes ()
    {
        // Define text raw xml.
        final String xml =
                "<bul:bulkRequest xmlns:bul=\"http://ws.sdt.moj.gov.uk/2013/sdt/BulkRequestSchema\""
                        + "    xmlns:cla=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\" "
                        + "    xmlns:bas=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\""
                        + "    xmlns:jud=\"http://ws.sdt.moj.gov.uk/2013/mcol/JudgmentSchema\""
                        + "    xmlns:cla1=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimStatusUpdateSchema\""
                        + "    xmlns:war=\"http://ws.sdt.moj.gov.uk/2013/mcol/WarrantSchema\">" + "    <bul:header>"
                        + "        <bul:sdtCustomerId>12345678</bul:sdtCustomerId>"
                        + "        <bul:targetApplicationId>mcol</bul:targetApplicationId>"
                        + "        <bul:requestCount>1</bul:requestCount>"
                        + "        <bul:customerReference>1</bul:customerReference>" + "    </bul:header>"
                        + "    <bul:requests>" + "        <bul:request requestType=\"mcolClaimStatusUpdate\""
                        + "            requestId=\"1-1\">" + "            <bul:mcolClaimStatusUpdate>"
                        + "                <cla1:claimNumber>claim123</cla1:claimNumber>"
                        + "                <cla1:defendantId>1</cla1:defendantId>"
                        + "                <cla1:notificationType>MP</cla1:notificationType>"
                        + "                <cla1:paidInFullDate>2012-01-01</cla1:paidInFullDate>"
                        + "            </bul:mcolClaimStatusUpdate>" + "        </bul:request>" + "    </bul:requests>"
                        + "</bul:bulkRequest>";

        final Map<String, String> allNamespaces = XmlNamespaceUtils.extractAllNamespaces (xml, null);

        String xmlFragment =
                "       <bul:mcolClaimStatusUpdate "
                        + "xmlns:bul=\"http://ws.sdt.moj.gov.uk/2013/sdt/BulkRequestSchema\"  "
                        + "xmlns:war=\"http://ws.sdt.moj.gov.uk/2013/mcol/WarrantSchema\" somAttribute=\"abcd\">"
                        + "  <cla1:claimNumber>claim123</cla1:claimNumber>" + "<cla1:defendantId>1</cla1:defendantId>"
                        + "<cla1:notificationType>MP</cla1:notificationType>"
                        + "<cla1:paidInFullDate>2012-01-01</cla1:paidInFullDate>" + "</bul:mcolClaimStatusUpdate>";

        final Map<String, String> matched = XmlNamespaceUtils.findMatchingNamespaces (xmlFragment, allNamespaces);

        // Remove namespaces to facilitate adding new namespaces.
        xmlFragment = XmlNamespaceUtils.removeEmbeddedNamespaces (xmlFragment);

        // Translate any embedded default namespaces to their target application equivalent.
        xmlFragment = XmlNamespaceUtils.translateDefaultNamespaces (xmlFragment, null);

        final String result = XmlNamespaceUtils.addNamespaces (xmlFragment, matched);

        final String expected =
                "       <bul:mcolClaimStatusUpdate "
                        + "xmlns:bul=\"http://ws.sdt.moj.gov.uk/2013/sdt/BulkRequestSchema\" "
                        + "somAttribute=\"abcd\">"
                        + "  <cla1:claimNumber xmlns:cla1=\"http://ws.sdt.moj.gov.uk/2013/mcol/"
                        + "ClaimStatusUpdateSchema\">claim123</cla1:claimNumber>"
                        + "<cla1:defendantId xmlns:cla1=\"http://ws.sdt.moj.gov.uk/2013/mcol/"
                        + "ClaimStatusUpdateSchema\">1</cla1:defendantId><cla1:notificationType "
                        + "xmlns:cla1=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimStatusUpdateSchema\">"
                        + "MP</cla1:notificationType><cla1:paidInFullDate "
                        + "xmlns:cla1=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimStatusUpdateSchema\">"
                        + "2012-01-01</cla1:paidInFullDate></bul:mcolClaimStatusUpdate>";

        Assert.assertEquals ("Generated xml fragment is incorrect", expected, result);
    }

    /**
     * Test addition of namespace to xml where namespaces already present on request (which CLX puts there) and with
     * attributes on request and an awkward space.
     */
    @Test
    public void testAddNamespacesAlreadyPresentAndAttributesWithSpace ()
    {
        // Define text raw xml.
        final String xml =
                "<bul:bulkRequest xmlns:bul=\"http://ws.sdt.moj.gov.uk/2013/sdt/BulkRequestSchema\""
                        + "    xmlns:cla=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimSchema\" "
                        + "    xmlns:bas=\"http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema\""
                        + "    xmlns:jud=\"http://ws.sdt.moj.gov.uk/2013/mcol/JudgmentSchema\""
                        + "    xmlns:cla1=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimStatusUpdateSchema\""
                        + "    xmlns:war=\"http://ws.sdt.moj.gov.uk/2013/mcol/WarrantSchema\">" + "    <bul:header>"
                        + "        <bul:sdtCustomerId>12345678</bul:sdtCustomerId>"
                        + "        <bul:targetApplicationId>mcol</bul:targetApplicationId>"
                        + "        <bul:requestCount>1</bul:requestCount>"
                        + "        <bul:customerReference>1</bul:customerReference>" + "    </bul:header>"
                        + "    <bul:requests>" + "        <bul:request requestType=\"mcolClaimStatusUpdate\""
                        + "            requestId=\"1-1\">" + "            <bul:mcolClaimStatusUpdate>"
                        + "                <cla1:claimNumber>claim123</cla1:claimNumber>"
                        + "                <cla1:defendantId>1</cla1:defendantId>"
                        + "                <cla1:notificationType>MP</cla1:notificationType>"
                        + "                <cla1:paidInFullDate>2012-01-01</cla1:paidInFullDate>"
                        + "            </bul:mcolClaimStatusUpdate>" + "        </bul:request>" + "    </bul:requests>"
                        + "</bul:bulkRequest>";

        final Map<String, String> allNamespaces = XmlNamespaceUtils.extractAllNamespaces (xml, null);

        String xmlFragment =
                "       <bul:mcolClaimStatusUpdate "
                        + "xmlns:bul=\"http://ws.sdt.moj.gov.uk/2013/sdt/BulkRequestSchema\"  "
                        + "xmlns:war=\"http://ws.sdt.moj.gov.uk/2013/mcol/WarrantSchema\" somAttribute=\"abcd\" >"
                        + "  <cla1:claimNumber>claim123</cla1:claimNumber>" + "<cla1:defendantId>1</cla1:defendantId>"
                        + "<cla1:notificationType>MP</cla1:notificationType>"
                        + "<cla1:paidInFullDate>2012-01-01</cla1:paidInFullDate>" + "</bul:mcolClaimStatusUpdate>";

        final Map<String, String> matched = XmlNamespaceUtils.findMatchingNamespaces (xmlFragment, allNamespaces);

        // Remove namespaces to facilitate adding new namespaces.
        xmlFragment = XmlNamespaceUtils.removeEmbeddedNamespaces (xmlFragment);

        // Translate any embedded default namespaces to their target application equivalent.
        xmlFragment = XmlNamespaceUtils.translateDefaultNamespaces (xmlFragment, null);

        final String result = XmlNamespaceUtils.addNamespaces (xmlFragment, matched);

        // CHECKSTYLE:OFF
        final String expected =
                "       <bul:mcolClaimStatusUpdate xmlns:bul=\"http://ws.sdt.moj.gov.uk/2013/sdt/BulkRequestSchema\" somAttribute=\"abcd\" >  <cla1:claimNumber xmlns:cla1=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimStatusUpdateSchema\">claim123</cla1:claimNumber><cla1:defendantId xmlns:cla1=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimStatusUpdateSchema\">1</cla1:defendantId><cla1:notificationType xmlns:cla1=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimStatusUpdateSchema\">MP</cla1:notificationType><cla1:paidInFullDate xmlns:cla1=\"http://ws.sdt.moj.gov.uk/2013/mcol/ClaimStatusUpdateSchema\">2012-01-01</cla1:paidInFullDate></bul:mcolClaimStatusUpdate>";
        // CHECKSTYLE:ON

        Assert.assertEquals ("Generated xml fragment is incorrect", expected, result);
    }
}