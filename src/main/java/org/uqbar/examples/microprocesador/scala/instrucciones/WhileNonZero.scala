package org.uqbar.examples.microprocesador.scala.instrucciones;

import org.uqbar.examples.microprocesador.scala.Microprocesador;
import scala.collection.mutable.ArrayBuffer

class WhileNonZero(children: Seq[Instruction]) extends Instruction(14) {
	
	override def execute(micro: Microprocesador) = {
		while (micro.a != 0) {
			for (instruction <- children) micro execute instruction
		}
	}

}
