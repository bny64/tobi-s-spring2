package springbook.learningtest.spring.ioc.bean;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "springbook/learningtest/spring/ioc/genericApplicationContext.xml")
public class ApplicationContextTest {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void genericApplicationContext() {
        GenericApplicationContext ac = new GenericApplicationContext();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(ac);
        reader.loadBeanDefinitions("springbook/learningtest/spring/ioc/genericApplicationContext.xml");
        ac.refresh();

        Hello hello = ac.getBean("hello", Hello.class);
        hello.print();
        String msg = hello.printer.toString();
        System.out.println(msg);

    }

    @Test
    public void genericXmlApplicationContextTest() {
        Hello hello = applicationContext.getBean("hello", Hello.class);
        hello.print();
        String msg = hello.printer.toString();
        System.out.println(msg);
    }
}
