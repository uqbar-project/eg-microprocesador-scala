package org.uqbar.examples.microprocesador.scala.dsltest;

import junit.framework.Assert
import org.junit.Test
import org.uqbar.examples.microprocesador.scala.Microprocesador

class MicroprocesadorTestOriginal {

	@Test
	def simpleProgram = {
		val program = Array[Byte] (
			7, 2,    // 0: LODV 2
			5,       // 2: SWAP 
			7, 4,    // 3: LODV 4
			1,       // 5: ADD
			9, 0, 0  // 6: STR 0
		)
		
		val micro = new Microprocesador
		micro execute program
		
		Assert.assertEquals(6, micro.data(0))
	}
}
