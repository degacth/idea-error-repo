package app

import app.interceptors.CreateProductCommandInterceptor
import org.axonframework.commandhandling.CommandBus
import org.axonframework.config.EventProcessingConfigurer
import org.axonframework.eventhandling.PropagatingErrorHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.ApplicationContext

@EnableDiscoveryClient
@SpringBootApplication class Service {
  @Autowired def registerCreateProductCommandInterceptor(ctx: ApplicationContext, bus: CommandBus): Unit =
    bus registerDispatchInterceptor ctx.getBean(classOf[CreateProductCommandInterceptor])

  @Autowired def configure(config: EventProcessingConfigurer): Unit = config
    .registerListenerInvocationErrorHandler("product-group",
      _ => PropagatingErrorHandler.instance())
  //      _ => new ProductsEventsErrorHandler())
}

object Service extends App {
  SpringApplication.run(classOf[Service], args:_*)
}
