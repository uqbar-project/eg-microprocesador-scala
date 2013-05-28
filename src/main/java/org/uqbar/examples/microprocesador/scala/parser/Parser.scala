package org.uqbar.examples.microprocesador.scala.parser;

import org.uqbar.examples.microprocesador.scala._
import org.uqbar.examples.microprocesador.scala.decorator.InstructionReader
import org.uqbar.examples.microprocesador.scala.decorator.ProgramReader
import org.uqbar.examples.microprocesador.scala.factories.WhileNonZeroFactory
import org.uqbar.examples.microprocesador.scala.instrucciones._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.ArrayBuffer

trait InstructionFactory {
	def code: Data
	def createInstruction(reader: InstructionReader): Instruction 
}

class Parser {
	var factories = new HashMap[Byte, InstructionFactory]()

	// ************************************************************************
	// ** Construccion
	// ************************************************************************

	/**
	 * Se crea el lector de instrucciones. Y se crea un mapa con las
	 * instrucciones
	 */
	addInstructionFactory(Add)
	addInstructionFactory(Swap)
	addInstructionFactory(new LoadValue)
	addInstructionFactory(new StoreIntoAddress)
	addInstructionFactory(new WhileNonZeroFactory(this))

	protected def addInstructionFactory(factory: InstructionFactory) = factories.put(factory.code, factory)

	// ************************************************************************
	// ** Parseo
	// ************************************************************************

	def parse(instructions: Array[Data]): Seq[Instruction] = parse(new ProgramReader(instructions))

	def parse(reader: InstructionReader): Seq[Instruction] = {
		var output = new ArrayBuffer[Instruction]
		while (reader.hasNext()) {
			output += readInstruction(reader)
		}

		output.toList
	}

	def readInstruction(reader: InstructionReader) = getInstructionFactory(reader.readByte(), reader).createInstruction(reader)

	private def getInstructionFactory(instructionCode: Data, reader: InstructionReader) = {
		this.factories.get(instructionCode) match {
			case Some(factory) ⇒ factory
			case None ⇒ throw new UnknownInstructionCodeException(instructionCode, reader.getPosition())
		}
	}
}
