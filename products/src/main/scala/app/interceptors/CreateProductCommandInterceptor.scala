package app.interceptors

import app.aggregate.{CreateProductCommand, ProductData}
import org.axonframework.commandhandling.CommandMessage
import org.axonframework.messaging.MessageDispatchInterceptor
import org.springframework.stereotype.Component

import java.math.BigDecimal
import java.util
import java.util.function.BiFunction

@Component
class CreateProductCommandInterceptor extends MessageDispatchInterceptor[CommandMessage[_]] {
  override def handle(messages: util.List[_ <: CommandMessage[_]]): BiFunction[Integer, CommandMessage[_], CommandMessage[_]] =
    (_, command) => {
      command match {
        case c: CreateProductCommand =>
          val error = if (c.product.price.compareTo(new BigDecimal(0)) <= 0) Some("price cannot be less or equal then '0'")
          else if (c.product.title.isBlank) Some("title cannot be empty")
          else None

          error.foreach { m =>
            throw new IllegalArgumentException(m)
          }
        case _ =>
      }
      command
    }
}
