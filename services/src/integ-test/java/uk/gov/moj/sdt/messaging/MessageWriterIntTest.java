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
package uk.gov.moj.sdt.messaging;

import java.text.SimpleDateFormat;

import javax.jms.JMSException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.UncategorizedJmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.gov.moj.sdt.messaging.api.ISdtMessage;

/**
 * IntegrationTest class for testing the MessageWriter implementation.
 * 
 * @author Manoj Kulkarni
 * 
 */
@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations = {"classpath*:**/applicationContext.xml", "/uk/gov/moj/sdt/dao/spring.context.xml",
        "/uk/gov/moj/sdt/consumers/spring.context.integ.test.xml", "/uk/gov/moj/sdt/transformers/spring.context.xml",
        "classpath*:/**/spring*.xml", "/uk/gov/moj/sdt/dao/spring*.xml", "/uk/gov/moj/sdt/utils/spring*.xml",
        "/uk/gov/moj/sdt/utils/transaction/synchronizer/spring*.xml"})
public class MessageWriterIntTest extends AbstractJUnit4SpringContextTests
{
    /**
     * Logger object.
     */
    private static final Logger LOG = LoggerFactory.getLogger (MessageWriterIntTest.class);

    /**
     * Test method to test the sending of message.
     * 
     * @throws JMSException exception
     * @throws InterruptedException exception
     * 
     */
    @Test
    public void testQueueMessage () throws JMSException, InterruptedException
    {
        // Get message writer from Spring.
        final MessageWriter messageWriter =
                (MessageWriter) this.applicationContext.getBean ("uk.gov.moj.sdt.messaging.api.IMessageWriter");

        final SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyyMMddHHmmss");

        final JmsTemplate jmsTemplate = (JmsTemplate) this.applicationContext.getBean ("jmsTemplate");

        // Send the first message.
        final ISdtMessage message1 = new SdtMessage ();
        final String strMessage1 =
                "TestMessage1" + dateFormat.format (new java.util.Date (System.currentTimeMillis ()));
        message1.setSdtRequestReference (strMessage1);
        messageWriter.queueMessage (message1);

        // Send the second message.
        final ISdtMessage message2 = new SdtMessage ();
        final String strMessage2 =
                "TestMessage2" + dateFormat.format (new java.util.Date (System.currentTimeMillis ()));
        message2.setSdtRequestReference (strMessage2);
        messageWriter.queueMessage (message2);

        // Wait for 10 seconds before checking the queue.
        Thread.sleep (10000);

        /* jmsTemplate.browse ("JMSTestQueue", new BrowserCallback<Object> ()
         * {
         * 
         * @Override
         * public Object doInJms (final Session session, final QueueBrowser browser) throws JMSException
         * {
         * 
         * @SuppressWarnings ("rawtypes") final Enumeration enumeration = browser.getEnumeration ();
         * if (enumeration.hasMoreElements ())
         * {
         * Assert.fail ("There should be no more messages as all messages are read");
         * }
         * Assert.assertTrue (true);
         * 
         * return null;
         * }
         * 
         * }); */

    }

    /**
     * Test method to test failure behaviour when ACTIVE MQ not running.
     * 
     * @throws JMSException exception
     * 
     */
    @Test
    public void testActiveMqDown () throws JMSException
    {
        // Get message writer from Spring.
        final MessageWriter messageWriter =
                (MessageWriter) this.applicationContext.getBean ("uk.gov.moj.sdt.messaging.api.IMessageWriterBad");

        final SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyyMMddHHmmss");

        // Send the message.
        final ISdtMessage message = new SdtMessage ();
        message.setSdtRequestReference ("Test message");

        try
        {
            messageWriter.queueMessage (message);
            Assert.fail ("Expected exception not thrown.");
        }
        catch (final UncategorizedJmsException e)
        {
            // Test has worked - swallow exception.
            Assert.assertTrue (true);
        }
    }
}
