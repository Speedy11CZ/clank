package net.hashsploit.clank.server.medius.handlers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import net.hashsploit.clank.server.MediusClient;
import net.hashsploit.clank.server.MediusGame;
import net.hashsploit.clank.server.Player;
import net.hashsploit.clank.server.medius.MediusCallbackStatus;
import net.hashsploit.clank.server.medius.MediusConstants;
import net.hashsploit.clank.server.medius.MediusLobbyServer;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.MediusPacketHandler;
import net.hashsploit.clank.server.medius.objects.MediusMessage;
import net.hashsploit.clank.server.medius.serializers.GetLobbyPlayerNames_ExtraInfoRequest;
import net.hashsploit.clank.server.medius.serializers.GetLobbyPlayerNames_ExtraInfoResponse;
import net.hashsploit.clank.utils.Utils;

public class MediusGetLobbyPlayerNames_ExtraInfoHandler extends MediusPacketHandler {

	private GetLobbyPlayerNames_ExtraInfoRequest reqPacket;
	
	public MediusGetLobbyPlayerNames_ExtraInfoHandler() {
		super(MediusMessageType.GetLobbyPlayerNames_ExtraInfo, MediusMessageType.GetLobbyPlayerNames_ExtraInfoResponse);
	}
	@Override
	public void read(MediusClient client, MediusMessage mm) {
		reqPacket = new GetLobbyPlayerNames_ExtraInfoRequest(mm.getPayload());
		logger.finest(reqPacket.toString());
	}
	
	@Override
	public List<MediusMessage> write(MediusClient client) {
		// RESPONSE

		List<MediusMessage> response = new ArrayList<MediusMessage>();
		
		int worldId = Utils.bytesToIntLittle(reqPacket.getLobbyWorldId());
		
		MediusLobbyServer server = (MediusLobbyServer) client.getServer();
		
		HashSet<Player> playersInWorld = server.getLobbyWorldPlayers(worldId);
		Iterator<Player> iterator = playersInWorld.iterator();
		
		for (int i = 0; i < playersInWorld.size(); i++) {
			Player player = iterator.next();
			
			logger.info("Player in the world: " + player.toString());
			byte[] callbackStatus = Utils.intToBytesLittle(MediusCallbackStatus.SUCCESS.getValue());
			byte[] accountID = Utils.intToBytesLittle(player.getAccountId());
			byte[] accountName = Utils.buildByteArrayFromString(player.getUsername(), MediusConstants.ACCOUNTNAME_MAXLEN.value);
			byte[] playerStatus = Utils.intToBytesLittle(player.getStatus().getValue());
			
			MediusGame game = server.getGameFromPlayer(player);
			byte[] gameWorldID;
			if (game == null) {
				gameWorldID = Utils.intToBytesLittle(0); 
			}
			else {
			    gameWorldID = Utils.intToBytesLittle(game.getWorldId());
			}
			byte[] lobbyName = Utils.buildByteArrayFromString(server.getChannelById(Utils.bytesToIntLittle(reqPacket.getLobbyWorldId())).getName(), MediusConstants.WORLDNAME_MAXLEN.value);
			byte[] gameName = Utils.buildByteArrayFromString("", MediusConstants.WORLDNAME_MAXLEN.value);
			
			byte[] endOfList;
			if (i == playersInWorld.size()-1) {
				endOfList = Utils.hexStringToByteArray("01000000");
			}
			else {
				endOfList = Utils.hexStringToByteArray("00000000");
			}
			GetLobbyPlayerNames_ExtraInfoResponse getLobbyPlayersResponse = new GetLobbyPlayerNames_ExtraInfoResponse(
					reqPacket.getMessageID(), 
					callbackStatus,
					accountID,
					accountName,
					playerStatus,
					reqPacket.getLobbyWorldId(),
					gameWorldID,
					lobbyName,
					gameName,
					endOfList
					);
			response.add(getLobbyPlayersResponse);
		}
		
		if (playersInWorld.size() == 0) {
			logger.info("MediusGetLobbyPlayerNames_ExtraInfoHandler: NO players found!");
			byte[] callbackStatus = Utils.intToBytesLittle(MediusCallbackStatus.NO_RESULT.getValue());
			byte[] accountID = Utils.intToBytesLittle(0);
			byte[] accountName = Utils.buildByteArrayFromString("", MediusConstants.ACCOUNTNAME_MAXLEN.value);
			byte[] playerStatus = Utils.intToBytesLittle(0);
			byte[] gameWorldId = Utils.intToBytesLittle(0);
			byte[] lobbyName = Utils.buildByteArrayFromString(server.getChannelById(Utils.bytesToIntLittle(reqPacket.getLobbyWorldId())).getName(), MediusConstants.WORLDNAME_MAXLEN.value);
			byte[] gameName = Utils.buildByteArrayFromString("", MediusConstants.WORLDNAME_MAXLEN.value);
			byte[] endOfList = Utils.hexStringToByteArray("01000000");
			GetLobbyPlayerNames_ExtraInfoResponse getLobbyPlayersResponse = new GetLobbyPlayerNames_ExtraInfoResponse(
					reqPacket.getMessageID(), 
					callbackStatus,
					accountID,
					accountName,
					playerStatus,
					reqPacket.getLobbyWorldId(),
					gameWorldId,
					lobbyName,
					gameName,
					endOfList
					);
			response.add(getLobbyPlayersResponse);
			
		}
		
		
		
		return response;
	}
	


}
