package org.uqbar.examples.microprocesador.scala.parser;

import scala.collection.mutable.Stack
import org.uqbar.examples.microprocesador.scala.instrucciones._

class ProgramBuilder {
	val compositeStack = Stack[CompositeInstruction](new Program())

	def addInstruction(instruction: Instruction) = compositeStack.top addChild instruction
	
	def addComposite(composite: CompositeInstruction) = compositeStack push composite
	def endComposite = addInstruction(compositeStack.pop)

	def build: Program = {
		if (compositeStack.size != 1) throw new MalformedInstructionException
		compositeStack.pop.asInstanceOf[Program]
	}
}

class MalformedInstructionException extends Exception