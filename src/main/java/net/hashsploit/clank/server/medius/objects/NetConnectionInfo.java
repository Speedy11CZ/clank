package net.hashsploit.clank.server.medius.objects;

import java.io.IOException;

import net.hashsploit.clank.server.medius.MediusSerializableObject;
import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.clank.server.medius.test.NetOutput;
import net.hashsploit.clank.utils.Utils;

public class NetConnectionInfo implements MediusSerializableObject {
	
	private final NetConnectionType netConnectionType; // 4 bytes
	private final NetAddressList addressList; // 24 + 24 = 48 bytes
	private final int worldId; // 4 bytes
	private final byte[] serverKey; // 64 bytes (RSA KEY)
	private final byte[] sessionKey; // 17 bytes
	private final byte[] accessKey; // 17 bytes
	
	public NetConnectionInfo(NetConnectionType type, NetAddressList addressList, int worldId, byte[] serverKey, byte[] sessionKey, byte[] accessKey) {
		this.netConnectionType = type;
		this.addressList = addressList;
		this.worldId = worldId;
		this.serverKey = serverKey;
		this.sessionKey = sessionKey;
		this.accessKey = accessKey;
	}

	@Override
	public void deserialize(NetInput netInput) {

	}

	@Override
	public void serialize(NetOutput netOutput) throws IOException {
		netOutput.writeInt(netConnectionType.getValue());
		netOutput.writeObject(addressList);
		netOutput.writeInt(worldId);
		netOutput.writeBytes(serverKey);
		netOutput.writeBytes(sessionKey);
		netOutput.writeBytes(accessKey);
		netOutput.skipBytes(2);
	}

	public byte[] serialize() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		return "NetConnectionInfo{" +
				"netConnectionType=" + netConnectionType.name() +
				", addressList=" + addressList.toString() +
				", worldId=" + worldId +
				", serverKey=" + Utils.bytesToHex(serverKey) +
				", sessionKey=" + Utils.bytesToHex(sessionKey) +
				", accessKey=" + Utils.bytesToHex(accessKey) +
				'}';
	}

	/**
	 * Get the Net Connection Type.
	 * @return
	 */
	public NetConnectionType getConnectionType() {
		return netConnectionType;
	}
	
	/**
	 * Get the Address List.
	 * @return
	 */
	public NetAddressList getAddressList() {
		return addressList;
	}
	
	/**
	 * Get the World Id.
	 * @return
	 */
	public int getWorldId() {
		return worldId;
	}
	
	/**
	 * Get the server RSA key.
	 * @return
	 */
	public byte[] getServerKey() {
		return serverKey;
	}
	
	/**
	 * Get the session key.
	 * @return
	 */
	public byte[] getSessionKey() {
		return sessionKey;
	}
	
	/**
	 * Get the key returned from 
	 * @return
	 */
	public byte[] getAccessKey() {
		return accessKey;
	}
}
