package com.upsight.android.marketing.internal.content;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.widget.ImageView;
import com.google.gson.JsonObject;
import com.upsight.android.Upsight;
import com.upsight.android.marketing.C0994R;
import com.upsight.android.marketing.UpsightBillboard.Dimensions;
import com.upsight.android.marketing.UpsightBillboard.Dimensions.LayoutOrientation;
import com.upsight.android.marketing.UpsightBillboard.PresentationStyle;
import com.upsight.android.marketing.UpsightContentMediator;
import com.upsight.android.marketing.internal.billboard.BillboardFragment;
import com.upsight.android.marketing.internal.billboard.BillboardFragment.BackPressHandler;
import com.upsight.android.marketing.internal.content.MarketingContentActions.MarketingContentActionContext;
import com.upsight.android.marketing.internal.content.MarketingContentModel.Presentation;
import com.upsight.android.marketing.internal.content.MarketingContentModel.Presentation.DialogLayout;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public final class DefaultContentMediator extends UpsightContentMediator<MarketingContentModel> {
    public static final String CONTENT_PROVIDER = "upsight";

    /* renamed from: com.upsight.android.marketing.internal.content.DefaultContentMediator.1 */
    class C10051 implements OnClickListener {
        final /* synthetic */ MarketingContent val$content;

        C10051(MarketingContent marketingContent) {
            this.val$content = marketingContent;
        }

        public void onClick(View view) {
            this.val$content.executeActions(MarketingContent.TRIGGER_CONTENT_DISMISSED);
        }
    }

    /* renamed from: com.upsight.android.marketing.internal.content.DefaultContentMediator.2 */
    class C10062 implements BackPressHandler {
        private boolean mIsDismissed;
        final /* synthetic */ MarketingContent val$content;

        C10062(MarketingContent marketingContent) {
            this.val$content = marketingContent;
            this.mIsDismissed = false;
        }

        public boolean onBackPress() {
            if (!this.mIsDismissed) {
                this.val$content.executeActions(MarketingContent.TRIGGER_CONTENT_DISMISSED);
                this.mIsDismissed = true;
            }
            return true;
        }
    }

    DefaultContentMediator() {
    }

    public String getContentProvider() {
        return CONTENT_PROVIDER;
    }

    public MarketingContentModel buildContentModel(MarketingContent<MarketingContentModel> marketingContent, MarketingContentActionContext actionContext, JsonObject model) {
        MarketingContentModel modelObject = null;
        try {
            modelObject = MarketingContentModel.from(model, actionContext.mGson);
        } catch (IOException e) {
            actionContext.mLogger.m207e(Upsight.LOG_TAG, "Failed to parse content model", e);
        }
        return modelObject;
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public View buildContentView(MarketingContent<MarketingContentModel> content, MarketingContentActionContext actionContext) {
        View contentView = LayoutInflater.from(actionContext.mUpsight).inflate(C0994R.layout.upsight_marketing_content_view, null);
        ((ImageView) contentView.findViewById(C0994R.id.upsight_marketing_content_view_close_button)).setOnClickListener(new C10051(content));
        WebView webView = (WebView) contentView.findViewById(C0994R.id.upsight_marketing_content_view_web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(actionContext.mContentTemplateWebViewClientFactory.create(content));
        webView.loadUrl(((MarketingContentModel) content.getContentModel()).getTemplateUrl());
        return contentView;
    }

    public void displayContent(MarketingContent<MarketingContentModel> content, FragmentManager fragmentManager, BillboardFragment fragment) {
        LayoutParams params = new LayoutParams(-1, -1);
        fragment.getContentViewContainer().addView(content.getContentView(), params);
        fragment.setBackPressHandler(new C10062(content));
        if (!fragment.isAdded()) {
            fragment.show(fragmentManager, null);
        }
        content.executeActions(MarketingContent.TRIGGER_CONTENT_DISPLAYED);
    }

    public void hideContent(MarketingContent<MarketingContentModel> marketingContent, FragmentManager fragmentManager, BillboardFragment fragment) {
        fragment.getContentViewContainer().removeAllViews();
    }

    public PresentationStyle getPresentationStyle(MarketingContent<MarketingContentModel> content) {
        String transition = ((MarketingContentModel) content.getContentModel()).getPresentationStyle();
        if (Presentation.STYLE_DIALOG.equals(transition)) {
            return PresentationStyle.Dialog;
        }
        if (Presentation.STYLE_FULLSCREEN.equals(transition)) {
            return PresentationStyle.Fullscreen;
        }
        return PresentationStyle.None;
    }

    public Set<Dimensions> getDimensions(MarketingContent<MarketingContentModel> content) {
        Set<Dimensions> dimensions = new HashSet();
        DialogLayout layouts = ((MarketingContentModel) content.getContentModel()).getDialogLayouts();
        if (layouts != null) {
            if (layouts.portrait != null && layouts.portrait.f266w > 0 && layouts.portrait.f265h > 0) {
                dimensions.add(new Dimensions(LayoutOrientation.Portrait, layouts.portrait.f266w, layouts.portrait.f265h));
            }
            if (layouts.landscape != null && layouts.landscape.f266w > 0 && layouts.landscape.f265h > 0) {
                dimensions.add(new Dimensions(LayoutOrientation.Landscape, layouts.landscape.f266w, layouts.landscape.f265h));
            }
        }
        return dimensions;
    }
}
