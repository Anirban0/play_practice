package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.filters.csrf._
import javax.inject.Inject
import play.api.db.Database
import play.api.mvc.AnyContent
import models._

import java.sql.Statement
import scala.concurrent.Future


/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }
  def login() = Action { implicit request: Request[AnyContent] =>
    request.session.get("connected").map{args =>
      Ok(views.html.userDetails(Seq(request.session.get("connected").head,"*******")))
    }.getOrElse(Ok(views.html.login()))

  }


//  def login

  def validateLoginGet(email: String, password: String): Action[AnyContent] = Action {implicit request: Request[AnyContent] =>
    val userData: Seq[String] = Seq(email,password)
    Ok(views.html.userDetails(userData))

  }


  def validateLoginPost(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>

    val req = request.body.asFormUrlEncoded
//    val emailSeq = req.get("email")
//    val email: String = emailSeq(0)
//    val passSeq = req.get("passoword")
//    val pass: String = passSeq(0)
//    Ok(views.html.userDetails(email,pass))
    
    req.map{ args =>
      val email = args.get("email").get.head

      val password = args.get("password").get.head

      val userData: Seq[String]= Seq(email,password)
      if (email == "anirban@gmail.com") {
        Ok(views.html.userDetails(userData)).withSession("connected"->email)
      }
      else{
        Redirect("login").flashing("Failed"->"User not found")
      }
    }.getOrElse(Redirect("login").flashing("failed"->"No values entered"))
  }
  def logout(): Action[AnyContent] = Action{ implicit request: Request[AnyContent] =>
    Redirect("/").withSession(request.session - "connected")

  }
  def getPerson(id: Int): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>

    Ok(views.html.person(models.Person()))
  }
}
