#!/bin/bash

current_dir=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

. $current_dir/common.sh --source-only

start(){
    echo "starting ....."
    if [ -n "$(get_pid)" ];then
        echo "appliaton has been started"
    else
        $(eval $app_launch_command)
    fi
}

stop(){
    echo "stopping ....."
    pid=$(get_pid)
    if [ -n "$pid" ];then
        kill -9 $pid
    else
        echo "appliaton has not been started"
    fi
}

restart(){
    stop
    start
}

get_pid() {
    ps -ef | grep ${app_jar_name} | grep -v grep | awk '{print $2}'
}
case "$1" in
  start)
    start
    ;;
  stop)
    stop
    ;;
  status)
    status -p "$mypidfile" $prog
    ;;
  restart)
    restart
    ;;
  *)
    echo $"Usage: $0 {start|stop|status|restart}"
    exit 2
esac