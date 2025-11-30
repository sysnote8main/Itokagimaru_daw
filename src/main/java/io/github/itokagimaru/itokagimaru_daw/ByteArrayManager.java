package io.github.itokagimaru.itokagimaru_daw;

import java.nio.ByteBuffer;

public class ByteArrayManager {
    public byte[] encode(int[] list) {
        ByteBuffer buffer = ByteBuffer.allocate(list.length * 4);
        for (int i : list) {
            buffer.putInt(i);
        }
        byte[] data = buffer.array();
        return data;
    }

    public int[] decode(byte[] data) {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        int[] list = new int[data.length / 4];
        for (int i = 0; i < list.length; i++) {
            list[i] = buffer.getInt();
        }
        return list;
    }
}
