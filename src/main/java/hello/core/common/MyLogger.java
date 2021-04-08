package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;


/*
    Log를 출력하기 위한 MyLogger 클래스
 */


@Component
//@Scope(value = "request")                   // request 스코프 지정 ( HTTP 요청 하나씩 생성, 요청이 끝나는 시점 소멸 )
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) // 프록시 스코프 ( 적용대상이 클래스면 TARGET .. )
                            // 이렇게 하면 가짜 프록시 MyLogger 생성이 되고 request 요청 들오기 전에도 주입이 가능하다.
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;       // requestURL 은 빈이 생성되는 시점에는 알 수 없으므로 외부에서 setter 입력 받기
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "]" + message);
    }


    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create:" + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close:" +this);
    }
}
