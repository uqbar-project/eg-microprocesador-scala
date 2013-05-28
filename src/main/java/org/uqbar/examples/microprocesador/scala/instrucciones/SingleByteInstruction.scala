package org.uqbar.examples.microprocesador.scala.instrucciones;

import org.uqbar.examples.microprocesador.scala._
import org.uqbar.examples.microprocesador.scala.parser._

abstract class SingleByteInstruction(code: Data) extends Instruction(code) with InstructionFactory {
	override def create(reader: ProgramReader, builder: ProgramBuilder) = builder addInstruction this
}

case object Add extends SingleByteInstruction(1) {
	override def execute(micro: Microprocesador) =  {
		val suma = micro.a + micro.b
		micro.updateAccumulators(suma)
	}
}

case object Swap extends SingleByteInstruction(5) {
	override def execute(micro: Microprocesador) = {
		val aux = micro.a
		micro.a = micro.b
		micro.b = aux
	}
}
