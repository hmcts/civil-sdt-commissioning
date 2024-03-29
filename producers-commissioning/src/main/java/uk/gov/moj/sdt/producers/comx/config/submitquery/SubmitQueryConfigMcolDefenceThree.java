package uk.gov.moj.sdt.producers.comx.config.submitquery;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.gov.moj.sdt.domain.ErrorLog;
import uk.gov.moj.sdt.domain.SubmitQueryRequest;

@Configuration
@EnableAutoConfiguration
public class SubmitQueryConfigMcolDefenceThree {

    @Bean("uk.gov.moj.sdt.domain.api.ISubmitQueryRequestMCOLDefence3")
    public SubmitQueryRequest submitQueryRequest() {
        SubmitQueryRequest submitQueryRequest = new SubmitQueryRequest();
        submitQueryRequest.setResultCount(10);
        submitQueryRequest.setErrorLog(errorLog());
        submitQueryRequest.setStatus("Error");
        String response = """
                 <sub:mcolDefenceDetail xmlns:sub="http://ws.sdt.moj.gov.uk/2013/sdt/SubmitQueryResponseSchema" xmlns:mquer="http://ws.sdt.moj.gov.uk/2013/mcol/QuerySchema">
            		<mquer:claimNumber>A0ZZ00DE</mquer:claimNumber>
            		<mquer:defendantResponse defendantId="1">
                      <mquer:filedDate>2014-01-02</mquer:filedDate>
                		<mquer:eventCreatedDateOnMcol>2014-01-02T10:04:21</mquer:eventCreatedDateOnMcol>
                      <mquer:raisedOnMcol>false</mquer:raisedOnMcol>
                		<mquer:responseType>DE</mquer:responseType>
            		</mquer:defendantResponse>
            	</sub:mcolDefenceDetail>
            	<sub:mcolDefenceDetail xmlns:sub="http://ws.sdt.moj.gov.uk/2013/sdt/SubmitQueryResponseSchema" xmlns:mquer="http://ws.sdt.moj.gov.uk/2013/mcol/QuerySchema">
            		<mquer:claimNumber>A0ZZ00C5</mquer:claimNumber>
            		<mquer:defendantResponse defendantId="1">
                      <mquer:filedDate>2014-01-02</mquer:filedDate>
                		<mquer:eventCreatedDateOnMcol>2014-01-02T11:45:01</mquer:eventCreatedDateOnMcol>
                      <mquer:raisedOnMcol>false</mquer:raisedOnMcol>
                		<mquer:responseType>PA</mquer:responseType>
            		</mquer:defendantResponse>
            	</sub:mcolDefenceDetail>
            	<sub:mcolDefenceDetail xmlns:sub="http://ws.sdt.moj.gov.uk/2013/sdt/SubmitQueryResponseSchema" xmlns:mquer="http://ws.sdt.moj.gov.uk/2013/mcol/QuerySchema">
            		<mquer:claimNumber>A0ZZ00FE</mquer:claimNumber>
            		<mquer:defendantResponse defendantId="1">
                      <mquer:filedDate>2014-01-02</mquer:filedDate>
            			<mquer:eventCreatedDateOnMcol>2014-01-03T14:21:17</mquer:eventCreatedDateOnMcol>
                      <mquer:raisedOnMcol>false</mquer:raisedOnMcol>
            			<mquer:responseType>DC</mquer:responseType>
            		</mquer:defendantResponse>
            	</sub:mcolDefenceDetail>
            	<sub:mcolDefenceDetail xmlns:sub="http://ws.sdt.moj.gov.uk/2013/sdt/SubmitQueryResponseSchema" xmlns:mquer="http://ws.sdt.moj.gov.uk/2013/mcol/QuerySchema">
            		<mquer:claimNumber>A0ZZ00HH</mquer:claimNumber>
            		<mquer:defendantResponse defendantId="1">
                      <mquer:filedDate>2014-01-02</mquer:filedDate>
                		<mquer:eventCreatedDateOnMcol>2014-01-03T18:03:32</mquer:eventCreatedDateOnMcol>
                      <mquer:raisedOnMcol>false</mquer:raisedOnMcol>
                		<mquer:responseType>DE</mquer:responseType>
            		</mquer:defendantResponse>
            	</sub:mcolDefenceDetail>
            	<sub:mcolDefenceDetail xmlns:sub="http://ws.sdt.moj.gov.uk/2013/sdt/SubmitQueryResponseSchema" xmlns:mquer="http://ws.sdt.moj.gov.uk/2013/mcol/QuerySchema">
            		<mquer:claimNumber>A0ZZ00HH</mquer:claimNumber>
            		<mquer:defendantResponse defendantId="2">
                      <mquer:filedDate>2014-01-02</mquer:filedDate>
            			<mquer:eventCreatedDateOnMcol>2014-01-03T09:55:19</mquer:eventCreatedDateOnMcol>
                      <mquer:raisedOnMcol>false</mquer:raisedOnMcol>
            			<mquer:responseType>DC</mquer:responseType>
            		</mquer:defendantResponse>
            	</sub:mcolDefenceDetail>
            	<sub:mcolDefenceDetail xmlns:sub="http://ws.sdt.moj.gov.uk/2013/sdt/SubmitQueryResponseSchema" xmlns:mquer="http://ws.sdt.moj.gov.uk/2013/mcol/QuerySchema">
            		<mquer:claimNumber>A0ZZ00HE</mquer:claimNumber>
            		<mquer:defendantResponse defendantId="1">
                      <mquer:filedDate>2014-01-03</mquer:filedDate>
            			<mquer:eventCreatedDateOnMcol>2014-01-03T14:31:42</mquer:eventCreatedDateOnMcol>
                      <mquer:raisedOnMcol>false</mquer:raisedOnMcol>
            			<mquer:responseType>DE</mquer:responseType>
            		</mquer:defendantResponse>
            	</sub:mcolDefenceDetail>
            	<sub:mcolDefenceDetail xmlns:sub="http://ws.sdt.moj.gov.uk/2013/sdt/SubmitQueryResponseSchema" xmlns:mquer="http://ws.sdt.moj.gov.uk/2013/mcol/QuerySchema">
            		<mquer:claimNumber>A0ZZ00H8</mquer:claimNumber>
            		<mquer:defendantResponse defendantId="1">
                      <mquer:filedDate>2014-01-03</mquer:filedDate>
            			<mquer:eventCreatedDateOnMcol>2014-01-03T14:31:42</mquer:eventCreatedDateOnMcol>
                      <mquer:raisedOnMcol>false</mquer:raisedOnMcol>
            			<mquer:responseType>DE</mquer:responseType>
            		</mquer:defendantResponse>
            	</sub:mcolDefenceDetail>
            	<sub:mcolDefenceDetail xmlns:sub="http://ws.sdt.moj.gov.uk/2013/sdt/SubmitQueryResponseSchema" xmlns:mquer="http://ws.sdt.moj.gov.uk/2013/mcol/QuerySchema">
            		<mquer:claimNumber>A0ZZ00HK</mquer:claimNumber>
            		<mquer:defendantResponse defendantId="1">
                      <mquer:filedDate>2014-01-03</mquer:filedDate>
            			<mquer:eventCreatedDateOnMcol>2014-01-03T15:12:54</mquer:eventCreatedDateOnMcol>
                      <mquer:raisedOnMcol>false</mquer:raisedOnMcol>
            			<mquer:responseType>PA</mquer:responseType>
            		</mquer:defendantResponse>
            	</sub:mcolDefenceDetail>
            	<sub:mcolDefenceDetail xmlns:sub="http://ws.sdt.moj.gov.uk/2013/sdt/SubmitQueryResponseSchema" xmlns:mquer="http://ws.sdt.moj.gov.uk/2013/mcol/QuerySchema">
            		<mquer:claimNumber>A0ZZ0018</mquer:claimNumber>
            		<mquer:defendantResponse defendantId="1">
                      <mquer:filedDate>2014-01-03</mquer:filedDate>
            			<mquer:eventCreatedDateOnMcol>2014-01-03T16:02:46</mquer:eventCreatedDateOnMcol>
                      <mquer:raisedOnMcol>false</mquer:raisedOnMcol>
            			<mquer:responseType>DC</mquer:responseType>
            		</mquer:defendantResponse>
            	</sub:mcolDefenceDetail>
            	<sub:mcolDefenceDetail xmlns:sub="http://ws.sdt.moj.gov.uk/2013/sdt/SubmitQueryResponseSchema" xmlns:mquer="http://ws.sdt.moj.gov.uk/2013/mcol/QuerySchema">
            		<mquer:claimNumber>A0ZZ2349</mquer:claimNumber>
            		<mquer:defendantResponse defendantId="1">
                      <mquer:filedDate>2014-01-03</mquer:filedDate>
            			<mquer:eventCreatedDateOnMcol>2014-01-03T17:16:15</mquer:eventCreatedDateOnMcol>
                      <mquer:raisedOnMcol>false</mquer:raisedOnMcol>
            			<mquer:responseType>DC</mquer:responseType>
            		</mquer:defendantResponse>
            	</sub:mcolDefenceDetail>
           """;
        submitQueryRequest.setTargetApplicationResponse(response);
        return submitQueryRequest;
    }

    private ErrorLog errorLog() {
        ErrorLog errorLog = new ErrorLog();
        errorLog.setErrorCode("78");
        errorLog.setErrorText("Results truncated - maximum number allowed reached.");
        return errorLog;
    }
}
