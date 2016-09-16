package com.nianticlabs.nia.network;

import java.nio.ByteBuffer;

public class NiaNet {
    private static final int CHUNK_SIZE = 32768;
    static ThreadLocal<ByteBuffer> readBuffer;
    private static final ThreadLocal<byte[]> threadChunk;

    /* renamed from: com.nianticlabs.nia.network.NiaNet.1 */
    static class C07721 extends ThreadLocal<byte[]> {
        C07721() {
        }

        protected byte[] initialValue() {
            return new byte[NiaNet.CHUNK_SIZE];
        }
    }

    /* renamed from: com.nianticlabs.nia.network.NiaNet.2 */
    static class C07732 extends ThreadLocal<ByteBuffer> {
        C07732() {
        }

        protected ByteBuffer initialValue() {
            return ByteBuffer.allocateDirect(NiaNet.CHUNK_SIZE);
        }
    }

    static {
        threadChunk = new C07721();
        readBuffer = new C07732();
    }

    private NiaNet() {
    }
}
