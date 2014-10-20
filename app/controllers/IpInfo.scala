package controllers

import play.api.mvc.{Action,Controller}
import play.api.libs.json.Json
import com.osinka.httpbl.HttpBL

object IpInfo extends Controller {
  import Serialization._

  def lookup(ip: String, apiKey: String) = Action {
    val api = HttpBL(apiKey)

    api(ip) map {
      case response: HttpBL.Result =>
        Ok(Json.toJson(response)).as(JSON)
      case response: HttpBL.SearchEngine =>
        Ok(Json.toJson(response)).as(JSON)
    } getOrElse NotFound
  }
}

object Serialization {
  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  def addReplyType(t: String) =
    (js: JsValue) => {
      val replyType = ("reply" -> JsString(t))
      js.validate[JsObject].map(_ + replyType) getOrElse JsNull
    }

  implicit val resultWrites: Writes[HttpBL.Result] = (
    (JsPath \ "days").write[Int] and
    (JsPath \ "threat").write[Int] and
    (JsPath \ "flags").write[Int]
  )(unlift(HttpBL.Result.unapply)) transform addReplyType("result")

  implicit val searchEngineWrites: Writes[HttpBL.SearchEngine] = (
    (JsPath \ "serial").write[Int] and
    (JsPath \ "flags").write[Int]
  )(unlift(HttpBL.SearchEngine.unapply)) transform addReplyType("searchEngine")
}
