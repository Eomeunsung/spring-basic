package hello.core.beanFind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력 하기")
    void findAllBean(){
        String[] bearDefinitionNames = ac.getBeanDefinitionNames();

        for (String bearDefinitionName : bearDefinitionNames) {
            Object bean = ac.getBean(bearDefinitionName);
            System.out.println("name = " +bearDefinitionName +", object = "+bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력 하기")
    void findApplicationBean(){
        String[] bearDefinitionNames = ac.getBeanDefinitionNames();

        for (String bearDefinitionName : bearDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(bearDefinitionName);
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(bearDefinitionName);
                System.out.println("name = " +bearDefinitionName +", object = "+bean);
            }
        }
    }
}
