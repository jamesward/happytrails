package controllers

import play.api.mvc.{Action, Result, AnyContent, Request}
import views.html.index
import play.api.mvc.Results._

package object utils {
  
  // renders the index template if the requested content type is HTML
  def ContentAction(f: Request[AnyContent] => Result): Action[AnyContent] = {
    Action { request =>
      request match {
        case AcceptExtractors.Accepts.Html() => Ok(index.render)
        case _ => f(request)
      }
    }
  }
  
  object AcceptExtractors extends play.api.mvc.AcceptExtractors

}