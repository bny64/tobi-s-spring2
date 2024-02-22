package springbook.learningtest.spring.ioc;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.StaticApplicationContext;

public class LearningClass {
    public static void main(String[] args) {

        StaticApplicationContext ac = new StaticApplicationContext();
        ac.registerSingleton("hello1", Hello.class);
//        assertThat(hello1, is(notNullValue()));

        //빈 메타정보를 담은 오브젝트를 만든다. 빈 클래스는 Hello로 지정한다.
        BeanDefinition hellDef = new RootBeanDefinition(Hello.class);
        //빈의 name 프로퍼티에 들어갈 값을 지정한다.
        //<property name="name" value="String"/>에 해당한다.
        hellDef.getPropertyValues().addPropertyValue("name", "Spring");
        //앞에서 생성한 빈 메타정보를 hello2라는 이름을 가진 빈으로 해서 등록한다.
        //<bean id="hello2/>에 해당한다.
        ac.registerBeanDefinition("hello2", hellDef);

        Hello hello2 = ac.getBean("hello2", Hello.class);

    }
}
