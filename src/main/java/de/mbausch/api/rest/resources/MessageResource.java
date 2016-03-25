package de.mbausch.api.rest.resources;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.pattern.Patterns;
import akka.util.Timeout;
import de.mbausch.app.SpringExtension;
import de.mbausch.core.message.model.Message;
import lombok.extern.slf4j.Slf4j;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

@Slf4j
@RestController
@RequestMapping("/messages")
public class MessageResource {
	
	@Autowired
	private ActorSystem system;
	
	@Autowired
	private SpringExtension ext;
	
	private ActorRef messageActor;
	
	@PostConstruct
	public void create() throws Exception {		
		log.info("create new Actor -> {}", "MessageActor1");
		messageActor = system.actorOf(ext.props("MessageActor1"), "MessageActor1");
	}
	
	@RequestMapping(path="/createActor", method=RequestMethod.GET)	
    public Message sendMessagesCreateActor(
    		@RequestParam(value="name", defaultValue="test message") String msg) throws Exception {
        log.info("call sndMsg Resource uses created Actor");
		
		Timeout timeout = new Timeout(Duration.create(5, "seconds"));
		Future<Object> future = Patterns.ask(messageActor, new Message(msg), timeout);
		return (Message) Await.result(future, timeout.duration());
    }
	
	@RequestMapping(path="/fetchActor", method=RequestMethod.GET)
    public Message sendMessagesFetchActor(
    		@RequestParam(value="name", defaultValue="test message") String msg) throws Exception {
        log.info("call sndMsg Resource Fetches exsisting Actor");
        
        ActorSelection sel = system.actorSelection("/user/MessageActor2");        
		Timeout timeout = new Timeout(Duration.create(5, "seconds"));
		Future<Object> future = Patterns.ask(sel, new Message(msg), timeout);
		return (Message) Await.result(future, timeout.duration());
    }
}
