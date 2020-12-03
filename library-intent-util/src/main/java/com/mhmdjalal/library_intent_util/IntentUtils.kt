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

@file:Suppress("NOTHING_TO_INLINE", "unused")
package com.mhmdjalal.library_intent_util

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


/**
 *  startActivityForResult - run on activity
 *  with/without passing data intent
 *
 *  @param params: the data set to be sent with the intent (type List<Pair<key, value>>)
 *  @param result: the return value of an intent
 * */
inline fun <reified T: Activity> ComponentActivity.startActivityForResult(params: List<Pair<String, Any?>> = arrayListOf(), crossinline result: (ActivityResult) -> Unit) {
    val intent = createIntent(this, T::class.java, params)

    val launcher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {
        result(it)
    }
    launcher.launch(intent)
}


/**
 *   startActivityForResult - run on fragment
 *   with/without passing data intent
 *
 *   @param params: the data set to be sent with the intent (type List<Pair<key, value>>)
 *   @param result: the return value of an intent
 * */
inline fun <reified T: Activity> Fragment.startActivityForResult(params: List<Pair<String, Any?>> = arrayListOf(), crossinline result: (ActivityResult) -> Unit) {
    this.requireActivity().run {
        val intent = createIntent(this, T::class.java, params)

        val launcher: ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {
            result(it)
        }
        launcher.launch(intent)
    }
}


/**
 *  function to create an intent
 *
 *  @param context: the context needed to execute the intent
 *  @param clazz: the class you want to target with an intent
 *  @param params: the data set to be sent with the intent (type List<Pair<key, value>>)
 * */
fun <T> createIntent(context: Context, clazz: Class<out T>, params: List<Pair<String, Any?>>): Intent =
    Intent(context, clazz).apply {
        if (params.isNotEmpty()) fillIntentArguments(this, params)
    }


/**
 *  function to pass arguments to the intent
 *
 *  @param intent: initialized intent to populate arguments
 *  @param params: the data set to be sent with the intent (type List<Pair<key, value>>)
 *
 * */
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


/**
 *  startActivity for fragment
 *  with/without passing data intent
 *
 *  @param params: the data set to be sent with the intent (type List<Pair<key, value>>)
 *  @param T: the activity class you want to target with an intent
 * */
inline fun <reified T: Activity> Fragment.startActivityFromFragment(params: List<Pair<String, Any?>> = arrayListOf()) = this.context?.let {
    startActivity(createIntent(it, T::class.java, params))
}


/**
 *  startActivity for fragment
 *  with/without passing data intent
 *
 *  @param params: the data set to be sent with the intent (type arrayOf<Pair<String, Any?>>)
 *  @param T: the activity class you want to target with an intent
 * */
inline fun <reified T: Activity> Fragment.startActivityFromFragment(vararg params: Pair<String, Any?>) = this.context?.let {
    startActivity(createIntent(it, T::class.java, params))
}


/**
 *  startActivity for activity
 *  with/without passing data intent
 *
 *  @param params: the data set to be sent with the intent (type List<Pair<key, value>>)
 *  @param T: the activity class you want to target with an intent
 * */
inline fun <reified T: Activity> ComponentActivity.startActivity(params: List<Pair<String, Any?>> = arrayListOf()) =
    startActivity(createIntent(this, T::class.java, params))


/**
 *  startActivity for activity
 *  with/without passing data intent
 *
 *  @param params: the data set to be sent with the intent (type arrayOf<Pair<String, Any?>>)
 *  @param T: the activity class you want to target with an intent
 * */
inline fun <reified T: Activity> ComponentActivity.startActivity(vararg params: Pair<String, Any?>) =
    startActivity(createIntent(this, T::class.java, params))


/**
 *  function to create an intent
 *
 *  @param context: the context needed to execute the intent
 *  @param clazz: the class you want to target with an intent
 *  @param params: the data set to be sent with the intent
 * */
fun <T> createIntent(context: Context, clazz: Class<out T>, params: Array<out Pair<String, Any?>>): Intent =
    Intent(context, clazz).apply {
        if (params.isNotEmpty()) fillIntentArguments(this, params)
    }


/**
 *  function to pass arguments to the intent
 *
 *  @param intent: initialized intent to populate arguments
 *  @param params: the data set to be sent with the intent
 *
 * */
private fun fillIntentArguments(intent: Intent, params: Array<out Pair<String, Any?>>) {
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