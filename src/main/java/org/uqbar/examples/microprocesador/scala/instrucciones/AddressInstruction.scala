package org.uqbar.examples.microprocesador.scala.instrucciones;

import org.uqbar.examples.microprocesador.scala._
import org.uqbar.examples.microprocesador.scala.decorator.InstructionReader
import org.uqbar.examples.microprocesador.scala.parser.InstructionFactory

abstract class AddressInstruction(code: Data) extends Instruction(code) with Cloneable with InstructionFactory {
	var address: Address = _

	override def createInstruction(reader: InstructionReader) = {
		val nueva = this.clone.asInstanceOf[AddressInstruction] 
		nueva.address = reader.readAddress 
		nueva
	}
}

// ****************************************************************************
// ** LoadFromAddress
// ****************************************************************************

object LoadFromAddress {
	def apply(value: Address): LoadFromAddress = new LoadFromAddress(value)
}

class LoadFromAddress extends AddressInstruction(8) {
	def this(address: Address) {
		this()
		this.address = address
	}

	override def execute(micro: Microprocesador) = micro.a = micro.data(address)
}

// ****************************************************************************
// ** StoreIntoAddress
// ****************************************************************************

object StoreIntoAddress {
	def apply(value: Address): StoreIntoAddress = new StoreIntoAddress(value)
}

case class StoreIntoAddress extends AddressInstruction(9) {
	def this(address: Address) {
		this()
		this.address = address
	}

	override def execute(micro: Microprocesador) = micro.data(address) = micro.a
}
