package com.mbridge.msdk.playercommon.exoplayer2.text.cea;

import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import androidx.core.view.MotionEventCompat;
import com.applovin.exoplayer2.common.base.Ascii;
import com.applovin.impl.L;
import com.mbridge.msdk.playercommon.exoplayer2.extractor.ts.PsExtractor;
import com.mbridge.msdk.playercommon.exoplayer2.text.Cue;
import com.mbridge.msdk.playercommon.exoplayer2.text.Subtitle;
import com.mbridge.msdk.playercommon.exoplayer2.text.SubtitleDecoderException;
import com.mbridge.msdk.playercommon.exoplayer2.text.SubtitleInputBuffer;
import com.mbridge.msdk.playercommon.exoplayer2.text.SubtitleOutputBuffer;
import com.mbridge.msdk.playercommon.exoplayer2.util.MimeTypes;
import com.mbridge.msdk.playercommon.exoplayer2.util.ParsableByteArray;
import com.vungle.ads.internal.protos.g;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public final class Cea608Decoder extends CeaDecoder {
    private static final int CC_FIELD_FLAG = 1;
    private static final byte CC_IMPLICIT_DATA_HEADER = -4;
    private static final int CC_MODE_PAINT_ON = 3;
    private static final int CC_MODE_POP_ON = 2;
    private static final int CC_MODE_ROLL_UP = 1;
    private static final int CC_MODE_UNKNOWN = 0;
    private static final int CC_TYPE_FLAG = 2;
    private static final int CC_VALID_608_ID = 4;
    private static final int CC_VALID_FLAG = 4;
    private static final byte CTRL_BACKSPACE = 33;
    private static final byte CTRL_CARRIAGE_RETURN = 45;
    private static final byte CTRL_DELETE_TO_END_OF_ROW = 36;
    private static final byte CTRL_END_OF_CAPTION = 47;
    private static final byte CTRL_ERASE_DISPLAYED_MEMORY = 44;
    private static final byte CTRL_ERASE_NON_DISPLAYED_MEMORY = 46;
    private static final byte CTRL_RESUME_CAPTION_LOADING = 32;
    private static final byte CTRL_RESUME_DIRECT_CAPTIONING = 41;
    private static final byte CTRL_ROLL_UP_CAPTIONS_2_ROWS = 37;
    private static final byte CTRL_ROLL_UP_CAPTIONS_3_ROWS = 38;
    private static final byte CTRL_ROLL_UP_CAPTIONS_4_ROWS = 39;
    private static final int DEFAULT_CAPTIONS_ROW_COUNT = 4;
    private static final int NTSC_CC_FIELD_1 = 0;
    private static final int NTSC_CC_FIELD_2 = 1;
    private static final int STYLE_ITALICS = 7;
    private static final int STYLE_UNCHANGED = 8;
    private int captionMode;
    private int captionRowCount;
    private List<Cue> cues;
    private List<Cue> lastCues;
    private final int packetLength;
    private byte repeatableControlCc1;
    private byte repeatableControlCc2;
    private boolean repeatableControlSet;
    private final int selectedField;
    private static final int[] ROW_INDICES = {11, 1, 3, 12, 14, 5, 7, 9};
    private static final int[] COLUMN_INDICES = {0, 4, 8, 12, 16, 20, 24, 28};
    private static final int[] STYLE_COLORS = {-1, -16711936, -16776961, -16711681, -65536, -256, -65281};
    private static final int[] BASIC_CHARACTER_SET = {32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 225, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 233, 93, 237, 243, 250, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 231, 247, 209, 241, 9632};
    private static final int[] SPECIAL_CHARACTER_SET = {174, 176, PsExtractor.PRIVATE_STREAM_1, 191, 8482, 162, 163, 9834, 224, 32, 232, 226, 234, 238, 244, 251};
    private static final int[] SPECIAL_ES_FR_CHARACTER_SET = {193, 201, 211, 218, 220, 252, 8216, 161, 42, 39, 8212, 169, 8480, 8226, 8220, 8221, PsExtractor.AUDIO_STREAM, 194, 199, 200, 202, 203, 235, 206, 207, 239, 212, 217, 249, 219, 171, 187};
    private static final int[] SPECIAL_PT_DE_CHARACTER_SET = {195, 227, 205, 204, 236, 210, 242, 213, 245, 123, 125, 92, 94, 95, 124, 126, 196, 228, 214, 246, g.STALE_CACHED_RESPONSE_VALUE, 165, 164, 9474, 197, 229, 216, 248, 9484, 9488, 9492, 9496};
    private final ParsableByteArray ccData = new ParsableByteArray();
    private final ArrayList<CueBuilder> cueBuilders = new ArrayList<>();
    private CueBuilder currentCueBuilder = new CueBuilder(0, 4);

    /* loaded from: classes3.dex */
    public static class CueBuilder {
        private static final int BASE_ROW = 15;
        private static final int SCREEN_CHARWIDTH = 32;
        private int captionMode;
        private int captionRowCount;
        private int indent;
        private int row;
        private int tabOffset;
        private final List<CueStyle> cueStyles = new ArrayList();
        private final List<SpannableString> rolledUpCaptions = new ArrayList();
        private final StringBuilder captionStringBuilder = new StringBuilder();

        /* loaded from: classes3.dex */
        public static class CueStyle {
            public int start;
            public final int style;
            public final boolean underline;

            public CueStyle(int i9, boolean z8, int i10) {
                this.style = i9;
                this.underline = z8;
                this.start = i10;
            }
        }

        public CueBuilder(int i9, int i10) {
            reset(i9);
            setCaptionRowCount(i10);
        }

        private static void setColorSpan(SpannableStringBuilder spannableStringBuilder, int i9, int i10, int i11) {
            if (i11 == -1) {
                return;
            }
            spannableStringBuilder.setSpan(new ForegroundColorSpan(i11), i9, i10, 33);
        }

        private static void setItalicSpan(SpannableStringBuilder spannableStringBuilder, int i9, int i10) {
            L.o(2, spannableStringBuilder, i9, i10, 33);
        }

        private static void setUnderlineSpan(SpannableStringBuilder spannableStringBuilder, int i9, int i10) {
            spannableStringBuilder.setSpan(new UnderlineSpan(), i9, i10, 33);
        }

        public void append(char c9) {
            this.captionStringBuilder.append(c9);
        }

        public void backspace() {
            int length = this.captionStringBuilder.length();
            if (length > 0) {
                this.captionStringBuilder.delete(length - 1, length);
                for (int size = this.cueStyles.size() - 1; size >= 0; size--) {
                    CueStyle cueStyle = this.cueStyles.get(size);
                    int i9 = cueStyle.start;
                    if (i9 == length) {
                        cueStyle.start = i9 - 1;
                    } else {
                        return;
                    }
                }
            }
        }

        public Cue build() {
            int i9;
            float f9;
            int i10;
            int i11;
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            for (int i12 = 0; i12 < this.rolledUpCaptions.size(); i12++) {
                spannableStringBuilder.append((CharSequence) this.rolledUpCaptions.get(i12));
                spannableStringBuilder.append('\n');
            }
            spannableStringBuilder.append((CharSequence) buildSpannableString());
            if (spannableStringBuilder.length() == 0) {
                return null;
            }
            int i13 = this.indent + this.tabOffset;
            int length = (32 - i13) - spannableStringBuilder.length();
            int i14 = i13 - length;
            if (this.captionMode == 2 && (Math.abs(i14) < 3 || length < 0)) {
                f9 = 0.5f;
                i9 = 1;
            } else if (this.captionMode == 2 && i14 > 0) {
                f9 = (((32 - length) / 32.0f) * 0.8f) + 0.1f;
                i9 = 2;
            } else {
                i9 = 0;
                f9 = ((i13 / 32.0f) * 0.8f) + 0.1f;
            }
            if (this.captionMode != 1 && (i10 = this.row) <= 7) {
                i11 = 0;
            } else {
                i10 = this.row - 17;
                i11 = 2;
            }
            return new Cue(spannableStringBuilder, Layout.Alignment.ALIGN_NORMAL, i10, 1, i11, f9, i9, Float.MIN_VALUE);
        }

        public SpannableString buildSpannableString() {
            int i9;
            boolean z8;
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.captionStringBuilder);
            int length = spannableStringBuilder.length();
            int i10 = -1;
            int i11 = -1;
            int i12 = -1;
            int i13 = -1;
            int i14 = 0;
            int i15 = 0;
            boolean z9 = false;
            while (i14 < this.cueStyles.size()) {
                CueStyle cueStyle = this.cueStyles.get(i14);
                boolean z10 = cueStyle.underline;
                int i16 = cueStyle.style;
                if (i16 != 8) {
                    if (i16 == 7) {
                        z8 = true;
                    } else {
                        z8 = false;
                    }
                    if (i16 != 7) {
                        i13 = Cea608Decoder.STYLE_COLORS[i16];
                    }
                    z9 = z8;
                }
                int i17 = cueStyle.start;
                i14++;
                if (i14 < this.cueStyles.size()) {
                    i9 = this.cueStyles.get(i14).start;
                } else {
                    i9 = length;
                }
                if (i17 != i9) {
                    if (i10 != -1 && !z10) {
                        setUnderlineSpan(spannableStringBuilder, i10, i17);
                        i10 = -1;
                    } else if (i10 == -1 && z10) {
                        i10 = i17;
                    }
                    if (i11 != -1 && !z9) {
                        setItalicSpan(spannableStringBuilder, i11, i17);
                        i11 = -1;
                    } else if (i11 == -1 && z9) {
                        i11 = i17;
                    }
                    if (i13 != i12) {
                        setColorSpan(spannableStringBuilder, i15, i17, i12);
                        i12 = i13;
                        i15 = i17;
                    }
                }
            }
            if (i10 != -1 && i10 != length) {
                setUnderlineSpan(spannableStringBuilder, i10, length);
            }
            if (i11 != -1 && i11 != length) {
                setItalicSpan(spannableStringBuilder, i11, length);
            }
            if (i15 != length) {
                setColorSpan(spannableStringBuilder, i15, length, i12);
            }
            return new SpannableString(spannableStringBuilder);
        }

        public int getRow() {
            return this.row;
        }

        public boolean isEmpty() {
            if (this.cueStyles.isEmpty() && this.rolledUpCaptions.isEmpty() && this.captionStringBuilder.length() == 0) {
                return true;
            }
            return false;
        }

        public void reset(int i9) {
            this.captionMode = i9;
            this.cueStyles.clear();
            this.rolledUpCaptions.clear();
            this.captionStringBuilder.setLength(0);
            this.row = 15;
            this.indent = 0;
            this.tabOffset = 0;
        }

        public void rollUp() {
            this.rolledUpCaptions.add(buildSpannableString());
            this.captionStringBuilder.setLength(0);
            this.cueStyles.clear();
            int min = Math.min(this.captionRowCount, this.row);
            while (this.rolledUpCaptions.size() >= min) {
                this.rolledUpCaptions.remove(0);
            }
        }

        public void setCaptionRowCount(int i9) {
            this.captionRowCount = i9;
        }

        public void setIndent(int i9) {
            this.indent = i9;
        }

        public void setRow(int i9) {
            this.row = i9;
        }

        public void setStyle(int i9, boolean z8) {
            this.cueStyles.add(new CueStyle(i9, z8, this.captionStringBuilder.length()));
        }

        public void setTab(int i9) {
            this.tabOffset = i9;
        }

        public String toString() {
            return this.captionStringBuilder.toString();
        }
    }

    public Cea608Decoder(String str, int i9) {
        int i10;
        if (MimeTypes.APPLICATION_MP4CEA608.equals(str)) {
            i10 = 2;
        } else {
            i10 = 3;
        }
        this.packetLength = i10;
        if (i9 != 3 && i9 != 4) {
            this.selectedField = 1;
        } else {
            this.selectedField = 2;
        }
        setCaptionMode(0);
        resetCueBuilders();
    }

    private static char getChar(byte b) {
        return (char) BASIC_CHARACTER_SET[(b & Ascii.DEL) - 32];
    }

    private List<Cue> getDisplayCues() {
        ArrayList arrayList = new ArrayList();
        for (int i9 = 0; i9 < this.cueBuilders.size(); i9++) {
            Cue build = this.cueBuilders.get(i9).build();
            if (build != null) {
                arrayList.add(build);
            }
        }
        return arrayList;
    }

    private static char getExtendedEsFrChar(byte b) {
        return (char) SPECIAL_ES_FR_CHARACTER_SET[b & Ascii.US];
    }

    private static char getExtendedPtDeChar(byte b) {
        return (char) SPECIAL_PT_DE_CHARACTER_SET[b & Ascii.US];
    }

    private static char getSpecialChar(byte b) {
        return (char) SPECIAL_CHARACTER_SET[b & Ascii.SI];
    }

    private boolean handleCtrl(byte b, byte b8) {
        boolean isRepeatable = isRepeatable(b);
        if (isRepeatable) {
            if (this.repeatableControlSet && this.repeatableControlCc1 == b && this.repeatableControlCc2 == b8) {
                this.repeatableControlSet = false;
                return true;
            }
            this.repeatableControlSet = true;
            this.repeatableControlCc1 = b;
            this.repeatableControlCc2 = b8;
        }
        if (isMidrowCtrlCode(b, b8)) {
            handleMidrowCtrl(b8);
        } else if (isPreambleAddressCode(b, b8)) {
            handlePreambleAddressCode(b, b8);
        } else if (isTabCtrlCode(b, b8)) {
            this.currentCueBuilder.setTab(b8 - 32);
        } else if (isMiscCode(b, b8)) {
            handleMiscCode(b8);
        }
        return isRepeatable;
    }

    private void handleMidrowCtrl(byte b) {
        boolean z8;
        this.currentCueBuilder.append(' ');
        if ((b & 1) == 1) {
            z8 = true;
        } else {
            z8 = false;
        }
        this.currentCueBuilder.setStyle((b >> 1) & 7, z8);
    }

    private void handleMiscCode(byte b) {
        if (b != 32) {
            if (b != 41) {
                switch (b) {
                    case 37:
                        setCaptionMode(1);
                        setCaptionRowCount(2);
                        return;
                    case MotionEventCompat.AXIS_GENERIC_7 /* 38 */:
                        setCaptionMode(1);
                        setCaptionRowCount(3);
                        return;
                    case 39:
                        setCaptionMode(1);
                        setCaptionRowCount(4);
                        return;
                    default:
                        int i9 = this.captionMode;
                        if (i9 == 0) {
                            return;
                        }
                        if (b != 33) {
                            switch (b) {
                                case 44:
                                    this.cues = null;
                                    if (i9 == 1 || i9 == 3) {
                                        resetCueBuilders();
                                        return;
                                    }
                                    return;
                                case 45:
                                    if (i9 == 1 && !this.currentCueBuilder.isEmpty()) {
                                        this.currentCueBuilder.rollUp();
                                        return;
                                    }
                                    return;
                                case MotionEventCompat.AXIS_GENERIC_15 /* 46 */:
                                    resetCueBuilders();
                                    return;
                                case MotionEventCompat.AXIS_GENERIC_16 /* 47 */:
                                    this.cues = getDisplayCues();
                                    resetCueBuilders();
                                    return;
                                default:
                                    return;
                            }
                        }
                        this.currentCueBuilder.backspace();
                        return;
                }
            }
            setCaptionMode(3);
            return;
        }
        setCaptionMode(2);
    }

    private void handlePreambleAddressCode(byte b, byte b8) {
        boolean z8;
        int i9;
        int i10 = ROW_INDICES[b & 7];
        if ((b8 & 32) != 0) {
            i10++;
        }
        if (i10 != this.currentCueBuilder.getRow()) {
            if (this.captionMode != 1 && !this.currentCueBuilder.isEmpty()) {
                CueBuilder cueBuilder = new CueBuilder(this.captionMode, this.captionRowCount);
                this.currentCueBuilder = cueBuilder;
                this.cueBuilders.add(cueBuilder);
            }
            this.currentCueBuilder.setRow(i10);
        }
        boolean z9 = false;
        if ((b8 & Ascii.DLE) == 16) {
            z8 = true;
        } else {
            z8 = false;
        }
        if ((b8 & 1) == 1) {
            z9 = true;
        }
        int i11 = (b8 >> 1) & 7;
        CueBuilder cueBuilder2 = this.currentCueBuilder;
        if (z8) {
            i9 = 8;
        } else {
            i9 = i11;
        }
        cueBuilder2.setStyle(i9, z9);
        if (z8) {
            this.currentCueBuilder.setIndent(COLUMN_INDICES[i11]);
        }
    }

    private static boolean isMidrowCtrlCode(byte b, byte b8) {
        return (b & 247) == 17 && (b8 & 240) == 32;
    }

    private static boolean isMiscCode(byte b, byte b8) {
        return (b & 247) == 20 && (b8 & 240) == 32;
    }

    private static boolean isPreambleAddressCode(byte b, byte b8) {
        return (b & 240) == 16 && (b8 & 192) == 64;
    }

    private static boolean isRepeatable(byte b) {
        return (b & 240) == 16;
    }

    private static boolean isTabCtrlCode(byte b, byte b8) {
        return (b & 247) == 23 && b8 >= 33 && b8 <= 35;
    }

    private void resetCueBuilders() {
        this.currentCueBuilder.reset(this.captionMode);
        this.cueBuilders.clear();
        this.cueBuilders.add(this.currentCueBuilder);
    }

    private void setCaptionMode(int i9) {
        int i10 = this.captionMode;
        if (i10 == i9) {
            return;
        }
        this.captionMode = i9;
        resetCueBuilders();
        if (i10 == 3 || i9 == 1 || i9 == 0) {
            this.cues = null;
        }
    }

    private void setCaptionRowCount(int i9) {
        this.captionRowCount = i9;
        this.currentCueBuilder.setCaptionRowCount(i9);
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.text.cea.CeaDecoder
    public final Subtitle createSubtitle() {
        List<Cue> list = this.cues;
        this.lastCues = list;
        return new CeaSubtitle(list);
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.text.cea.CeaDecoder
    public final void decode(SubtitleInputBuffer subtitleInputBuffer) {
        byte readUnsignedByte;
        int i9;
        this.ccData.reset(subtitleInputBuffer.data.array(), subtitleInputBuffer.data.limit());
        boolean z8 = false;
        boolean z9 = false;
        while (true) {
            int bytesLeft = this.ccData.bytesLeft();
            int i10 = this.packetLength;
            if (bytesLeft < i10) {
                break;
            }
            if (i10 == 2) {
                readUnsignedByte = CC_IMPLICIT_DATA_HEADER;
            } else {
                readUnsignedByte = (byte) this.ccData.readUnsignedByte();
            }
            byte readUnsignedByte2 = (byte) (this.ccData.readUnsignedByte() & 127);
            byte readUnsignedByte3 = (byte) (this.ccData.readUnsignedByte() & 127);
            if ((readUnsignedByte & 6) == 4 && ((i9 = this.selectedField) != 1 || (readUnsignedByte & 1) == 0)) {
                if (i9 != 2 || (readUnsignedByte & 1) == 1) {
                    if (readUnsignedByte2 != 0 || readUnsignedByte3 != 0) {
                        if ((readUnsignedByte2 & 247) == 17 && (readUnsignedByte3 & 240) == 48) {
                            this.currentCueBuilder.append(getSpecialChar(readUnsignedByte3));
                        } else if ((readUnsignedByte2 & 246) == 18 && (readUnsignedByte3 & 224) == 32) {
                            this.currentCueBuilder.backspace();
                            if ((readUnsignedByte2 & 1) == 0) {
                                this.currentCueBuilder.append(getExtendedEsFrChar(readUnsignedByte3));
                            } else {
                                this.currentCueBuilder.append(getExtendedPtDeChar(readUnsignedByte3));
                            }
                        } else if ((readUnsignedByte2 & 224) == 0) {
                            z9 = handleCtrl(readUnsignedByte2, readUnsignedByte3);
                        } else {
                            this.currentCueBuilder.append(getChar(readUnsignedByte2));
                            if ((readUnsignedByte3 & 224) != 0) {
                                this.currentCueBuilder.append(getChar(readUnsignedByte3));
                            }
                        }
                        z8 = true;
                    }
                }
            }
        }
        if (z8) {
            if (!z9) {
                this.repeatableControlSet = false;
            }
            int i11 = this.captionMode;
            if (i11 == 1 || i11 == 3) {
                this.cues = getDisplayCues();
            }
        }
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.text.cea.CeaDecoder, com.mbridge.msdk.playercommon.exoplayer2.decoder.Decoder
    public final /* bridge */ /* synthetic */ SubtitleInputBuffer dequeueInputBuffer() throws SubtitleDecoderException {
        return super.dequeueInputBuffer();
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.text.cea.CeaDecoder, com.mbridge.msdk.playercommon.exoplayer2.decoder.Decoder
    public final /* bridge */ /* synthetic */ SubtitleOutputBuffer dequeueOutputBuffer() throws SubtitleDecoderException {
        return super.dequeueOutputBuffer();
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.text.cea.CeaDecoder, com.mbridge.msdk.playercommon.exoplayer2.decoder.Decoder
    public final void flush() {
        super.flush();
        this.cues = null;
        this.lastCues = null;
        setCaptionMode(0);
        setCaptionRowCount(4);
        resetCueBuilders();
        this.repeatableControlSet = false;
        this.repeatableControlCc1 = (byte) 0;
        this.repeatableControlCc2 = (byte) 0;
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.text.cea.CeaDecoder, com.mbridge.msdk.playercommon.exoplayer2.decoder.Decoder
    public final String getName() {
        return "Cea608Decoder";
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.text.cea.CeaDecoder
    public final boolean isNewSubtitleDataAvailable() {
        if (this.cues != this.lastCues) {
            return true;
        }
        return false;
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.text.cea.CeaDecoder
    public final /* bridge */ /* synthetic */ void queueInputBuffer(SubtitleInputBuffer subtitleInputBuffer) throws SubtitleDecoderException {
        super.queueInputBuffer(subtitleInputBuffer);
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.text.cea.CeaDecoder, com.mbridge.msdk.playercommon.exoplayer2.decoder.Decoder
    public final void release() {
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.text.cea.CeaDecoder, com.mbridge.msdk.playercommon.exoplayer2.text.SubtitleDecoder
    public final /* bridge */ /* synthetic */ void setPositionUs(long j7) {
        super.setPositionUs(j7);
    }
}
