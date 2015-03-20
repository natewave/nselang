// Copyright 2015 © Nizar S. All rights reserved.

package nse.core

case class ReadStream(tokens: Vector[String], position: Int = 0) {

  def pointer(p: Int): ReadStream = copy(position=p)

  // Returns the token at the current position
  def peek: String = tokens(position)

  // Returns the tokens at the current position and increments the position
  def next: (String, ReadStream) = {
    val token         = peek
    val nextPosition  = position + 1
    val stream        = pointer(nextPosition)

    (token, stream)
  }

}

object Reads {
  val expressions = """[\s,]*(~@|[\[\]{}()'`~^@]|"(?:\\.|[^\\"])*"|;.*|[^\s\[\]{}('"`,;)]*)""".r

  def read_str(text: String) {
    val tokens     = tokenizer(text)
    val readStream = ReadStream(tokens)
    read_form(readStream)
  }

  def tokenizer(text: String): Vector[String] = {
    val sexps = expressions.findAllIn(text)
    sexps.toVector
  }

  def read_form(readStream: ReadStream, typesList: MalList): MalType = {
    val token = readStream.peek

    token match {
      case "(" => read_list(token, readStream, typesList)
      case _   => read_atom(token, readStream)
    }
  }

  def read_list(token: String, readStream: ReadStream, typesList: MalList): MalType = {
    token match {
      case ")" => typesList
      case "EOF" => {
        if (typesList(typesList.length) != ")" )
          MalError("Parse Error: missing enclosing char )")
        else typesList
      }
      case _ => {
        val tokens = typesList :+ GenericMalType(value=tokens)
        read_form(readStream, tokens)
      }
    }
  }
  def read_atom(char: String, readStream: ReadStream): MalType = {
    MalError
  }
}
