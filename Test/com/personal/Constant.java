package com.personal;

public class Constant {

    /**
     * 移动中音5元铃音盒600902014000001151	1
     * 移动中音3元铃音盒600902014000000706	1
     * 移动中音3元铃音盒600927014000000706	1
     * 北京移动3元计费	1
     * 甘肃移动个性化彩铃5元计费	1
     * 安徽移动个彩化彩铃5元计费	1
     * 联通商户炫铃5元VAC	1
     * 联通互联网5元VAC计费	1
     * 联通中音5元VAC计费"	1
     * 重庆联通商务炫铃5元计费	1
     * 安徽联通5元ISMP计费	1
     * 联通5元VAC首月免	1
     * 联通中音3元VAC计费	1
     * 电信中音5元ISMP	1
     * 电信中音5元铃音盒810099998013	1
     * 电信中音3元铃音盒810099998004	1
     * 音乐名片5元专用音乐盒	1
     * 安徽电信商户彩铃5元铃音盒120099990005	1
     * 江苏电信商户彩铃8元铃音盒8209001391	1
     * 音乐名片10元专用音乐盒	2
     * 移动中音5元铃音盒600927014000001151 2
     * 音乐名片20元专用音乐盒	3
     */
    public static String[] oneList = {"移动中音5元铃音盒600902014000001151", "移动中音3元铃音盒600902014000000706",
            "移动中音3元铃音盒600927014000000706", "北京移动3元计费", "甘肃移动个性化彩铃5元计费", "安徽移动个彩化彩铃5元计费",
            "联通商户炫铃5元VAC", "联通互联网5元VAC计费", "联通中音5元VAC计费", "重庆联通商务炫铃5元计费", "安徽联通5元ISMP计费",
            "联通5元VAC首月免费", "联通中音3元VAC计费", "电信中音5元ISMP", "电信中音5元铃音盒810099998013", "电信中音3元铃音盒810099998004",
            "音乐名片5元专用音乐盒", "安徽电信商户彩铃5元铃音盒120099990005", "江苏电信商户彩铃8元铃音盒8209001391"};
    public static String[] twoList = {"移动中音5元铃音盒600927014000001151", "音乐名片10元专用音乐盒"};

    /**
     * 套餐类型
     */
    public static String CARD_TEN_MUSIC = "音乐名片10元专用音乐盒";

    public static String MOVE_FIVE_MUSIC = "移动中音5元铃音盒600927014000001151";

    public static String CARD_TWENTY_MUSIC = "音乐名片20元专用音乐盒";

    public static String AB_MUSIC = "联通能力平台商户炫铃5元VAC";


    /**
     * 运营商
     */
    public static String MOVE = "移动";

    public static String UNICOM = "联通";

    public static String TELECOM = "电信";

    /**
     * 订购状态
     */
    public static String ONLINE = "在网";

    public static String OFFONLINE = "退订";

    /**
     * 是否为空
     */
    public static String NULL = "null";


    /**
     * FTP地址
     */
    public static String HOST = "192.168.58.129";
    public static Integer PORT = 21;
    public static String ACCOUNT = "kaili";
    public static String PASSWORD = "kaili";

    public static String ORDER = "Order";
    public static String SH_ORDER = "SH_Order";


    /**
     * 转化前FTP地址
     */
    public static String FRONT_URL = "/orderText/";
    /**
     * 转化后临时地址
     */
    public static String MOME_URL = "D:\\test\\back\\";
    /**
     * 转化后上传FTP地址
     */
    public static String BACK_URL = "/orderBackText/";
}
