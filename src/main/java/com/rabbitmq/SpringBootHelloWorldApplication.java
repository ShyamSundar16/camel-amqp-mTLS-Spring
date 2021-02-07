package com.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Shyam Sundar on 04/02/21.
 */

@SpringBootApplication
public class SpringBootHelloWorldApplication extends SpringBootServletInitializer {
	public static ApplicationContext applicationContext;
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
	{
		return application.sources(SpringBootHelloWorldApplication.class);
	}
	public static void main(String[] args) {
		applicationContext= new ClassPathXmlApplicationContext("applicationContext.xml");
		SpringApplication.run(SpringBootHelloWorldApplication.class, args);
//		displayAllBeans();
	}
	/*public static void displayAllBeans() {
		String[] allBeanNames = applicationContext.getBeanDefinitionNames();
		Object readClientp12 = applicationContext.getBean("readP12");
		for(String beanName : allBeanNames) {
			System.out.println(beanName);
		}
		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
		System.out.println(Arrays.asList(beanDefinitionNames));
		for (String beanDefinitionName : beanDefinitionNames) {
			System.out.println("Bean Name :" + beanDefinitionName + ": Object "+applicationContext.getBean(beanDefinitionName));
		}
		KeyStore keyStoreInstance = applicationContext.getBean("keyStoreInstance", KeyStore.class);
		System.out.println(keyStoreInstance);
	}*/
}
