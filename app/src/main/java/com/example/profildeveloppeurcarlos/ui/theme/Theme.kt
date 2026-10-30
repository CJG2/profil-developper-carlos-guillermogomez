package com.example.profildeveloppeurcarlos.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = ForestGreen,
    onPrimary = White,
    primaryContainer = ForestGreen,
    onPrimaryContainer = White,
    secondary = DustGrey,
    onSecondary = Gunmetal,
    secondaryContainer = DustGrey,
    onSecondaryContainer = Gunmetal,
    background = Gunmetal,
    onBackground = SoftLinen,
    surface = Gunmetal,
    onSurface = SoftLinen,
    surfaceVariant = SoftLinen,
    onSurfaceVariant = Gunmetal
)

private val LightColorScheme = lightColorScheme(
    primary = ForestGreen,
    onPrimary = White,
    primaryContainer = Gunmetal,
    onPrimaryContainer = SoftLinen,
    secondary = Gunmetal,
    onSecondary = SoftLinen,
    secondaryContainer = DustGrey,
    onSecondaryContainer = Gunmetal,
    background = DustGrey,
    onBackground = Gunmetal,
    surface = DustGrey,
    onSurface = Gunmetal,
    surfaceVariant = SoftLinen,
    onSurfaceVariant = Gunmetal
)

@Composable
fun ProfilDeveloppeurCarlosTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Désactivé pour utiliser notre palette au lieu des couleurs du fond d'écran (Android 12+)
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
