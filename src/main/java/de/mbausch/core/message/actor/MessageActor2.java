package de.mbausch.core.message.actor;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import akka.actor.ActorSystem;
import akka.actor.UntypedActor;
import de.mbausch.app.SpringExtension;
import de.mbausch.core.message.manager.IMessageManager;
import de.mbausch.core.message.model.Message;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("MessageActor2")
@Scope("prototype")
public class MessageActor2 extends UntypedActor {
	
	@Autowired
	private ActorSystem system;
	
	@Autowired
	private SpringExtension ext;
	
	@Autowired
	private IMessageManager manager;
	
	@PostConstruct
	public void create() throws Exception {		
		log.info("create new Actor -> {}", "MessageActor2");
		system.actorOf(ext.props("MessageActor2"), "MessageActor2");
	}

    @Override
    public void onReceive(Object message) throws Exception {
    	log.info("Got message: {} from: {}", new Object[] {message, sender()});
    	
    	if (message instanceof Message) {
    		Message result = manager.handleMessage((Message) message);
    		sender().tell(result, getSelf());
    	} else {
    		unhandled(message);
    	}    	
    }
}

