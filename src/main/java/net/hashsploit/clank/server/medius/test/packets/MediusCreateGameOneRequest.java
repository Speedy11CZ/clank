package net.hashsploit.clank.server.medius.test.packets;

import net.hashsploit.clank.server.medius.MediusConstants;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.objects.MediusGameHostType;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.clank.utils.Utils;

import java.io.IOException;

public class MediusCreateGameOneRequest extends MediusPacket {

    private final byte[] messageID = new byte[MediusConstants.MESSAGEID_MAXLEN.value];
    private final byte[] sessionKey = new byte[MediusConstants.SESSIONKEY_MAXLEN.value];
    private final int applicationID;
    private final int minPlayers;
    private final int maxPlayers;
    private final int gameLevel;
    private final String gameName;
    private final String gamePassword;
    private final String spectatorPassword;
    private final int playerSkillLevel;
    private final int rulesSet;
    private final int genericField1;
    private final int genericField2;
    private final int genericField3;
    private final MediusGameHostType gameHostType;
    private int attributes;
    private boolean hasAttributes;

    public MediusCreateGameOneRequest(NetInput netInput) throws IOException {
        super(MediusMessageType.CreateGame1, netInput);
        int length = netInput.available();
        netInput.readBytes(messageID);
        netInput.readBytes(sessionKey);
        netInput.skipBytes(2);
        applicationID = netInput.readInt();
        minPlayers = netInput.readInt();
        maxPlayers = netInput.readInt();
        gameLevel = netInput.readInt();
        gameName = netInput.readString(MediusConstants.GAMENAME_MAXLEN);
        gamePassword = netInput.readString(MediusConstants.GAMEPASSWORD_MAXLEN);
        spectatorPassword = netInput.readString(MediusConstants.GAMEPASSWORD_MAXLEN);
        playerSkillLevel = netInput.readInt();
        rulesSet = netInput.readInt();
        genericField1 = netInput.readInt();
        genericField2 = netInput.readInt();
        genericField3 = netInput.readInt();
        gameHostType = MediusGameHostType.getTypeFromValue(netInput.readInt());
        if(length == 212) {
            attributes = netInput.readInt();
            hasAttributes = true;
        }
    }

    public byte[] getMessageID() {
        return messageID;
    }

    public int getApplicationID() {
        return applicationID;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getGameLevel() {
        return gameLevel;
    }

    public String getGameName() {
        return gameName;
    }

    public String getGamePassword() {
        return gamePassword;
    }

    public String getSpectatorPassword() {
        return spectatorPassword;
    }

    public int getPlayerSkillLevel() {
        return playerSkillLevel;
    }

    public int getRulesSet() {
        return rulesSet;
    }

    public int getGenericField1() {
        return genericField1;
    }

    public int getGenericField2() {
        return genericField2;
    }

    public int getGenericField3() {
        return genericField3;
    }

    public MediusGameHostType getGameHostType() {
        return gameHostType;
    }

    public int getAttributes() {
        return attributes;
    }

    public boolean isHasAttributes() {
        return hasAttributes;
    }

    @Override
    public String toString() {
        return "MediusCreateGameOneRequest{" +
                "messageID=" + Utils.bytesToHex(messageID) +
                ", sessionKey=" + Utils.bytesToHex(sessionKey) +
                ", applicationID=" + applicationID +
                ", minPlayers=" + minPlayers +
                ", maxPlayers=" + maxPlayers +
                ", gameLevel=" + gameLevel +
                ", gameName='" + gameName + '\'' +
                ", gamePassword='" + gamePassword + '\'' +
                ", spectatorPassword='" + spectatorPassword + '\'' +
                ", playerSkillLevel=" + playerSkillLevel +
                ", rulesSet=" + rulesSet +
                ", genericField1=" + genericField1 +
                ", genericField2=" + genericField2 +
                ", genericField3=" + genericField3 +
                ", gameHostType=" + gameHostType +
                ", attributes=" + attributes +
                ", hasAttributes=" + hasAttributes +
                '}';
    }
}
