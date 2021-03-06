package com.mopub.volley;

import android.os.Handler;
import com.voxelbusters.nativeplugins.defines.Keys.Twitter;
import java.util.concurrent.Executor;

public class ExecutorDelivery implements ResponseDelivery {
    private final Executor mResponsePoster;

    /* renamed from: com.mopub.volley.ExecutorDelivery.1 */
    class C07291 implements Executor {
        final /* synthetic */ Handler val$handler;

        C07291(Handler handler) {
            this.val$handler = handler;
        }

        public void execute(Runnable command) {
            this.val$handler.post(command);
        }
    }

    private class ResponseDeliveryRunnable implements Runnable {
        private final Request mRequest;
        private final Response mResponse;
        private final Runnable mRunnable;

        public ResponseDeliveryRunnable(Request request, Response response, Runnable runnable) {
            this.mRequest = request;
            this.mResponse = response;
            this.mRunnable = runnable;
        }

        public void run() {
            if (this.mRequest.isCanceled()) {
                this.mRequest.finish("canceled-at-delivery");
                return;
            }
            if (this.mResponse.isSuccess()) {
                this.mRequest.deliverResponse(this.mResponse.result);
            } else {
                this.mRequest.deliverError(this.mResponse.error);
            }
            if (this.mResponse.intermediate) {
                this.mRequest.addMarker("intermediate-response");
            } else {
                this.mRequest.finish(Twitter.COMPOSER_ACTION_DONE);
            }
            if (this.mRunnable != null) {
                this.mRunnable.run();
            }
        }
    }

    public ExecutorDelivery(Handler handler) {
        this.mResponsePoster = new C07291(handler);
    }

    public ExecutorDelivery(Executor executor) {
        this.mResponsePoster = executor;
    }

    public void postResponse(Request<?> request, Response<?> response) {
        postResponse(request, response, null);
    }

    public void postResponse(Request<?> request, Response<?> response, Runnable runnable) {
        request.markDelivered();
        request.addMarker("post-response");
        this.mResponsePoster.execute(new ResponseDeliveryRunnable(request, response, runnable));
    }

    public void postError(Request<?> request, VolleyError error) {
        request.addMarker("post-error");
        this.mResponsePoster.execute(new ResponseDeliveryRunnable(request, Response.error(error), null));
    }
}
