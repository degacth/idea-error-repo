package app.data

import java.beans.JavaBean
import javax.persistence.{Column, Entity, Id}

@Entity
@SerialVersionUID(2000)
class ProductLookupEntity {
  @Id @JavaBean var productId: String = _
  @Column(unique = true) @JavaBean var title: String = _

  def this(productId: String, title: String) = {
    this()
    this.productId = productId
    this.title = title
  }
}
