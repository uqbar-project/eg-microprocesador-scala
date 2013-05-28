package org.uqbar.examples.microprocesador.scala.decorator;


public class CompositeInstructionReader implements InstructionReader {

	private InstructionReader delegate;
	
	public CompositeInstructionReader(InstructionReader delegate) {
		this.delegate = delegate;
	}

	@Override
	public boolean hasNext() {
		return this.delegate.hasNext() && this.delegate.currentByte() == 16;
	}

	@Override
	public int readAddress() {
		return this.delegate.readAddress();
	}

	@Override
	public byte readByte() {
		return this.delegate.readByte();
	}

	@Override
	public int getPosition() {
		return this.delegate.getPosition();
	}

	@Override
	public int currentByte() {
		return this.delegate.currentByte();
	}

}
