package code
package comet

import net.liftweb._
import http._
import actor._

case object ReRender

object ChatServer extends LiftActor with ListenerManager {
  
  def createUpdate = ReRender
  
  override def lowPriority = {
    case ReRender =>
      updateListeners()
  }
}
