package org.uqbar.examples.microprocesador.scala.test

import org.uqbar.examples.microprocesador.scala._
import org.scalatest._
import scala.collection.mutable.ArrayBuffer

class MicroprocessorTest {
	implicit def programToArray(p: Program): Array[Byte] = p.toArray

	class Program extends ByteManipulation {
		var programBuffer = new ArrayBuffer[Byte]

		def LODV(value: Data) = add(7, value)

		def SWAP = add(5)
		def ADD = add(1)
		def STR(address: Address) = add(9, highPart(address), lowPart(address))

		def add(data: Byte*) = programBuffer ++= data
		def toArray = programBuffer.toArray
	}

}

