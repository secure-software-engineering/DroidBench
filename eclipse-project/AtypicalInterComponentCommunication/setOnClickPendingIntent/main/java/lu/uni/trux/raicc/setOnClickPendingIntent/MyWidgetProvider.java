package lu.uni.trux.raicc.setOnClickPendingIntent;

import java.util.Random;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.RemoteViews;


public class MyWidgetProvider extends AppWidgetProvider {

    private static final String ACTION_CLICK = "ACTION_CLICK";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {

        ComponentName thisWidget = new ComponentName(context,
                MyWidgetProvider.class);
        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
        for (int widgetId : allWidgetIds) {
            int number = (new Random().nextInt(100));

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.widget_layout);
            remoteViews.setTextViewText(R.id.update, String.valueOf(number));
            System.out.println("TEST");

            String imei = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
            Intent i = new Intent(context, TargetActivity.class);
            i.putExtra("DroidBench", imei);
            PendingIntent pi = PendingIntent.getActivity(context, 0, i, 0);
            remoteViews.setOnClickPendingIntent(R.id.update, pi);
            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
    }
}