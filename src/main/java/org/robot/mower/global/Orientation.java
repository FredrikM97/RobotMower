package org.robot.mower.global;

public enum Orientation {
	N("North", -1),
	E("East", 1),
	S("South", 1),
	W("West", -1);

	private static final Orientation[] vals = values();
	private final String direction;
	private final int action;
	Orientation(String direction, int action) {
		this.direction = direction;
		this.action = action;
	}

	public Orientation next() {
		return vals[(this.ordinal() + 1) % vals.length];
	}

	public Orientation previous() {
		return vals[(this.ordinal() - 1 + vals.length) % vals.length];
	}

	public String getDirection() {
		return direction;
	}

	public int getAction() {
		return action;
	}
}