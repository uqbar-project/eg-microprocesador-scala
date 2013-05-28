package org.uqbar.examples.microprocesador.scala.parser

import org.uqbar.examples.microprocesador.scala.instrucciones.WhileNonZero

class WhileNonZeroFactory extends InstructionFactory {
	override def code = 14
	override def create(reader: ProgramReader, builder: ProgramBuilder) = builder addComposite new WhileNonZero()
}
