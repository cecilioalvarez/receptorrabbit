package es.logixs.receptor;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReceptorApplication {

	static final String exchange = "spring-boot-exchange";
	static final String cola = "micola";

	public static void main(String[] args) {
		SpringApplication.run(ReceptorApplication.class, args);
	}


	@Bean
	Queue queue() {
		return new Queue(cola, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(exchange);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("miruta");
	}

	@Bean
	MessageListenerAdapter listenerAdapter(Receptor receptor) {
		return new MessageListenerAdapter(receptor, "recibir");
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
											 MessageListenerAdapter listenerAdapter) {

		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(cola);
		container.setMessageListener(listenerAdapter);
		return container;
	}
}
