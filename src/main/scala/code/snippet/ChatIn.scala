package code
package snippet

import net.liftweb._
import http._
import js._
import JsCmds._
import JE._

import comet.ChatServer
import comet.ReRender

import code.comet.PlayerInstance

object ChangePlayerName {
  def render = SHtml.onSubmit(s => {
    PlayerInstance.is.name = s
    ChatServer ! ReRender
    S.notice("You changed your name to " + s)
  })
}