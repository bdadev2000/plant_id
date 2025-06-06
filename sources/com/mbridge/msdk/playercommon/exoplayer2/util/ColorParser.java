package com.mbridge.msdk.playercommon.exoplayer2.util;

import android.text.TextUtils;
import androidx.core.view.ViewCompat;
import com.mbridge.msdk.foundation.entity.o;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public final class ColorParser {
    private static final Map<String, Integer> COLOR_MAP;
    private static final String RGB = "rgb";
    private static final String RGBA = "rgba";
    private static final Pattern RGB_PATTERN = Pattern.compile("^rgb\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\)$");
    private static final Pattern RGBA_PATTERN_INT_ALPHA = Pattern.compile("^rgba\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\)$");
    private static final Pattern RGBA_PATTERN_FLOAT_ALPHA = Pattern.compile("^rgba\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3}),(\\d*\\.?\\d*?)\\)$");

    static {
        HashMap hashMap = new HashMap();
        COLOR_MAP = hashMap;
        o.t(-984833, hashMap, "aliceblue", -332841, "antiquewhite");
        o.y(hashMap, "aqua", -16711681, -8388652, "aquamarine");
        o.t(-983041, hashMap, "azure", -657956, "beige");
        o.t(-6972, hashMap, "bisque", ViewCompat.MEASURED_STATE_MASK, "black");
        o.t(-5171, hashMap, "blanchedalmond", -16776961, "blue");
        o.t(-7722014, hashMap, "blueviolet", -5952982, "brown");
        o.t(-2180985, hashMap, "burlywood", -10510688, "cadetblue");
        o.t(-8388864, hashMap, "chartreuse", -2987746, "chocolate");
        o.t(-32944, hashMap, "coral", -10185235, "cornflowerblue");
        o.t(-1828, hashMap, "cornsilk", -2354116, "crimson");
        o.y(hashMap, "cyan", -16711681, -16777077, "darkblue");
        o.t(-16741493, hashMap, "darkcyan", -4684277, "darkgoldenrod");
        o.y(hashMap, "darkgray", -5658199, -16751616, "darkgreen");
        o.y(hashMap, "darkgrey", -5658199, -4343957, "darkkhaki");
        o.t(-7667573, hashMap, "darkmagenta", -11179217, "darkolivegreen");
        o.t(-29696, hashMap, "darkorange", -6737204, "darkorchid");
        o.t(-7667712, hashMap, "darkred", -1468806, "darksalmon");
        o.t(-7357297, hashMap, "darkseagreen", -12042869, "darkslateblue");
        hashMap.put("darkslategray", -13676721);
        hashMap.put("darkslategrey", -13676721);
        o.y(hashMap, "darkturquoise", -16724271, -7077677, "darkviolet");
        o.t(-60269, hashMap, "deeppink", -16728065, "deepskyblue");
        hashMap.put("dimgray", -9868951);
        hashMap.put("dimgrey", -9868951);
        o.y(hashMap, "dodgerblue", -14774017, -5103070, "firebrick");
        o.t(-1296, hashMap, "floralwhite", -14513374, "forestgreen");
        o.y(hashMap, "fuchsia", -65281, -2302756, "gainsboro");
        o.t(-460545, hashMap, "ghostwhite", -10496, "gold");
        hashMap.put("goldenrod", -2448096);
        hashMap.put("gray", -8355712);
        o.t(-16744448, hashMap, "green", -5374161, "greenyellow");
        o.y(hashMap, "grey", -8355712, -983056, "honeydew");
        o.t(-38476, hashMap, "hotpink", -3318692, "indianred");
        o.t(-11861886, hashMap, "indigo", -16, "ivory");
        o.t(-989556, hashMap, "khaki", -1644806, "lavender");
        o.t(-3851, hashMap, "lavenderblush", -8586240, "lawngreen");
        o.t(-1331, hashMap, "lemonchiffon", -5383962, "lightblue");
        o.t(-1015680, hashMap, "lightcoral", -2031617, "lightcyan");
        hashMap.put("lightgoldenrodyellow", -329006);
        hashMap.put("lightgray", -2894893);
        hashMap.put("lightgreen", -7278960);
        hashMap.put("lightgrey", -2894893);
        o.t(-18751, hashMap, "lightpink", -24454, "lightsalmon");
        o.t(-14634326, hashMap, "lightseagreen", -7876870, "lightskyblue");
        hashMap.put("lightslategray", -8943463);
        hashMap.put("lightslategrey", -8943463);
        o.y(hashMap, "lightsteelblue", -5192482, -32, "lightyellow");
        o.t(-16711936, hashMap, "lime", -13447886, "limegreen");
        hashMap.put("linen", -331546);
        hashMap.put("magenta", -65281);
        o.t(-8388608, hashMap, "maroon", -10039894, "mediumaquamarine");
        o.t(-16777011, hashMap, "mediumblue", -4565549, "mediumorchid");
        o.t(-7114533, hashMap, "mediumpurple", -12799119, "mediumseagreen");
        o.t(-8689426, hashMap, "mediumslateblue", -16713062, "mediumspringgreen");
        o.t(-12004916, hashMap, "mediumturquoise", -3730043, "mediumvioletred");
        o.t(-15132304, hashMap, "midnightblue", -655366, "mintcream");
        o.t(-6943, hashMap, "mistyrose", -6987, "moccasin");
        o.t(-8531, hashMap, "navajowhite", -16777088, "navy");
        o.t(-133658, hashMap, "oldlace", -8355840, "olive");
        o.t(-9728477, hashMap, "olivedrab", -23296, "orange");
        o.t(-47872, hashMap, "orangered", -2461482, "orchid");
        o.t(-1120086, hashMap, "palegoldenrod", -6751336, "palegreen");
        o.t(-5247250, hashMap, "paleturquoise", -2396013, "palevioletred");
        o.t(-4139, hashMap, "papayawhip", -9543, "peachpuff");
        o.t(-3308225, hashMap, "peru", -16181, "pink");
        o.t(-2252579, hashMap, "plum", -5185306, "powderblue");
        o.t(-8388480, hashMap, "purple", -10079335, "rebeccapurple");
        o.t(-65536, hashMap, "red", -4419697, "rosybrown");
        o.t(-12490271, hashMap, "royalblue", -7650029, "saddlebrown");
        o.t(-360334, hashMap, "salmon", -744352, "sandybrown");
        o.t(-13726889, hashMap, "seagreen", -2578, "seashell");
        o.t(-6270419, hashMap, "sienna", -4144960, "silver");
        o.t(-7876885, hashMap, "skyblue", -9807155, "slateblue");
        hashMap.put("slategray", -9404272);
        hashMap.put("slategrey", -9404272);
        o.y(hashMap, "snow", -1286, -16711809, "springgreen");
        o.t(-12156236, hashMap, "steelblue", -2968436, "tan");
        o.t(-16744320, hashMap, "teal", -2572328, "thistle");
        o.t(-40121, hashMap, "tomato", 0, "transparent");
        o.t(-12525360, hashMap, "turquoise", -1146130, "violet");
        o.t(-663885, hashMap, "wheat", -1, "white");
        o.t(-657931, hashMap, "whitesmoke", -256, "yellow");
        hashMap.put("yellowgreen", -6632142);
    }

    private ColorParser() {
    }

    private static int argb(int i9, int i10, int i11, int i12) {
        return (i9 << 24) | (i10 << 16) | (i11 << 8) | i12;
    }

    private static int parseColorInternal(String str, boolean z8) {
        Pattern pattern;
        int parseInt;
        Assertions.checkArgument(!TextUtils.isEmpty(str));
        String replace = str.replace(" ", "");
        if (replace.charAt(0) == '#') {
            int parseLong = (int) Long.parseLong(replace.substring(1), 16);
            if (replace.length() == 7) {
                return (-16777216) | parseLong;
            }
            if (replace.length() == 9) {
                return ((parseLong & 255) << 24) | (parseLong >>> 8);
            }
            throw new IllegalArgumentException();
        }
        if (replace.startsWith(RGBA)) {
            if (z8) {
                pattern = RGBA_PATTERN_FLOAT_ALPHA;
            } else {
                pattern = RGBA_PATTERN_INT_ALPHA;
            }
            Matcher matcher = pattern.matcher(replace);
            if (matcher.matches()) {
                if (z8) {
                    parseInt = (int) (Float.parseFloat(matcher.group(4)) * 255.0f);
                } else {
                    parseInt = Integer.parseInt(matcher.group(4), 10);
                }
                return argb(parseInt, Integer.parseInt(matcher.group(1), 10), Integer.parseInt(matcher.group(2), 10), Integer.parseInt(matcher.group(3), 10));
            }
        } else if (replace.startsWith(RGB)) {
            Matcher matcher2 = RGB_PATTERN.matcher(replace);
            if (matcher2.matches()) {
                return rgb(Integer.parseInt(matcher2.group(1), 10), Integer.parseInt(matcher2.group(2), 10), Integer.parseInt(matcher2.group(3), 10));
            }
        } else {
            Integer num = COLOR_MAP.get(Util.toLowerInvariant(replace));
            if (num != null) {
                return num.intValue();
            }
        }
        throw new IllegalArgumentException();
    }

    public static int parseCssColor(String str) {
        return parseColorInternal(str, true);
    }

    public static int parseTtmlColor(String str) {
        return parseColorInternal(str, false);
    }

    private static int rgb(int i9, int i10, int i11) {
        return argb(255, i9, i10, i11);
    }
}
