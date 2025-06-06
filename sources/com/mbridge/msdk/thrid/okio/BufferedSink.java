package com.mbridge.msdk.thrid.okio;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;

/* loaded from: classes3.dex */
public interface BufferedSink extends Sink, WritableByteChannel {
    Buffer buffer();

    BufferedSink emit() throws IOException;

    BufferedSink emitCompleteSegments() throws IOException;

    @Override // com.mbridge.msdk.thrid.okio.Sink, java.io.Flushable
    void flush() throws IOException;

    OutputStream outputStream();

    BufferedSink write(ByteString byteString) throws IOException;

    BufferedSink write(Source source, long j7) throws IOException;

    BufferedSink write(byte[] bArr) throws IOException;

    BufferedSink write(byte[] bArr, int i9, int i10) throws IOException;

    long writeAll(Source source) throws IOException;

    BufferedSink writeByte(int i9) throws IOException;

    BufferedSink writeDecimalLong(long j7) throws IOException;

    BufferedSink writeHexadecimalUnsignedLong(long j7) throws IOException;

    BufferedSink writeInt(int i9) throws IOException;

    BufferedSink writeIntLe(int i9) throws IOException;

    BufferedSink writeLong(long j7) throws IOException;

    BufferedSink writeLongLe(long j7) throws IOException;

    BufferedSink writeShort(int i9) throws IOException;

    BufferedSink writeShortLe(int i9) throws IOException;

    BufferedSink writeString(String str, int i9, int i10, Charset charset) throws IOException;

    BufferedSink writeString(String str, Charset charset) throws IOException;

    BufferedSink writeUtf8(String str) throws IOException;

    BufferedSink writeUtf8(String str, int i9, int i10) throws IOException;

    BufferedSink writeUtf8CodePoint(int i9) throws IOException;
}
