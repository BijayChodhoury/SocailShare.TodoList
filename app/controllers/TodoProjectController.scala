package controllers

import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json
import play.api.mvc._

import javax.inject._


case class listDetails(listName: String, listDescription: String)


class TodoProjectController@Inject()(val controllerComponents: ControllerComponents) extends BaseController {
  val userForm = Form(
    mapping(
      "listName" -> text,
      "listDescription" -> text
    )(listDetails.apply)(listDetails.unapply)
  )

  def createList = Action { implicit request =>
    userForm.bindFromRequest.fold(
      err => BadRequest("error"),
      suc => Ok(Json.obj(
        "List Name"->suc.listName,
        "List Description"->suc.listDescription))
    )
  }

  object todoList{
    var list_01 = List()
  }
}
