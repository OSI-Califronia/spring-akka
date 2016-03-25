package de.mbausch.app;

import org.springframework.context.ApplicationContext;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;

/**
 * This class implements a new producer to create Actors
 * 
 * @author OSI
 *
 */
public class SpringActorProducer implements IndirectActorProducer {

    private final ApplicationContext applicationContext;
    private final String actorBeanName;

    /**
     * 
     * @param applicationContext
     * @param actorBeanName
     */
    public SpringActorProducer(ApplicationContext applicationContext,
        String actorBeanName) {
        this.applicationContext = applicationContext;
        this.actorBeanName = actorBeanName;
    }

    @Override
    public Actor produce() {
        return (Actor) applicationContext.getBean(actorBeanName);
    }

    @SuppressWarnings("unchecked")
	@Override
    public Class<? extends Actor> actorClass() {
        return (Class<? extends Actor>) applicationContext.getType(actorBeanName);
    }
}
