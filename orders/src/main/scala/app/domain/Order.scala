package app.domain

import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.{AggregateIdentifier, AggregateLifecycle, TargetAggregateIdentifier}
import org.axonframework.spring.stereotype.Aggregate
import org.springframework.beans.BeanUtils
import org.springframework.data.jpa.repository.JpaRepository

import java.beans.BeanProperty
import javax.persistence.{Entity, Id, Table}
import javax.validation.constraints.{Min, NotBlank}

@Entity
@Table(name = "orders")
@Aggregate class Order {
  @Id
  @TargetAggregateIdentifier
  @AggregateIdentifier
  @BeanProperty var id: String = _

  @BeanProperty var userId: String = _
  @BeanProperty var status: String = _

  @NotBlank
  @BeanProperty var productId: String = _

  @Min(1)
  @BeanProperty var quantity: Int = _

  @NotBlank
  @BeanProperty var addressId: String = _

  @CommandHandler def this(order: Order) = {
    this()
    AggregateLifecycle apply order
  }

  @EventSourcingHandler def on(event: this.type): Unit = event.status match {
    case OrderStatus.created => BeanUtils.copyProperties(event, this)
  }
}

case object OrderStatus {
  val created = "created"
  val approved = "approved"
  val rejected = "rejected"
}

trait OrdersRepository extends JpaRepository[Order, String]
