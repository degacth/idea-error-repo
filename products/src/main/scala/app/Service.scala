package app

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication class Service

object Service extends App {
  SpringApplication.run(classOf[Service], args:_*)
}
