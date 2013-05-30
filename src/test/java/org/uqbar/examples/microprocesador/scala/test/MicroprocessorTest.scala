package org.uqbar.examples.microprocesador.scala.test

import org.uqbar.examples.microprocesador.scala._
import org.scalatest._
import scala.collection.mutable.ArrayBuffer
import org.uqbar.examples.microprocesador.scala.instrucciones.WhileNonZero

class BasicMicroTest {
	implicit def programToArray(p: TestProgram): Array[Byte] = p.toArray
}

class MicroprocessorTest extends BasicMicroTest with FunSuite {
	implicit class ProgramTester(program: TestProgram) {
		def should(name: String, assertions: (Microprocesador ⇒ Option[String])*): Unit = {
			test(name) {
				val micro = new Microprocesador
				micro execute program
				for (assertion ← assertions) assert(assertion(micro))
			}
		}
	}
	
	def data(d: Data) = Asserter { micro: Microprocesador ⇒ micro.data(d) }
	
	case class Asserter(left: Microprocesador ⇒ Any) {
		def ===(right: Any) = { micro: Microprocesador ⇒ left(micro) === right }
	}
}

class TestProgram extends ByteManipulation {
	var programBuffer = new ArrayBuffer[Byte]

	def LODV(value: Data) = add(7, value)

	def ADD = add(1)
	def SUB = add(2)
	def SWAP = add(5)
	
	def LOD(address: Address) = add(8, highPart(address), lowPart(address))
	def STR(address: Address) = add(9, highPart(address), lowPart(address))

	def WHNZ = add(14)
	def END = add(16)
	
	def add(data: Byte*) = programBuffer ++= data
	def toArray = programBuffer.toArray
}
