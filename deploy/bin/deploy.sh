#!/bin/bash

# 获取deploy.sh脚本所在目录
current_dir=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

project_root_path=$(dirname $(dirname $current_dir))

echo $project_root_path

. $current_dir/common.sh --source-only

function println_info()
{
   echo "[INFO]========> $1"
}

function println_exec_info()
{
   println_info "执行命令: $1"
}

function println_error()
{
   echo "[ERROR]========>$1"
}

if [ "$1" = "test" ]; then
   current_env=test
   remote_host_name=root
   remote_ip=192.168.10.56
elif [ "$1" = "test68" ]; then
   current_env=test68
   remote_host_name=root
   remote_ip=192.168.10.68
elif [ "$1" = "development" ] || [ -z "$1" ]; then
   current_env=development
   remote_host_name=root
   remote_ip=192.168.10.43
elif [ "$1" = "staging" ]; then
   current_env=staging
   remote_host_name=root
   remote_ip=192.168.10.71
elif [ "$1" = "production104" ]; then
   current_env=production
   remote_host_name=root
   remote_ip=192.168.100.104
elif [ "$1" = "production139" ]; then
   current_env=production
   remote_host_name=root
   remote_ip=172.18.34.139
else
   println_info "输入参数有误，仅支持[development、test、staging、production139]"
   exit
fi

remote_host=$remote_host_name@$remote_ip
ssh_command="ssh $remote_host"

scp_command="scp"

current_unix_time=`date +%s`

temp_dir_name="${app_name}$current_unix_time"

temp_dir_path="/tmp/$temp_dir_name"

build_command="$temp_dir_path/$build_exec"

application_exec="application.sh"
common_exec="common.sh"

application_exec_path=$temp_dir_path/deploy/bin/$application_exec

println_exec_info "mkdir $temp_dir_path"
mkdir $temp_dir_path

rm -rf $temp_dir_path/build

# 拷贝工程代码到临时目录中
cp -r $project_root_path/* $temp_dir_path

println_info "进入临时目录${temp_dir_path}"
println_info "执行${build_command}命令构建应用"
cd $temp_dir_path

rm -rf build

for i in "${configurable_files[@]}"
do
   rm -f $temp_dir_path/src/main/resources/$i
done

$build_command
echo $error_code

println_info "当前目录："`pwd`

$ssh_command "mkdir -p $deploy_to"

$ssh_command "mkdir -p $deploy_config_to"

$ssh_command "mkdir -p $deploy_scripts_to"

println_exec_info "$scp_command $application_exec_path $remote_host:$deploy_scripts_to"
$scp_command $application_exec_path $remote_host:$deploy_scripts_to
println_exec_info "$scp_command $temp_dir_path/deploy/bin/$common_exec $remote_host:$deploy_scripts_to"
$scp_command $temp_dir_path/deploy/bin/$common_exec $remote_host:$deploy_scripts_to

for i in "${configurable_files[@]}"
do
   println_exec_info "$temp_dir_path/deploy/environments/${current_env}/${i} $remote_host:$deploy_config_to"
   $scp_command $temp_dir_path/deploy/environments/${current_env}/${i} $remote_host:$deploy_config_to
done

# 拷贝Jar包到远程服务器上
println_exec_info "$scp_command $temp_dir_path/build/libs/${app_jar_name} $remote_host:$deploy_to"
$scp_command "$temp_dir_path/build/libs/${app_jar_name}" $remote_host:$deploy_to

println_exec_info "$ssh_command $deploy_scripts_to/$application_exec restart"
$ssh_command "$deploy_scripts_to/$application_exec restart"

