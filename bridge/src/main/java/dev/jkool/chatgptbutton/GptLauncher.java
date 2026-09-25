package dev.jkool.chatgptbutton;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;

final class GptLauncher {
    private static final ComponentName CHATGPT_VOICE = new ComponentName(
            "com.openai.chatgpt",
            "com.openai.voice.assistant.AssistantActivity"
    );

    static boolean launch(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            ActivityInfo info = pm.getActivityInfo(CHATGPT_VOICE, 0);
            if (!info.exported || info.permission != null) return false;

            Intent intent = new Intent()
                    .setComponent(CHATGPT_VOICE)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            return true;
        } catch (Throwable ignored) {
            return false;
        }
    }
}
