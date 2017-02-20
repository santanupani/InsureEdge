package za.co.reverside.repository;

import javax.jms.JMSException;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Repository;

import za.co.reverside.domain.Notification;

@Repository
public class MessageRepository {

    @Autowired
    private JmsTemplate jmsTemplate;

   
    public void publish(final Notification notification, String queue){
        jmsTemplate.send(queue, new MessageCreator() {
            public javax.jms.Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(notification);
            }
        });

    }

}
