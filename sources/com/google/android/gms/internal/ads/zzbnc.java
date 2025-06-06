package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "HttpResponseParcelCreator")
/* loaded from: classes2.dex */
public final class zzbnc extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbnc> CREATOR = new zzbnd();

    @SafeParcelable.Field(id = 1)
    public final boolean zza;

    @SafeParcelable.Field(id = 2)
    public final String zzb;

    @SafeParcelable.Field(id = 3)
    public final int zzc;

    @SafeParcelable.Field(id = 4)
    public final byte[] zzd;

    @SafeParcelable.Field(id = 5)
    public final String[] zze;

    @SafeParcelable.Field(id = 6)
    public final String[] zzf;

    @SafeParcelable.Field(id = 7)
    public final boolean zzg;

    @SafeParcelable.Field(id = 8)
    public final long zzh;

    @SafeParcelable.Constructor
    public zzbnc(@SafeParcelable.Param(id = 1) boolean z8, @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) int i9, @SafeParcelable.Param(id = 4) byte[] bArr, @SafeParcelable.Param(id = 5) String[] strArr, @SafeParcelable.Param(id = 6) String[] strArr2, @SafeParcelable.Param(id = 7) boolean z9, @SafeParcelable.Param(id = 8) long j7) {
        this.zza = z8;
        this.zzb = str;
        this.zzc = i9;
        this.zzd = bArr;
        this.zze = strArr;
        this.zzf = strArr2;
        this.zzg = z9;
        this.zzh = j7;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        boolean z8 = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, z8);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzc);
        SafeParcelWriter.writeByteArray(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeStringArray(parcel, 5, this.zze, false);
        SafeParcelWriter.writeStringArray(parcel, 6, this.zzf, false);
        SafeParcelWriter.writeBoolean(parcel, 7, this.zzg);
        SafeParcelWriter.writeLong(parcel, 8, this.zzh);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
