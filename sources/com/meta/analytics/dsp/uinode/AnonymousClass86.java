package com.meta.analytics.dsp.uinode;

import android.media.AudioManager;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/* renamed from: com.facebook.ads.redexgen.X.86, reason: invalid class name */
/* loaded from: assets/audience_network.dex */
public final class AnonymousClass86 extends QQ {
    public static byte[] A04;
    public static String[] A05 = {"1cdJmqZo5JmjBSz3V6iIBc", "b5mtsG63LfUTmVo", "v7pUKp70YLsspQnihAQa", "8RT4D0PvYsYuyNKYG0J", "LG3ePim6beoWq", "NyBn7Ghd81GFryl", "6ZzG2MB7DBeQL6nfhWDwO", "j693JmATHJpRsHF"};
    public WeakReference<AudioManager.OnAudioFocusChangeListener> A00;
    public final AbstractC1413Pu A01;
    public final AbstractC1380On A02;
    public final O7 A03;

    public static String A02(int i9, int i10, int i11) {
        byte[] copyOfRange = Arrays.copyOfRange(A04, i9, i9 + i10);
        for (int i12 = 0; i12 < copyOfRange.length; i12++) {
            copyOfRange[i12] = (byte) ((copyOfRange[i12] ^ i11) ^ 96);
        }
        return new String(copyOfRange);
    }

    public static void A05() {
        A04 = new byte[]{69, 81, 64, 77, 75};
    }

    static {
        A05();
    }

    public AnonymousClass86(C1636Yn c1636Yn) {
        super(c1636Yn);
        this.A00 = null;
        this.A01 = new AbstractC1413Pu() { // from class: com.facebook.ads.redexgen.X.8M
            public static byte[] A01;

            static {
                A01();
            }

            public static String A00(int i9, int i10, int i11) {
                byte[] copyOfRange = Arrays.copyOfRange(A01, i9, i9 + i10);
                for (int i12 = 0; i12 < copyOfRange.length; i12++) {
                    copyOfRange[i12] = (byte) ((copyOfRange[i12] - i11) - 66);
                }
                return new String(copyOfRange);
            }

            public static void A01() {
                A01 = new byte[]{-26, -6, -23, -18, -12};
            }

            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.meta.analytics.dsp.uinode.AbstractC09988s
            /* renamed from: A02, reason: merged with bridge method [inline-methods] */
            public final void A03(C9H c9h) {
                WeakReference weakReference;
                WeakReference weakReference2;
                AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener;
                AudioManager audioManager = (AudioManager) AnonymousClass86.this.getContext().getApplicationContext().getSystemService(A00(0, 5, 67));
                weakReference = AnonymousClass86.this.A00;
                if (weakReference == null) {
                    onAudioFocusChangeListener = null;
                } else {
                    weakReference2 = AnonymousClass86.this.A00;
                    onAudioFocusChangeListener = (AudioManager.OnAudioFocusChangeListener) weakReference2.get();
                }
                audioManager.abandonAudioFocus(onAudioFocusChangeListener);
            }
        };
        this.A02 = new AbstractC1380On() { // from class: com.facebook.ads.redexgen.X.8L
            public static byte[] A01;
            public static String[] A02 = {"3T8L8cxvDKNdHoEU8ndZ6FAxHol6AQmj", "T9d9jeRvA6TPkjlyE6ANkunwJ", "ZZYtC1qzWr9Z64iPtzSxbZEtAt5IIjlM", "LskUfs4Yi749bFjDIqQWoA2BC0G", "rATJhvtsPzynSvx3", "X5OC7Y9E", "WElhmvPz", "o0UdQ4xy8VrknzG9L7EyZnB"};

            public static String A00(int i9, int i10, int i11) {
                byte[] copyOfRange = Arrays.copyOfRange(A01, i9, i9 + i10);
                for (int i12 = 0; i12 < copyOfRange.length; i12++) {
                    copyOfRange[i12] = (byte) ((copyOfRange[i12] ^ i11) ^ 90);
                }
                return new String(copyOfRange);
            }

            public static void A01() {
                byte[] bArr = {74, 94, 79, 66, 68};
                if (A02[4].length() != 16) {
                    throw new RuntimeException();
                }
                A02[0] = "gxAB8m8awSLzC5VQ45NFPzppUrTsIZFp";
                A01 = bArr;
            }

            static {
                A01();
            }

            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.meta.analytics.dsp.uinode.AbstractC09988s
            /* renamed from: A02, reason: merged with bridge method [inline-methods] */
            public final void A03(AnonymousClass98 anonymousClass98) {
                WeakReference weakReference;
                WeakReference weakReference2;
                AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener;
                AudioManager audioManager = (AudioManager) AnonymousClass86.this.getContext().getApplicationContext().getSystemService(A00(0, 5, 113));
                weakReference = AnonymousClass86.this.A00;
                if (weakReference == null) {
                    onAudioFocusChangeListener = null;
                } else {
                    weakReference2 = AnonymousClass86.this.A00;
                    onAudioFocusChangeListener = (AudioManager.OnAudioFocusChangeListener) weakReference2.get();
                }
                audioManager.abandonAudioFocus(onAudioFocusChangeListener);
                String[] strArr = A02;
                if (strArr[5].length() != strArr[6].length()) {
                    throw new RuntimeException();
                }
                A02[7] = "auSH";
            }
        };
        this.A03 = new C8D(this);
    }

    @Override // com.meta.analytics.dsp.uinode.QQ
    public final void A07() {
        super.A07();
        if (getVideoView() != null) {
            getVideoView().getEventBus().A03(this.A03, this.A01, this.A02);
        }
    }

    @Override // com.meta.analytics.dsp.uinode.QQ
    public final void A08() {
        if (getVideoView() != null) {
            getVideoView().getEventBus().A04(this.A02, this.A01, this.A03);
        }
        super.A08();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        AudioManager audioManager = (AudioManager) getContext().getApplicationContext().getSystemService(A02(0, 5, 68));
        WeakReference<AudioManager.OnAudioFocusChangeListener> weakReference = this.A00;
        audioManager.abandonAudioFocus(weakReference == null ? null : weakReference.get());
        super.onDetachedFromWindow();
        if (A05[4].length() != 13) {
            throw new RuntimeException();
        }
        A05[3] = "22FJBjB3Y4dWSYUEkMs";
    }
}
