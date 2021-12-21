package app.query

import app.data.{ProductsRepository, ProductsRestModel}
import org.axonframework.queryhandling.QueryHandler
import org.springframework.beans.BeanUtils
import org.springframework.stereotype.Component

import java.util.stream.Collectors

@Component
class ProductsQueryHandler(val repository: ProductsRepository) {
  @QueryHandler def find(query: FindProductsQuery) = repository
    .findAll()
    .stream()
    .map(product => {
      val model = new ProductsRestModel()
      BeanUtils.copyProperties(product, model)
      model
    })
    .collect(Collectors.toList[ProductsRestModel])
}
