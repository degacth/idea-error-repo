package app.aggregate

import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.{AggregateIdentifier, AggregateLifecycle, TargetAggregateIdentifier}
import org.axonframework.spring.stereotype.Aggregate
import java.math.BigDecimal

@Aggregate
class ProductAggregate {
  @AggregateIdentifier
  var id: String = _
  var title: String = _
  var price: BigDecimal = _
  var quantity: Int = _

  @CommandHandler
  def this(create: CreateProductCommand) = {
    this()
    AggregateLifecycle.apply {
      ProductCreatedEvent(create.id, create.product)
    }
  }

  @EventSourcingHandler
  def on(event: ProductCreatedEvent): Unit = {
    id = event.id
    title = event.product.title
    price = event.product.price
    quantity = event.product.quantity
  }
}

case class ProductData(title: String,
                       price: BigDecimal,
                       quantity: Int)

case class CreateProductCommand(@TargetAggregateIdentifier id: String, product: ProductData)
case class ProductCreatedEvent(id: String, product: ProductData)
