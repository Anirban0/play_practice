package configs

import io.getquill.mirrorContextWithQueryProbing.schemaMeta
import io.getquill.{MysqlJdbcContext, SnakeCase}

object QuillContext {
  lazy val ctx = new MysqlJdbcContext(SnakeCase,"quillCtx")
}

