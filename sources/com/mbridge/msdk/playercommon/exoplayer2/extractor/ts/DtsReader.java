package com.mbridge.msdk.playercommon.exoplayer2.extractor.ts;

import com.mbridge.msdk.playercommon.exoplayer2.Format;
import com.mbridge.msdk.playercommon.exoplayer2.audio.DtsUtil;
import com.mbridge.msdk.playercommon.exoplayer2.extractor.ExtractorOutput;
import com.mbridge.msdk.playercommon.exoplayer2.extractor.TrackOutput;
import com.mbridge.msdk.playercommon.exoplayer2.extractor.ts.TsPayloadReader;
import com.mbridge.msdk.playercommon.exoplayer2.util.ParsableByteArray;

/* loaded from: classes3.dex */
public final class DtsReader implements ElementaryStreamReader {
    private static final int HEADER_SIZE = 18;
    private static final int STATE_FINDING_SYNC = 0;
    private static final int STATE_READING_HEADER = 1;
    private static final int STATE_READING_SAMPLE = 2;
    private int bytesRead;
    private Format format;
    private String formatId;
    private final String language;
    private TrackOutput output;
    private long sampleDurationUs;
    private int sampleSize;
    private int syncBytes;
    private long timeUs;
    private final ParsableByteArray headerScratchBytes = new ParsableByteArray(new byte[18]);
    private int state = 0;

    public DtsReader(String str) {
        this.language = str;
    }

    private boolean continueRead(ParsableByteArray parsableByteArray, byte[] bArr, int i9) {
        int min = Math.min(parsableByteArray.bytesLeft(), i9 - this.bytesRead);
        parsableByteArray.readBytes(bArr, this.bytesRead, min);
        int i10 = this.bytesRead + min;
        this.bytesRead = i10;
        if (i10 == i9) {
            return true;
        }
        return false;
    }

    private void parseHeader() {
        byte[] bArr = this.headerScratchBytes.data;
        if (this.format == null) {
            Format parseDtsFormat = DtsUtil.parseDtsFormat(bArr, this.formatId, this.language, null);
            this.format = parseDtsFormat;
            this.output.format(parseDtsFormat);
        }
        this.sampleSize = DtsUtil.getDtsFrameSize(bArr);
        this.sampleDurationUs = (int) ((DtsUtil.parseDtsAudioSampleCount(bArr) * 1000000) / this.format.sampleRate);
    }

    private boolean skipToNextSync(ParsableByteArray parsableByteArray) {
        while (parsableByteArray.bytesLeft() > 0) {
            int i9 = this.syncBytes << 8;
            this.syncBytes = i9;
            int readUnsignedByte = i9 | parsableByteArray.readUnsignedByte();
            this.syncBytes = readUnsignedByte;
            if (DtsUtil.isSyncWord(readUnsignedByte)) {
                byte[] bArr = this.headerScratchBytes.data;
                int i10 = this.syncBytes;
                bArr[0] = (byte) ((i10 >> 24) & 255);
                bArr[1] = (byte) ((i10 >> 16) & 255);
                bArr[2] = (byte) ((i10 >> 8) & 255);
                bArr[3] = (byte) (i10 & 255);
                this.bytesRead = 4;
                this.syncBytes = 0;
                return true;
            }
        }
        return false;
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.extractor.ts.ElementaryStreamReader
    public final void consume(ParsableByteArray parsableByteArray) {
        while (parsableByteArray.bytesLeft() > 0) {
            int i9 = this.state;
            if (i9 != 0) {
                if (i9 != 1) {
                    if (i9 == 2) {
                        int min = Math.min(parsableByteArray.bytesLeft(), this.sampleSize - this.bytesRead);
                        this.output.sampleData(parsableByteArray, min);
                        int i10 = this.bytesRead + min;
                        this.bytesRead = i10;
                        int i11 = this.sampleSize;
                        if (i10 == i11) {
                            this.output.sampleMetadata(this.timeUs, 1, i11, 0, null);
                            this.timeUs += this.sampleDurationUs;
                            this.state = 0;
                        }
                    }
                } else if (continueRead(parsableByteArray, this.headerScratchBytes.data, 18)) {
                    parseHeader();
                    this.headerScratchBytes.setPosition(0);
                    this.output.sampleData(this.headerScratchBytes, 18);
                    this.state = 2;
                }
            } else if (skipToNextSync(parsableByteArray)) {
                this.state = 1;
            }
        }
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.extractor.ts.ElementaryStreamReader
    public final void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        this.formatId = trackIdGenerator.getFormatId();
        this.output = extractorOutput.track(trackIdGenerator.getTrackId(), 1);
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.extractor.ts.ElementaryStreamReader
    public final void packetFinished() {
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.extractor.ts.ElementaryStreamReader
    public final void packetStarted(long j7, boolean z8) {
        this.timeUs = j7;
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.extractor.ts.ElementaryStreamReader
    public final void seek() {
        this.state = 0;
        this.bytesRead = 0;
        this.syncBytes = 0;
    }
}
