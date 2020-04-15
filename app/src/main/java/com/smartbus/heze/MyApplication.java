package com.smartbus.heze;

import com.taobao.sophix.SophixManager;

import org.litepal.LitePalApplication;

/**
 * @author: Allen.
 * @date: 2018/7/25
 * @description: application
 */

public class MyApplication extends LitePalApplication {
    public static MyApplication myApp;
    public static final int TIMEOUT = 60;

    @Override
    public void onCreate() {
        super.onCreate();
        SophixManager.getInstance().queryAndLoadNewPatch();
        myApp = this;
        com.igexin.sdk.PushManager.getInstance().initialize(getApplicationContext(), DemoPushService.class);
        // DemoIntentService 为第三方自定义的推送服务事件接收类
        com.igexin.sdk.PushManager.getInstance().registerPushIntentService(getApplicationContext(), DemoIntentService.class);
    }


}
