package app.command

import app.aggregate.ProductCreatedEvent
import app.data.{ProductLookupEntity, ProductLookupRepository}
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
@ProcessingGroup("product-group")
class ProductLookupEventsHandler(val repository: ProductLookupRepository) {
  @EventHandler def on(event: ProductCreatedEvent): Unit = repository.save {
    new ProductLookupEntity(event.id, event.product.title)
  }
}
