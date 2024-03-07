package springbook.learningtest.spring.ioc.bean;

import org.springframework.context.annotation.Bean;
import springbook.learningtest.spring.ioc.Hello;
import springbook.learningtest.spring.ioc.Printer;
import springbook.learningtest.spring.ioc.StringPrinter;

public class HelloService {
    private Printer printer;

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

    @Bean
    private Hello hello() {
        Hello hello = new Hello();
        hello.setPrinter(this.printer);
        return hello;
    }

    @Bean
    private Hello hello2() {
        Hello hello = new Hello();
        hello.setPrinter(this.printer);
        return hello;
    }

    @Bean
    private Printer printer() {
        return new StringPrinter();
    }
}
