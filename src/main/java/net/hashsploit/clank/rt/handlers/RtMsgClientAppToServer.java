package net.hashsploit.clank.rt.handlers;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import net.hashsploit.clank.rt.RtMessageHandler;
import net.hashsploit.clank.rt.serializers.RT_ClientAppToServer;
import net.hashsploit.clank.server.MediusClient;
import net.hashsploit.clank.server.RTMessage;
import net.hashsploit.clank.server.RtMessageId;
import net.hashsploit.clank.server.medius.objects.MediusMessage;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.MediusPacketHandler;
import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.clank.utils.Utils;

public class RtMsgClientAppToServer extends RtMessageHandler {

	private RT_ClientAppToServer reqPacket;
	
	public RtMsgClientAppToServer() {
		super(RtMessageId.CLIENT_APP_TOSERVER);
	}

	@Override
	public void read(ByteBuf buffer) {
		reqPacket = new RT_ClientAppToServer(buffer);		
	}
	
	@Override
	public List<RTMessage> write(MediusClient client) {
		
		if (reqPacket.getMediusMessageType() == null) {
			byte[] mediusMessageTypeShort = new byte[2];
			reqPacket.getPayload().getBytes(0, mediusMessageTypeShort);
			logger.severe("Medius type not found: 0x" + Utils.bytesToHex(mediusMessageTypeShort));
			return null;
		}
		
		logger.finest("--------- MEDIUS TYPE: " + reqPacket.getMediusMessageType().name());
		
		List<RTMessage> responses = new ArrayList<RTMessage>();



		MediusPacketHandler mediusPacketTest = client.getMediusMessageMapTest().get(reqPacket.getMediusMessageType());

		if(mediusPacketTest != null) {
			try {
				mediusPacketTest.read(new NetInput(ByteBuffer.wrap(reqPacket.getMediusPayload())), client);

				List<MediusPacket> mediusPackets = new ArrayList<>();
				mediusPacketTest.write(mediusPackets, client);

				for (MediusPacket packet : mediusPackets) {
					logger.finest("--------- MEDIUS MESSAGE: " + packet.toString());

					//System.out.println("_________________________" + Utils.bytesToHex(packet.toBytes()));
					responses.add(new RTMessage(RtMessageId.SERVER_APP, packet.toBytes()));
				}
				return responses;
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}


		// Detect which medius packet is being parsed
		net.hashsploit.clank.server.medius.MediusPacketHandler mediusPacket = client.getMediusMessageMap().get(reqPacket.getMediusMessageType());
		
		if (mediusPacket == null) {
			logger.severe("Unknown medius packet handler found!: [MediusId: " + reqPacket.toString() + "\nRaw bytes: " + Utils.bytesToHex(Utils.nettyByteBufToByteArray(reqPacket.getFullMessage())) + "]");
		}


		// Testing new packet
		//if (mediusPacket.getType() == MediusMessageType.LadderList_ExtraInfo) {
		//	logger.info("LadderList_ExtraInfo called!");
		//}
		
		// Process this medius packet
		mediusPacket.read(client, new MediusMessage(reqPacket.getMediusMessageType(), reqPacket.getMediusPayload()));
		List<MediusMessage> mediusMessages = mediusPacket.write(client);
		
		if (mediusMessages != null) {
			for (MediusMessage mm : mediusMessages) {
				logger.finest("--------- MEDIUS MESSAGE: " + mm.toString() + "\n" + mm.getDebugString());
				responses.add(new RTMessage(RtMessageId.SERVER_APP, mm.toBytes()));
			}
		}
		
		return responses;
	}
	
}
