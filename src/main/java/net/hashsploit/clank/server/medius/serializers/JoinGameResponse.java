package net.hashsploit.clank.server.medius.serializers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.objects.MediusMessage;
import net.hashsploit.clank.server.medius.objects.NetConnectionInfo;
import net.hashsploit.clank.utils.Utils;

public class JoinGameResponse extends MediusMessage {

	private byte[] messageID;
	private byte[] callbackStatus;
	private byte[] gameHostType;
	private NetConnectionInfo netConnectionInfo;

	public JoinGameResponse(byte[] messageID, byte[]  callbackStatus, byte[] gameHostType, NetConnectionInfo netConnectionInfo) {
		super(MediusMessageType.JoinGameResponse);
		this.messageID = messageID;
		this.callbackStatus = callbackStatus;
		this.gameHostType = gameHostType;
		this.netConnectionInfo = netConnectionInfo;
	}

	@Override
	public byte[] getPayload() {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		try {
			outputStream.write(messageID);
			outputStream.write(Utils.hexStringToByteArray("000000")); // Padding
			outputStream.write(callbackStatus);
			outputStream.write(gameHostType);
			outputStream.write(netConnectionInfo.serialize());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outputStream.toByteArray();
	}
	
	
	public String toString() {
		return "JoinGameResponse: \n" + 
				"messageID: " + Utils.bytesToHex(messageID) + '\n' + 
				"callbackStatus: " + Utils.bytesToHex(callbackStatus) + '\n' + 
				"gameHostType: " + Utils.bytesToHex(gameHostType) + '\n' +
				"netConnectionInfoRaw: " + Utils.bytesToHex(netConnectionInfo.serialize()) + '\n' + 
				"netConnectionInfo: " + netConnectionInfo.toString();
	}

	public synchronized byte[] getMessageID() {
		return messageID;
	}

	public synchronized void setMessageID(byte[] messageID) {
		this.messageID = messageID;
	}

	public synchronized byte[] getCallbackStatus() {
		return callbackStatus;
	}

	public synchronized void setCallbackStatus(byte[] callbackStatus) {
		this.callbackStatus = callbackStatus;
	}

	public synchronized byte[] getGameHostType() {
		return gameHostType;
	}

	public synchronized void setGameHostType(byte[] gameHostType) {
		this.gameHostType = gameHostType;
	}

	public synchronized NetConnectionInfo getNetConnectionInfo() {
		return netConnectionInfo;
	}

	public synchronized void setNetConnectionInfo(NetConnectionInfo netConnectionInfo) {
		this.netConnectionInfo = netConnectionInfo;
	}
	

}
