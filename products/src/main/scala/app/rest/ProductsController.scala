package app.rest

import app.aggregate.{CreateProductCommand, ProductData}
import org.springframework.web.bind.annotation.{PostMapping, RequestBody, RestController}
import app.domain.Product
import org.axonframework.commandhandling.gateway.CommandGateway

import java.util.UUID

@RestController
class ProductsController(var gateway: CommandGateway) {

  @PostMapping
  def create(@RequestBody product: Product): String = {
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
