package com.university.presentation;

import com.university.application.AppConfig;
import com.university.application.ApplicationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by redric on 10.11.17.
 * Main application for working with all functionality.
 */

public class Main {

    /**
     * Main function of the program.
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        ApplicationService service = (ApplicationService) applicationContext.getBean(ApplicationService.class);
        service.createHundredRandomEntities();
        service.removeEntitiesBeginningWithChar('K');
    }
}
