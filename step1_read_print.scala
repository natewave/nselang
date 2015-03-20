// Copyright 2015 © Nizar S. All rights reserved.

package nse.repl

object REPL {
  def main(args: Array[String]) {
    println("Welcome to aREPL v0.0.1")
    println("nse-lang v0.0.1")
    repl()
  }

  def repl() {
    import scala.io.StdIn

    val namespace = "user"
    val prompt    = namespace + "> "

    val inputText = StdIn.readLine(prompt)

    inputText match {
      case "^D" | "exit" | "quit" | null => println("\nBye for now!")
      case _ => {
        val response = rep(inputText)
        println(response)
        repl()
      }
    }

  }

  def rep(text: String): String = {
    val readText      = read(text)
    val evaluatedText = eval(readText)

    print(evaluatedText)
  }

  def read(text: String): String  = text
  def eval(text: String): String  = text
  def print(text: String): String = text
}
