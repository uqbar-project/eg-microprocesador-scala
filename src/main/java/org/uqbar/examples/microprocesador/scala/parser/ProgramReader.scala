package org.uqbar.examples.microprocesador.scala.parser

import org.uqbar.examples.microprocesador.scala._

class ProgramReader(instructions: Array[OpCode]) extends ByteManipulation {
	var position = 0

	def hasNext = position < instructions.length;

	def readAddress = asAddress(readByte, readByte)

	def readByte = {
		val byte = instructions(position)
		position += 1
		byte
	}
}
