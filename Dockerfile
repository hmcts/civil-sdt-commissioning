 # renovate: datasource=github-releases depName=microsoft/ApplicationInsights-Java
ARG APP_INSIGHTS_AGENT_VERSION=3.7.3
FROM hmctspublic.azurecr.io/base/java:17-distroless

USER hmcts
COPY lib/applicationinsights.json /opt/app
COPY build/libs/civil-sdt-commissioning.jar /opt/app/

EXPOSE 4750
CMD [ "civil-sdt-commissioning.jar" ]
