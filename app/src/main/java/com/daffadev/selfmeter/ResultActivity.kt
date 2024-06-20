package com.daffadev.selfmeter

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity


class ResultActivity : AppCompatActivity() {

    private lateinit var llBarChart: LinearLayout
    private lateinit var btnBackToMain: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_page)

        val predictedLabel = intent.getStringExtra("predicted_label")
        val predictedPercentages = intent.getSerializableExtra("predicted_percentages") as HashMap<String, Double>

        val tvResultLabel: TextView = findViewById(R.id.tv_result_label)
        llBarChart = findViewById(R.id.llBarChart)
        btnBackToMain = findViewById(R.id.btnBackToMain)

        tvResultLabel.text = "Here's your result: $predictedLabel"


        val maxPercentage = predictedPercentages.values.maxOrNull() ?: 0.0


        for ((condition, percentage) in predictedPercentages) {
            val barContainer = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    1f
                )
                setPadding(0, 0, resources.getDimensionPixelSize(R.dimen.bar_margin_end), 0)
                gravity = android.view.Gravity.BOTTOM
            }

            val percentageLabel = TextView(this).apply {
                text = "${String.format("%.2f", percentage)}%"
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                gravity = android.view.Gravity.CENTER
            }

            val barView = TextView(this).apply {
                setBackgroundColor(resources.getColor(R.color.barColor))
                val barHeight = (percentage / maxPercentage * MAX_BAR_HEIGHT).toInt()
                val barWidth = resources.getDimensionPixelSize(R.dimen.bar_width)
                val params = LinearLayout.LayoutParams(barWidth, barHeight)
                layoutParams = params
            }

            val conditionLabel = TextView(this).apply {
                text = condition
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                gravity = android.view.Gravity.TOP
            }

            barContainer.addView(percentageLabel)
            barContainer.addView(barView)
            barContainer.addView(conditionLabel)

            llBarChart.addView(barContainer)
        }


        btnBackToMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }
    }

    companion object {
        private const val MAX_BAR_HEIGHT = 300
    }
}