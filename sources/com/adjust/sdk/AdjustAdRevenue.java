package com.adjust.sdk;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class AdjustAdRevenue {
    private static final ILogger logger = AdjustFactory.getLogger();
    public Integer adImpressionsCount;
    public String adRevenueNetwork;
    public String adRevenuePlacement;
    public String adRevenueUnit;
    public Map<String, String> callbackParameters;
    public String currency;
    public Map<String, String> partnerParameters;
    public Double revenue;
    public String source;

    public AdjustAdRevenue(String str) {
        if (isValidSource(str)) {
            this.source = str;
        }
    }

    private boolean isValidSource(String str) {
        if (str == null) {
            logger.error("Missing source", new Object[0]);
            return false;
        }
        if (!str.isEmpty()) {
            return true;
        }
        logger.error("Source can't be empty", new Object[0]);
        return false;
    }

    public void addCallbackParameter(String str, String str2) {
        if (Util.isValidParameter(str, "key", "Callback") && Util.isValidParameter(str2, AppMeasurementSdk.ConditionalUserProperty.VALUE, "Callback")) {
            if (this.callbackParameters == null) {
                this.callbackParameters = new LinkedHashMap();
            }
            if (this.callbackParameters.put(str, str2) != null) {
                logger.warn("Key %s was overwritten", str);
            }
        }
    }

    public void addPartnerParameter(String str, String str2) {
        if (Util.isValidParameter(str, "key", "Partner") && Util.isValidParameter(str2, AppMeasurementSdk.ConditionalUserProperty.VALUE, "Partner")) {
            if (this.partnerParameters == null) {
                this.partnerParameters = new LinkedHashMap();
            }
            if (this.partnerParameters.put(str, str2) != null) {
                logger.warn("Key %s was overwritten", str);
            }
        }
    }

    public boolean isValid() {
        return isValidSource(this.source);
    }

    public void setAdImpressionsCount(Integer num) {
        this.adImpressionsCount = num;
    }

    public void setAdRevenueNetwork(String str) {
        this.adRevenueNetwork = str;
    }

    public void setAdRevenuePlacement(String str) {
        this.adRevenuePlacement = str;
    }

    public void setAdRevenueUnit(String str) {
        this.adRevenueUnit = str;
    }

    public void setRevenue(Double d2, String str) {
        this.revenue = d2;
        this.currency = str;
    }
}
