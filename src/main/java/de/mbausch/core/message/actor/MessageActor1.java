package de.mbausch.core.message.actor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import akka.actor.UntypedActor;
import de.mbausch.core.message.manager.IMessageManager;
import de.mbausch.core.message.model.Message;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("MessageActor1")
@Scope("prototype")
public class MessageActor1 extends UntypedActor {
	

	@Autowired
	private IMessageManager manager;

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

