package utils

import play.api.Play.current
import play.api.mvc.Call
import play.api.Play

object CDN {
  
  def apply(call: Call): String = {
    
    Play.maybeApplication.flatMap { app =>
      app.configuration.getString("cdnurl")
    }.map { cdnurl =>
      cdnurl.format(call.url)
    }.getOrElse(call.url)

  }
  
}