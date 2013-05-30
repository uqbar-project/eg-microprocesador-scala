package org.uqbar.examples.microprocesador.scala.parser;

import scala.collection.mutable.HashMap
import org.uqbar.examples.microprocesador.scala.Data
import org.uqbar.examples.microprocesador.scala.OpCode
import org.uqbar.examples.microprocesador.scala.instrucciones.Add
import org.uqbar.examples.microprocesador.scala.instrucciones.LoadFromAddress
import org.uqbar.examples.microprocesador.scala.instrucciones.LoadValue
import org.uqbar.examples.microprocesador.scala.instrucciones.Program
import org.uqbar.examples.microprocesador.scala.instrucciones.StoreIntoAddress
import org.uqbar.examples.microprocesador.scala.instrucciones.Sub
import org.uqbar.examples.microprocesador.scala.instrucciones.Swap
import org.uqbar.examples.microprocesador.scala.instrucciones.WhileNonZero

trait InstructionFactory {
	def code: OpCode
	def create(reader: ProgramReader, builder: ProgramBuilder): Unit
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
	defineFactories(
		Add,
		Sub,
		Swap,
		LoadValue,
		LoadFromAddress,
		StoreIntoAddress,
		WhileNonZero,
		EndHandler)

	protected def defineFactories(newFactories: InstructionFactory*) =
		for (factory ← newFactories) { factories.put(factory.code, factory) }

	// ************************************************************************
	// ** Parseo
	// ************************************************************************

	def parse(instructions: Array[Data]): Program = parse(new ProgramReader(instructions))

	def parse(implicit reader: ProgramReader): Program = {
		val builder = new ProgramBuilder
		while (reader.hasNext) {
			val opCode = reader.readByte
			val factory = getInstructionFactory(opCode)
			factory create(reader, builder)	
		}
		
		builder.build
	}

	private def getInstructionFactory(instructionCode: Data)(implicit reader: ProgramReader) = {
		this.factories.get(instructionCode) match {
			case Some(factory) ⇒ factory
			case None ⇒ throw new UnknownInstructionCodeException(instructionCode, reader.position)
		}
	}
}

class UnknownInstructionCodeException(instructionCode: OpCode, position: Int)
	extends Exception("Unknown instruction code: " + instructionCode + " at position: " + position) 

object EndHandler extends InstructionFactory {
	override def code = 16
	override def create(reader: ProgramReader, builder: ProgramBuilder) = builder.endComposite
}

