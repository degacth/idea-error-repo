package app.domain

import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@ProcessingGroup("order-group")
@Component class OrderEventsHandler(val repository: OrdersRepository) {
  @EventHandler def handle(order: Order): Unit = repository.save(order)
}
