package com.mbridge.msdk.thrid.okhttp;

import com.mbridge.msdk.thrid.okhttp.internal.Util;
import com.mbridge.msdk.thrid.okhttp.internal.http.HttpDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import x0.AbstractC2914a;

/* loaded from: classes3.dex */
public final class Cookie {
    private final String domain;
    private final long expiresAt;
    private final boolean hostOnly;
    private final boolean httpOnly;
    private final String name;
    private final String path;
    private final boolean persistent;
    private final boolean secure;
    private final String value;
    private static final Pattern YEAR_PATTERN = Pattern.compile("(\\d{2,4})[^\\d]*");
    private static final Pattern MONTH_PATTERN = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");
    private static final Pattern DAY_OF_MONTH_PATTERN = Pattern.compile("(\\d{1,2})[^\\d]*");
    private static final Pattern TIME_PATTERN = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");

    /* loaded from: classes3.dex */
    public static final class Builder {
        String domain;
        boolean hostOnly;
        boolean httpOnly;
        String name;
        boolean persistent;
        boolean secure;
        String value;
        long expiresAt = HttpDate.MAX_DATE;
        String path = "/";

        public Cookie build() {
            return new Cookie(this);
        }

        public Builder domain(String str) {
            return domain(str, false);
        }

        public Builder expiresAt(long j7) {
            if (j7 <= 0) {
                j7 = Long.MIN_VALUE;
            }
            if (j7 > HttpDate.MAX_DATE) {
                j7 = 253402300799999L;
            }
            this.expiresAt = j7;
            this.persistent = true;
            return this;
        }

        public Builder hostOnlyDomain(String str) {
            return domain(str, true);
        }

        public Builder httpOnly() {
            this.httpOnly = true;
            return this;
        }

        public Builder name(String str) {
            if (str != null) {
                if (str.trim().equals(str)) {
                    this.name = str;
                    return this;
                }
                throw new IllegalArgumentException("name is not trimmed");
            }
            throw new NullPointerException("name == null");
        }

        public Builder path(String str) {
            if (str.startsWith("/")) {
                this.path = str;
                return this;
            }
            throw new IllegalArgumentException("path must start with '/'");
        }

        public Builder secure() {
            this.secure = true;
            return this;
        }

        public Builder value(String str) {
            if (str != null) {
                if (str.trim().equals(str)) {
                    this.value = str;
                    return this;
                }
                throw new IllegalArgumentException("value is not trimmed");
            }
            throw new NullPointerException("value == null");
        }

        private Builder domain(String str, boolean z8) {
            if (str != null) {
                String canonicalizeHost = Util.canonicalizeHost(str);
                if (canonicalizeHost != null) {
                    this.domain = canonicalizeHost;
                    this.hostOnly = z8;
                    return this;
                }
                throw new IllegalArgumentException("unexpected domain: ".concat(str));
            }
            throw new NullPointerException("domain == null");
        }
    }

    private Cookie(String str, String str2, long j7, String str3, String str4, boolean z8, boolean z9, boolean z10, boolean z11) {
        this.name = str;
        this.value = str2;
        this.expiresAt = j7;
        this.domain = str3;
        this.path = str4;
        this.secure = z8;
        this.httpOnly = z9;
        this.hostOnly = z10;
        this.persistent = z11;
    }

    private static int dateCharacterOffset(String str, int i9, int i10, boolean z8) {
        boolean z9;
        while (i9 < i10) {
            char charAt = str.charAt(i9);
            if ((charAt >= ' ' || charAt == '\t') && charAt < 127 && ((charAt < '0' || charAt > '9') && ((charAt < 'a' || charAt > 'z') && ((charAt < 'A' || charAt > 'Z') && charAt != ':')))) {
                z9 = false;
            } else {
                z9 = true;
            }
            if (z9 == (!z8)) {
                return i9;
            }
            i9++;
        }
        return i10;
    }

    private static boolean domainMatch(String str, String str2) {
        if (str.equals(str2)) {
            return true;
        }
        if (str.endsWith(str2) && str.charAt((str.length() - str2.length()) - 1) == '.' && !Util.verifyAsIpAddress(str)) {
            return true;
        }
        return false;
    }

    public static Cookie parse(HttpUrl httpUrl, String str) {
        return parse(System.currentTimeMillis(), httpUrl, str);
    }

    public static List<Cookie> parseAll(HttpUrl httpUrl, Headers headers) {
        List<String> values = headers.values("Set-Cookie");
        int size = values.size();
        ArrayList arrayList = null;
        for (int i9 = 0; i9 < size; i9++) {
            Cookie parse = parse(httpUrl, values.get(i9));
            if (parse != null) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(parse);
            }
        }
        if (arrayList != null) {
            return Collections.unmodifiableList(arrayList);
        }
        return Collections.emptyList();
    }

    private static String parseDomain(String str) {
        if (!str.endsWith(".")) {
            if (str.startsWith(".")) {
                str = str.substring(1);
            }
            String canonicalizeHost = Util.canonicalizeHost(str);
            if (canonicalizeHost != null) {
                return canonicalizeHost;
            }
            throw new IllegalArgumentException();
        }
        throw new IllegalArgumentException();
    }

    private static long parseExpires(String str, int i9, int i10) {
        int dateCharacterOffset = dateCharacterOffset(str, i9, i10, false);
        Matcher matcher = TIME_PATTERN.matcher(str);
        int i11 = -1;
        int i12 = -1;
        int i13 = -1;
        int i14 = -1;
        int i15 = -1;
        int i16 = -1;
        while (dateCharacterOffset < i10) {
            int dateCharacterOffset2 = dateCharacterOffset(str, dateCharacterOffset + 1, i10, true);
            matcher.region(dateCharacterOffset, dateCharacterOffset2);
            if (i12 == -1 && matcher.usePattern(TIME_PATTERN).matches()) {
                i12 = Integer.parseInt(matcher.group(1));
                i15 = Integer.parseInt(matcher.group(2));
                i16 = Integer.parseInt(matcher.group(3));
            } else if (i13 == -1 && matcher.usePattern(DAY_OF_MONTH_PATTERN).matches()) {
                i13 = Integer.parseInt(matcher.group(1));
            } else {
                if (i14 == -1) {
                    Pattern pattern = MONTH_PATTERN;
                    if (matcher.usePattern(pattern).matches()) {
                        i14 = pattern.pattern().indexOf(matcher.group(1).toLowerCase(Locale.US)) / 4;
                    }
                }
                if (i11 == -1 && matcher.usePattern(YEAR_PATTERN).matches()) {
                    i11 = Integer.parseInt(matcher.group(1));
                }
            }
            dateCharacterOffset = dateCharacterOffset(str, dateCharacterOffset2 + 1, i10, false);
        }
        if (i11 >= 70 && i11 <= 99) {
            i11 += 1900;
        }
        if (i11 >= 0 && i11 <= 69) {
            i11 += 2000;
        }
        if (i11 >= 1601) {
            if (i14 != -1) {
                if (i13 >= 1 && i13 <= 31) {
                    if (i12 >= 0 && i12 <= 23) {
                        if (i15 >= 0 && i15 <= 59) {
                            if (i16 >= 0 && i16 <= 59) {
                                GregorianCalendar gregorianCalendar = new GregorianCalendar(Util.UTC);
                                gregorianCalendar.setLenient(false);
                                gregorianCalendar.set(1, i11);
                                gregorianCalendar.set(2, i14 - 1);
                                gregorianCalendar.set(5, i13);
                                gregorianCalendar.set(11, i12);
                                gregorianCalendar.set(12, i15);
                                gregorianCalendar.set(13, i16);
                                gregorianCalendar.set(14, 0);
                                return gregorianCalendar.getTimeInMillis();
                            }
                            throw new IllegalArgumentException();
                        }
                        throw new IllegalArgumentException();
                    }
                    throw new IllegalArgumentException();
                }
                throw new IllegalArgumentException();
            }
            throw new IllegalArgumentException();
        }
        throw new IllegalArgumentException();
    }

    private static long parseMaxAge(String str) {
        try {
            long parseLong = Long.parseLong(str);
            if (parseLong <= 0) {
                return Long.MIN_VALUE;
            }
            return parseLong;
        } catch (NumberFormatException e4) {
            if (str.matches("-?\\d+")) {
                if (str.startsWith("-")) {
                    return Long.MIN_VALUE;
                }
                return Long.MAX_VALUE;
            }
            throw e4;
        }
    }

    private static boolean pathMatch(HttpUrl httpUrl, String str) {
        String encodedPath = httpUrl.encodedPath();
        if (encodedPath.equals(str)) {
            return true;
        }
        if (encodedPath.startsWith(str)) {
            if (str.endsWith("/") || encodedPath.charAt(str.length()) == '/') {
                return true;
            }
            return false;
        }
        return false;
    }

    public String domain() {
        return this.domain;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Cookie)) {
            return false;
        }
        Cookie cookie = (Cookie) obj;
        if (!cookie.name.equals(this.name) || !cookie.value.equals(this.value) || !cookie.domain.equals(this.domain) || !cookie.path.equals(this.path) || cookie.expiresAt != this.expiresAt || cookie.secure != this.secure || cookie.httpOnly != this.httpOnly || cookie.persistent != this.persistent || cookie.hostOnly != this.hostOnly) {
            return false;
        }
        return true;
    }

    public long expiresAt() {
        return this.expiresAt;
    }

    public int hashCode() {
        int b = AbstractC2914a.b(AbstractC2914a.b(AbstractC2914a.b(AbstractC2914a.b(527, 31, this.name), 31, this.value), 31, this.domain), 31, this.path);
        long j7 = this.expiresAt;
        return ((((((((b + ((int) (j7 ^ (j7 >>> 32)))) * 31) + (!this.secure ? 1 : 0)) * 31) + (!this.httpOnly ? 1 : 0)) * 31) + (!this.persistent ? 1 : 0)) * 31) + (!this.hostOnly ? 1 : 0);
    }

    public boolean hostOnly() {
        return this.hostOnly;
    }

    public boolean httpOnly() {
        return this.httpOnly;
    }

    public boolean matches(HttpUrl httpUrl) {
        boolean domainMatch;
        if (this.hostOnly) {
            domainMatch = httpUrl.host().equals(this.domain);
        } else {
            domainMatch = domainMatch(httpUrl.host(), this.domain);
        }
        if (!domainMatch || !pathMatch(httpUrl, this.path)) {
            return false;
        }
        if (this.secure && !httpUrl.isHttps()) {
            return false;
        }
        return true;
    }

    public String name() {
        return this.name;
    }

    public String path() {
        return this.path;
    }

    public boolean persistent() {
        return this.persistent;
    }

    public boolean secure() {
        return this.secure;
    }

    public String toString() {
        return toString(false);
    }

    public String value() {
        return this.value;
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00f1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.mbridge.msdk.thrid.okhttp.Cookie parse(long r23, com.mbridge.msdk.thrid.okhttp.HttpUrl r25, java.lang.String r26) {
        /*
            Method dump skipped, instructions count: 310
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.thrid.okhttp.Cookie.parse(long, com.mbridge.msdk.thrid.okhttp.HttpUrl, java.lang.String):com.mbridge.msdk.thrid.okhttp.Cookie");
    }

    public String toString(boolean z8) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        sb.append('=');
        sb.append(this.value);
        if (this.persistent) {
            if (this.expiresAt == Long.MIN_VALUE) {
                sb.append("; max-age=0");
            } else {
                sb.append("; expires=");
                sb.append(HttpDate.format(new Date(this.expiresAt)));
            }
        }
        if (!this.hostOnly) {
            sb.append("; domain=");
            if (z8) {
                sb.append(".");
            }
            sb.append(this.domain);
        }
        sb.append("; path=");
        sb.append(this.path);
        if (this.secure) {
            sb.append("; secure");
        }
        if (this.httpOnly) {
            sb.append("; httponly");
        }
        return sb.toString();
    }

    public Cookie(Builder builder) {
        String str = builder.name;
        if (str != null) {
            String str2 = builder.value;
            if (str2 != null) {
                String str3 = builder.domain;
                if (str3 != null) {
                    this.name = str;
                    this.value = str2;
                    this.expiresAt = builder.expiresAt;
                    this.domain = str3;
                    this.path = builder.path;
                    this.secure = builder.secure;
                    this.httpOnly = builder.httpOnly;
                    this.persistent = builder.persistent;
                    this.hostOnly = builder.hostOnly;
                    return;
                }
                throw new NullPointerException("builder.domain == null");
            }
            throw new NullPointerException("builder.value == null");
        }
        throw new NullPointerException("builder.name == null");
    }
}
