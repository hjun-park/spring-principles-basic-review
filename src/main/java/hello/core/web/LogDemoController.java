package hello.core.web;

import hello.core.common.MyLogger;
import hello.core.logdemo.LogDemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/*
    MyLogger가 잘 작동하는지 확인하는 테스트용 컨트롤러 ( common/MyLogger 참고 )
 */

@Controller
@RequiredArgsConstructor    // final이 붙은 키워드에 대해 자동으로 생성자를 생성
public class LogDemoController {

    private final LogDemoService logDemoService;
//    private final ObjectProvider<MyLogger> myLoggerProvider;        // Object Provider
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {     // HttpServletRequest 이용해서 request를 받아옴
                                                            // requestURL : http://localhost:8080/log-demo
        String requestURL = request.getRequestURL().toString();

        // ObjectProvider의 getObject 덕분에 request scope 빈 생성 지연 가능
        // 이것보다 더 좋은 방법이 있다 ( 프록시 )  ==> MyLogger 참고
//        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.setRequestURL(requestURL);                 // requestURL 값을 myLogger에 저장
                                                            // myLogger는 HTTP 요청 당 각각 구분이 됨
                                                            // 따라서 다른 요청에 의해 requestURL 값이 변경될 우려하지 않아도 됨

        myLogger.log("controller test");                    // 컨트롤러에서 해당 로그르 남긴다 .
        logDemoService.logic("testId");

        return "OK";
    }

}
