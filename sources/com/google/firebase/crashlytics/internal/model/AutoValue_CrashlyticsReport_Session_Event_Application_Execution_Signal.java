package com.google.firebase.crashlytics.internal.model;

import android.support.v4.media.d;
import androidx.annotation.NonNull;
import com.applovin.impl.adview.t;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;

/* loaded from: classes.dex */
final class AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Signal extends CrashlyticsReport.Session.Event.Application.Execution.Signal {
    private final long address;
    private final String code;
    private final String name;

    /* loaded from: classes.dex */
    public static final class Builder extends CrashlyticsReport.Session.Event.Application.Execution.Signal.Builder {
        private long address;
        private String code;
        private String name;
        private byte set$0;

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Signal.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.Signal build() {
            String str;
            String str2;
            if (this.set$0 == 1 && (str = this.name) != null && (str2 = this.code) != null) {
                return new AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Signal(str, str2, this.address);
            }
            StringBuilder sb = new StringBuilder();
            if (this.name == null) {
                sb.append(" name");
            }
            if (this.code == null) {
                sb.append(" code");
            }
            if ((1 & this.set$0) == 0) {
                sb.append(" address");
            }
            throw new IllegalStateException(t.m("Missing required properties:", sb));
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Signal.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.Signal.Builder setAddress(long j2) {
            this.address = j2;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Signal.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.Signal.Builder setCode(String str) {
            if (str == null) {
                throw new NullPointerException("Null code");
            }
            this.code = str;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Signal.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.Signal.Builder setName(String str) {
            if (str == null) {
                throw new NullPointerException("Null name");
            }
            this.name = str;
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CrashlyticsReport.Session.Event.Application.Execution.Signal)) {
            return false;
        }
        CrashlyticsReport.Session.Event.Application.Execution.Signal signal = (CrashlyticsReport.Session.Event.Application.Execution.Signal) obj;
        return this.name.equals(signal.getName()) && this.code.equals(signal.getCode()) && this.address == signal.getAddress();
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Signal
    @NonNull
    public long getAddress() {
        return this.address;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Signal
    @NonNull
    public String getCode() {
        return this.code;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Signal
    @NonNull
    public String getName() {
        return this.name;
    }

    public int hashCode() {
        int hashCode = (((this.name.hashCode() ^ 1000003) * 1000003) ^ this.code.hashCode()) * 1000003;
        long j2 = this.address;
        return hashCode ^ ((int) (j2 ^ (j2 >>> 32)));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Signal{name=");
        sb.append(this.name);
        sb.append(", code=");
        sb.append(this.code);
        sb.append(", address=");
        return d.p(sb, this.address, "}");
    }

    private AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Signal(String str, String str2, long j2) {
        this.name = str;
        this.code = str2;
        this.address = j2;
    }
}
