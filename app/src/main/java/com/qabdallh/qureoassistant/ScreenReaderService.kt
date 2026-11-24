package com.qabdallh.qureoassistant

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo

class ScreenReaderService : AccessibilityService() {
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        val root = rootInActiveWindow ?: return
        val buffer = StringBuilder()
        collectText(root, buffer)
        latestVisibleText = buffer.toString().trim()
        root.recycle()
    }

    private fun collectText(node: AccessibilityNodeInfo?, buffer: StringBuilder) {
        if (node == null) return
        val value = node.text?.toString()?.trim().orEmpty()
        if (value.isNotEmpty()) {
            if (buffer.isNotEmpty()) buffer.append('\n')
            buffer.append(value)
        }
        for (index in 0 until node.childCount) collectText(node.getChild(index), buffer)
    }

    override fun onInterrupt() = Unit

    companion object {
        @Volatile var latestVisibleText: String = ""
    }
}
