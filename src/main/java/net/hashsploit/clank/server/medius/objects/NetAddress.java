package net.hashsploit.clank.server.medius.objects;

import java.io.IOException;
import java.nio.ByteBuffer;

import net.hashsploit.clank.server.medius.MediusSerializableObject;
import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.clank.server.medius.test.NetOutput;
import net.hashsploit.clank.server.scert.SCERTConstants;
import net.hashsploit.clank.server.scert.SCERTObject;
import net.hashsploit.clank.utils.Utils;

public class NetAddress implements MediusSerializableObject {
	
	public final NetAddressType type;
	public final String address;
	public final int port;
	
	public NetAddress(final NetAddressType type, final String address, final int port) {
		this.type = type;
		this.address = address;
		this.port = port;
	}

	@Override
	public void deserialize(NetInput netInput) throws IOException {
	}

	@Override
	public void serialize(NetOutput netOutput) throws IOException {
		netOutput.writeInt(type.getValue());
		netOutput.writeString(address, 16);
		netOutput.writeInt(port);
	}

	public NetAddressType getType() {
		return type;
	}

	public String getAddress() {
		return address;
	}

	public int getPort() {
		return port;
	}

	@Override
	public String toString() {
		return "NetAddress{" +
				"type=" + type.name() +
				", address='" + address + '\'' +
				", port=" + port +
				'}';
	}
}
