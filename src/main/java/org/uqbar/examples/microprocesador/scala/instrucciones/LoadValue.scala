package org.uqbar.examples.microprocesador.scala.instrucciones;

import org.uqbar.examples.microprocesador.scala._
import org.uqbar.examples.microprocesador.scala.parser._

object LoadValue extends InstructionFactory {
	
	override def code = 7
	
	def create(reader: ProgramReader, builder: ProgramBuilder) = {
		val value = reader.readByte
		val instruction = apply(value)
		builder addInstruction instruction
	}
}

case class LoadValue(value: Data) extends Instruction {
	override def execute(micro: Microprocesador) = micro.a = value
}
