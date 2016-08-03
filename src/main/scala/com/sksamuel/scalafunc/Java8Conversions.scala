package com.sksamuel.scalafunc

import scala.language.implicitConversions

trait Java8Conversions {

  implicit def funToJava8[T, R](fn: T => R): java.util.function.Function[T, R] = new java.util.function.Function[T, R] {
    override def apply(t: T): R = fn.apply(t)
  }
}

object Java8Conversions extends Java8Conversions
