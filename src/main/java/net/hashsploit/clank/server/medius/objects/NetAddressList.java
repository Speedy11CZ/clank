package net.hashsploit.clank.server.medius.objects;

import net.hashsploit.clank.server.medius.MediusSerializableObject;
import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.clank.server.medius.test.NetOutput;
import net.hashsploit.clank.utils.Utils;

import java.io.IOException;

public class NetAddressList implements MediusSerializableObject {
	
	public static final int NET_ADDRESS_LIST_COUNT = 2;
	private final NetAddress[] addressList;
	
	public NetAddressList(NetAddress first, NetAddress second) {
		this.addressList = new NetAddress[NET_ADDRESS_LIST_COUNT];
		this.addressList[0] = first;
		this.addressList[1] = second;
	}

	@Override
	public void deserialize(NetInput netInput) throws IOException {

	}

	@Override
	public void serialize(NetOutput netOutput) throws IOException {
		netOutput.writeObject(addressList[0]);
		netOutput.writeObject(addressList[1]);
	}

	@Override
	public String toString() {
		return "NetAddressList{" +
				"firstNetAddress=" + addressList[0].toString() +
				", secondNetAddress" + addressList[1].toString() +
				'}';
	}
}
