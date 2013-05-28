package org.uqbar.examples.microprocesador.scala.instrucciones;

import org.uqbar.examples.microprocesador.scala._
import org.uqbar.examples.microprocesador.scala.decorator.InstructionReader
import org.uqbar.examples.microprocesador.scala.parser.InstructionFactory

object LoadValue {
	def apply(value: Data): LoadValue = new LoadValue(value)
}

case class LoadValue extends Instruction(7) with Cloneable with InstructionFactory {
	var value: Data = _

	def this(value: Data) = {
		this()
		this.value = value
	}

	override def execute(micro: Microprocesador) = micro.a = value

	override def createInstruction(reader: InstructionReader) = {
		val nueva = this.clone.asInstanceOf[LoadValue]
		nueva.value = reader.readByte
		nueva
	}
}
