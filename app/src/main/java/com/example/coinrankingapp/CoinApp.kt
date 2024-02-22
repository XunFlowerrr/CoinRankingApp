package com.example.coinrankingapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * This is the main Application class for the CoinApp.
 * It is annotated with @HiltAndroidApp, which triggers Hilt's code generation and
 * adds an application-level dependency container.
 *
 * This container is parent to all other containers in the app, and can provide
 * dependencies to any other containers.
 *
 * @see Application
 */
@HiltAndroidApp
class CoinApp : Application()