package app.rest

import app.domain.{Order, OrderStatus}
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.web.bind.annotation.{PostMapping, RequestBody, RestController}

import java.util.UUID
import javax.validation.Valid

@RestController class OrdersCommandController(val gateway: CommandGateway) {
  @PostMapping def create(@Valid @RequestBody order: Order): String = {
    order.id = UUID.randomUUID().toString
    order.userId = "1"
    order.status = OrderStatus.created
    gateway sendAndWait order
  }
}
