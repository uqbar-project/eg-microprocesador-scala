package org.uqbar.examples.microprocesador.scala.decorator;

public interface InstructionReader {

	public boolean hasNext();

	public int readAddress();

	public byte readByte();

	public int getPosition();

	public int currentByte();

}