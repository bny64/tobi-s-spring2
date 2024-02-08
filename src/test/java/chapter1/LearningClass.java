package chapter1;

import chatper1.Hello;
import chatper1.StringPrinter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.StaticApplicationContext;

public class LearningClass {

    @Test
    public void registerBeanWithDependency() {
        StaticApplicationContext ac = new StaticApplicationContext();

        ac.registerBeanDefinition("printer", new RootBeanDefinition(StringPrinter.class));
        //StringPinter 클래스 타입이며 printer 이름을 가진 빈을 등록한다.
        BeanDefinition helloDef = new RootBeanDefinition(Hello.class);
        helloDef.getPropertyValues().addPropertyValue("name", "Spring");
        //단순 값을 갖는 프로퍼티 등록
        helloDef.getPropertyValues().addPropertyValue("printer", new RuntimeBeanReference("printer"));
        //아이디가 printer인 빈에 대한 레퍼런스를 프로퍼티로 등록
        ac.registerBeanDefinition("hello", helloDef);

        Hello hello = ac.getBean("hello", Hello.class);
        hello.print();
    }
}
