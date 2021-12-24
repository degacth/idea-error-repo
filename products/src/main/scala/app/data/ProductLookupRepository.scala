package app.data

import org.springframework.data.jpa.repository.JpaRepository

trait ProductLookupRepository extends JpaRepository[ProductLookupEntity, String] {
  def findByProductIdOrTitle(productId: String, title: String): ProductLookupEntity
}
