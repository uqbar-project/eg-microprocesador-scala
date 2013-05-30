package org.uqbar.examples.microprocesador.scala.dsltest

import org.scalatest._
import org.uqbar.examples.microprocesador.scala._
import org.uqbar.examples.microprocesador.scala.instrucciones._
import org.uqbar.examples.microprocesador.scala.test.MicroprocessorTest

class MicroprocesadorTestDsl3 extends FunSuite with ShouldMatchers {
	implicit var micro: Microprocesador = _

	testProgram("add 2 + 4 = 6") {
		LODV(2)
		SWAP
		LODV(4)

		accumA should equal (4)
		accumB should equal (2)

		ADD
		STR(0)

		data(0) should equal (6)
	}

	// ************************************************************************
	// ** Test Utilities
	// ************************************************************************

	def testProgram(name: String)(code: ⇒ Unit): Unit = {
		test(name) {
			micro = new Microprocesador
			code
		}
	}

	// ************************************************************************
	// ** Instructions
	// ************************************************************************

	def LODV(value: Data) = micro execute LoadValue(value)
	def SWAP = micro execute Swap
	def ADD = micro execute Add
	def STR(address: Address) = micro execute StoreIntoAddress(address)

	// ************************************************************************
	// ** Information
	// ************************************************************************

	def accumA = micro.a
	def accumB = micro.b
	def data(d: Data) = micro.data(d)
}