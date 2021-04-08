package hello.core.logdemo;


import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

/*
    Provider 없이 실행하면 오류가 발생하게 된다.
    request 스코프 빈은 아직 생성되지 않는다. 실제 고객의 요청이 와야 생성이 된다.

    해결방법 : Provider 사용 ( Object Provider )
 */

@Service
@RequiredArgsConstructor
public class LogDemoService {

//    private final ObjectProvider<MyLogger> myLoggerProvider;
    private final MyLogger myLogger;

    public void logic(String id) {
//        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.log("service id = " + id);
    }
}
