package net.hashsploit.clank.server.medius.handlers;

import java.util.HashSet;
import java.util.List;

import net.hashsploit.clank.server.ChatColor;
import net.hashsploit.clank.server.MediusClient;
import net.hashsploit.clank.server.Player;
import net.hashsploit.clank.server.medius.MediusConstants;
import net.hashsploit.clank.server.medius.MediusLobbyServer;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.MediusPacketHandler;
import net.hashsploit.clank.server.medius.objects.MediusChatMessageType;
import net.hashsploit.clank.server.medius.objects.MediusMessage;
import net.hashsploit.clank.server.medius.serializers.ChatFwdMessageResponse;
import net.hashsploit.clank.server.medius.serializers.ChatMessageRequest;
import net.hashsploit.clank.utils.Utils;

public class MediusChatMessageHandler extends MediusPacketHandler {

	private ChatMessageRequest requestPacket;

	public MediusChatMessageHandler() {
		super(MediusMessageType.ChatMessage, MediusMessageType.ChatFwdMessage);
	}

	@Override
	public void read(MediusClient client, MediusMessage mm) {
		requestPacket = new ChatMessageRequest(mm.getPayload());

		logger.finest(requestPacket.getDebugString());
	}

	@Override
	public List<MediusMessage> write(MediusClient client) {

		/*else {
			logger.warning("Unimplemented MediusChatMessageType: " + requestPacket.getDebugString());
		}
		 */

		return null;
	}

}
