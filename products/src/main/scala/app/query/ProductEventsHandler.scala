package app.query

import app.aggregate.ProductCreatedEvent
import app.data.{ProductEntity, ProductsRepository}
import org.axonframework.eventhandling.EventHandler
import org.springframework.beans.BeanUtils
import org.springframework.stereotype.Component

@Component
class ProductEventsHandler(val productsRepository: ProductsRepository) {
  @EventHandler def on(event: ProductCreatedEvent): Unit = productsRepository.save(ProductEntity(event))
}
