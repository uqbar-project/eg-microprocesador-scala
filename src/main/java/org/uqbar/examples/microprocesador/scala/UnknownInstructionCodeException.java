package org.uqbar.examples.microprocesador.scala;

public class UnknownInstructionCodeException extends RuntimeException {

	public UnknownInstructionCodeException(byte instructionCode, int position) {
		super("Unknown instruction code: " + instructionCode + " at position: " + position);
	}
}
