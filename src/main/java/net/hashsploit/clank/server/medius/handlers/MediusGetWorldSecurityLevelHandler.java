package net.hashsploit.clank.server.medius.handlers;

import java.util.ArrayList;
import java.util.List;

import net.hashsploit.clank.server.MediusClient;
import net.hashsploit.clank.server.medius.MediusCallbackStatus;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.MediusPacketHandler;
import net.hashsploit.clank.server.medius.objects.MediusApplicationType;
import net.hashsploit.clank.server.medius.objects.MediusMessage;
import net.hashsploit.clank.server.medius.objects.MediusWorldSecurityLevelType;
import net.hashsploit.clank.server.medius.serializers.GetWorldSecurityLevelRequest;
import net.hashsploit.clank.server.medius.serializers.GetWorldSecurityLevelResponse;
import net.hashsploit.clank.utils.Utils;

public class MediusGetWorldSecurityLevelHandler extends MediusPacketHandler {

	private GetWorldSecurityLevelRequest reqPacket;
	private GetWorldSecurityLevelResponse respPacket;
	
	public MediusGetWorldSecurityLevelHandler() {
		super(MediusMessageType.GetWorldSecurityLevel, MediusMessageType.GetWorldSecurityLevelResponse);
	}
	
	@Override
	public void read(MediusClient client, MediusMessage mm) {
		reqPacket = new GetWorldSecurityLevelRequest(mm.getPayload());
		logger.finest(reqPacket.toString());
	}
	
	@Override
	public List<MediusMessage> write(MediusClient client) {
		
		byte[] callbackStatus = Utils.intToBytesLittle((MediusCallbackStatus.SUCCESS.getValue()));
		byte[] appType = Utils.intToBytesLittle(MediusApplicationType.MEDIUS_APP_TYPE_GAME.value);
		byte[] worldSecurityLevelType = Utils.intToBytesLittle(MediusWorldSecurityLevelType.WORLD_SECURITY_NONE.value); // TODO: Get this from the actual world
		
		respPacket = new GetWorldSecurityLevelResponse(reqPacket.getMessageID(), callbackStatus, reqPacket.getWorldId(), appType, worldSecurityLevelType);
		List<MediusMessage> response = new ArrayList<MediusMessage>();
		response.add(respPacket);
		return response;
	}
}
