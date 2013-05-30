package org.uqbar.examples.microprocesador.scala.instrucciones;

import org.uqbar.examples.microprocesador.scala._
import scala.collection.mutable.ArrayBuffer
import org.uqbar.examples.microprocesador.scala.parser._
import scala.collection.generic.Growable

abstract class CompositeInstruction extends Instruction {
	val children = new ArrayBuffer[Instruction]
	def addChild(newChild: Instruction) = children += newChild

	def executeChildren(micro: Microprocesador) = for (instruction ← children) micro execute instruction
	override def toString = getClass.getSimpleName + children.mkString("(", ", ", ")")
}

abstract class CompositeInstructionCompanion {
	def apply(): CompositeInstruction
	def apply(instructions: Instruction*): CompositeInstruction = {
		val result = apply()
		instructions foreach result.addChild
		result
	}
}

abstract class CompositeInstructionFactory(val code: OpCode) extends CompositeInstructionCompanion with InstructionFactory {
	override def create(reader: ProgramReader, builder: ProgramBuilder) = builder addComposite apply()
}

object WhileNonZero extends CompositeInstructionFactory(14)
case class WhileNonZero extends CompositeInstruction {
	override def execute(micro: Microprocesador) = while (micro.a != 0) executeChildren(micro)
}

object Program extends CompositeInstructionCompanion
case class Program extends CompositeInstruction {
	override def execute(micro: Microprocesador) = executeChildren(micro)
}
