package app.data

import java.math.BigDecimal
import scala.beans.BeanProperty

class ProductsRestModel {
  @BeanProperty var productId: String = _
  @BeanProperty var title: String = _
  @BeanProperty var price: BigDecimal = _
  @BeanProperty var quantity: Int = _
}
