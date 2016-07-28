
GIT_HOME=/usr/bin

CURRENT_FOLDER_PATH=$(pwd)
UP_FOLDER_PATH=${CURRENT_FOLDER_PATH%/*}

TOMCAT_HOME=${UP_FOLDER_PATH}/apache-tomcat-8.0.36

echo ${CURRENT_FOLDER_PATH}
echo ${UP_FOLDER_PATH}

rm -rf ${UP_FOLDER_PATH}/code/*
rm -rf ${UP_FOLDER_PATH}/code/.gitignore
rm -rf ${UP_FOLDER_PATH}/code/.git

${GIT_HOME}/git clone http://gitlab.baidu.com/fenglei/fusion.git ${UP_FOLDER_PATH}/code/

mkdir -p ${UP_FOLDER_PATH}/code/
cd ${UP_FOLDER_PATH}/code/

sh build.sh online

cd -

rm -rf ${TOMCAT_HOME}/webapps/fusion-api/*
#/home/work/opt/tomcat/fusion-api/code/fusion-api/api-web/target
mkdir -p ${TOMCAT_HOME}/webapps/fusion-api/
cp ${UP_FOLDER_PATH}/code/fusion-api/fusion-api-server/target/fusion-api.war ${TOMCAT_HOME}/webapps/fusion-api/

unzip ${TOMCAT_HOME}/webapps/fusion-api/fusion-api.war -d ${TOMCAT_HOME}/webapps/fusion-api/

# restart tomcat
ps -ef | grep tomcat | grep fusion-api | awk '{print $2}' | xargs kill -9

#echo ${TOMCAT_HOME}/bin/
cd ${TOMCAT_HOME}/bin/

sleep 3

sh startup.sh

cd -

echo fusion-api deployed SUCCESS

#tail -f ${TOMCAT_HOME}/logs/catalina.out
