package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.GuardedBy;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Size;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import org.checkerframework.dataflow.qual.Pure;

/* loaded from: classes4.dex */
public final class zzlj extends zzh {

    @VisibleForTesting
    protected zzlk zza;
    private volatile zzlk zzb;
    private volatile zzlk zzc;
    private final Map<Integer, zzlk> zzd;

    @GuardedBy
    private Activity zze;

    @GuardedBy
    private volatile boolean zzf;
    private volatile zzlk zzg;
    private zzlk zzh;

    @GuardedBy
    private boolean zzi;
    private final Object zzj;

    public zzlj(zzhy zzhyVar) {
        super(zzhyVar);
        this.zzj = new Object();
        this.zzd = new ConcurrentHashMap();
    }

    public final zzlk zzaa() {
        return this.zzb;
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

    @Override // com.google.android.gms.measurement.internal.zzja, com.google.android.gms.measurement.internal.zzjc
    @Pure
    public final /* bridge */ /* synthetic */ zzab zzd() {
        return super.zzd();
    }

    @Override // com.google.android.gms.measurement.internal.zzja
    @Pure
    public final /* bridge */ /* synthetic */ zzag zze() {
        return super.zze();
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

    @MainThread
    private final zzlk zzd(@NonNull Activity activity) {
        Preconditions.checkNotNull(activity);
        zzlk zzlkVar = this.zzd.get(Integer.valueOf(activity.hashCode()));
        if (zzlkVar == null) {
            zzlk zzlkVar2 = new zzlk(null, zza(activity.getClass(), "Activity"), zzq().zzn());
            this.zzd.put(Integer.valueOf(activity.hashCode()), zzlkVar2);
            zzlkVar = zzlkVar2;
        }
        return this.zzg != null ? this.zzg : zzlkVar;
    }

    @MainThread
    public final void zzb(Activity activity) {
        synchronized (this.zzj) {
            this.zzi = false;
            this.zzf = true;
        }
        long elapsedRealtime = zzb().elapsedRealtime();
        if (!zze().zzw()) {
            this.zzb = null;
            zzl().zzb(new zzln(this, elapsedRealtime));
        } else {
            zzlk zzd = zzd(activity);
            this.zzc = this.zzb;
            this.zzb = null;
            zzl().zzb(new zzlq(this, zzd, elapsedRealtime));
        }
    }

    @MainThread
    public final void zzc(Activity activity) {
        synchronized (this.zzj) {
            this.zzi = true;
            if (activity != this.zze) {
                synchronized (this.zzj) {
                    this.zze = activity;
                    this.zzf = false;
                }
                if (zze().zzw()) {
                    this.zzg = null;
                    zzl().zzb(new zzlp(this));
                }
            }
        }
        if (!zze().zzw()) {
            this.zzb = this.zzg;
            zzl().zzb(new zzlo(this));
        } else {
            zza(activity, zzd(activity), false);
            zzb zzc = zzc();
            zzc.zzl().zzb(new zzc(zzc, zzc.zzb().elapsedRealtime()));
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzja, com.google.android.gms.measurement.internal.zzjc
    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    @WorkerThread
    public final zzlk zza(boolean z2) {
        zzu();
        zzt();
        if (!z2) {
            return this.zza;
        }
        zzlk zzlkVar = this.zza;
        return zzlkVar != null ? zzlkVar : this.zzh;
    }

    @VisibleForTesting
    private final String zza(Class<?> cls, String str) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName == null) {
            return str;
        }
        String[] split = canonicalName.split("\\.");
        String str2 = split.length > 0 ? split[split.length - 1] : "";
        return str2.length() > zze().zza((String) null, false) ? str2.substring(0, zze().zza((String) null, false)) : str2;
    }

    @MainThread
    public final void zzb(Activity activity, Bundle bundle) {
        zzlk zzlkVar;
        if (!zze().zzw() || bundle == null || (zzlkVar = this.zzd.get(Integer.valueOf(activity.hashCode()))) == null) {
            return;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putLong("id", zzlkVar.zzc);
        bundle2.putString("name", zzlkVar.zza);
        bundle2.putString("referrer_name", zzlkVar.zzb);
        bundle.putBundle("com.google.app_measurement.screen_service", bundle2);
    }

    public static /* synthetic */ void zza(zzlj zzljVar, Bundle bundle, zzlk zzlkVar, zzlk zzlkVar2, long j2) {
        if (bundle != null) {
            bundle.remove(FirebaseAnalytics.Param.SCREEN_NAME);
            bundle.remove(FirebaseAnalytics.Param.SCREEN_CLASS);
        }
        zzljVar.zza(zzlkVar, zzlkVar2, j2, true, zzljVar.zzq().zza((String) null, FirebaseAnalytics.Event.SCREEN_VIEW, bundle, (List<String>) null, false));
    }

    @MainThread
    private final void zza(Activity activity, zzlk zzlkVar, boolean z2) {
        zzlk zzlkVar2;
        zzlk zzlkVar3 = this.zzb == null ? this.zzc : this.zzb;
        if (zzlkVar.zzb == null) {
            zzlkVar2 = new zzlk(zzlkVar.zza, activity != null ? zza(activity.getClass(), "Activity") : null, zzlkVar.zzc, zzlkVar.zze, zzlkVar.zzf);
        } else {
            zzlkVar2 = zzlkVar;
        }
        this.zzc = this.zzb;
        this.zzb = zzlkVar2;
        zzl().zzb(new zzll(this, zzlkVar2, zzlkVar3, zzb().elapsedRealtime(), z2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r8v4, types: [android.os.BaseBundle] */
    /* JADX WARN: Type inference failed for: r8v5, types: [long, android.os.BaseBundle] */
    @WorkerThread
    public final void zza(zzlk zzlkVar, zzlk zzlkVar2, long j2, boolean z2, Bundle bundle) {
        long j3;
        zzt();
        boolean z3 = false;
        boolean z4 = (zzlkVar2 != null && zzlkVar2.zzc == zzlkVar.zzc && Objects.equals(zzlkVar2.zzb, zzlkVar.zzb) && Objects.equals(zzlkVar2.zza, zzlkVar.zza)) ? false : true;
        if (z2 && this.zza != null) {
            z3 = true;
        }
        if (z4) {
            zzos.zza(zzlkVar, bundle != null ? new Bundle(bundle) : new Bundle(), true);
            if (zzlkVar2 != null) {
                String str = zzlkVar2.zza;
                if (str != null) {
                    "_pn".putString("_pn", str);
                }
                String str2 = zzlkVar2.zzb;
                if (str2 != null) {
                    "_pc".putString("_pc", str2);
                }
                ?? r8 = zzlkVar2.zzc;
                r8.putLong("_pi", r8);
            }
            ?? r82 = 0;
            if (z3) {
                long zza = zzp().zzb.zza(j2);
                if (zza > 0) {
                    zzq().zza((Bundle) null, zza);
                }
            }
            if (!zze().zzw()) {
                r82.putLong("_mst", 1L);
            }
            String str3 = zzlkVar.zze ? "app" : "auto";
            long currentTimeMillis = zzb().currentTimeMillis();
            if (zzlkVar.zze) {
                currentTimeMillis = zzlkVar.zzf;
                if (currentTimeMillis != 0) {
                    j3 = currentTimeMillis;
                    zzm().zza(str3, "_vs", j3, (Bundle) null);
                }
            }
            j3 = currentTimeMillis;
            zzm().zza(str3, "_vs", j3, (Bundle) null);
        }
        if (z3) {
            zza(this.zza, true, j2);
        }
        this.zza = zzlkVar;
        if (zzlkVar.zze) {
            this.zzh = zzlkVar;
        }
        zzo().zza(zzlkVar);
    }

    @MainThread
    public final void zza(Activity activity, Bundle bundle) {
        Bundle bundle2;
        if (!zze().zzw() || bundle == null || (bundle2 = bundle.getBundle("com.google.app_measurement.screen_service")) == null) {
            return;
        }
        this.zzd.put(Integer.valueOf(activity.hashCode()), new zzlk(bundle2.getString("name"), bundle2.getString("referrer_name"), bundle2.getLong("id")));
    }

    @MainThread
    public final void zza(Activity activity) {
        synchronized (this.zzj) {
            try {
                if (activity == this.zze) {
                    this.zze = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (zze().zzw()) {
            this.zzd.remove(Integer.valueOf(activity.hashCode()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zza(zzlk zzlkVar, boolean z2, long j2) {
        zzc().zza(zzb().elapsedRealtime());
        if (!zzp().zza(zzlkVar != null && zzlkVar.zzd, z2, j2) || zzlkVar == null) {
            return;
        }
        zzlkVar.zzd = false;
    }

    @Deprecated
    public final void zza(@NonNull Activity activity, @Size String str, @Size String str2) {
        if (!zze().zzw()) {
            zzj().zzv().zza("setCurrentScreen cannot be called while screen reporting is disabled.");
            return;
        }
        zzlk zzlkVar = this.zzb;
        if (zzlkVar == null) {
            zzj().zzv().zza("setCurrentScreen cannot be called while no activity active");
            return;
        }
        if (this.zzd.get(Integer.valueOf(activity.hashCode())) == null) {
            zzj().zzv().zza("setCurrentScreen must be called with an activity in the activity lifecycle");
            return;
        }
        if (str2 == null) {
            str2 = zza(activity.getClass(), "Activity");
        }
        boolean equals = Objects.equals(zzlkVar.zzb, str2);
        boolean equals2 = Objects.equals(zzlkVar.zza, str);
        if (equals && equals2) {
            zzj().zzv().zza("setCurrentScreen cannot be called with the same class and name");
            return;
        }
        if (str != null && (str.length() <= 0 || str.length() > zze().zza((String) null, false))) {
            zzj().zzv().zza("Invalid screen name length in setCurrentScreen. Length", Integer.valueOf(str.length()));
            return;
        }
        if (str2 != null && (str2.length() <= 0 || str2.length() > zze().zza((String) null, false))) {
            zzj().zzv().zza("Invalid class name length in setCurrentScreen. Length", Integer.valueOf(str2.length()));
            return;
        }
        zzj().zzp().zza("Setting current screen to name, class", str == null ? "null" : str, str2);
        zzlk zzlkVar2 = new zzlk(str, str2, zzq().zzn());
        this.zzd.put(Integer.valueOf(activity.hashCode()), zzlkVar2);
        zza(activity, zzlkVar2, true);
    }

    public final void zza(Bundle bundle, long j2) {
        String str;
        synchronized (this.zzj) {
            try {
                if (!this.zzi) {
                    zzj().zzv().zza("Cannot log screen view event when the app is in the background.");
                    return;
                }
                String str2 = null;
                if (bundle != null) {
                    String string = bundle.getString(FirebaseAnalytics.Param.SCREEN_NAME);
                    if (string != null && (string.length() <= 0 || string.length() > zze().zza((String) null, false))) {
                        zzj().zzv().zza("Invalid screen name length for screen view. Length", Integer.valueOf(string.length()));
                        return;
                    }
                    String string2 = bundle.getString(FirebaseAnalytics.Param.SCREEN_CLASS);
                    if (string2 != null && (string2.length() <= 0 || string2.length() > zze().zza((String) null, false))) {
                        zzj().zzv().zza("Invalid screen class length for screen view. Length", Integer.valueOf(string2.length()));
                        return;
                    } else {
                        str = string;
                        str2 = string2;
                    }
                } else {
                    str = null;
                }
                if (str2 == null) {
                    Activity activity = this.zze;
                    str2 = activity != null ? zza(activity.getClass(), "Activity") : "Activity";
                }
                String str3 = str2;
                zzlk zzlkVar = this.zzb;
                if (this.zzf && zzlkVar != null) {
                    this.zzf = false;
                    boolean equals = Objects.equals(zzlkVar.zzb, str3);
                    boolean equals2 = Objects.equals(zzlkVar.zza, str);
                    if (equals && equals2) {
                        zzj().zzv().zza("Ignoring call to log screen view event with duplicate parameters.");
                        return;
                    }
                }
                zzj().zzp().zza("Logging screen view with name, class", str == null ? "null" : str, str3 == null ? "null" : str3);
                zzlk zzlkVar2 = this.zzb == null ? this.zzc : this.zzb;
                zzlk zzlkVar3 = new zzlk(str, str3, zzq().zzn(), true, j2);
                this.zzb = zzlkVar3;
                this.zzc = zzlkVar2;
                this.zzg = zzlkVar3;
                zzl().zzb(new zzlm(this, bundle, zzlkVar3, zzlkVar2, zzb().elapsedRealtime()));
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
