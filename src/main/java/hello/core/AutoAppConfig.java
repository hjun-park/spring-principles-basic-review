package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// 설명은 AutoAppConfigTest를 참고

@Configuration // 컴포넌트 스캔을 사용하기 위해 사용
@ComponentScan( // 필터는 AppConfig에 있는 Configuratioin 정보도 자동 등록되기 때문에 해당 @Configuration은 제외
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class),
        basePackages = "hello.core" // 처음부터 스캔하면 스캔 시간이 걸리므로 필요한 부분부터 스캔
        // 권장하는 방법은 패키지 위치를 지정하지 않고 설정 정보 클래스 위치를 프로젝트 최상단에 둔다.
        // 여기서는 hello.core에 두면 된다.
)
public class AutoAppConfig {

}
