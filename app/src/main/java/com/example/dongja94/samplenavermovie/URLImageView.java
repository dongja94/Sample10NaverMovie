package com.example.dongja94.samplenavermovie;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by dongja94 on 2016-02-05.
 */
public class URLImageView extends ImageView {
    public URLImageView(Context context) {
        super(context);
    }

    public URLImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    ImageRequest mRequest;
    public void setImageURL(String url) {
        if (mRequest != null) {
            mRequest.cancel();
            mRequest = null;
        }
        if (!TextUtils.isEmpty(url)) {
            setImageResource(R.drawable.ic_stub);
            ImageRequest request = new ImageRequest(url);
            mRequest = request;
            NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<Bitmap>() {
                @Override
                public void onSuccess(NetworkRequest<Bitmap> request, Bitmap result) {
                    setImageBitmap(result);
                    mRequest = null;
                }

                @Override
                public void onFailure(NetworkRequest<Bitmap> request, int errorCode, int responseCode, String message, Throwable excepton) {
                    setImageResource(R.drawable.ic_error);
                    mRequest = null;
                }
            });
        } else {
            setImageResource(R.drawable.ic_empty);
        }

    }
}
