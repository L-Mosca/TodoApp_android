package com.example.todo_app.util

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.todo_app.R

enum class TransitionAnimation {
    TRANSLATE_FROM_RIGHT, TRANSLATE_FROM_DOWN, TRANSLATE_FROM_LEFT, TRANSLATE_FROM_UP, NO_ANIMATION, FADE
}

fun Fragment.hideKeyboard() {
    val inputMethodManager =
        context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    inputMethodManager?.hideSoftInputFromWindow(view?.windowToken, 0)
}

fun Fragment.navigate(
    directions: NavDirections,
    clearBackStack: Boolean? = false,
    animation: TransitionAnimation? = TransitionAnimation.TRANSLATE_FROM_RIGHT
) {
    navigate(directions, animation, null, clearBackStack, null)
}

fun Fragment.navigate(
    directions: NavDirections,
    animation: TransitionAnimation? = TransitionAnimation.TRANSLATE_FROM_RIGHT,
    popUpTo: Int? = null,
    clearBackStack: Boolean? = false,
    sharedElements: Pair<View, String>? = null
) {
    val navController = NavHostFragment.findNavController(this)
    val destinationId = if (clearBackStack == true) navController.graph.id else popUpTo
    val transitionAnimation = if (sharedElements == null) animation else null
    val options = buildOptions(transitionAnimation, clearBackStack, destinationId)
    val extras = sharedElements?.let {
        FragmentNavigatorExtras(it)
    }

    navController.navigate(directions.actionId, directions.arguments, options, extras)
}

fun Fragment.navigate(
    @IdRes resId: Int,
    animation: TransitionAnimation? = TransitionAnimation.TRANSLATE_FROM_RIGHT,
    bundle: Bundle? = null,
    clearBackStack: Boolean? = false
) {
    try {
        val navController = NavHostFragment.findNavController(this)
        val destinationId = if (clearBackStack == true) navController.graph.id else null
        val options = buildOptions(animation, clearBackStack, destinationId)
        navController.navigate(resId, bundle, options)
    } catch (exception: Exception) {
        exception.printStackTrace()
    }
}

fun Fragment.popBackStack() {
    findNavController().popBackStack()
    hideKeyboard()
}

fun Fragment.onBackPressed(action: () -> Unit) {
    val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            action()
        }
    }

    callback.isEnabled
    activity?.onBackPressedDispatcher?.addCallback(this, callback)
}

private fun buildOptions(
    transitionAnimation: TransitionAnimation?, clearBackStack: Boolean?, @IdRes destinationId: Int?
): NavOptions {
    return navOptions {
        anim {
            when (transitionAnimation) {
                TransitionAnimation.TRANSLATE_FROM_RIGHT -> {
                    enter = R.anim.translate_left_enter
                    exit = R.anim.translate_left_exit
                    popEnter = R.anim.translate_right_enter
                    popExit = R.anim.translate_right_exit
                }
                TransitionAnimation.TRANSLATE_FROM_DOWN -> {
                    enter = R.anim.translate_slide_bottom_up
                    exit = R.anim.translate_no_change
                    popEnter = R.anim.translate_no_change
                    popExit = R.anim.translate_slide_bottom_down
                }
                TransitionAnimation.TRANSLATE_FROM_LEFT -> {
                    enter = R.anim.translate_right_enter
                    exit = R.anim.translate_right_exit
                    popEnter = R.anim.translate_left_enter
                    popExit = R.anim.translate_left_exit
                }
                TransitionAnimation.TRANSLATE_FROM_UP -> {
                    enter = R.anim.translate_slide_bottom_down
                    exit = R.anim.translate_no_change
                    popEnter = R.anim.translate_no_change
                    popExit = R.anim.translate_slide_bottom_up
                }
                TransitionAnimation.NO_ANIMATION -> {
                    enter = R.anim.translate_no_change
                    exit = R.anim.translate_no_change
                    popEnter = R.anim.translate_no_change
                    popExit = R.anim.translate_no_change
                }
                TransitionAnimation.FADE -> {
                    enter = R.anim.translate_fade_in
                    exit = R.anim.translate_fade_out
                    popEnter = R.anim.translate_fade_in
                    popExit = R.anim.translate_fade_out
                }
                else -> {
                }
            }
        }

        // To clean the stack below the current fragment,
        // you must set the 'destinationId' = navGraphId and 'inclusive' = true
        destinationId?.let {
            popUpTo(destinationId) {
                inclusive = clearBackStack == true
            }
        }
    }
}