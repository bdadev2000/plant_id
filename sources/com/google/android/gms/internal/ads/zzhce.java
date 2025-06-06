package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Iterator;

/* loaded from: classes2.dex */
final class zzhce extends InputStream {
    private Iterator zza;
    private ByteBuffer zzb;
    private int zzc = 0;
    private int zzd;
    private int zze;
    private boolean zzf;
    private byte[] zzg;
    private int zzh;
    private long zzi;

    public zzhce(Iterable iterable) {
        this.zza = iterable.iterator();
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            this.zzc++;
        }
        this.zzd = -1;
        if (!zzb()) {
            this.zzb = zzhcb.zze;
            this.zzd = 0;
            this.zze = 0;
            this.zzi = 0L;
        }
    }

    private final void zza(int i9) {
        int i10 = this.zze + i9;
        this.zze = i10;
        if (i10 == this.zzb.limit()) {
            zzb();
        }
    }

    private final boolean zzb() {
        this.zzd++;
        if (!this.zza.hasNext()) {
            return false;
        }
        ByteBuffer byteBuffer = (ByteBuffer) this.zza.next();
        this.zzb = byteBuffer;
        this.zze = byteBuffer.position();
        if (this.zzb.hasArray()) {
            this.zzf = true;
            this.zzg = this.zzb.array();
            this.zzh = this.zzb.arrayOffset();
        } else {
            this.zzf = false;
            this.zzi = zzhfa.zze(this.zzb);
            this.zzg = null;
        }
        return true;
    }

    @Override // java.io.InputStream
    public final int read() throws IOException {
        if (this.zzd == this.zzc) {
            return -1;
        }
        if (this.zzf) {
            int i9 = this.zzg[this.zze + this.zzh] & 255;
            zza(1);
            return i9;
        }
        int zza = zzhfa.zza(this.zze + this.zzi) & 255;
        zza(1);
        return zza;
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i9, int i10) throws IOException {
        if (this.zzd == this.zzc) {
            return -1;
        }
        int limit = this.zzb.limit();
        int i11 = this.zze;
        int i12 = limit - i11;
        if (i10 > i12) {
            i10 = i12;
        }
        if (this.zzf) {
            System.arraycopy(this.zzg, i11 + this.zzh, bArr, i9, i10);
            zza(i10);
        } else {
            int position = this.zzb.position();
            this.zzb.position(this.zze);
            this.zzb.get(bArr, i9, i10);
            this.zzb.position(position);
            zza(i10);
        }
        return i10;
    }
}
