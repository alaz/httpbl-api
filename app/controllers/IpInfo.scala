package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._
import com.osinka.httpbl.HttpBL

object IpInfo extends Controller {
  implicit object responseWrites extends Writes[HttpBL.Response] {
    override def writes(r: HttpBL.Response) =
      Json.obj(
        "days" -> r.days,
        "threat" -> r.threat,
        "flags" -> r.flags
      )
  }

  def lookup(ip: String, apiKey: String) = Action {
    val api = HttpBL(apiKey)

    api(ip) match {
      case None =>
        NotFound

      case Some(response) =>
        Ok(Json.toJson(response)).as(JSON)
    }
  }
}
