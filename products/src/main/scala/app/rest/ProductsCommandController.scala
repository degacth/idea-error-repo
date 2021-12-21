package app.rest

import app.aggregate.{CreateProductCommand, ProductData}
import org.springframework.web.bind.annotation.{PostMapping, RequestBody, RestController}
import org.axonframework.commandhandling.gateway.CommandGateway

import java.math.BigDecimal
import java.util.UUID
import javax.validation.Valid
import javax.validation.constraints.{Max, Min, NotBlank}
import scala.beans.BeanProperty

@RestController
class ProductsCommandController(var gateway: CommandGateway) {
  @PostMapping def create(@Valid @RequestBody product: Product): String = {
    gateway.sendAndWait {
      CreateProductCommand(
        UUID.randomUUID().toString,
        ProductData(
          product.title,
          product.price,
          product.quantity
        )
      )
    }
  }
}

class Product {
  @NotBlank
  @BeanProperty var title: String = _

  @Min(1)
  @BeanProperty var price: BigDecimal = _

  @Min(1)
  @Max(5)
  @BeanProperty var quantity: Int = _
}
