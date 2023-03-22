package com.neobrahma.portfolio.presentation

import android.content.Context
import android.content.res.Resources
import androidx.core.content.ContextCompat
import com.neobrahma.portfolio.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Utils @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun drawableFromResourcesByName(resourceName: String): Int {
        return try {
            context.resources.getIdentifier(
                resourceName,
                "drawable",
                context.packageName
            )
        } catch (e: Resources.NotFoundException) {
            R.drawable.logo_default
        }
    }

    fun getColor(colorId : Int) : Int {
        return ContextCompat.getColor(context, colorId)
    }

}