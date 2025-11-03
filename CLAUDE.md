# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

ZWidget is an Android home screen widget app called "Ze-Ultra-Widget for Android Tidiness & Travel". It provides travelers and location-aware users with:

1. Main location time with weather (temperature and emoji)
2. Sub-selectable list of location times (click-through for weather descriptions)
3. In-widget currency converter (local ↔ home currency using xe.com)
4. Bitcoin-SV current trading price
5. Quick access buttons to Google Translate, Maps & Lens

## Tech Stack

- **Language**: Kotlin
- **UI Framework**: Android Jetpack Compose for Glance (widgets)
- **Networking**: Retrofit for API calls
- **Background Work**: WorkManager for periodic updates

## Development Setup

### Building and Running
- `./gradlew build` - Build the project
- `./gradlew test` - Run unit tests
- `./gradlew connectedAndroidTest` - Run instrumented tests
- `./gradlew installDebug` - Install debug APK on connected device
- `./gradlew assembleRelease` - Build release APK

### Testing Individual Components
- Run single test class: `./gradlew test --tests com.zwidget.app.ClassName`
- Run instrumented tests: `./gradlew connectedAndroidTest`

## Project Architecture

### Package Structure
```
com.zwidget.app/
├── data/
│   ├── api/           - Retrofit API interfaces (WeatherApi, CurrencyApi, BitcoinApi)
│   ├── models/        - Data models (WeatherData, CurrencyData, BitcoinData, WidgetState)
│   ├── repository/    - Repository layer for API calls
│   └── preferences/   - DataStore preferences management
├── di/                - Dependency injection (NetworkModule)
├── util/              - Utility classes (WeatherUtil, TimeUtil)
├── widget/            - Glance widget implementation
│   ├── ZWidget.kt              - Main widget UI with Compose for Glance
│   ├── ZWidgetReceiver.kt      - Widget receiver
│   ├── WidgetActions.kt        - Click actions for widget buttons
│   ├── WidgetStateManager.kt   - Widget state management
│   └── WidgetUpdater.kt        - Widget data refresh logic
├── worker/            - WorkManager background tasks
└── ZWidgetApplication.kt - Application class

### Key Components

**Widget System**
- Uses Jetpack Compose for Glance to build declarative widget UI
- `ZWidget.kt` contains all UI components (main location, sub-locations, currency, bitcoin, shortcuts)
- `WidgetStateManager` manages cached widget state
- `WidgetUpdater` handles fetching data from all APIs and updating widget
- Updates every 30 minutes via `WidgetUpdateWorker`

**API Integration**
- Weather: Google Weather API (requires Google Cloud API key with Weather API enabled)
  - 10,000 free calls per month
  - Advanced neural weather models from Google DeepMind
  - Get API key at: https://developers.google.com/maps/documentation/weather/get-api-key
- Currency: exchangerate-api.com (free alternative to xe.com)
- Bitcoin-SV: CoinGecko API (free, no key required)

**Data Flow**
1. WorkManager triggers `WidgetUpdateWorker` every 30 minutes
2. Worker calls `WidgetUpdater.updateWidget()`
3. Updater fetches data from all repositories in parallel
4. Updates `WidgetStateManager` with new data
5. Calls `ZWidget().updateAll()` to refresh all widget instances

**Preferences**
- Uses DataStore for preferences (main location, currencies, API keys, etc.)
- Default locations: London (main), New York, Tokyo, Sydney (sub-locations)
- Default currencies: USD (home), EUR (local)

### Important Implementation Details

**Weather Emoji Mapping**
- Weather descriptions from Google Weather API are mapped to emojis in `WeatherUtil.getWeatherEmojiFromDescription()`
- Uses text matching on weather descriptions (e.g., "rain", "snow", "clear", "cloudy")
- Supports thunderstorm, rain, drizzle, snow, ice, fog, clear, clouds, wind, etc.

**Timezone Handling**
- Uses Java `TimeZone` API to calculate times for different locations
- `TimeUtil` provides formatting for time and date in specific timezones

**Widget Actions**
- Location click: Triggers refresh (can be extended to show weather details)
- Translate button: Opens Google Translate web
- Maps button: Opens Google Maps app or web fallback
- Lens button: Opens Google Lens app or Play Store if not installed

### Configuration Requirements

Before the widget works fully, users need to:
1. Obtain Google Cloud API key with Weather API enabled:
   - Go to https://console.cloud.google.com/
   - Create a new project or select existing one
   - Enable the Weather API
   - Create credentials (API key)
   - Get 10,000 free calls per month (currently in Preview with $0 charges)
2. Store API key in DataStore preferences (currently no UI for this - would need settings activity)
3. Optionally configure preferred locations and currencies

The app includes 60+ predefined city coordinates in `LocationUtil.kt` for easy weather lookup.

### Known Limitations

- No configuration UI yet - all settings via DataStore defaults
- Currency converter display is read-only (shows rate, not interactive input)
- Weather API key must be added programmatically or via future settings screen
