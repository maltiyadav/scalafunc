package com.sksamuel.scalafunc

import scala.language.implicitConversions

trait KotlinConversions {

  implicit def funToKotlin[R](fn: () => R): kotlin.jvm.functions.Function0[R] = new kotlin.jvm.functions.Function0[R] {
    override def invoke(): R = fn.apply()
  }

  implicit def funToKotlin[T, R](fn: (T) => R): kotlin.jvm.functions.Function1[T, R] = new kotlin.jvm.functions.Function1[T, R] {
    override def invoke(t: T): R = fn.apply(t)
  }

  implicit def funToKotlin[T1, T2, R](fn: (T1, T2) => R): kotlin.jvm.functions.Function2[T1, T2, R] = new kotlin.jvm.functions.Function2[T1, T2, R] {
    override def invoke(t1: T1, t2: T2): R = fn.apply(t1, t2)
  }

  implicit def funToKotlin[T1, T2, T3, R](fn: (T1, T2, T3) => R): kotlin.jvm.functions.Function3[T1, T2, T3, R] = new kotlin.jvm.functions.Function3[T1, T2, T3, R] {
    override def invoke(t1: T1, t2: T2, t3: T3): R = fn.apply(t1, t2, t3)
  }
}

object KotlinConversions extends KotlinConversions
