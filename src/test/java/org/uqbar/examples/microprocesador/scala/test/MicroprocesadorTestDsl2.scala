package org.uqbar.examples.microprocesador.scala.test

import org.uqbar.examples.microprocesador.scala._
import org.scalatest._

class MicroprocesadorTestDsl2 extends MicroprocessorTest with FunSuite {
	new Program {
		LODV(2)
		SWAP
		LODV(4)
		ADD
		STR(0)
	} should {
		data(0) === 6
	}

	// ************************************************************************
	// ** Helpers
	// ************************************************************************

	implicit class ProgramTester(program: Program) {
		def should(assertions: (Microprocesador ⇒ Option[String])*): Unit = {
			test("add 2 + 4 = 6") {
				val micro = new Microprocesador
				micro ejecutar program
				for (assertion ← assertions) assert(assertion(micro))
			}
		}
	}

	def data(d: Data) = Asserter { micro: Microprocesador ⇒ micro.data(d) }

	case class Asserter(left: Microprocesador ⇒ Any) {
		def ===(right: Any) = { micro: Microprocesador ⇒ left(micro) === right }
	}
}
