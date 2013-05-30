package org.uqbar.examples.microprocesador.scala;

import java.util.List
import org.uqbar.examples.microprocesador.scala.instrucciones.Instruction
import org.uqbar.examples.microprocesador.scala.parser.Parser
import scala.collection.mutable.ArrayBuffer
import scala.collection.JavaConversions._

class Microprocesador extends ByteManipulation {
	val data = ArrayBuffer.fill[Data](1024)(0)
	val _a = new Accumulator()
	val _b = new Accumulator()

	def execute(instruction: Instruction) = instruction.execute(this)

	def execute(instrucciones: Array[OpCode]) {
		val programa = new Parser().parse(instrucciones)
		execute(programa)
	}

	def accumulators: Address = asAddress(b, a)
	def accumulators_=(word: Address) = {
		a = lowPart(word)
		b = highPart(word)
	}

	def a = _a.get
	def a_=(newValue: Data) = _a.set(newValue)

	def b = _b.get
	def b_=(newValue: Data) = _b.set(newValue)

	override def toString = "A: " + a + ", B: " + b + ", data: " + data
}

class Accumulator {
	var value: Data = 0

	def get = value
	def set(newValue: Data) = {
		if (newValue > 255) throw new IllegalAccumulatorValueException
		value = newValue
	}
}

class IllegalAccumulatorValueException extends Exception

trait ByteManipulation {
	implicit def asData(i: Int): Data = i.asInstanceOf[Data]

	def highPart(address: Address): Data = (address & 0xFF00) >> 8
	def lowPart(address: Address): Data = address & 0xFF
	def split(address: Address): (Data, Data) = (highPart(address), lowPart(address))

	def asAddress(high: Data, low: Data) = (high << 8) + low
}
