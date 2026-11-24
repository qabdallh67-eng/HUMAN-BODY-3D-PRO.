package com.qabdallh.qureoassistant

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.*

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val title = TextView(this).apply { text = "QUREO AI Assistant"; textSize = 26f }
        val intro = TextView(this).apply {
            text = "مساعد للمذاكرة يقرأ السؤال الظاهر ويساعدك على فهمه."
            textSize = 17f
        }
        val result = TextView(this).apply {
            text = "النص المقروء سيظهر هنا."
            textSize = 16f
            setTextIsSelectable(true)
        }
        val permission = Button(this).apply {
            text = "تفعيل قارئ الشاشة"
            setOnClickListener { startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)) }
        }
        val read = Button(this).apply {
            text = "قراءة نص الشاشة"
            setOnClickListener {
                val current = ScreenReaderService.latestVisibleText
                result.text = if (current.isBlank()) "لا يوجد نص بعد. افتح صفحة التدريب ثم حاول مرة أخرى." else current
            }
        }
        val note = TextView(this).apply {
            text = "نسخة أولية للتدريب والفهم. لا تعتمد تقييمات أو ترسل إجابات تلقائيًا."
            textSize = 14f
        }
        val content = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(24, 32, 24, 24)
            addView(title); addView(intro); addView(permission); addView(read); addView(result); addView(note)
        }
        setContentView(ScrollView(this).apply { addView(content) })
    }
}
