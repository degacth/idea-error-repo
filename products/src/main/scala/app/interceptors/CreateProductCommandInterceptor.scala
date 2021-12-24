package app.interceptors

import app.aggregate.{CreateProductCommand, ProductData}
import app.data.ProductLookupRepository
import org.axonframework.commandhandling.CommandMessage
import org.axonframework.messaging.MessageDispatchInterceptor
import org.springframework.stereotype.Component

import java.util
import java.util.function.BiFunction

@Component
class CreateProductCommandInterceptor(val productLookupRepository: ProductLookupRepository)
  extends MessageDispatchInterceptor[CommandMessage[_]] {

  override def handle(messages: util.List[_ <: CommandMessage[_]]): BiFunction[Integer, CommandMessage[_], CommandMessage[_]] =
    (_, command) => {
      command.getPayload match {
        case c: CreateProductCommand => Option {
          productLookupRepository.findByProductIdOrTitle(c.id, c.product.title)
        } foreach {
          _ =>
            throw new IllegalStateException(
              s"Product with productId ${c.id}, or title ${c.product.title} already exists"
            )
        }

        case _ =>
      }
      command
    }
}
