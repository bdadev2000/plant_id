package com.bytedance.sdk.openadsdk.core;

import com.applovin.sdk.AppLovinErrorCodes;
import com.mbridge.msdk.playercommon.exoplayer2.C;

/* loaded from: classes.dex */
public class QR {
    public static String zp(int i9) {
        try {
            if (i9 != -16) {
                if (i9 != -15) {
                    if (i9 != 117) {
                        if (i9 != 118) {
                            if (i9 != 127) {
                                if (i9 != 128) {
                                    if (i9 != 137) {
                                        if (i9 != 138) {
                                            if (i9 != 201) {
                                                if (i9 != 202) {
                                                    if (i9 != 10002) {
                                                        if (i9 != 10003) {
                                                            switch (i9) {
                                                                case -100:
                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_init_setting_config_not_complete");
                                                                case 113:
                                                                    return "dynamic1 parse error";
                                                                case 123:
                                                                    return "dynamic2 parse error";
                                                                case 133:
                                                                    return "ugen parse error";
                                                                case AD_SERVER_ERROR_VALUE:
                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_no_ad");
                                                                case 40000:
                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_content_type");
                                                                case 40001:
                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_request_pb_error");
                                                                case 40002:
                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_app_empty");
                                                                case 40003:
                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_wap_empty");
                                                                case 40004:
                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_adslot_empty");
                                                                case 40005:
                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_adslot_size_empty");
                                                                case 40006:
                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_adslot_id_error");
                                                                case 40007:
                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_error_code_adcount_error");
                                                                case 40008:
                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_error_image_size");
                                                                case 40009:
                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_error_media_id");
                                                                case 40010:
                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_error_media_type");
                                                                case 40011:
                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_error_ad_type");
                                                                case 40012:
                                                                case 40017:
                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_error_access_method_pass");
                                                                case 40013:
                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_error_splash_ad_type");
                                                                case 40014:
                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_error_redirect");
                                                                case 40015:
                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_error_request_invalid");
                                                                case 40016:
                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_error_slot_id_app_id_differ");
                                                                case 40018:
                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_error_package_name");
                                                                case 40019:
                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_error_adtype_differ");
                                                                case 40020:
                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_error_new_register_limit");
                                                                case 40021:
                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_error_apk_sign_check_error");
                                                                case 40022:
                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_error_origin_ad_error");
                                                                case 40023:
                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_error_union_os_error");
                                                                case 40024:
                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_error_union_sdk_too_old");
                                                                case 40026:
                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_error_device_ip");
                                                                case 40028:
                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_error_update_version");
                                                                case 40034:
                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_error_bidding_type");
                                                                case 40042:
                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_error_interstitial_version");
                                                                case 40052:
                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_error_empty_content");
                                                                case 50001:
                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_sys_error");
                                                                case 60007:
                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_error_verify_reward");
                                                                default:
                                                                    switch (i9) {
                                                                        case -12:
                                                                            return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_splash_not_have_cache_error");
                                                                        case -11:
                                                                            return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_splash_cache_expired_error");
                                                                        case -10:
                                                                            return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_splash_cache_parse_error");
                                                                        case -9:
                                                                            return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_request_body_error");
                                                                        case AppLovinErrorCodes.INVALID_AD_TOKEN /* -8 */:
                                                                            return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_frequent_call_erroe");
                                                                        case AppLovinErrorCodes.INVALID_ZONE /* -7 */:
                                                                            return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_splash_ad_load_image_error");
                                                                        case AppLovinErrorCodes.UNABLE_TO_RENDER_AD /* -6 */:
                                                                            return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_insert_ad_load_image_error");
                                                                        case C.RESULT_FORMAT_READ /* -5 */:
                                                                            return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_banner_ad_load_image_error");
                                                                        case C.RESULT_BUFFER_READ /* -4 */:
                                                                            return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_ad_data_error");
                                                                        case C.RESULT_NOTHING_READ /* -3 */:
                                                                            return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_no_ad_parse");
                                                                        case -2:
                                                                            return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_net_error");
                                                                        case -1:
                                                                            return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_parse_fail");
                                                                        default:
                                                                            switch (i9) {
                                                                                case 101:
                                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_render_render_parse_error");
                                                                                case 102:
                                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_render_main_template_invalid");
                                                                                case 103:
                                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_render_diff_template_invalid");
                                                                                case 104:
                                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_render_fail_meta_invalid");
                                                                                case 105:
                                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_render_fail_template_parse_error");
                                                                                case 106:
                                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_render_fail_unknown");
                                                                                case 107:
                                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_render_fail_timeout");
                                                                                case 108:
                                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_reder_ad_load_timeout");
                                                                                case 109:
                                                                                    return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_template_load_fail");
                                                                                default:
                                                                                    switch (i9) {
                                                                                        case 60001:
                                                                                            return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_ror_code_show_event_error");
                                                                                        case 60002:
                                                                                            return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_error_code_click_event_error");
                                                                                        default:
                                                                                            return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_error_unknow");
                                                                                    }
                                                                            }
                                                                    }
                                                            }
                                                        }
                                                        return "resource error";
                                                    }
                                                    return "load time out";
                                                }
                                                return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_load_creative_icon_error");
                                            }
                                            return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_load_creative_icon_response_error");
                                        }
                                        return "ugen render error";
                                    }
                                    return "ugen render time_out";
                                }
                                return "dynamic2 render error";
                            }
                            return "dynamic2 render time_out";
                        }
                        return "dynamic1 render error";
                    }
                    return "dynamic1 render time_out";
                }
                return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_lack_android_manifest_configuration");
            }
            return com.bytedance.sdk.component.utils.cz.zp(KVG.zp(), "tt_error_ad_able_false_msg");
        } catch (Throwable unused) {
            return "";
        }
    }
}
