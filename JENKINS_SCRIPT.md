# 지정된 포트 번호를 사용하는 프로세스를 찾아서 종료합니다.
PID=$(netstat -tpln | grep ":$PORT_NUMBER" | awk '{print $7}' | awk -F '/' '{print $1}')
echo $PID

if [ -z "$PID" ]; then
    echo "포트 $PORT_NUMBER를 사용하는 프로세스가 없습니다."
else
    echo "포트 $PORT_NUMBER를 사용하는 프로세스 (PID: $PID)를 종료합니다."
    kill $PID
    
    # 프로세스가 종료될 때까지 대기합니다.
    sleep 5
fi

# JAR 파일이 존재하는지 확인합니다.
if [ ! -f "$JAR_PATH" ]; then
    echo "주어진 경로에 JAR 파일이 존재하지 않습니다: $JAR_PATH"
    exit 1
fi

# JAR 파일을 백그라운드에서 실행합니다.
BUILD_ID=dontKillMe nohup java -jar "$JAR_PATH" > jar_execution.log 2>&1 &


# 실행된 프로세스의 포트 번호를 다시 확인합니다.
echo "JAR 파일이 정상적으로 실행 중입니다. 포트 확인:"
netstat -tuln | grep ":$PORT_NUMBER" | awk '{print $7}' | awk -F '/' '{print $1}'
