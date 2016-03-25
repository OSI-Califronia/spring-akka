package de.mbausch.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import lombok.extern.slf4j.Slf4j;

/**
 * Main class to Start spring Application
 * @author OSI
 *
 */
@Slf4j
@SpringBootApplication
public class Application {
	
	/**
	 * Main method to start application
	 * @param args
	 */
	public static void main(String[] args) {
		log.info("Starting Akka - Spring Application");
        ApplicationContext context = SpringApplication.run(Application.class, args);

        // print all Spring Handled lulus
        for (String name : context.getBeanDefinitionNames()) {
        	log.info("Bean named {} found", name);
        }
        
//        ActorSystem system = context.getBean(ActorSystem.class);
//        SpringExtension ext = context.getBean(SpringExtension.class);
//        ActorRef actor = system.actorOf(ext.props("MessageActor"));
//        actor.tell(new Message("bufu"), null);
    }
}
