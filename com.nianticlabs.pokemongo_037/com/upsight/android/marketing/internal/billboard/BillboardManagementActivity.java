package com.upsight.android.marketing.internal.billboard;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.ExploreByTouchHelper;
import android.text.TextUtils;
import com.squareup.otto.Subscribe;
import com.upsight.android.Upsight;
import com.upsight.android.UpsightContext;
import com.upsight.android.UpsightMarketingExtension;
import com.upsight.android.analytics.internal.session.ApplicationStatus;
import com.upsight.android.analytics.internal.session.ApplicationStatus.State;
import com.upsight.android.marketing.C0994R;
import com.upsight.android.marketing.UpsightBillboard.Dimensions;
import com.upsight.android.marketing.UpsightBillboard.PresentationStyle;
import com.upsight.android.marketing.UpsightContentMediator;
import com.upsight.android.marketing.UpsightMarketingComponent;
import com.upsight.android.marketing.internal.content.MarketingContent;
import com.upsight.android.marketing.internal.content.MarketingContent.PendingDialog;
import com.upsight.android.marketing.internal.content.MarketingContent.ScopelessAvailabilityEvent;
import com.upsight.android.marketing.internal.content.MarketingContent.SubcontentAvailabilityEvent;
import com.upsight.android.marketing.internal.content.MarketingContent.SubdialogAvailabilityEvent;
import com.upsight.android.marketing.internal.content.MarketingContentActions.DestroyEvent;
import com.upsight.android.marketing.internal.content.MarketingContentStore;
import com.upsight.android.persistence.UpsightSubscription;
import com.upsight.android.persistence.annotation.Created;
import com.upsight.android.persistence.annotation.Updated;
import java.util.Queue;
import java.util.Set;
import javax.inject.Inject;
import spacemadness.com.lunarconsole.C1562R;

public class BillboardManagementActivity extends Activity {
    static final String INTENT_EXTRA_MARKETING_CONTENT_DIALOG_THEME = "marketingContentDialogTheme";
    static final String INTENT_EXTRA_MARKETING_CONTENT_ID = "marketingContentId";
    static final String INTENT_EXTRA_MARKETING_CONTENT_PREFERRED_STYLE = "marketingContentPreferredStyle";
    private static final String LOG_TAG = "BillboardActivity";
    private static final int STYLE_DIALOG;
    private static final int STYLE_FULLSCREEN = 16974122;
    private Billboard mBillboard;
    private MarketingContent mContent;
    @Inject
    MarketingContentStore mContentStore;
    private UpsightSubscription mDataStoreSubscription;
    private BillboardFragment mFragment;
    private boolean mIsForeground;
    private boolean mShouldAttachOnResume;
    @Inject
    UpsightContext mUpsight;

    /* renamed from: com.upsight.android.marketing.internal.billboard.BillboardManagementActivity.1 */
    static /* synthetic */ class C10041 {
        static final /* synthetic */ int[] f264x10de5d48;

        static {
            f264x10de5d48 = new int[PresentationStyle.values().length];
            try {
                f264x10de5d48[PresentationStyle.Dialog.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f264x10de5d48[PresentationStyle.Fullscreen.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public BillboardManagementActivity() {
        this.mContent = null;
        this.mBillboard = null;
        this.mFragment = null;
        this.mDataStoreSubscription = null;
        this.mIsForeground = false;
        this.mShouldAttachOnResume = false;
    }

    static {
        STYLE_DIALOG = C0994R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_UpsightDialog;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UpsightMarketingExtension extension = (UpsightMarketingExtension) Upsight.createContext(this).getUpsightExtension(UpsightMarketingExtension.EXTENSION_NAME);
        if (extension != null) {
            ((UpsightMarketingComponent) extension.getComponent()).inject(this);
            setContentView(C0994R.layout.upsight_activity_billboard_management);
            String id = getIntent().getStringExtra(INTENT_EXTRA_MARKETING_CONTENT_ID);
            this.mUpsight.getLogger().m205d(LOG_TAG, "onCreate activity=" + this + " marketingContentId=" + id, new Object[STYLE_DIALOG]);
            if (TextUtils.isEmpty(id)) {
                finish();
                return;
            }
            MarketingContent content = (MarketingContent) this.mContentStore.get(id);
            if (content != null) {
                this.mContent = content;
                this.mBillboard = content.getBoundBillboard();
                this.mShouldAttachOnResume = savedInstanceState == null;
            } else {
                finish();
            }
            this.mDataStoreSubscription = this.mUpsight.getDataStore().subscribe(this);
            this.mUpsight.getCoreComponent().bus().register(this);
        }
    }

    protected void onResume() {
        super.onResume();
        this.mIsForeground = true;
        MarketingContent<?> content = this.mContent;
        if (content != null) {
            if (this.mShouldAttachOnResume) {
                this.mShouldAttachOnResume = false;
                if (content.hasPendingDialog()) {
                    attachDialog(content.popPendingDialog());
                } else {
                    attachBillboard(content);
                }
            }
            Queue<Object> eventQueue = content.getEventQueue();
            this.mUpsight.getLogger().m205d(LOG_TAG, "onResume activity=" + this + " eventQueueSize=" + eventQueue.size(), new Object[STYLE_DIALOG]);
            while (!eventQueue.isEmpty()) {
                Object event = eventQueue.poll();
                if (event instanceof SubcontentAvailabilityEvent) {
                    handle((SubcontentAvailabilityEvent) event);
                } else if (event instanceof SubdialogAvailabilityEvent) {
                    handle((SubdialogAvailabilityEvent) event);
                } else if (event instanceof ScopelessAvailabilityEvent) {
                    handle((ScopelessAvailabilityEvent) event);
                } else if (event instanceof DestroyEvent) {
                    handle((DestroyEvent) event);
                }
            }
        }
    }

    protected void onPause() {
        this.mIsForeground = false;
        this.mUpsight.getLogger().m205d(LOG_TAG, "onPause activity=" + this, new Object[STYLE_DIALOG]);
        super.onPause();
    }

    protected void onDestroy() {
        this.mDataStoreSubscription.unsubscribe();
        this.mUpsight.getCoreComponent().bus().unregister(this);
        this.mUpsight.getLogger().m205d(LOG_TAG, "onDestroy activity=" + this, new Object[STYLE_DIALOG]);
        super.onDestroy();
    }

    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof BillboardFragment) {
            this.mFragment = (BillboardFragment) fragment;
        }
    }

    @Subscribe
    public void handleAvailabilityEvent(SubcontentAvailabilityEvent event) {
        this.mUpsight.getLogger().m205d(LOG_TAG, "Received SubcontentAvailabilityEvent activity=" + this + " marketingContentId=" + event.getId(), new Object[STYLE_DIALOG]);
        if (this.mIsForeground) {
            handle(event);
            return;
        }
        MarketingContent<?> content = this.mContent;
        if (content != null) {
            content.getEventQueue().add(event);
        }
    }

    @Subscribe
    public void handleAvailabilityEvent(SubdialogAvailabilityEvent event) {
        this.mUpsight.getLogger().m205d(LOG_TAG, "Received SubdialogAvailabilityEvent activity=" + this + " marketingContentId=" + event.getId(), new Object[STYLE_DIALOG]);
        if (this.mIsForeground) {
            handle(event);
            return;
        }
        MarketingContent<?> content = this.mContent;
        if (content != null) {
            content.getEventQueue().add(event);
        }
    }

    @Subscribe
    public void handleAvailabilityEvent(ScopelessAvailabilityEvent event) {
        this.mUpsight.getLogger().m205d(LOG_TAG, "Received ScopelessAvailabilityEvent activity=" + this + " marketingContentId=" + event.getId(), new Object[STYLE_DIALOG]);
        if (this.mIsForeground) {
            handle(event);
            return;
        }
        MarketingContent<?> content = this.mContent;
        if (content != null) {
            content.getEventQueue().add(event);
        }
    }

    @Subscribe
    public void handleActionEvent(DestroyEvent event) {
        this.mUpsight.getLogger().m205d(LOG_TAG, "Received DestroyEvent activity=" + this + " marketingContentId=" + event.mId, new Object[STYLE_DIALOG]);
        if (this.mIsForeground) {
            handle(event);
            return;
        }
        MarketingContent<?> content = this.mContent;
        if (content != null) {
            content.getEventQueue().add(event);
        }
    }

    @Created
    @Updated
    public void onApplicationStatus(ApplicationStatus appStatus) {
        if (appStatus.getState() == State.BACKGROUND) {
            this.mUpsight.getLogger().m205d(LOG_TAG, "Received application background event activity=" + this, new Object[STYLE_DIALOG]);
            MarketingContent content = this.mContent;
            if (content != null) {
                content.executeActions(MarketingContent.TRIGGER_APP_BACKGROUNDED);
            }
        }
    }

    private void handle(SubcontentAvailabilityEvent event) {
        MarketingContent content = this.mContent;
        if (content != null && event.getId().equals(content.getId())) {
            attachBillboard(content);
        }
    }

    private void handle(SubdialogAvailabilityEvent event) {
        MarketingContent content = this.mContent;
        if (content != null && event.getId().equals(content.getId())) {
            attachDialog(event.getPendingDialog());
        }
    }

    private void handle(ScopelessAvailabilityEvent event) {
        MarketingContent content = this.mContent;
        if (content != null && content.getId().equals(event.getParentId())) {
            Billboard billboard = this.mBillboard;
            if (billboard != null) {
                MarketingContent nextContent = (MarketingContent) this.mContentStore.get(event.getId());
                if (nextContent != null && nextContent.isAvailable()) {
                    UpsightContentMediator<?> mediator = content.getContentMediator();
                    UpsightContentMediator<?> nextMediator = nextContent.getContentMediator();
                    if (mediator != null && nextMediator != null) {
                        this.mContent = nextContent;
                        nextContent.bindBillboard(billboard);
                        billboard.getHandler().onNextView();
                        FragmentManager fragmentManager = getFragmentManager();
                        mediator.hideContent(content, fragmentManager, this.mFragment);
                        nextMediator.displayContent(nextContent, fragmentManager, this.mFragment);
                    }
                }
            }
        }
    }

    private void handle(DestroyEvent event) {
        MarketingContent content = this.mContent;
        if (content != null && content.getId().equals(event.mId)) {
            detachBillboard();
        }
    }

    void attachDialog(PendingDialog pendingDialog) {
        this.mUpsight.getLogger().m205d(LOG_TAG, "Attach dialog activity=" + this + " marketingContentId=" + pendingDialog.mId, new Object[STYLE_DIALOG]);
        Intent intent = getIntent();
        if (intent.hasExtra(INTENT_EXTRA_MARKETING_CONTENT_DIALOG_THEME)) {
            BillboardDialogFragment.newInstance(pendingDialog, intent.getIntExtra(INTENT_EXTRA_MARKETING_CONTENT_DIALOG_THEME, ExploreByTouchHelper.INVALID_ID)).show(getFragmentManager(), null);
        } else {
            BillboardDialogFragment.newInstance(pendingDialog).show(getFragmentManager(), null);
        }
    }

    void attachBillboard(MarketingContent content) {
        this.mUpsight.getLogger().m205d(LOG_TAG, "Attach billboard activity=" + this + " marketingContentId=" + content.getId(), new Object[STYLE_DIALOG]);
        UpsightContentMediator<?> mediator = content.getContentMediator();
        if (mediator != null) {
            int style;
            PresentationStyle preferredStyle = (PresentationStyle) getIntent().getSerializableExtra(INTENT_EXTRA_MARKETING_CONTENT_PREFERRED_STYLE);
            if (preferredStyle == null || preferredStyle.equals(PresentationStyle.None)) {
                preferredStyle = mediator.getPresentationStyle(content);
            }
            Set<Dimensions> fragmentDimensions = null;
            switch (C10041.f264x10de5d48[preferredStyle.ordinal()]) {
                case C1562R.styleable.LoadingImageView_imageAspectRatio /*1*/:
                    style = STYLE_DIALOG;
                    fragmentDimensions = mediator.getDimensions(content);
                    break;
                default:
                    style = STYLE_FULLSCREEN;
                    break;
            }
            this.mFragment = BillboardFragment.newInstance(this, fragmentDimensions);
            this.mFragment.setStyle(1, style);
            this.mFragment.setCancelable(false);
            mediator.displayContent(content, getFragmentManager(), this.mFragment);
        }
    }

    void detachBillboard() {
        Billboard billboard = this.mBillboard;
        if (billboard != null) {
            this.mUpsight.getLogger().m205d(LOG_TAG, "Detach billboard activity=" + this + " scope=" + billboard.getScope(), new Object[STYLE_DIALOG]);
            DialogFragment fragment = this.mFragment;
            if (fragment != null && fragment.isAdded()) {
                fragment.dismiss();
            }
            this.mContent = null;
            this.mBillboard = null;
            this.mFragment = null;
            finish();
            billboard.getHandler().onDetach();
        }
    }
}
