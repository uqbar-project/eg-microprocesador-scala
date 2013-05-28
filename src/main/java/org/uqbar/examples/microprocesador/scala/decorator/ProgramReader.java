package org.uqbar.examples.microprocesador.scala.decorator;



public class ProgramReader implements InstructionReader  {

	private byte[] instructions;
	private int position;
	
	public ProgramReader(byte[] instructions) {
		this.instructions = instructions;
		this.position = 0;
	}

	@Override
	public boolean hasNext() {
		return position < instructions.length;
	}

	@Override
	public int readAddress() {
		int low = this.readByte();
		int high = this.readByte();
		return (high << 8) + low;
	}

	@Override
	public byte readByte() {
		return this.instructions[this.position++];
	}

	@Override
	public int getPosition() {
		return this.position;
	}

	@Override
	public int currentByte() {
		return this.instructions[this.position];
	}
	
}
