package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {
    ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    void statefulServiceSingleton() {
        StatefulService statefulServcie1 = ac.getBean(StatefulService.class);
        StatefulService statefulServcie2 = ac.getBean(StatefulService.class);
        
        //ThreadA: 사용자가 만원 주문
        statefulServcie1.order("userA", 10000);
        
        //ThreadB: 사용자가 2만원 주문
        statefulServcie1.order("userA", 20000);
        
        //ThreadA: 사용자A 주문 금액 조회
        int price = statefulServcie1.getPrice();

        System.out.println("price = "+price);

        Assertions.assertThat(statefulServcie1.getPrice()).isEqualTo(20000);

    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

}