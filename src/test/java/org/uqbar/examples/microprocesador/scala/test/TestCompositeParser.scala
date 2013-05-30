package org.uqbar.examples.microprocesador.scala.test

import org.scalatest._
import org.uqbar.examples.microprocesador.scala._
import org.uqbar.examples.microprocesador.scala.instrucciones._

class TestCompositeParser extends MicroprocessorTest with FunSuite {
	new TestProgram {
		LODV(16); STR(0) // a = 16
		LODV(3); STR(1) // b = 3
		LODV(0); STR(2) // c = 0
		LOD(1)
		WHNZ
			LOD(0); SWAP; LOD(2); ADD; STR(2) // c = c + a
			LODV(1); SWAP; LOD(1); SUB; STR(1) // b = b - 1
			LOD(1)
		END
	} should ("parse WHNZ",
		data(0) === 16,
		data(1) === 0,
		data(2) === 48)
}
