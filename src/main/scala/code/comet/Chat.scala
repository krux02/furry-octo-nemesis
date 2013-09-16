package code
package comet

import net.liftweb._
import http._
import util._
import Helpers._

object PlayerInstance extends SessionVar[Player](new Player)


class PlayerInfo extends CometActor with CometListener {
  
  
  def registerWith = ChatServer
  
  override def lowPriority = {
    case ReRender =>
      reRender()
  }
  
  def render = "#player_name" #> PlayerInstance.is.name &
               "#planet_list li *" #> PlayerInstance.is.planets.map(_.toString)
}


class Player {
  var name = "Anonymous"
  var planets:List[Planet] = List.fill(2)(Planet.random)
  var arnium = 0.0
  var felixium = 0.0
  var ships = 0
  
  override def toString = "Player("+ name +")"
}

object Planet {
  import scala.util.Random.{ nextDouble => rd, nextPrintableChar => rc }
  import scala.util.Random.{ nextInt => ri }
  
  def random = {
    val chars = ('A' to 'Z') ++ ('0' to '9')
    val name = (1 to 4).map( _ => chars(ri.abs % chars.size) ).mkString
    val mass = rd*rd*rd*1.5*1.5*1.5
    val size = rd*rd*rd*1.5*1.5*1.5
    Planet(name, mass, size, arniumpersec=rd*mass, felixiumpersec=rd*mass/size)
  }
}

case class Planet(name:String, mass:Double, size:Double, arniumpersec:Double, felixiumpersec:Double) {
  override def toString = "Planet("+name+")"
}
