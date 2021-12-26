package app.query

import app.aggregate.ProductCreatedEvent
import app.data.{ProductEntity, ProductsRepository}
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.axonframework.messaging.interceptors.ExceptionHandler
import org.springframework.stereotype.Component

@Component
@ProcessingGroup("product-group")
class ProductEventsHandler(val productsRepository: ProductsRepository) {
  @EventHandler def on(event: ProductCreatedEvent): Unit = productsRepository.save(ProductEntity(event))

  @ExceptionHandler(resultType = classOf[Exception]) def handle(exc: Exception) = throw exc
}
