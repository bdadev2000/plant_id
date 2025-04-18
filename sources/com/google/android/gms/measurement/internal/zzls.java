package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.Clock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.checkerframework.dataflow.qual.Pure;

/* loaded from: classes4.dex */
public final class zzls extends zzh {
    private final zzmq zza;
    private zzgb zzb;
    private volatile Boolean zzc;
    private final zzav zzd;
    private final zznl zze;
    private final List<Runnable> zzf;
    private final zzav zzg;

    public zzls(zzhy zzhyVar) {
        super(zzhyVar);
        this.zzf = new ArrayList();
        this.zze = new zznl(zzhyVar.zzb());
        this.zza = new zzmq(this);
        this.zzd = new zzlt(this, zzhyVar);
        this.zzg = new zzmg(this, zzhyVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zzaq() {
        zzt();
        zzj().zzp().zza("Processing queued up service tasks", Integer.valueOf(this.zzf.size()));
        Iterator<Runnable> it = this.zzf.iterator();
        while (it.hasNext()) {
            try {
                it.next().run();
            } catch (RuntimeException e) {
                zzj().zzg().zza("Task exception while flushing queue", e);
            }
        }
        this.zzf.clear();
        this.zzg.zza();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zzar() {
        zzt();
        this.zze.zzb();
        this.zzd.zza(zzbh.zzal.zza(null).longValue());
    }

    @WorkerThread
    public final zzaj zzaa() {
        zzt();
        zzu();
        zzgb zzgbVar = this.zzb;
        if (zzgbVar == null) {
            zzae();
            zzj().zzc().zza("Failed to get consents; not connected to service yet.");
            return null;
        }
        zzo zzc = zzc(false);
        Preconditions.checkNotNull(zzc);
        try {
            zzaj zza = zzgbVar.zza(zzc);
            zzar();
            return zza;
        } catch (RemoteException e) {
            zzj().zzg().zza("Failed to get consents; remote exception", e);
            return null;
        }
    }

    public final Boolean zzab() {
        return this.zzc;
    }

    @WorkerThread
    public final void zzac() {
        zzt();
        zzu();
        zza(new zzme(this, zzc(true)));
    }

    @WorkerThread
    public final void zzad() {
        zzt();
        zzu();
        zzo zzc = zzc(true);
        zzh().zzab();
        zza(new zzmb(this, zzc));
    }

    @WorkerThread
    public final void zzae() {
        zzt();
        zzu();
        if (zzal()) {
            return;
        }
        if (zzap()) {
            this.zza.zza();
            return;
        }
        if (zze().zzy()) {
            return;
        }
        List<ResolveInfo> queryIntentServices = zza().getPackageManager().queryIntentServices(new Intent().setClassName(zza(), "com.google.android.gms.measurement.AppMeasurementService"), 65536);
        if (queryIntentServices == null || queryIntentServices.isEmpty()) {
            zzj().zzg().zza("Unable to use remote or local measurement implementation. Please register the AppMeasurementService service in the app manifest");
            return;
        }
        Intent intent = new Intent("com.google.android.gms.measurement.START");
        intent.setComponent(new ComponentName(zza(), "com.google.android.gms.measurement.AppMeasurementService"));
        this.zza.zza(intent);
    }

    @WorkerThread
    public final void zzaf() {
        zzt();
        zzu();
        this.zza.zzb();
        try {
            ConnectionTracker.getInstance().unbindService(zza(), this.zza);
        } catch (IllegalArgumentException | IllegalStateException unused) {
        }
        this.zzb = null;
    }

    public final /* synthetic */ void zzag() {
        zzgb zzgbVar = this.zzb;
        if (zzgbVar == null) {
            zzj().zzg().zza("Failed to send Dma consent settings to service");
            return;
        }
        try {
            zzo zzc = zzc(false);
            Preconditions.checkNotNull(zzc);
            zzgbVar.zzg(zzc);
            zzar();
        } catch (RemoteException e) {
            zzj().zzg().zza("Failed to send Dma consent settings to the service", e);
        }
    }

    public final /* synthetic */ void zzah() {
        zzgb zzgbVar = this.zzb;
        if (zzgbVar == null) {
            zzj().zzg().zza("Failed to send storage consent settings to service");
            return;
        }
        try {
            zzo zzc = zzc(false);
            Preconditions.checkNotNull(zzc);
            zzgbVar.zzi(zzc);
            zzar();
        } catch (RemoteException e) {
            zzj().zzg().zza("Failed to send storage consent settings to the service", e);
        }
    }

    @WorkerThread
    public final void zzai() {
        zzt();
        zzu();
        zzo zzc = zzc(false);
        zzh().zzaa();
        zza(new zzma(this, zzc));
    }

    @WorkerThread
    public final void zzaj() {
        zzt();
        zzu();
        zza(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzlu
            @Override // java.lang.Runnable
            public final void run() {
                zzls.this.zzag();
            }
        });
    }

    @WorkerThread
    public final void zzak() {
        zzt();
        zzu();
        zza(new zzmh(this, zzc(true)));
    }

    @WorkerThread
    public final boolean zzal() {
        zzt();
        zzu();
        return this.zzb != null;
    }

    @WorkerThread
    public final boolean zzam() {
        zzt();
        zzu();
        return !zzap() || zzq().zzg() >= 200900;
    }

    @WorkerThread
    public final boolean zzan() {
        zzt();
        zzu();
        return !zzap() || zzq().zzg() >= zzbh.zzbt.zza(null).intValue();
    }

    @WorkerThread
    public final boolean zzao() {
        zzt();
        zzu();
        return !zzap() || zzq().zzg() >= 241200;
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x00b5, code lost:
    
        if (r0 == null) goto L36;
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00f1  */
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean zzap() {
        /*
            r6 = this;
            r6.zzt()
            r6.zzu()
            java.lang.Boolean r0 = r6.zzc
            if (r0 != 0) goto Lfe
            r6.zzt()
            r6.zzu()
            com.google.android.gms.measurement.internal.zzha r0 = r6.zzk()
            java.lang.Boolean r0 = r0.zzp()
            r1 = 1
            if (r0 == 0) goto L23
            boolean r2 = r0.booleanValue()
            if (r2 == 0) goto L23
            goto Lf8
        L23:
            com.google.android.gms.measurement.internal.zzgg r2 = r6.zzg()
            int r2 = r2.zzaa()
            if (r2 != r1) goto L30
        L2d:
            r3 = r1
            goto Ld5
        L30:
            com.google.android.gms.measurement.internal.zzgo r2 = r6.zzj()
            com.google.android.gms.measurement.internal.zzgq r2 = r2.zzp()
            java.lang.String r3 = "Checking service availability"
            r2.zza(r3)
            com.google.android.gms.measurement.internal.zzos r2 = r6.zzq()
            r3 = 12451000(0xbdfcb8, float:1.7447567E-38)
            int r2 = r2.zza(r3)
            if (r2 == 0) goto Lc6
            r3 = 0
            if (r2 == r1) goto Lb8
            r4 = 2
            if (r2 == r4) goto L98
            r0 = 3
            if (r2 == r0) goto L8a
            r0 = 9
            if (r2 == r0) goto L7c
            r0 = 18
            if (r2 == r0) goto L6e
            com.google.android.gms.measurement.internal.zzgo r0 = r6.zzj()
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzu()
            java.lang.String r1 = "Unexpected service status"
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.zza(r1, r2)
        L6c:
            r1 = r3
            goto Ld5
        L6e:
            com.google.android.gms.measurement.internal.zzgo r0 = r6.zzj()
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzu()
            java.lang.String r2 = "Service updating"
            r0.zza(r2)
            goto L2d
        L7c:
            com.google.android.gms.measurement.internal.zzgo r0 = r6.zzj()
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzu()
            java.lang.String r1 = "Service invalid"
            r0.zza(r1)
            goto L6c
        L8a:
            com.google.android.gms.measurement.internal.zzgo r0 = r6.zzj()
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzu()
            java.lang.String r1 = "Service disabled"
            r0.zza(r1)
            goto L6c
        L98:
            com.google.android.gms.measurement.internal.zzgo r2 = r6.zzj()
            com.google.android.gms.measurement.internal.zzgq r2 = r2.zzc()
            java.lang.String r4 = "Service container out of date"
            r2.zza(r4)
            com.google.android.gms.measurement.internal.zzos r2 = r6.zzq()
            int r2 = r2.zzg()
            r4 = 17443(0x4423, float:2.4443E-41)
            if (r2 >= r4) goto Lb5
        Lb1:
            r5 = r3
            r3 = r1
            r1 = r5
            goto Ld5
        Lb5:
            if (r0 != 0) goto L6c
            goto Ld5
        Lb8:
            com.google.android.gms.measurement.internal.zzgo r0 = r6.zzj()
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzp()
            java.lang.String r2 = "Service missing"
            r0.zza(r2)
            goto Lb1
        Lc6:
            com.google.android.gms.measurement.internal.zzgo r0 = r6.zzj()
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzp()
            java.lang.String r2 = "Service available"
            r0.zza(r2)
            goto L2d
        Ld5:
            if (r1 != 0) goto Lef
            com.google.android.gms.measurement.internal.zzag r0 = r6.zze()
            boolean r0 = r0.zzy()
            if (r0 == 0) goto Lef
            com.google.android.gms.measurement.internal.zzgo r0 = r6.zzj()
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzg()
            java.lang.String r2 = "No way to upload. Consider using the full version of Analytics"
            r0.zza(r2)
            goto Lf8
        Lef:
            if (r3 == 0) goto Lf8
            com.google.android.gms.measurement.internal.zzha r0 = r6.zzk()
            r0.zza(r1)
        Lf8:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
            r6.zzc = r0
        Lfe:
            java.lang.Boolean r0 = r6.zzc
            boolean r0 = r0.booleanValue()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzls.zzap():boolean");
    }

    @Override // com.google.android.gms.measurement.internal.zzja, com.google.android.gms.measurement.internal.zzjc
    @Pure
    public final /* bridge */ /* synthetic */ zzab zzd() {
        return super.zzd();
    }

    @Override // com.google.android.gms.measurement.internal.zzja
    @Pure
    public final /* bridge */ /* synthetic */ zzaz zzf() {
        return super.zzf();
    }

    @Override // com.google.android.gms.measurement.internal.zze
    public final /* bridge */ /* synthetic */ zzgg zzg() {
        return super.zzg();
    }

    @Override // com.google.android.gms.measurement.internal.zze
    public final /* bridge */ /* synthetic */ zzgf zzh() {
        return super.zzh();
    }

    @Override // com.google.android.gms.measurement.internal.zzja
    @Pure
    public final /* bridge */ /* synthetic */ zzgh zzi() {
        return super.zzi();
    }

    @Override // com.google.android.gms.measurement.internal.zzja, com.google.android.gms.measurement.internal.zzjc
    @Pure
    public final /* bridge */ /* synthetic */ zzgo zzj() {
        return super.zzj();
    }

    @Override // com.google.android.gms.measurement.internal.zzja
    @Pure
    public final /* bridge */ /* synthetic */ zzha zzk() {
        return super.zzk();
    }

    @Override // com.google.android.gms.measurement.internal.zzja, com.google.android.gms.measurement.internal.zzjc
    @Pure
    public final /* bridge */ /* synthetic */ zzhv zzl() {
        return super.zzl();
    }

    @Override // com.google.android.gms.measurement.internal.zze
    public final /* bridge */ /* synthetic */ zzjq zzm() {
        return super.zzm();
    }

    @Override // com.google.android.gms.measurement.internal.zze
    public final /* bridge */ /* synthetic */ zzlj zzn() {
        return super.zzn();
    }

    @Override // com.google.android.gms.measurement.internal.zze
    public final /* bridge */ /* synthetic */ zzls zzo() {
        return super.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zze
    public final /* bridge */ /* synthetic */ zznb zzp() {
        return super.zzp();
    }

    @Override // com.google.android.gms.measurement.internal.zzja
    @Pure
    public final /* bridge */ /* synthetic */ zzos zzq() {
        return super.zzq();
    }

    @Override // com.google.android.gms.measurement.internal.zze, com.google.android.gms.measurement.internal.zzja
    public final /* bridge */ /* synthetic */ void zzr() {
        super.zzr();
    }

    @Override // com.google.android.gms.measurement.internal.zze, com.google.android.gms.measurement.internal.zzja
    public final /* bridge */ /* synthetic */ void zzs() {
        super.zzs();
    }

    @Override // com.google.android.gms.measurement.internal.zze, com.google.android.gms.measurement.internal.zzja
    public final /* bridge */ /* synthetic */ void zzt() {
        super.zzt();
    }

    @Override // com.google.android.gms.measurement.internal.zzh
    public final boolean zzz() {
        return false;
    }

    public static /* synthetic */ void zzd(zzls zzlsVar) {
        zzlsVar.zzt();
        if (zzlsVar.zzal()) {
            zzlsVar.zzj().zzp().zza("Inactivity, disconnecting from the service");
            zzlsVar.zzaf();
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzja, com.google.android.gms.measurement.internal.zzjc
    @Pure
    public final /* bridge */ /* synthetic */ Clock zzb() {
        return super.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zze
    public final /* bridge */ /* synthetic */ zzb zzc() {
        return super.zzc();
    }

    @Override // com.google.android.gms.measurement.internal.zzja
    @Pure
    public final /* bridge */ /* synthetic */ zzag zze() {
        return super.zze();
    }

    @WorkerThread
    private final zzo zzc(boolean z2) {
        return zzg().zza(z2 ? zzj().zzx() : null);
    }

    @Override // com.google.android.gms.measurement.internal.zzja, com.google.android.gms.measurement.internal.zzjc
    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    @WorkerThread
    public final void zzb(boolean z2) {
        zzt();
        zzu();
        if ((!com.google.android.gms.internal.measurement.zznm.zza() || !zze().zza(zzbh.zzcx)) && z2) {
            zzh().zzaa();
        }
        zza(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzlr
            @Override // java.lang.Runnable
            public final void run() {
                zzls.this.zzah();
            }
        });
    }

    public static /* synthetic */ void zza(zzls zzlsVar, ComponentName componentName) {
        zzlsVar.zzt();
        if (zzlsVar.zzb != null) {
            zzlsVar.zzb = null;
            zzlsVar.zzj().zzp().zza("Disconnected from device MeasurementService", componentName);
            zzlsVar.zzt();
            zzlsVar.zzae();
        }
    }

    @WorkerThread
    public final void zza(com.google.android.gms.internal.measurement.zzdo zzdoVar) {
        zzt();
        zzu();
        zza(new zzmc(this, zzc(false), zzdoVar));
    }

    @WorkerThread
    public final void zza(AtomicReference<String> atomicReference) {
        zzt();
        zzu();
        zza(new zzlz(this, atomicReference, zzc(false)));
    }

    @WorkerThread
    public final void zza(com.google.android.gms.internal.measurement.zzdo zzdoVar, String str, String str2) {
        zzt();
        zzu();
        zza(new zzmo(this, str, str2, zzc(false), zzdoVar));
    }

    @WorkerThread
    public final void zza(AtomicReference<List<zzae>> atomicReference, String str, String str2, String str3) {
        zzt();
        zzu();
        zza(new zzml(this, atomicReference, str, str2, str3, zzc(false)));
    }

    @WorkerThread
    public final void zza(AtomicReference<List<zzno>> atomicReference, Bundle bundle) {
        zzt();
        zzu();
        zza(new zzly(this, atomicReference, zzc(false), bundle));
    }

    @WorkerThread
    public final void zza(AtomicReference<List<zzon>> atomicReference, boolean z2) {
        zzt();
        zzu();
        zza(new zzlv(this, atomicReference, zzc(false), z2));
    }

    @WorkerThread
    public final void zza(com.google.android.gms.internal.measurement.zzdo zzdoVar, String str, String str2, boolean z2) {
        zzt();
        zzu();
        zza(new zzlw(this, str, str2, zzc(false), z2, zzdoVar));
    }

    @WorkerThread
    public final void zza(AtomicReference<List<zzon>> atomicReference, String str, String str2, String str3, boolean z2) {
        zzt();
        zzu();
        zza(new zzmn(this, atomicReference, str, str2, str3, zzc(false), z2));
    }

    @WorkerThread
    public final void zza(zzbf zzbfVar, String str) {
        Preconditions.checkNotNull(zzbfVar);
        zzt();
        zzu();
        zza(new zzmj(this, true, zzc(true), zzh().zza(zzbfVar), zzbfVar, str));
    }

    @WorkerThread
    public final void zza(com.google.android.gms.internal.measurement.zzdo zzdoVar, zzbf zzbfVar, String str) {
        zzt();
        zzu();
        if (zzq().zza(GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE) != 0) {
            zzj().zzu().zza("Not bundling data. Service unavailable or out of date");
            zzq().zza(zzdoVar, new byte[0]);
        } else {
            zza(new zzmi(this, zzbfVar, str, zzdoVar));
        }
    }

    @WorkerThread
    private final void zza(Runnable runnable) throws IllegalStateException {
        zzt();
        if (zzal()) {
            runnable.run();
        } else {
            if (this.zzf.size() >= 1000) {
                zzj().zzg().zza("Discarding data. Max runnable queue size reached");
                return;
            }
            this.zzf.add(runnable);
            this.zzg.zza(60000L);
            zzae();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0139 A[ADDED_TO_REGION, SYNTHETIC] */
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zza(com.google.android.gms.measurement.internal.zzgb r37, com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable r38, com.google.android.gms.measurement.internal.zzo r39) {
        /*
            Method dump skipped, instructions count: 326
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzls.zza(com.google.android.gms.measurement.internal.zzgb, com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable, com.google.android.gms.measurement.internal.zzo):void");
    }

    @WorkerThread
    public final void zza(zzae zzaeVar) {
        Preconditions.checkNotNull(zzaeVar);
        zzt();
        zzu();
        zza(new zzmm(this, true, zzc(true), zzh().zza(zzaeVar), new zzae(zzaeVar), zzaeVar));
    }

    @WorkerThread
    public final void zza(boolean z2) {
        zzt();
        zzu();
        if ((!com.google.android.gms.internal.measurement.zznm.zza() || !zze().zza(zzbh.zzcx)) && z2) {
            zzh().zzaa();
        }
        if (zzan()) {
            zza(new zzmk(this, zzc(false)));
        }
    }

    @WorkerThread
    public final void zza(zzlk zzlkVar) {
        zzt();
        zzu();
        zza(new zzmd(this, zzlkVar));
    }

    @WorkerThread
    public final void zza(Bundle bundle) {
        zzt();
        zzu();
        zza(new zzmf(this, zzc(false), bundle));
    }

    @WorkerThread
    public final void zza(zzgb zzgbVar) {
        zzt();
        Preconditions.checkNotNull(zzgbVar);
        this.zzb = zzgbVar;
        zzar();
        zzaq();
    }

    @WorkerThread
    public final void zza(zzon zzonVar) {
        zzt();
        zzu();
        zza(new zzlx(this, zzc(true), zzh().zza(zzonVar), zzonVar));
    }
}
