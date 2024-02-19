package springbook.learningtest.spring.ioc.bean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/springbook/learningtest/spring/ioc/genericApplicationContext.xml")
public class ApplicationContextTest {

    String basePath = StringUtils.cleanPath(ClassUtils.classPackageAsResourcePath(getClass())) + "/";

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void genericApplicationContext() {
        GenericApplicationContext ac = new GenericApplicationContext();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(ac);
        reader.loadBeanDefinitions("classpath:/springbook/learningtest/spring/ioc/genericApplicationContext.xml");
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

//        ApplicationContext parent = new GenericXmlApplicationContext(basePath + "parentContext.xml");
        //아래처럼 어플리케이션을 생성할 때 앞에서 만든 parent를 부모 컨텍스트로 지정해준다.
        //새롭게 만들어지는 child라는 이름의 어플리케이션 컨텍스트는 parent 컨텍스트를 부모 컨텍스트로 갖게 된다.
//        GenericApplicationContext child = new GenericApplicationContext(parent);
//
//        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(child);
//        reader.loadBeanDefinitions(basePath + "childContext.xml");
//        child.refresh();
//
//        Printer printer = child.getBean("printer", Printer.class);
//        System.out.println(printer.toString());

    }

}
