package org.uqbar.examples.microprocesador.scala;

import java.util.List
import org.uqbar.examples.microprocesador.scala.instrucciones.Instruction
import org.uqbar.examples.microprocesador.scala.parser.Parser
import scala.collection.mutable.ArrayBuffer
import scala.collection.JavaConversions._

class Microprocesador extends ByteManipulation {
	val data = ArrayBuffer.fill[Data](1024)(0)
	var a: Data = 0
	var b: Data = 0

	def execute(instruction: Instruction) = instruction.execute(this)
	
	def ejecutar(instrucciones: Array[Byte]) {
		val programa = new Parser().parse(instrucciones)
		println(programa)
		
		for (instruction <- programa) {
			println(instruction)
			execute(instruction)
		}
	}
	
	def updateAccumulators(suma: Address) {
		a = lowPart(suma)
		b = highPart(suma)
	}

	override def toString = "A: " + a + ", B: " + b + ", data: " + data
}

trait ByteManipulation {
	implicit def asData(i: Int): Data = i.asInstanceOf[Data]
	def highPart(address: Address): Data = (address & 0xFF00) >> 8
	def lowPart(address: Address): Data = address & 0xFF
}
