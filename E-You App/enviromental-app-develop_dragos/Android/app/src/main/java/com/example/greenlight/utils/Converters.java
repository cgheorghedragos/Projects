package com.example.greenlight.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.example.greenlight.R;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class Converters {
    public static BitmapDescriptor BitmapFromVector(Context context, int vectorResId, int division) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        int iconSize = 0;
        if (width > height) {
            iconSize = height / division;
        } else {
            iconSize = width / division;
        }
        // below line is use to generate a drawable.
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);

        vectorDrawable.setBounds(0, 0, iconSize, iconSize);

        Bitmap bitmap = Bitmap.createBitmap(iconSize, iconSize, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);

        vectorDrawable.draw(canvas);

        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    public static int getMarkerIcon(String markerType){

        if(markerType.equals(MarkersType.ANIMALS)){
            return R.drawable.ic_injured_animal;
        }

        if(markerType.equals(MarkersType.BIOLOGICAL_HAZARD)){
            return R.drawable.ic_biological_hazard;
        }

        if(markerType.equals(MarkersType.DEFORESTING)){
            return R.drawable.ic_deforesting;
        }

        if(markerType.equals(MarkersType.FIRE)){
            return R.drawable.ic_building_fire;
        }

        if(markerType.equals(MarkersType.FISHING)){
            return R.drawable.ic_water_trash;
        }

        if(markerType.equals(MarkersType.FLOOD)){
            return R.drawable.ic_flood;
        }

        if(markerType.equals(MarkersType.GARBAGE)){
            return R.drawable.ic_trash;
        }

        if(markerType.equals(MarkersType.RADIOACTIVITY)){
            return R.drawable.ic_radiocativity;
        }

        return R.drawable.ic_building_fire;
    }
}

