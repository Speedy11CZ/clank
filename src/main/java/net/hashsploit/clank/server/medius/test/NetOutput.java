package net.hashsploit.clank.server.medius.test;

import com.google.common.primitives.Longs;
import net.hashsploit.clank.server.medius.MediusCallbackStatus;
import net.hashsploit.clank.server.medius.MediusConstants;
import net.hashsploit.clank.server.medius.MediusSerializableObject;
import net.hashsploit.clank.utils.Utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class NetOutput {

    private final ByteArrayOutputStream byteArrayOutputStream;
    private final DataOutputStream dataOutputStream;

    public NetOutput() {
        this.byteArrayOutputStream = new ByteArrayOutputStream();
        this.dataOutputStream = new DataOutputStream(byteArrayOutputStream);
    }

    public NetOutput(byte[] data) {
        this.byteArrayOutputStream = new ByteArrayOutputStream();
        this.byteArrayOutputStream.writeBytes(data);
        this.dataOutputStream = new DataOutputStream(byteArrayOutputStream);
    }

    public ByteArrayOutputStream getOutputStream() {
        return byteArrayOutputStream;
    }

    public byte[] toByteArray() {
        return byteArrayOutputStream.toByteArray();
    }

    public void writeObject(MediusSerializableObject mediusSerializableObject) throws IOException {
        NetOutput netOutput = new NetOutput();
        mediusSerializableObject.serialize(netOutput);
        this.writeBytes(netOutput.toByteArray());
    }

    public void writeBoolean(boolean b) throws IOException {
        this.writeInt(b ? 1 : 0);
    }

    public void writeByte(int b) throws IOException {
        this.dataOutputStream.writeByte(b);
    }

    public void writeShort(int s) throws IOException {
        this.dataOutputStream.write(0xFF & s);
        this.dataOutputStream.write(0xFF & (s >> 8));
    }

    public void writeChar(int c) throws IOException {
        this.writeShort(c);
    }

    public void writeInt(int i) throws IOException {
        this.dataOutputStream.write(0xFF & i);
        this.dataOutputStream.write(0xFF & (i >> 8));
        this.dataOutputStream.write(0xFF & (i >> 16));
        this.dataOutputStream.write(0xFF & (i >> 24));
    }

    public void writeLong(long l) throws IOException {
        byte[] bytes = Longs.toByteArray(Long.reverseBytes(l));
        this.dataOutputStream.write(bytes, 0, bytes.length);
    }

    public void writeFloat(float f) throws IOException {
        this.writeInt(Float.floatToIntBits(f));
    }

    public void writeDouble(double d) throws IOException {
        this.writeLong(Double.doubleToLongBits(d));
    }

    public void writeBytes(byte[] b) throws IOException {
        this.byteArrayOutputStream.write(b);
    }

    public void writeShorts(short[] s) throws IOException {
        this.writeShorts(s, s.length);
    }

    public void writeShorts(short[] s, int length) throws IOException {
        for(int index = 0; index < length; index++) {
            this.writeShort(s[index]);
        }
    }

    public void writeInts(int[] i) throws IOException {
        this.writeInts(i, i.length);
    }

    public void writeInts(int[] i, int length) throws IOException {
        for(int index = 0; index < length; index++) {
            this.writeInt(i[index]);
        }
    }

    public void writeLongs(long[] l) throws IOException {
        this.writeLongs(l, l.length);
    }

    public void writeLongs(long[] l, int length) throws IOException {
        for(int index = 0; index < length; index++) {
            this.writeLong(l[index]);
        }
    }

    public void writeString(String s, int length) throws IOException {
        this.writeBytes(Utils.buildByteArrayFromString(s, length));
    }

    public void writeString(String s, MediusConstants mediusConstant) throws IOException {
        this.writeString(s, mediusConstant.value);
    }

    public void writeHexString(String s) throws IOException {
        this.writeBytes(Utils.hexStringToByteArray(s));
    }

    public void skipBytes(int length) throws IOException {
        this.writeBytes(new byte[length]);
    }
}