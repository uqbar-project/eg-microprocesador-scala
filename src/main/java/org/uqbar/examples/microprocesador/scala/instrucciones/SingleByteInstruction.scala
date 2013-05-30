package org.uqbar.examples.microprocesador.scala.instrucciones;

import org.uqbar.examples.microprocesador.scala._
import org.uqbar.examples.microprocesador.scala.parser._

abstract class SingleByteInstruction(val code: Data) extends Instruction with InstructionFactory {
	override def create(reader: ProgramReader, builder: ProgramBuilder) = builder addInstruction this
}

case object Add extends SingleByteInstruction(1) {
	override def execute(micro: Microprocesador) =  micro.accumulators = micro.a + micro.b
}

case object Sub extends SingleByteInstruction(2) {
	override def execute(micro: Microprocesador) = micro.accumulators = micro.a - micro.b
}

case object Swap extends SingleByteInstruction(5) {
	override def execute(micro: Microprocesador) = {
		val aux = micro.a
		micro.a = micro.b
		micro.b = aux
	}
}
