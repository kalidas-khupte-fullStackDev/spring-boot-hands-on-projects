package com.loose.coupling;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LooseCouplingExample {
    public static void main(String[] args) {

        // With DB --> A (MySql )
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationBeanLooseCouplingContext.xml");

       // UserDataProviderInterface i_userDataService = (LooseUserDataBaseService) applicationContext.getBean("dataBaseServiceBean");
       // UserDetailsBusinessLogicService userLogicService = new UserDetailsBusinessLogicService(i_userDataService);
       UserDetailsBusinessLogicService userLogicService_DB = (UserDetailsBusinessLogicService) applicationContext.getBean("userDetailsBusinessLogicServiceRef1");
        System.out.println(userLogicService_DB.toGetUserDetailsFromService());


        // With DB --> B (Web Service )
        //UserDataProviderInterface i_userDataService_webServiceBased = (LooseUserWebServiceImpl) applicationContext.getBean("webServiceBasedBean");
//        UserDetailsBusinessLogicService userLogicService_new = new UserDetailsBusinessLogicService(i_userDataService_webServiceBased);
        UserDetailsBusinessLogicService userLogicService_WebService = (UserDetailsBusinessLogicService) applicationContext.getBean("userDetailsBusinessLogicServiceRef2");
        System.out.println(userLogicService_WebService.toGetUserDetailsFromService());
    }
}