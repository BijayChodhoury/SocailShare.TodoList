package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import services.SocialShareServices
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class HomeController @Inject()(val socialShareServices: SocialShareServices, val controllerComponents: ControllerComponents) extends BaseController {

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }
  def popup() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.popup())
  }

  def shareOnSP(selected_option: String, feedId: Int) = Action { request =>
    val feedDetails = socialShareServices.getFeedDetails(feedId)

    selected_option match {
      case "FB" => socialShareServices.postOnFb(feedDetails.imgUrl, feedDetails.feedDesc)
      case "Reddit" => socialShareServices.postOnReddit(feedDetails.imgUrl, feedDetails.feedDesc)
      case "Twitter" => socialShareServices.postOnTwitter(feedDetails.imgUrl, feedDetails.feedDesc)
      case "LinkedIn" => socialShareServices.postOnLinkedIn(feedDetails.imgUrl, feedDetails.feedDesc)
      case "Skype" => socialShareServices.postOnSkype(feedDetails.imgUrl, feedDetails.feedDesc)
      case "Gmail" => socialShareServices.postOnGmail(feedDetails.imgUrl, feedDetails.feedDesc)
      case "WhatsApp" => socialShareServices.postOnWhatsApp(feedDetails.imgUrl, feedDetails.feedDesc)
      case "Pinterest" => socialShareServices.postOnPinterest(feedDetails.imgUrl, feedDetails.feedDesc)
    }
  }
}
