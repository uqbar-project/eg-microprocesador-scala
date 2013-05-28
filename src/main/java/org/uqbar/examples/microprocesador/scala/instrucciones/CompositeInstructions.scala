package org.uqbar.examples.microprocesador.scala.instrucciones;

import org.uqbar.examples.microprocesador.scala._
import scala.collection.mutable.ArrayBuffer

abstract class CompositeInstruction(code: OpCode) extends Instruction(code) {
	val children = new ArrayBuffer[Instruction]
	
	def addChild(newChild: Instruction) = children += newChild
	def executeChildren(micro: Microprocesador) = for (instruction <- children) micro execute instruction
}

class WhileNonZero extends CompositeInstruction(14) {
	override def execute(micro: Microprocesador) = while (micro.a != 0) executeChildren(micro)
}

class Program extends CompositeInstruction(null.asInstanceOf[OpCode]) {
	override def execute(micro: Microprocesador) = executeChildren(micro)
}
