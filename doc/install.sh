#!/bin/bash

pwd > pwd.txt
DIR=`cat pwd.txt`

# --データベース作成
sudo -u postgres psql < dump/CREATE_DATABASE.sql
PGPASSWORD=password psql -h 127.0.0.1 -U userid traning_report < dump/training_report.ddl
PGPASSWORD=password psql -h 127.0.0.1 -U userid traning_report < dump/holiday.txt

# Tomcat連携
cp conf/trainingreport.conf /etc/nginx/default.d/.
# systemctl restart nginx

# TrainingReport
# systemctl stop tomcat
cd /usr/local/tomcat/webapps
rm -rf TrainingReport.war
rm -rf TrainingReport
cp $DIR/TrainingReport.war /usr/local/tomcat/webapps/.
chown tomcat:tomcat /usr/local/tomcat/webapps/TrainingReport.war
# systemctl start tomcat

rm pwd.txt
