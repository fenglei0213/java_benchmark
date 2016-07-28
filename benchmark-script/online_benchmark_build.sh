
GIT_HOME=/usr/bin
BENCHMARK_HOME=/home/work/opt/benchmark/benchmark-core

CURRENT_FOLDER_PATH=$(pwd)
UP_FOLDER_PATH=${CURRENT_FOLDER_PATH%/*}

echo ${CURRENT_FOLDER_PATH}
echo ${UP_FOLDER_PATH}

rm -rf ${UP_FOLDER_PATH}/code/*
rm -rf ${UP_FOLDER_PATH}/code/.gitignore
rm -rf ${UP_FOLDER_PATH}/code/.git

${GIT_HOME}/git clone http://gitlab.baidu.com/fenglei/fusion.git ${UP_FOLDER_PATH}/code/

mkdir -p ${UP_FOLDER_PATH}/code/
cd ${UP_FOLDER_PATH}/code/

sh build.sh online

cd ${UP_FOLDER_PATH}/code/fusion-binlog/fusion-binlog-assembly/
sh build.sh

rm -rf ${FUSION_BINLOG_HOME}/fusion-binlog/*
cp ${UP_FOLDER_PATH}/code/fusion-binlog/fusion-binlog-assembly/target/fusion-binlog.tar.gz ${FUSION_BINLOG_HOME}/fusion-binlog

tar -xzvf ${FUSION_BINLOG_HOME}/fusion-binlog/fusion-binlog.tar.gz -C ${FUSION_BINLOG_HOME}/fusion-binlog
rm -rf ${FUSION_BINLOG_HOME}/fusion-binlog/fusion-binlog.tar.gz

cd ${FUSION_BINLOG_HOME}/fusion-binlog/bin
sh stop.sh
sleep 2
sh start.sh

echo fusion-binlog deployed SUCCESS
