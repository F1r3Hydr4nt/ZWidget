package com.zwidget.app.widget

import android.content.Context
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.*
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.*
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import androidx.compose.ui.graphics.Color
import com.zwidget.app.data.models.LocationTimeData
import com.zwidget.app.util.TimeUtil
import com.zwidget.app.util.WeatherUtil

class ZWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            ZWidgetContent(context)
        }
    }
}

@Composable
fun ZWidgetContent(context: Context) {
    val widgetState = WidgetStateManager.getState(context)

    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .padding(16.dp)
            .background(ColorProvider(Color(0xFFF5F5F5)))
            .cornerRadius(16.dp),
        verticalAlignment = Alignment.Top,
        horizontalAlignment = Alignment.Start
    ) {
        // Main location time with weather
        MainLocationSection(widgetState)

        Spacer(modifier = GlanceModifier.height(12.dp))

        // Sub-locations
        SubLocationsSection(widgetState)

        Spacer(modifier = GlanceModifier.height(12.dp))

        // Currency converter
        CurrencyConverterSection(widgetState)

        Spacer(modifier = GlanceModifier.height(12.dp))

        // Bitcoin-SV price
        BitcoinSection(widgetState)

        Spacer(modifier = GlanceModifier.height(12.dp))

        // Shortcut buttons
        ShortcutButtonsSection(context)
    }
}

@Composable
fun MainLocationSection(state: com.zwidget.app.data.models.WidgetState) {
    val mainLocation = state.mainLocation ?: LocationTimeData("London", "Europe/London", true)
    val currentTime = TimeUtil.getCurrentTimeForTimeZone(mainLocation.timeZoneId)
    val currentDate = TimeUtil.getDateForTimeZone(mainLocation.timeZoneId)
    val weather = state.weatherData

    Row(
        modifier = GlanceModifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = GlanceModifier.defaultWeight()) {
            Text(
                text = mainLocation.locationName,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = currentTime,
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = currentDate,
                style = TextStyle(fontSize = 14.sp)
            )
        }

        if (weather != null) {
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = weather.emoji,
                    style = TextStyle(fontSize = 40.sp)
                )
                Text(
                    text = WeatherUtil.formatTemperature(weather.temperature),
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }
    }
}

@Composable
fun SubLocationsSection(state: com.zwidget.app.data.models.WidgetState) {
    if (state.subLocations.isEmpty()) return

    Column(modifier = GlanceModifier.fillMaxWidth()) {
        Text(
            text = "Other Locations",
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        )
        Spacer(modifier = GlanceModifier.height(4.dp))

        state.subLocations.forEach { location ->
            val time = TimeUtil.getCurrentTimeForTimeZone(location.timeZoneId)
            Row(
                modifier = GlanceModifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable(actionRunCallback<LocationClickAction>()),
                horizontalAlignment = Alignment.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${location.locationName}: ",
                    style = TextStyle(fontSize = 14.sp)
                )
                Text(
                    text = time,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }
    }
}

@Composable
fun CurrencyConverterSection(state: com.zwidget.app.data.models.WidgetState) {
    Column(modifier = GlanceModifier.fillMaxWidth()) {
        Text(
            text = "Currency Converter",
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        )
        Spacer(modifier = GlanceModifier.height(4.dp))

        if (state.currencyRate != null) {
            Text(
                text = "1 USD = ${String.format("%.2f", state.currencyRate)} EUR",
                style = TextStyle(fontSize = 14.sp)
            )
        } else {
            Text(
                text = "Loading...",
                style = TextStyle(fontSize = 12.sp)
            )
        }
    }
}

@Composable
fun BitcoinSection(state: com.zwidget.app.data.models.WidgetState) {
    Row(
        modifier = GlanceModifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "BSV: ",
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        )
        if (state.bitcoinPrice != null) {
            Text(
                text = "$${String.format("%.2f", state.bitcoinPrice)}",
                style = TextStyle(fontSize = 14.sp)
            )
        } else {
            Text(
                text = "Loading...",
                style = TextStyle(fontSize = 12.sp)
            )
        }
    }
}

@Composable
fun ShortcutButtonsSection(context: Context) {
    Row(
        modifier = GlanceModifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "🌐",
            style = TextStyle(fontSize = 24.sp),
            modifier = GlanceModifier
                .padding(horizontal = 8.dp)
                .clickable(actionRunCallback<TranslateClickAction>())
        )
        Text(
            text = "🗺️",
            style = TextStyle(fontSize = 24.sp),
            modifier = GlanceModifier
                .padding(horizontal = 8.dp)
                .clickable(actionRunCallback<MapsClickAction>())
        )
        Text(
            text = "📷",
            style = TextStyle(fontSize = 24.sp),
            modifier = GlanceModifier
                .padding(horizontal = 8.dp)
                .clickable(actionRunCallback<LensClickAction>())
        )
    }
}

class ZWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = ZWidget()
}
