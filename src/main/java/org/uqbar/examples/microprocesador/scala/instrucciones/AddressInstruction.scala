package org.uqbar.examples.microprocesador.scala.instrucciones;

import org.uqbar.examples.microprocesador.scala._
import org.uqbar.examples.microprocesador.scala.parser.InstructionFactory
import org.uqbar.examples.microprocesador.scala.parser.ProgramBuilder
import org.uqbar.examples.microprocesador.scala.parser.ProgramReader

abstract class AddressInstruction(address: Address) extends Instruction {
	override def toString = getClass.getSimpleName + "(" + address + ")"
}

trait AddressInstructionFactory extends InstructionFactory {
	def apply(address: Address): AddressInstruction

	override def create(reader: ProgramReader, builder: ProgramBuilder) = {
		val address = reader.readAddress
		val instruction = apply(address)
		builder addInstruction instruction
	}
}

// ****************************************************************************
// ** LoadFromAddress
// ****************************************************************************

object LoadFromAddress extends AddressInstructionFactory {
	override def code = 8
}

case class LoadFromAddress(address: Address) extends AddressInstruction(address) {
	override def execute(micro: Microprocesador) = micro.a = micro.data(address)
}

// ****************************************************************************
// ** StoreIntoAddress
// ****************************************************************************

object StoreIntoAddress extends AddressInstructionFactory  {
	override def code = 9
}

case class StoreIntoAddress(address: Address) extends AddressInstruction(address) {
	override def execute(micro: Microprocesador) = micro.data(address) = micro.a
}
