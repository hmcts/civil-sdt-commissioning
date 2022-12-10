/* Copyrights and Licenses
 *
 * Copyright (c) 2010 by the Ministry of Justice. All rights reserved.
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
package uk.gov.moj.sdt.transformers;

import org.springframework.stereotype.Component;
import uk.gov.moj.sdt.domain.ErrorLog;
import uk.gov.moj.sdt.domain.IndividualRequest;
import uk.gov.moj.sdt.domain.api.IErrorLog;
import uk.gov.moj.sdt.domain.api.IIndividualRequest;
import uk.gov.moj.sdt.domain.api.IIndividualRequest.IndividualRequestStatus;
import uk.gov.moj.sdt.transformers.api.ITransformer;
import uk.gov.moj.sdt.ws._2013.sdt.baseschema.ErrorType;
import uk.gov.moj.sdt.ws._2013.sdt.baseschema.StatusCodeType;
import uk.gov.moj.sdt.ws._2013.sdt.baseschema.StatusType;
import uk.gov.moj.sdt.ws._2013.sdt.baseschema.UpdateStatusCodeType;
import uk.gov.moj.sdt.ws._2013.sdt.baseschema.UpdateStatusType;
import uk.gov.moj.sdt.ws._2013.sdt.individualupdaterequestschema.HeaderType;
import uk.gov.moj.sdt.ws._2013.sdt.individualupdaterequestschema.UpdateRequestType;
import uk.gov.moj.sdt.ws._2013.sdt.individualupdateresponseschema.UpdateResponseType;

import static uk.gov.moj.sdt.domain.api.IIndividualRequest.IndividualRequestStatus.RESUBMIT_MESSAGE;

/**
 * Maps UpdateItem JAXB object tree to domain object and vice-versa.
 *
 * @author Manoj Kulkarni
 */
@Component("UpdateItemTransformer")
public class UpdateItemTransformer extends AbstractTransformer implements
    ITransformer<UpdateRequestType, UpdateResponseType, IIndividualRequest, IIndividualRequest> {
    @Override
    public IIndividualRequest transformJaxbToDomain(final UpdateRequestType updateRequest) {
        final IIndividualRequest individualRequest = new IndividualRequest();
        final UpdateStatusType status = updateRequest.getStatus();
        final UpdateStatusCodeType statusCode = status.getCode();

        final HeaderType headerType = updateRequest.getHeader();
        individualRequest.setSdtRequestReference(headerType.getSdtRequestId());

        if (UpdateStatusCodeType.REJECTED.equals(statusCode)) {
            final ErrorType errorType = status.getError();
            final IErrorLog errorLog = new ErrorLog(errorType.getCode(), errorType.getDescription());
            individualRequest.markRequestAsRejected(errorLog);
        } else if (UpdateStatusCodeType.ACCEPTED.equals(statusCode)) {
            individualRequest.markRequestAsAccepted();
        } else if (UpdateStatusCodeType.RESUBMIT_MESSAGE.equals(statusCode)) {
            individualRequest.setRequestStatus(RESUBMIT_MESSAGE.getStatus());
        }

        return individualRequest;
    }

    @Override
    public UpdateResponseType transformDomainToJaxb(final IIndividualRequest individualRequest) {
        final UpdateResponseType updateResponseType = new UpdateResponseType();
        final StatusType status = new StatusType();
        status.setCode(StatusCodeType.OK);

        updateResponseType.setStatus(status);
        return updateResponseType;
    }

}
