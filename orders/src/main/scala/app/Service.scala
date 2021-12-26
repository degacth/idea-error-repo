package app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.SpringApplication

@SpringBootApplication class Service

object Service extends App {
  SpringApplication.run(classOf[Service], args:_*)
}
