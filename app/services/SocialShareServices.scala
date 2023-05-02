package services


import javax.inject.Inject
import play.api.libs.ws._
import play.api.mvc.Results.{BadRequest, Ok, Redirect}


import scala.concurrent.ExecutionContext.Implicits.global
import java.net.URLEncoder

case class feedDetails(imgUrl: String, feedDesc: String)

class SocialShareServices @Inject() (ws: WSClient){
  def getFeedDetails(feedId: Int) = {
    val imgUrl = "https://images.pexels.com/photos/209037/pexels-photo-209037.jpeg?auto=compress&cs=tinysrgb&w=1600"
    val feedDesc = "This is just a demo description..."
    feedDetails(imgUrl, feedDesc)
  }

  def postOnFb(imgUrl: String, feedDesc: String) ={
    val imgUrlEnc = URLEncoder.encode(imgUrl, "UTF-8")
    Redirect(s"${credentials.fb}" + s"u=$imgUrlEnc&t=$feedDesc")
  }

  def postOnReddit(imgUrl: String, feedDesc: String) ={
    val imgUrlEnc = URLEncoder.encode(imgUrl, "UTF-8")
    Redirect(s"${credentials.reddit}" + s"url=$imgUrlEnc&title=$feedDesc")
  }

  def postOnTwitter(imgUrl: String, feedDesc: String) = {
    val imgUrlEnc = URLEncoder.encode(imgUrl, "UTF-8")
    Redirect(s"${credentials.twitter}" + s"url=$imgUrlEnc&text=$feedDesc")
  }

  def postOnLinkedIn(imgUrl: String, feedDesc: String) = {
    val imgUrlEnc = URLEncoder.encode(imgUrl, "UTF-8")
    Redirect(s"${credentials.linkedin}" + s"url=$imgUrlEnc")
  }

  def postOnSkype(imgUrl: String, feedDesc: String) = {
    val imgUrlEnc = URLEncoder.encode(imgUrl, "UTF-8")
    Redirect(s"${credentials.skype}" + s"url=$imgUrlEnc&text=$feedDesc")
  }

  def postOnGmail(imgUrl: String, feedDesc: String) = {
    val imgUrlEnc = URLEncoder.encode(imgUrl, "UTF-8")
    Redirect(s"${credentials.gmail}" + s"su=$feedDesc&body=$imgUrlEnc")
  }

  def postOnWhatsApp(imgUrl: String, feedDesc: String) = {
    val imgUrlEnc = URLEncoder.encode(imgUrl, "UTF-8")
    Redirect(s"${credentials.whatsapp}" + s"text=$feedDesc%20$imgUrlEnc")
  }

  def postOnPinterest(imgUrl: String, feedDesc: String) = {
    val imgUrlEnc = URLEncoder.encode(imgUrl, "UTF-8")
    Redirect(s"${credentials.pinterest}" + s"url=$imgUrlEnc")
  }


  object credentials{
    val fb = "https://www.facebook.com/sharer.php?"
    val reddit = "https://reddit.com/submit?"
    val twitter = "https://twitter.com/intent/tweet?"
    val linkedin = "https://www.linkedin.com/sharing/share-offsite/?"
    val skype = "https://web.skype.com/share?"
    val gmail = "https://mail.google.com/mail/?view=cm&to=&"
    val whatsapp = "https://api.whatsapp.com/send?&"
    val pinterest = "http://pinterest.com/pin/create/link/?"
  }
}
