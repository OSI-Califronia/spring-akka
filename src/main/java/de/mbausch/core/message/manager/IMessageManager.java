package de.mbausch.core.message.manager;

import de.mbausch.core.message.model.Message;

public interface IMessageManager {

	Message handleMessage(Message message);

}