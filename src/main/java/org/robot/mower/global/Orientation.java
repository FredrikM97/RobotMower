package org.robot.mower.global;

public enum Orientation {
	N,
	E,
	S,
	W;

	private static final Orientation[] vals = values();

	public Orientation next() {
		return vals[(this.ordinal() + 1) % vals.length];
	}

	public Orientation previous() {
		return vals[(this.ordinal() - 1 + vals.length) % vals.length];
	}
}