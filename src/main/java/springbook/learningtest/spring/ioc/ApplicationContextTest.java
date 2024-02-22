package springbook.learningtest.spring.ioc;

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

        //부모 컨텍스트 생성
        ApplicationContext parent = new GenericXmlApplicationContext(basePath + "parentContext.xml");
        //자식 컨텍스트 생성시 부모 컨텍스트 지정
        //아래처럼 지정하면 새로 만들어지는 child라는 이름의 어플리케이션 컨텍스트는 parenㅅ 컨텍스트를 부모 컨텍스트로 갖게 된다.
        GenericApplicationContext child = new GenericApplicationContext(parent);

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(child);
        reader.loadBeanDefinitions(basePath + "childContext.xml");
        child.refresh(); //reader를 사용해서 설정을 읽은 경우에는 반드시 refresh()로 초기화해야 한다.

        //부모 컨텍스트에서 printer를 찾는다.
        Printer printer = child.getBean("printer", Printer.class);
        System.out.println(printer.toString());

    }

}
