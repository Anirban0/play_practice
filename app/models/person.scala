package models
import akka.http.scaladsl.model.HttpHeader.ParsingResult.Ok
import io.getquill.mirrorContextWithQueryProbing.{liftQuery, query, querySchema, quote, schemaMeta}
import io.getquill.{Embedded, Literal, MirrorSqlDialect, SqlMirrorContext}
import configs.QuillContext
import configs.QuillContext.ctx
import zio.ZIO
import io.getquill.EntityQuery

import java.sql.SQLException



case class Person(id: Int, name: String ,age: Int)





//val SchemaMeta = quote {
//  querySchema[Person](
//    "Person",
//    _.id -> "id",
//    _.name -> "name",
//    _.age -> "age"
//  )
//  //    ctx.run(SchemaMeta)
//
//}

object Person{

//  val ctx = new SqlMirrorContext(MirrorSqlDialect, Literal)
implicit val schema = schemaMeta[Person](
  "my_custom_name",
  _.id -> "id",
  _.name -> "name",
  _.age -> "age"
)
//


   //    ctx.run(q).toString
//    val q = quote{
//      query[Person].map(x=> x.name)
//    }

    val q = quote(query[Person])
    ctx.run(q)



}

