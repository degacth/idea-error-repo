package data

import org.axonframework.commandhandling.TargetAggregateIdentifier
import org.axonframework.commandhandling.model.AggregateIdentifier

import java.util.UUID
import scala.beans.BeanProperty

trait AggregateId {
  @TargetAggregateIdentifier
  @AggregateIdentifier
  @BeanProperty var id: String = _
}

object AggregateId {
  def apply(): String = UUID.randomUUID().toString
}
