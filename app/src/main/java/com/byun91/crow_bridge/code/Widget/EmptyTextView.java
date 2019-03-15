package com.byun91.crow_bridge.code.Widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Html;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by dwpark on 2017. 7. 5..
 */

public class EmptyTextView extends TextView {
    public EmptyTextView(Context context) {
        super(context);
    }

    public EmptyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmptyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public EmptyTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setText(CharSequence s, BufferType type) {
        if(s == null ||
                s.toString()==null
                || s.equals("null")
                || s.toString().startsWith("null ~") || s.toString().startsWith("null~")
                || s.toString().endsWith("~ null") || s.toString().endsWith("~null")){
            s=" ";
        }

        if(s.equals(" ")){
            this.setVisibility(View.GONE);
        }

        s = Html.fromHtml((String) s);

        super.setText(s, type);
    }
}
