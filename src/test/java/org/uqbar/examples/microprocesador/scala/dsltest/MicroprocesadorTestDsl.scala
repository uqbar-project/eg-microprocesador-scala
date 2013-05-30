package org.uqbar.examples.microprocesador.scala.dsltest

import org.scalatest._
import org.uqbar.examples.microprocesador.scala.Microprocesador
import org.uqbar.examples.microprocesador.scala.test.MicroprocessorTest
import org.uqbar.examples.microprocesador.scala.test.TestProgram
import org.uqbar.examples.microprocesador.scala.test.BasicMicroTest

class MicroprocesadorTestDsl extends BasicMicroTest with FlatSpec with ShouldMatchers {
	"A microprocessor" should "add 2 + 4 = 6" in {

		val p = new TestProgram {
			LODV(2)
			SWAP
			LODV(4)
			ADD
			STR(0)
		}

		val micro = new Microprocesador
		micro execute p

		micro.data(0) should equal (6)
	}
}