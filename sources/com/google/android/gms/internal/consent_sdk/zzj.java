package com.google.android.gms.internal.consent_sdk;

import android.app.Activity;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.ump.ConsentInformation;
import com.google.android.ump.ConsentRequestParameters;
import com.google.android.ump.FormError;

/* loaded from: classes3.dex */
public final class zzj implements ConsentInformation {
    private final zzap zza;
    private final zzu zzb;
    private final zzbn zzc;
    private final Object zzd = new Object();
    private final Object zze = new Object();
    private boolean zzf = false;
    private boolean zzg = false;
    private ConsentRequestParameters zzh = new ConsentRequestParameters.Builder().build();

    public zzj(zzap zzapVar, zzu zzuVar, zzbn zzbnVar) {
        this.zza = zzapVar;
        this.zzb = zzuVar;
        this.zzc = zzbnVar;
    }

    @Override // com.google.android.ump.ConsentInformation
    public final boolean canRequestAds() {
        if (!this.zza.zzk()) {
            int zza = !zzc() ? 0 : this.zza.zza();
            if (zza != 1 && zza != 3) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.android.ump.ConsentInformation
    public final int getConsentStatus() {
        if (zzc()) {
            return this.zza.zza();
        }
        return 0;
    }

    @Override // com.google.android.ump.ConsentInformation
    public final ConsentInformation.PrivacyOptionsRequirementStatus getPrivacyOptionsRequirementStatus() {
        return !zzc() ? ConsentInformation.PrivacyOptionsRequirementStatus.UNKNOWN : this.zza.zzb();
    }

    @Override // com.google.android.ump.ConsentInformation
    public final boolean isConsentFormAvailable() {
        return this.zzc.zzf();
    }

    @Override // com.google.android.ump.ConsentInformation
    public final void requestConsentInfoUpdate(@Nullable Activity activity, ConsentRequestParameters consentRequestParameters, ConsentInformation.OnConsentInfoUpdateSuccessListener onConsentInfoUpdateSuccessListener, ConsentInformation.OnConsentInfoUpdateFailureListener onConsentInfoUpdateFailureListener) {
        synchronized (this.zzd) {
            this.zzf = true;
        }
        this.zzh = consentRequestParameters;
        this.zzb.zzc(activity, consentRequestParameters, onConsentInfoUpdateSuccessListener, onConsentInfoUpdateFailureListener);
    }

    @Override // com.google.android.ump.ConsentInformation
    public final void reset() {
        this.zzc.zzd(null);
        this.zza.zze();
        synchronized (this.zzd) {
            this.zzf = false;
        }
    }

    public final void zza(@Nullable Activity activity) {
        if (zzc() && !zzd()) {
            zzb(true);
            this.zzb.zzc(activity, this.zzh, new ConsentInformation.OnConsentInfoUpdateSuccessListener() { // from class: com.google.android.gms.internal.consent_sdk.zzh
                @Override // com.google.android.ump.ConsentInformation.OnConsentInfoUpdateSuccessListener
                public final void onConsentInfoUpdateSuccess() {
                    zzj.this.zzb(false);
                }
            }, new ConsentInformation.OnConsentInfoUpdateFailureListener() { // from class: com.google.android.gms.internal.consent_sdk.zzi
                @Override // com.google.android.ump.ConsentInformation.OnConsentInfoUpdateFailureListener
                public final void onConsentInfoUpdateFailure(FormError formError) {
                    zzj.this.zzb(false);
                }
            });
            return;
        }
        Log.w("UserMessagingPlatform", "Retry request is not executed. consentInfoUpdateHasBeenCalled=" + zzc() + ", retryRequestIsInProgress=" + zzd());
    }

    public final void zzb(boolean z2) {
        synchronized (this.zze) {
            this.zzg = z2;
        }
    }

    public final boolean zzc() {
        boolean z2;
        synchronized (this.zzd) {
            z2 = this.zzf;
        }
        return z2;
    }

    public final boolean zzd() {
        boolean z2;
        synchronized (this.zze) {
            z2 = this.zzg;
        }
        return z2;
    }
}
