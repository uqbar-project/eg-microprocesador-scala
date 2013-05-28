package org.uqbar.examples.microprocesador.scala.instrucciones;

import org.uqbar.examples.microprocesador.scala._

abstract class Instruction(val code: Data) {
	def execute(micro: Microprocesador)
	// def unexecute(micro: Microprocesador)
}
