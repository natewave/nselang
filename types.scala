// Copyright 2015 © Nizar S. All rights reserved.

package nse.core.types

object Types {
  def isNumber(value: String): Boolean = {

  }
}

trait MalType

case class MalError(value: String) extends MalType

case class MalList(types: List[MalType]) extends MalType

case class GenericMalType(value: String) extends MalType

case class Number(value: Int) extends MalType

case class Symbol(value: String) extends MalType

object Symbol {
  def apply(value: String): Symbol = {
    value match {
      case "+" | "-" | "/" | "*" => Symbol(value)
      case _ => MalError("Type Error: " + value + " is not a Symbol")
    }
  }
}



