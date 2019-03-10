#!/bin/sh

if [ $# -ne 2 ];then
    echo "Usage: $ ./regisdsl.sh version[number] effective_date[yyyymmdd]"
    exit 1
else
    if [ -z `echo $1 | egrep '^[0-9]+$'` ] ; then
        echo "Usage: $ ./regisdsl.sh version[number] effective_date[yyyymmdd]"
        exit 1 
    fi
    
    if [ -z `echo $2 | egrep '^[0-9]{4}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$'` ]; then
        echo "Usage: $ ./regisdsl.sh version[number] effective_date[yyyymmdd]"
        exit 1
    fi
fi

BASE_DIR=$(pwd)

LOG4J="${BASE_DIR}/conf"
CONF="${BASE_DIR}/conf/"
export CONF=${CONF}
DSL="${BASE_DIR}/dsl"
OUT="${BASE_DIR}/out/object"
LIB="${BASE_DIR}/lib"

VER=$1
EFFECTIVE_DATE=$2

JAVA_OPTS=" -Xmx2048m"

PARSER_CLASS="jp.co.fluxengine.stateengine.parser.DslParser"
REGISTER_CLASS="jp.co.fluxengine.stateengine.dsl.DslRegister"

for libfile in ${LIB}/*.jar ; do
if [ -f $libfile ] ; then
    CLASSPATH=$libfile:${CLASSPATH}
#    echo $libfile
fi
done

CLASSPATH=${CONF}:${CLASSPATH}

java ${JAVA_OPTS} -server -cp ${CLASSPATH} ${PARSER_CLASS} ${DSL} ${OUT}

if [ -f $OUT ] ; then
  java ${JAVA_OPTS} -server -cp ${CLASSPATH} ${REGISTER_CLASS} ${OUT} ${VER} ${EFFECTIVE_DATE}
  rm -r $OUT
fi
