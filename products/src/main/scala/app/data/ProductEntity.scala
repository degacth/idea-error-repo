package app.data

import app.aggregate.ProductCreatedEvent
import org.springframework.beans.BeanUtils

import javax.persistence.{Column, Entity, Id}
import scala.beans.BeanProperty

@Entity
@SerialVersionUID(1000)
class ProductEntity {
  @BeanProperty
  @Id
  var productId: String = _

  @BeanProperty
  @Column(unique = true)
  var title: String = _

  @BeanProperty var price: java.math.BigDecimal = _

  @BeanProperty var quantity: Int = _
}

object ProductEntity {
  def apply(event: ProductCreatedEvent): ProductEntity = {
    val entity = new ProductEntity()
    BeanUtils.copyProperties(event.product, entity)

    entity.productId = event.id
    entity
  }
}
