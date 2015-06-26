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
 * $Id: BulkFeedbackRequestXsdTest.java 16385 2013-05-22 09:56:52Z agarwals $
 * $LastChangedRevision: 16385 $
 * $LastChangedDate: 2013-05-22 10:56:52 +0100 (Wed, 22 May 2013) $
 * $LastChangedBy: holmessm $ */
package uk.gov.moj.sdt.producers.api;

import org.junit.Test;

import uk.gov.moj.sdt.utils.AbstractSdtXmlTestBase;

/**
 * Test case for Submit Query Request Generic Xsd.
 * 
 * @author Simon Holmes
 * 
 */
// CHECKSTYLE:OFF
public class SubmitQueryRequestTargetAppGenericXsdTest extends AbstractSdtXmlTestBase
// CHECKSTYLE:ON
{
    /**
     * The name of the xsd.
     */
    private static final String XSD_NAME = "SubmitQueryRequestTargetAppGeneric";

    /**
     * The name of the folder where XSD is stored.
     */
    private static final String XSD_DIR = "SubmitQueryRequestResponse/";

    /**
     * The name of the folder where all valid/invalid XML is stored.
     */
    private static final String XML_DIR = "SubmitQueryRequestResponse/";

    /**
     * The path of the xsd file.
     */
    private static final String XSD_PATH = XSD_DIR + XSD_NAME + ".xsd";

    /**
     * Tests XML file is valid.
     */
    @Test
    public void testValidXml ()
    {
        final String condition = "Valid";
        final String xmlPath = XML_DIR + XSD_NAME + condition + AbstractSdtXmlTestBase.XML_FILE_SUFFIX;
        this.validateXsd (xmlPath, XSD_PATH, null);
    }

    /**
     * Tests that expected errors are reported for missing mandatory fields.
     */
    @Test
    public void testInvalidXmlMandatoryMissing ()
    {
        final String condition = "MandatoryMissing";
        final String xmlPath = XML_DIR + XSD_NAME + condition + AbstractSdtXmlTestBase.XML_FILE_SUFFIX;
        final String errorFilePathname = XML_DIR + XSD_NAME + condition + AbstractSdtXmlTestBase.ERROR_FILE_SUFFIX;

        this.validateXsd (xmlPath, XSD_PATH, errorFilePathname);
    }
}
