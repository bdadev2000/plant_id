package u6;

import android.util.SparseIntArray;
import com.tools.arruler.photomeasure.camera.ruler.R;

/* loaded from: classes3.dex */
public final class B extends AbstractC2784A {

    /* renamed from: t, reason: collision with root package name */
    public static final SparseIntArray f23189t;

    /* renamed from: s, reason: collision with root package name */
    public long f23190s;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        f23189t = sparseIntArray;
        sparseIntArray.put(R.id.videoView, 1);
        sparseIntArray.put(R.id.imgClose, 2);
    }

    @Override // d0.AbstractC2158e
    public final void E() {
        synchronized (this) {
            this.f23190s = 0L;
        }
    }

    @Override // d0.AbstractC2158e
    public final boolean H() {
        synchronized (this) {
            try {
                if (this.f23190s != 0) {
                    return true;
                }
                return false;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // d0.AbstractC2158e
    public final void I() {
        synchronized (this) {
            this.f23190s = 1L;
        }
        L();
    }
}
