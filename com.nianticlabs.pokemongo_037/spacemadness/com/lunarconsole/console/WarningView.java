package spacemadness.com.lunarconsole.console;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import spacemadness.com.lunarconsole.C1562R;
import spacemadness.com.lunarconsole.core.Destroyable;
import spacemadness.com.lunarconsole.debug.Log;
import spacemadness.com.lunarconsole.debug.Tags;

public class WarningView extends FrameLayout implements Destroyable {
    private Listener listener;
    private TextView messageText;

    public interface Listener {
        void onDetailsClick(WarningView warningView);

        void onDismissClick(WarningView warningView);
    }

    /* renamed from: spacemadness.com.lunarconsole.console.WarningView.1 */
    class C15851 implements OnTouchListener {
        C15851() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            return true;
        }
    }

    /* renamed from: spacemadness.com.lunarconsole.console.WarningView.2 */
    class C15862 implements OnClickListener {
        C15862() {
        }

        public void onClick(View v) {
            WarningView.this.notifyDismiss();
        }
    }

    /* renamed from: spacemadness.com.lunarconsole.console.WarningView.3 */
    class C15873 implements OnClickListener {
        C15873() {
        }

        public void onClick(View v) {
            WarningView.this.notifyDetails();
        }
    }

    public WarningView(Context context) {
        super(context);
        init(context);
    }

    public WarningView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @TargetApi(11)
    public WarningView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(21)
    public WarningView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(C1562R.layout.lunar_layout_warning, null, false);
        setupUI(view);
        addView(view, new LayoutParams(-1, -2, 81));
    }

    private void setupUI(View view) {
        this.messageText = (TextView) view.findViewById(C1562R.id.lunar_console_text_warning_message);
        view.setOnTouchListener(new C15851());
        setOnClickListener(view, C1562R.id.lunar_console_button_dismiss, new C15862());
        setOnClickListener(view, C1562R.id.lunar_console_button_details, new C15873());
    }

    private void setOnClickListener(View root, int id, OnClickListener listener) {
        root.findViewById(id).setOnClickListener(listener);
    }

    public void destroy() {
        Log.m885d(Tags.WARNING_VIEW, "Destroy warning", new Object[0]);
        this.listener = null;
    }

    private void notifyDismiss() {
        if (this.listener != null) {
            try {
                this.listener.onDismissClick(this);
            } catch (Exception e) {
                Log.m886e("Error while notifying listener", new Object[0]);
            }
        }
    }

    private void notifyDetails() {
        if (this.listener != null) {
            try {
                this.listener.onDetailsClick(this);
            } catch (Exception e) {
                Log.m886e("Error while notifying listener", new Object[0]);
            }
        }
    }

    public void setMessage(String message) {
        this.messageText.setText(message);
    }

    public Listener getListener() {
        return this.listener;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }
}
