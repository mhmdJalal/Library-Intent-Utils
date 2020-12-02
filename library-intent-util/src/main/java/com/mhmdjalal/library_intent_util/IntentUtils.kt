package com.mhmdjalal.library_intent_util
/*
 * Copyright 2020 Muhamad Jalaludin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import org.jetbrains.anko.AnkoException
import java.io.Serializable

/*
* Created by Muhamad Jalaludin @2020
*
* */
object IntentUtil {

    /*
    * Usage intent with passing data
    *
    * val params = arrayListOf<Pair<String, Any?>>()
    * params.add(Pair("key", "value"))
    *
    * startActivityForResult<ForumDetailActivity>(params) {
    *     if (it.resultCode == RESULT_OK) {
    *         // result success
    *     } else {
    *         // result failed
    *     }
    * }
    * */

    /*
    * startActivityForResult - run on activity
    *
    * without data intent passed
    * */
    @JvmStatic
    inline fun <reified T: Activity> ComponentActivity.startActivityForResult(params: List<Pair<String, Any?>>, crossinline result: (ActivityResult) -> Unit) {
        val intent = createIntent(this, T::class.java, params)

        val launcher: ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {
            result(it)
        }
        launcher.launch(intent)
    }

    /*
    * startActivityForResult - run on activity
    *
    * without data intent passed
    * */
    @JvmStatic
    inline fun <reified T: Activity> ComponentActivity.startActivityForResult(crossinline result: (ActivityResult) -> Unit) {
        this.run {
            val intent = Intent(this, T::class.java)
            val launcher: ActivityResultLauncher<Intent> = registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()) {
                result(it)
            }
            launcher.launch(intent)
        }
    }

    /*
    * startActivityForResult - run on fragment
    *
    * without data intent passed
    * */
    @JvmStatic
    inline fun <reified T: Activity> Fragment.startActivityForResult(crossinline result: (ActivityResult) -> Unit) {
        this.requireActivity().run {
            val intent = Intent(this, T::class.java)
            val launcher: ActivityResultLauncher<Intent> = registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()) {
                result(it)
            }
            launcher.launch(intent)
        }
    }

    /*
    * startActivityForResult - run on fragment
    *
    * with data intent passed
    * */
    @JvmStatic
    inline fun <reified T: Activity> Fragment.startActivityForResult(params: List<Pair<String, Any?>>, crossinline result: (ActivityResult) -> Unit) {
        this.requireActivity().run {
            val intent = createIntent(this, T::class.java, params)

            val launcher: ActivityResultLauncher<Intent> = registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()) {
                result(it)
            }
            launcher.launch(intent)
        }
    }

    @JvmStatic
    fun <T> createIntent(ctx: Context, clazz: Class<out T>, params: List<Pair<String, Any?>>): Intent {
        val intent = Intent(ctx, clazz)
        if (params.isNotEmpty()) fillIntentArguments(intent, params)
        return intent
    }

    @JvmStatic
    private fun fillIntentArguments(intent: Intent, params: List<Pair<String, Any?>>) {
        params.forEach {
            when (val value = it.second) {
                null -> intent.putExtra(it.first, null as Serializable?)
                is Int -> intent.putExtra(it.first, value)
                is Long -> intent.putExtra(it.first, value)
                is CharSequence -> intent.putExtra(it.first, value)
                is String -> intent.putExtra(it.first, value)
                is Float -> intent.putExtra(it.first, value)
                is Double -> intent.putExtra(it.first, value)
                is Char -> intent.putExtra(it.first, value)
                is Short -> intent.putExtra(it.first, value)
                is Boolean -> intent.putExtra(it.first, value)
                is Serializable -> intent.putExtra(it.first, value)
                is Bundle -> intent.putExtra(it.first, value)
                is Parcelable -> intent.putExtra(it.first, value)
                is Array<*> -> when {
                    value.isArrayOf<CharSequence>() -> intent.putExtra(it.first, value)
                    value.isArrayOf<String>() -> intent.putExtra(it.first, value)
                    value.isArrayOf<Parcelable>() -> intent.putExtra(it.first, value)
                    else -> throw AnkoException("Intent extra ${it.first} has wrong type ${value.javaClass.name}")
                }
                is IntArray -> intent.putExtra(it.first, value)
                is LongArray -> intent.putExtra(it.first, value)
                is FloatArray -> intent.putExtra(it.first, value)
                is DoubleArray -> intent.putExtra(it.first, value)
                is CharArray -> intent.putExtra(it.first, value)
                is ShortArray -> intent.putExtra(it.first, value)
                is BooleanArray -> intent.putExtra(it.first, value)
                else -> throw AnkoException("Intent extra ${it.first} has wrong type ${value.javaClass.name}")
            }
            return@forEach
        }
    }
}