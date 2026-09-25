package dev.jkool.chatgptbutton;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public final class VoiceCommandActivity extends Activity {
    private final Handler handler = new Handler(Looper.getMainLooper());
    private boolean launched;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setShowWhenLocked(true);
        setTurnScreenOn(false);
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (launched) return;
        launched = true;

        // Give this system-started transparent activity a brief TOP/visible moment
        // under keyguard before handing off to ChatGPT Voice.
        handler.postDelayed(() -> {
            GptLauncher.launch(this);
            finish();
            overridePendingTransition(0, 0);
        }, 150);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
