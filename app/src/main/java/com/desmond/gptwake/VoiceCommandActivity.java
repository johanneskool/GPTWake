package com.desmond.gptwake;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

/**
 * Entry point for Bluetooth headset voice-command buttons.
 *
 * Android dispatches ACTION_VOICE_COMMAND as an Activity intent. Because this activity is started
 * by the system in direct response to a hardware/headset action, it gets a visible/TOP window even
 * under keyguard. We use that moment to hand off to ChatGPT's exported AssistantActivity.
 */
public final class VoiceCommandActivity extends Activity {

    private final Handler ui = new Handler(Looper.getMainLooper());
    private boolean launched;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setShowWhenLocked(true);
        setTurnScreenOn(false);
        overridePendingTransition(0, 0);
        L.i("VOICE_COMMAND_CREATE action=" + getIntent().getAction());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (launched) return;
        launched = true;

        // Let this transparent activity become TOP before starting ChatGPT. This mirrors the
        // lock-screen launch strategy already used by GPTWake's ShimActivity.
        ui.postDelayed(() -> {
            boolean ok = GptLauncher.launch(this);
            L.i("VOICE_COMMAND_LAUNCH_RESULT=" + ok);
            finish();
            overridePendingTransition(0, 0);
        }, 150);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        launched = false;
    }

    @Override
    protected void onDestroy() {
        ui.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
