package app.errorhandling

import org.axonframework.eventhandling.{EventMessage, EventMessageHandler, ListenerInvocationErrorHandler}

class ProductsEventsErrorHandler extends ListenerInvocationErrorHandler {
  override def onError(exc: Exception, e: EventMessage[_], handler: EventMessageHandler): Unit = throw exc
}
