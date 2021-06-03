package net.hashsploit.clank.server.medius.objects;

import net.hashsploit.clank.server.medius.MediusSerializableObject;
import net.hashsploit.clank.server.medius.test.NetOutput;

import java.io.IOException;

public enum MediusWorldSecurityLevelType implements MediusSerializableObject {

	/**
	 * No security on world.
	 */
	WORLD_SECURITY_NONE(0),

	/**
	 * Password required to join as a player
	 */
	WORLD_SECURITY_PLAYER_PASSWORD(1),

	/**
	 * World is closed to new players
	 */
	WORLD_SECURITY_CLOSED(2),

	/**
	 * Password is required to join as a spectator
	 */
	WORLD_SECURITY_SPECTATOR_PASSWORD(4);

	public final int value;

	MediusWorldSecurityLevelType(int value) {
		this.value = value;
	}

	@Override
	public void serialize(NetOutput netOutput) throws IOException {
		netOutput.writeInt(value);
	}

	public int getValue() {
		return value;
	}
}
