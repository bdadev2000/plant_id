package com.mbridge.msdk.playercommon.exoplayer2.upstream.cache;

import com.mbridge.msdk.playercommon.exoplayer2.upstream.DataSink;
import com.mbridge.msdk.playercommon.exoplayer2.upstream.DataSpec;
import com.mbridge.msdk.playercommon.exoplayer2.upstream.cache.Cache;
import com.mbridge.msdk.playercommon.exoplayer2.util.Assertions;
import com.mbridge.msdk.playercommon.exoplayer2.util.ReusableBufferedOutputStream;
import com.mbridge.msdk.playercommon.exoplayer2.util.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes3.dex */
public final class CacheDataSink implements DataSink {
    public static final int DEFAULT_BUFFER_SIZE = 20480;
    private final int bufferSize;
    private ReusableBufferedOutputStream bufferedOutputStream;
    private final Cache cache;
    private DataSpec dataSpec;
    private long dataSpecBytesWritten;
    private File file;
    private final long maxCacheFileSize;
    private OutputStream outputStream;
    private long outputStreamBytesWritten;
    private final boolean syncFileDescriptor;
    private FileOutputStream underlyingFileOutputStream;

    /* loaded from: classes3.dex */
    public static class CacheDataSinkException extends Cache.CacheException {
        public CacheDataSinkException(IOException iOException) {
            super(iOException);
        }
    }

    public CacheDataSink(Cache cache, long j7) {
        this(cache, j7, DEFAULT_BUFFER_SIZE, true);
    }

    private void closeCurrentOutputStream() throws IOException {
        OutputStream outputStream = this.outputStream;
        if (outputStream == null) {
            return;
        }
        try {
            outputStream.flush();
            if (this.syncFileDescriptor) {
                this.underlyingFileOutputStream.getFD().sync();
            }
            Util.closeQuietly(this.outputStream);
            this.outputStream = null;
            File file = this.file;
            this.file = null;
            this.cache.commitFile(file);
        } catch (Throwable th) {
            Util.closeQuietly(this.outputStream);
            this.outputStream = null;
            File file2 = this.file;
            this.file = null;
            file2.delete();
            throw th;
        }
    }

    private void openNextOutputStream() throws IOException {
        long min;
        long j7 = this.dataSpec.length;
        if (j7 == -1) {
            min = this.maxCacheFileSize;
        } else {
            min = Math.min(j7 - this.dataSpecBytesWritten, this.maxCacheFileSize);
        }
        long j9 = min;
        Cache cache = this.cache;
        DataSpec dataSpec = this.dataSpec;
        this.file = cache.startFile(dataSpec.key, this.dataSpecBytesWritten + dataSpec.absoluteStreamPosition, j9);
        FileOutputStream fileOutputStream = new FileOutputStream(this.file);
        this.underlyingFileOutputStream = fileOutputStream;
        if (this.bufferSize > 0) {
            ReusableBufferedOutputStream reusableBufferedOutputStream = this.bufferedOutputStream;
            if (reusableBufferedOutputStream == null) {
                this.bufferedOutputStream = new ReusableBufferedOutputStream(this.underlyingFileOutputStream, this.bufferSize);
            } else {
                reusableBufferedOutputStream.reset(fileOutputStream);
            }
            this.outputStream = this.bufferedOutputStream;
        } else {
            this.outputStream = fileOutputStream;
        }
        this.outputStreamBytesWritten = 0L;
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.upstream.DataSink
    public final void close() throws CacheDataSinkException {
        if (this.dataSpec == null) {
            return;
        }
        try {
            closeCurrentOutputStream();
        } catch (IOException e4) {
            throw new CacheDataSinkException(e4);
        }
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.upstream.DataSink
    public final void open(DataSpec dataSpec) throws CacheDataSinkException {
        if (dataSpec.length == -1 && !dataSpec.isFlagSet(2)) {
            this.dataSpec = null;
            return;
        }
        this.dataSpec = dataSpec;
        this.dataSpecBytesWritten = 0L;
        try {
            openNextOutputStream();
        } catch (IOException e4) {
            throw new CacheDataSinkException(e4);
        }
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.upstream.DataSink
    public final void write(byte[] bArr, int i9, int i10) throws CacheDataSinkException {
        if (this.dataSpec == null) {
            return;
        }
        int i11 = 0;
        while (i11 < i10) {
            try {
                if (this.outputStreamBytesWritten == this.maxCacheFileSize) {
                    closeCurrentOutputStream();
                    openNextOutputStream();
                }
                int min = (int) Math.min(i10 - i11, this.maxCacheFileSize - this.outputStreamBytesWritten);
                this.outputStream.write(bArr, i9 + i11, min);
                i11 += min;
                long j7 = min;
                this.outputStreamBytesWritten += j7;
                this.dataSpecBytesWritten += j7;
            } catch (IOException e4) {
                throw new CacheDataSinkException(e4);
            }
        }
    }

    public CacheDataSink(Cache cache, long j7, boolean z8) {
        this(cache, j7, DEFAULT_BUFFER_SIZE, z8);
    }

    public CacheDataSink(Cache cache, long j7, int i9) {
        this(cache, j7, i9, true);
    }

    public CacheDataSink(Cache cache, long j7, int i9, boolean z8) {
        this.cache = (Cache) Assertions.checkNotNull(cache);
        this.maxCacheFileSize = j7;
        this.bufferSize = i9;
        this.syncFileDescriptor = z8;
    }
}
