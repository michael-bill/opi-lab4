FROM quay.io/wildfly/wildfly:26.1.2.Final-jdk17

ENV DEPLOYMENT_DIR /opt/jboss/wildfly/standalone/deployments/
ENV WILDFLY_USER admin
ENV WILDFLY_PASS admin

COPY target/*.war $DEPLOYMENT_DIR

RUN /opt/jboss/wildfly/bin/add-user.sh user1 Qwerty123
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
