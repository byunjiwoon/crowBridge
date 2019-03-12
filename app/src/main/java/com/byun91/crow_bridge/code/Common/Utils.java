package com.byun91.crow_bridge.code.Common;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

public class Utils {

    public static String getPath(Uri uri, Context context){ // 갤러리에서 인텐트로 받은 이미지 주소를 한번에 가져오는게 불가능하므로 따로 정의해주는 메소드
        String [] proj = {MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader = new CursorLoader(context, uri, proj, null, null, null);

        Cursor cursor = cursorLoader.loadInBackground();
        int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();
        return cursor.getString(index);
    }

}
