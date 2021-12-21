package app.rest

import app.data.ProductsRestModel
import app.query.FindProductsQuery
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController}

@RestController
@RequestMapping(Array("/products"))
class ProductsQueryController(val queryGateway: QueryGateway) {
  @GetMapping def getProducts: java.util.List[ProductsRestModel] = queryGateway.query(
      new FindProductsQuery(),
      ResponseTypes.multipleInstancesOf(classOf[ProductsRestModel]))
    .join()

}
