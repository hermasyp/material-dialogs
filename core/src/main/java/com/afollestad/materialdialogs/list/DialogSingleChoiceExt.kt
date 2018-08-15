/*
 * Licensed under Apache-2.0
 *
 * Designed an developed by Aidan Follestad (afollestad)
 */

@file:Suppress("unused")

package com.afollestad.materialdialogs.list

import android.support.annotation.ArrayRes
import android.support.annotation.CheckResult
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.WhichButton.POSITIVE
import com.afollestad.materialdialogs.actions.setActionButtonEnabled
import com.afollestad.materialdialogs.internal.list.SingleChoiceDialogAdapter
import com.afollestad.materialdialogs.utilext.assertOneSet
import com.afollestad.materialdialogs.utilext.getStringArray

/**
 * @param res The string array resource to populate the list with.
 * @param items The literal string array to populate the list with.
 * @param initialSelection The initially selected item's index.
 * @param waitForPositiveButton When true, the [selection] listener won't be called until
 *    the positive action button is pressed. Defaults to true if the dialog has buttons.
 * @param selection A listener invoked when an item in the list is selected.
 */
@CheckResult
fun MaterialDialog.listItemsSingleChoice(
  @ArrayRes res: Int? = null,
  items: Array<String>? = null,
  initialSelection: Int = -1,
  waitForPositiveButton: Boolean = true,
  selection: SingleChoiceListener = null
): MaterialDialog {
  assertOneSet(res, items)
  val array = items ?: getStringArray(res)
  val adapter = getListAdapter()

  if (adapter is SingleChoiceDialogAdapter) {
    adapter.replaceItems(array, selection)
    return this
  }

  setActionButtonEnabled(POSITIVE, initialSelection < 0)
  return customListAdapter(
      SingleChoiceDialogAdapter(
          dialog = this,
          items = array,
          initialSelection = initialSelection,
          waitForActionButton = waitForPositiveButton,
          selection = selection
      )
  )
}
