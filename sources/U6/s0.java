package u6;

import android.util.SparseIntArray;
import com.tools.arruler.photomeasure.camera.ruler.R;

/* loaded from: classes3.dex */
public final class s0 extends m0 {

    /* renamed from: r, reason: collision with root package name */
    public static final SparseIntArray f23410r;

    /* renamed from: q, reason: collision with root package name */
    public long f23411q;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        f23410r = sparseIntArray;
        sparseIntArray.put(R.id.rvUnit, 1);
    }

    @Override // d0.AbstractC2158e
    public final void E() {
        synchronized (this) {
            this.f23411q = 0L;
        }
    }

    @Override // d0.AbstractC2158e
    public final boolean H() {
        synchronized (this) {
            try {
                if (this.f23411q != 0) {
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
            this.f23411q = 1L;
        }
        L();
    }
}
