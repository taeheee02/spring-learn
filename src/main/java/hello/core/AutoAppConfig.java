package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        //basePackages = "hello.core", // 시작 점을 명시함으로써, 실행 시간 가능
        // -> 관례) 설정 정보 클래스의 위치를 프로젝트 최상단에 둠 => 별도 시작 위치를 지정하지 않으면 @ComponentScan이 붙은 설정 정보 클래스의 패키지가 시작 위치
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,
                                            classes = Configuration.class) // 기존 코드 유지를 위한 코드 -> 실무에서 필요한 건 아님
)
public class AutoAppConfig {

}
