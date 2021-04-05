package hello.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작 시 호출
    public void connect() {
        System.out.println("connect: = " + url);
    }

    public void call(String message) {
        System.out.println("call: = " + url + " message = " + message);
    }

    public void disConnect() {
        System.out.println("close = " + url);
    }


    // @PostConstruct로 초기화하고 PreDestory로 소멸자 역할
    // 초기화 및 소멸은 위 두 애노테이션을 이용할 것
    @PostConstruct
    public void init() {
        System.out.println("NetworkClient = " + url);
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disConnect();
    }


}
