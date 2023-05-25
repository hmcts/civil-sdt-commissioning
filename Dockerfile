 # renovate: datasource=github-releases depName=microsoft/ApplicationInsights-Java
ARG APP_INSIGHTS_AGENT_VERSION=3.4.13
FROM hmctspublic.azurecr.io/base/java:17-distroless

COPY build/libs/civil-sdt-commissioning.jar /opt/app/

EXPOSE 4750
CMD [ "civil-sdt-commissioning.jar" ]
