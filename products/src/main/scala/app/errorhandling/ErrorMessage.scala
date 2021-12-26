package app.errorhandling

import java.util.Date
import scala.beans.BeanProperty

case class ErrorMessage(@BeanProperty var time: Date = null,
                   @BeanProperty var msg: String = null)

object ErrorMessage {
  def apply(time: Date, msg: String) = new ErrorMessage(time, msg)
}
