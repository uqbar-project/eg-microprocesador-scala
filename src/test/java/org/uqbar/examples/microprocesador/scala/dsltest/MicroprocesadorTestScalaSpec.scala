package org.uqbar.examples.microprocesador.scala.dsltest

import org.uqbar.examples.microprocesador.scala.Microprocesador;
import org.scalatest._

class MicroprocesadorTestScalaSpec extends FlatSpec with ShouldMatchers {
	"A microprocessor" should "add 2 + 4 = 6" in {
		val program = Array[Byte](
			7, 2, // 0: LODV 2
			5, // 2: SWAP 
			7, 4, // 3: LODV 4
			1, // 5: ADD
			9, 0, 0 // 6: STR 0
		)

		val micro = new Microprocesador
		micro ejecutar program

		micro.data(0) should equal (6)
	}
}