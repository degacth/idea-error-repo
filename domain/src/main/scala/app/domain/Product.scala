package app.domain

import scala.beans.BeanProperty
import java.math.BigDecimal

class Product {
  @BeanProperty var title: String = _
  @BeanProperty var price: BigDecimal = _
  @BeanProperty var quantity: Int = _
}
