package com.google.android.gms.internal.ads;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public final class zzeyh implements zzexh {
    private final Context zza;
    private final ScheduledExecutorService zzb;
    private final Executor zzc;
    private final int zzd;
    private final boolean zze;
    private final boolean zzf;
    private final zzbzu zzg;

    public zzeyh(zzbzu zzbzuVar, Context context, ScheduledExecutorService scheduledExecutorService, Executor executor, int i2, boolean z2, boolean z3) {
        this.zzg = zzbzuVar;
        this.zza = context;
        this.zzb = scheduledExecutorService;
        this.zzc = executor;
        this.zzd = i2;
        this.zze = z2;
        this.zzf = z3;
    }

    @Override // com.google.android.gms.internal.ads.zzexh
    public final int zza() {
        return 40;
    }

    @Override // com.google.android.gms.internal.ads.zzexh
    public final ListenableFuture zzb() {
        if (!((Boolean) com.google.android.gms.ads.internal.client.zzbe.zzc().zza(zzbcv.zzaY)).booleanValue()) {
            return zzgfo.zzg(new Exception("Did not ad Ad ID into query param."));
        }
        return (zzgff) zzgfo.zze((zzgff) zzgfo.zzo((zzgff) zzgfo.zzm(zzgff.zzu(this.zzg.zza(this.zza, this.zzd)), new zzfxq() { // from class: com.google.android.gms.internal.ads.zzeyf
            @Override // com.google.android.gms.internal.ads.zzfxq
            public final Object apply(Object obj) {
                return zzeyh.this.zzc((AdvertisingIdClient.Info) obj);
            }
        }, this.zzc), ((Long) com.google.android.gms.ads.internal.client.zzbe.zzc().zza(zzbcv.zzaZ)).longValue(), TimeUnit.MILLISECONDS, this.zzb), Throwable.class, new zzfxq() { // from class: com.google.android.gms.internal.ads.zzeyg
            @Override // com.google.android.gms.internal.ads.zzfxq
            public final Object apply(Object obj) {
                return zzeyh.this.zzd((Throwable) obj);
            }
        }, this.zzc);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x005e, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0061, code lost:
    
        com.google.android.gms.ads.internal.zzu.zzo().zzw(r0, "AdIdInfoSignalSource.getPaidV1");
        r0 = new com.google.android.gms.internal.ads.zzfur();
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002f, code lost:
    
        if (((java.lang.Boolean) com.google.android.gms.ads.internal.client.zzbe.zzc().zza(com.google.android.gms.internal.ads.zzbcv.zzdd)).booleanValue() != false) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0019, code lost:
    
        if (((java.lang.Boolean) com.google.android.gms.ads.internal.client.zzbe.zzc().zza(com.google.android.gms.internal.ads.zzbcv.zzdc)).booleanValue() == false) goto L6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0031, code lost:
    
        r1 = com.google.android.gms.internal.ads.zzfuu.zzj(r7.zza);
        java.util.Objects.requireNonNull(r8);
        r2 = r8.getId();
        java.util.Objects.requireNonNull(r2);
        r0 = r1.zzi(r2, r7.zza.getPackageName(), ((java.lang.Long) com.google.android.gms.ads.internal.client.zzbe.zzc().zza(com.google.android.gms.internal.ads.zzbcv.zzdi)).longValue(), r7.zzf);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final /* synthetic */ com.google.android.gms.internal.ads.zzeyi zzc(com.google.android.gms.ads.identifier.AdvertisingIdClient.Info r8) {
        /*
            r7 = this;
            com.google.android.gms.internal.ads.zzfur r0 = new com.google.android.gms.internal.ads.zzfur
            r0.<init>()
            boolean r1 = r7.zze
            if (r1 != 0) goto L1b
            com.google.android.gms.internal.ads.zzbcm r1 = com.google.android.gms.internal.ads.zzbcv.zzdc
            com.google.android.gms.internal.ads.zzbct r2 = com.google.android.gms.ads.internal.client.zzbe.zzc()
            java.lang.Object r1 = r2.zza(r1)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 != 0) goto L31
        L1b:
            boolean r1 = r7.zze
            if (r1 == 0) goto L6f
            com.google.android.gms.internal.ads.zzbcm r1 = com.google.android.gms.internal.ads.zzbcv.zzdd
            com.google.android.gms.internal.ads.zzbct r2 = com.google.android.gms.ads.internal.client.zzbe.zzc()
            java.lang.Object r1 = r2.zza(r1)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L6f
        L31:
            android.content.Context r0 = r7.zza     // Catch: java.lang.IllegalArgumentException -> L5e java.io.IOException -> L60
            com.google.android.gms.internal.ads.zzfuu r1 = com.google.android.gms.internal.ads.zzfuu.zzj(r0)     // Catch: java.lang.IllegalArgumentException -> L5e java.io.IOException -> L60
            java.util.Objects.requireNonNull(r8)     // Catch: java.lang.IllegalArgumentException -> L5e java.io.IOException -> L60
            java.lang.String r2 = r8.getId()     // Catch: java.lang.IllegalArgumentException -> L5e java.io.IOException -> L60
            java.util.Objects.requireNonNull(r2)     // Catch: java.lang.IllegalArgumentException -> L5e java.io.IOException -> L60
            android.content.Context r0 = r7.zza     // Catch: java.lang.IllegalArgumentException -> L5e java.io.IOException -> L60
            java.lang.String r3 = r0.getPackageName()     // Catch: java.lang.IllegalArgumentException -> L5e java.io.IOException -> L60
            com.google.android.gms.internal.ads.zzbcm r0 = com.google.android.gms.internal.ads.zzbcv.zzdi     // Catch: java.lang.IllegalArgumentException -> L5e java.io.IOException -> L60
            com.google.android.gms.internal.ads.zzbct r4 = com.google.android.gms.ads.internal.client.zzbe.zzc()     // Catch: java.lang.IllegalArgumentException -> L5e java.io.IOException -> L60
            java.lang.Object r0 = r4.zza(r0)     // Catch: java.lang.IllegalArgumentException -> L5e java.io.IOException -> L60
            java.lang.Long r0 = (java.lang.Long) r0     // Catch: java.lang.IllegalArgumentException -> L5e java.io.IOException -> L60
            long r4 = r0.longValue()     // Catch: java.lang.IllegalArgumentException -> L5e java.io.IOException -> L60
            boolean r6 = r7.zzf     // Catch: java.lang.IllegalArgumentException -> L5e java.io.IOException -> L60
            com.google.android.gms.internal.ads.zzfur r0 = r1.zzi(r2, r3, r4, r6)     // Catch: java.lang.IllegalArgumentException -> L5e java.io.IOException -> L60
            goto L6f
        L5e:
            r0 = move-exception
            goto L61
        L60:
            r0 = move-exception
        L61:
            java.lang.String r1 = "AdIdInfoSignalSource.getPaidV1"
            com.google.android.gms.internal.ads.zzcad r2 = com.google.android.gms.ads.internal.zzu.zzo()
            r2.zzw(r0, r1)
            com.google.android.gms.internal.ads.zzfur r0 = new com.google.android.gms.internal.ads.zzfur
            r0.<init>()
        L6f:
            com.google.android.gms.internal.ads.zzeyi r1 = new com.google.android.gms.internal.ads.zzeyi
            r2 = 0
            r1.<init>(r8, r2, r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzeyh.zzc(com.google.android.gms.ads.identifier.AdvertisingIdClient$Info):com.google.android.gms.internal.ads.zzeyi");
    }

    public final /* synthetic */ zzeyi zzd(Throwable th) {
        com.google.android.gms.ads.internal.client.zzbc.zzb();
        ContentResolver contentResolver = this.zza.getContentResolver();
        return new zzeyi(null, contentResolver == null ? null : Settings.Secure.getString(contentResolver, "android_id"), new zzfur());
    }
}
