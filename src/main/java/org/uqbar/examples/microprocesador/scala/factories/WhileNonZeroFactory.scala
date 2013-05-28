package org.uqbar.examples.microprocesador.scala.factories;

import org.uqbar.examples.microprocesador.scala.decorator.CompositeInstructionReader;
import org.uqbar.examples.microprocesador.scala.decorator.InstructionReader;
import org.uqbar.examples.microprocesador.scala.instrucciones.Instruction;
import org.uqbar.examples.microprocesador.scala.instrucciones.WhileNonZero;
import org.uqbar.examples.microprocesador.scala.parser.InstructionFactory;
import org.uqbar.examples.microprocesador.scala.parser.Parser;

class WhileNonZeroFactory(parser: Parser) extends InstructionFactory {
	override def code = 14

	override def createInstruction(reader: InstructionReader) = {
		val instructions = parser.parse(new CompositeInstructionReader(reader))
		
		// para consumir le ultimo end
		reader.readByte
		
		new WhileNonZero(instructions)
	}

}
