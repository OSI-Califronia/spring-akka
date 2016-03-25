package de.mbausch.core.message.manager;

import org.springframework.stereotype.Service;

import de.mbausch.core.message.model.Message;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MessageManager implements IMessageManager {
	
	/* (non-Javadoc)
	 * @see de.mbausch.core.message.manager.IMessageManager#handleMessage(de.mbausch.core.message.model.Message)
	 */
	@Override
	public Message handleMessage(final Message message) {
		log.info("Receive message: ", message);
		return new Message("Backend Result");
	}

}
