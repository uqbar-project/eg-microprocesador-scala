package org.uqbar.examples.microprocesador.scala.dsltest

import org.uqbar.examples.microprocesador.scala._
import org.scalatest._
import org.uqbar.examples.microprocesador.scala.test.MicroprocessorTest
import org.uqbar.examples.microprocesador.scala.test.TestProgram

class MicroprocesadorTestDsl2 extends MicroprocessorTest {
	new TestProgram {
		LODV(2)
		SWAP
		LODV(4)
		ADD
		STR(0)
	} should ("add 2 + 4 = 6", data(0) === 6)
}
