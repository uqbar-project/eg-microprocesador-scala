package org.uqbar.examples.microprocesador.scala.dsltest

import org.uqbar.examples.microprocesador.scala.Microprocesador;
import org.scalatest._

class MicroprocesadorTestDsl extends MicroprocessorTest with FlatSpec with ShouldMatchers {
	"A microprocessor" should "add 2 + 4 = 6" in {

		val p = new Program {
			LODV(2)
			SWAP
			LODV(4)
			ADD
			STR(0)
		}

		val micro = new Microprocesador
		micro ejecutar p

		micro.data(0) should equal (6)
	}
}