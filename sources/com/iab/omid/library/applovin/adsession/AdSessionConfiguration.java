package com.iab.omid.library.applovin.adsession;

import com.iab.omid.library.applovin.utils.c;
import com.iab.omid.library.applovin.utils.g;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class AdSessionConfiguration {
    private final CreativeType creativeType;
    private final Owner impressionOwner;
    private final ImpressionType impressionType;
    private final boolean isolateVerificationScripts;
    private final Owner mediaEventsOwner;

    private AdSessionConfiguration(CreativeType creativeType, ImpressionType impressionType, Owner owner, Owner owner2, boolean z8) {
        this.creativeType = creativeType;
        this.impressionType = impressionType;
        this.impressionOwner = owner;
        if (owner2 == null) {
            this.mediaEventsOwner = Owner.NONE;
        } else {
            this.mediaEventsOwner = owner2;
        }
        this.isolateVerificationScripts = z8;
    }

    public static AdSessionConfiguration createAdSessionConfiguration(CreativeType creativeType, ImpressionType impressionType, Owner owner, Owner owner2, boolean z8) {
        g.a(creativeType, "CreativeType is null");
        g.a(impressionType, "ImpressionType is null");
        g.a(owner, "Impression owner is null");
        g.a(owner, creativeType, impressionType);
        return new AdSessionConfiguration(creativeType, impressionType, owner, owner2, z8);
    }

    public boolean isNativeImpressionOwner() {
        return Owner.NATIVE == this.impressionOwner;
    }

    public boolean isNativeMediaEventsOwner() {
        return Owner.NATIVE == this.mediaEventsOwner;
    }

    public JSONObject toJsonObject() {
        JSONObject jSONObject = new JSONObject();
        c.a(jSONObject, "impressionOwner", this.impressionOwner);
        c.a(jSONObject, "mediaEventsOwner", this.mediaEventsOwner);
        c.a(jSONObject, "creativeType", this.creativeType);
        c.a(jSONObject, "impressionType", this.impressionType);
        c.a(jSONObject, "isolateVerificationScripts", Boolean.valueOf(this.isolateVerificationScripts));
        return jSONObject;
    }
}
