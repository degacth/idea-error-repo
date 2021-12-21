package app.data

import org.springframework.data.jpa.repository.JpaRepository

trait ProductsRepository extends JpaRepository[ProductEntity, String]
