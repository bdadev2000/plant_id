package com.vungle.ads.internal.downloader;

/* loaded from: classes3.dex */
public final class h {
    public static final e Companion = new e(null);
    private int progressPercent;
    private long sizeBytes;
    private long startBytes;
    private int status;
    private long timestampDownloadStart;

    @g
    public static /* synthetic */ void getStatus$annotations() {
    }

    public final int getProgressPercent() {
        return this.progressPercent;
    }

    public final long getSizeBytes() {
        return this.sizeBytes;
    }

    public final long getStartBytes() {
        return this.startBytes;
    }

    public final int getStatus() {
        return this.status;
    }

    public final long getTimestampDownloadStart() {
        return this.timestampDownloadStart;
    }

    public final void setProgressPercent(int i9) {
        this.progressPercent = i9;
    }

    public final void setSizeBytes(long j7) {
        this.sizeBytes = j7;
    }

    public final void setStartBytes(long j7) {
        this.startBytes = j7;
    }

    public final void setStatus(int i9) {
        this.status = i9;
    }

    public final void setTimestampDownloadStart(long j7) {
        this.timestampDownloadStart = j7;
    }
}
