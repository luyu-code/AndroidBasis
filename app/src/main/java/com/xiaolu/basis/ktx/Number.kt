package com.xiaolu.basis.ktx

import android.content.res.Resources
import android.util.TypedValue

/**
 * dp->px
 */
val Int.dp
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, toFloat(), Resources.getSystem().displayMetrics).toInt()

/**
 * dp->px
 */
val Float.dp
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics)