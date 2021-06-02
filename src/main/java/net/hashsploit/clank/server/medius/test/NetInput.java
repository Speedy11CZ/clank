package net.hashsploit.clank.server.medius.test;

import net.hashsploit.clank.server.medius.MediusConstants;
import net.hashsploit.clank.server.medius.MediusSerializableObject;
import net.hashsploit.clank.utils.Utils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class NetInput {

    private final ByteBuffer buffer;

    public NetInput(ByteBuffer buffer) {
        this.buffer = buffer;
        this.buffer.order(ByteOrder.LITTLE_ENDIAN);
    }

    public NetInput(byte[] data) {
        this(ByteBuffer.wrap(data));
    }

    public ByteBuffer getByteBuffer() {
        return this.buffer;
    }

    public void readObject(MediusSerializableObject mediusSerializableObject) throws IOException {
        mediusSerializableObject.deserialize(this);
    }

    public boolean readBoolean() throws IOException {
        return this.readHexString(4).equals("01000000");
    }

    public byte readByte() throws IOException {
        return this.buffer.get();
    }

    public int readUnsignedByte() throws IOException {
        return this.buffer.get() & 0xFF;
    }

    public short readShort() throws IOException {
        return this.buffer.getShort();
    }

    public int readUnsignedShort() throws IOException {
        return this.buffer.getShort() & 0xFFFF;
    }

    public char readChar() throws IOException {
        return this.buffer.getChar();
    }

    public int readInt() throws IOException {
        return this.buffer.getInt();
    }

    public long readLong() throws IOException {
        return this.buffer.getLong();
    }

    public float readFloat() throws IOException {
        return this.buffer.getFloat();
    }

    public double readDouble() throws IOException {
        return this.buffer.getDouble();
    }

    public byte[] readBytes(int length) throws IOException {
        if(length < 0) {
            throw new IllegalArgumentException("Array cannot have length less than 0.");
        }

        byte[] b = new byte[length];
        this.buffer.get(b);
        return b;
    }

    public int readBytes(byte[] b) throws IOException {
        return this.readBytes(b, 0, b.length);
    }

    public int readBytes(byte[] b, int offset, int length) throws IOException {
        int readable = this.buffer.remaining();
        if(readable <= 0) {
            return -1;
        }

        if(readable < length) {
            length = readable;
        }

        this.buffer.get(b, offset, length);
        return length;
    }

    public short[] readShorts(int length) throws IOException {
        if(length < 0) {
            throw new IllegalArgumentException("Array cannot have length less than 0.");
        }

        short[] s = new short[length];
        for(int index = 0; index < length; index++) {
            s[index] = this.readShort();
        }

        return s;
    }

    public int readShorts(short[] s) throws IOException {
        return this.readShorts(s, 0, s.length);
    }

    public int readShorts(short[] s, int offset, int length) throws IOException {
        int readable = this.buffer.remaining();
        if(readable <= 0) {
            return -1;
        }

        if(readable < length * 2) {
            length = readable / 2;
        }

        for(int index = offset; index < offset + length; index++) {
            s[index] = this.readShort();
        }

        return length;
    }

    public int[] readInts(int length) throws IOException {
        if(length < 0) {
            throw new IllegalArgumentException("Array cannot have length less than 0.");
        }

        int[] i = new int[length];
        for(int index = 0; index < length; index++) {
            i[index] = this.readInt();
        }

        return i;
    }

    public int readInts(int[] i) throws IOException {
        return this.readInts(i, 0, i.length);
    }

    public int readInts(int[] i, int offset, int length) throws IOException {
        int readable = this.buffer.remaining();
        if(readable <= 0) {
            return -1;
        }

        if(readable < length * 4) {
            length = readable / 4;
        }

        for(int index = offset; index < offset + length; index++) {
            i[index] = this.readInt();
        }

        return length;
    }

    public long[] readLongs(int length) throws IOException {
        if(length < 0) {
            throw new IllegalArgumentException("Array cannot have length less than 0.");
        }

        long[] l = new long[length];
        for(int index = 0; index < length; index++) {
            l[index] = this.readLong();
        }

        return l;
    }

    public int readLongs(long[] l) throws IOException {
        return this.readLongs(l, 0, l.length);
    }

    public int readLongs(long[] l, int offset, int length) throws IOException {
        int readable = this.buffer.remaining();
        if(readable <= 0) {
            return -1;
        }

        if(readable < length * 2) {
            length = readable / 2;
        }

        for(int index = offset; index < offset + length; index++) {
            l[index] = this.readLong();
        }

        return length;
    }

    public String readString(int length) throws IOException {
        return Utils.parseMediusString(this.readBytes(length));
    }

    public String readString(MediusConstants mediusConstant) throws IOException {
        return this.readString(mediusConstant.value);
    }

    public String readHexString(int length) throws IOException {
        return Utils.bytesToHex(this.readBytes(length));
    }

    public void skipBytes(int length) throws IOException {
        this.readBytes(new byte[length]);
    }

    public int available() throws IOException {
        return this.buffer.remaining();
    }
}