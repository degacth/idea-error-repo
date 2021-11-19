package app

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.{GetMapping, RestController}

@RestController
@EnableDiscoveryClient
@SpringBootApplication class Service(val env: Environment) {
  @GetMapping
  def hello: String = env getProperty "local.server.port"
}

object Service extends App {
  SpringApplication.run(classOf[Service], args:_*)
}
