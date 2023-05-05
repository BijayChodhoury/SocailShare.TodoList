package controllers


import javax.inject._
import play.api._
import play.api.libs.json.Json
import play.api.mvc._
import services.SocialShareServices

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Try, Success, Failure}

import services.{BirthDayServices, person, dob}

@Singleton
class BirthDayController @Inject()(val birthDayServices: BirthDayServices, val controllerComponents: ControllerComponents) extends BaseController {
  def testing = Action { request =>
    request.body.asJson.map { json =>
      (json \ "fname").asOpt[String].map { fname =>
        (json \ "lname").asOpt[String].map { lname =>
          (json \ "dob").asOpt[String].map {
            case "" => BadRequest("DOB is invalid")
            case db =>
              Try{
                val DOB = db.split("-")
                val p_obj = person(fname, lname, dob(DOB(0).toInt, DOB(1).toInt, DOB(2).toInt))
                val results = birthDayServices.isTodayYourBirthDay(p_obj)
                results
              } match {
                case Success(value) => Ok(value)
                case Failure(ex) => BadRequest("error occurred" + ex.printStackTrace())
              }
          }.getOrElse {BadRequest("Missing parameter [dob]")}
        }.getOrElse {BadRequest("Missing parameter [last name]")}
      }.getOrElse {BadRequest("Missing parameter [first name]")}
    }.getOrElse {BadRequest("Expecting Json data")}
  }


  def fileWithName = Action {
    Ok.sendFile(
      content = new java.io.File("C:\\Users\\Bijay Choudhary\\OneDrive\\Pictures\\report.pdf"),
      fileName = _ => Some("report.pdf")
    )
//    Ok(views.html.index()).flashing()
  }
}



