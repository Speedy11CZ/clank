package net.hashsploit.clank.server.medius.objects;

import net.hashsploit.clank.server.medius.MediusSerializableObject;
import net.hashsploit.clank.server.medius.test.NetOutput;

import java.io.IOException;

public enum MediusApplicationType implements MediusSerializableObject {
	
	MEDIUS_APP_TYPE_GAME(0),
	
	LOBBY_CHAT_CHANNEL(1);
	
	public final int value;
	
	MediusApplicationType(int value) {
		this.value = value;
	}

	@Override
	public void serialize(NetOutput netOutput) throws IOException {
		netOutput.writeInt(value);
	}
}
