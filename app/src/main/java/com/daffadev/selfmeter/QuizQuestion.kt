package com.daffadev.selfmeter
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuizQuestion : AppCompatActivity() {
    private lateinit var tvQuestion: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var tvProgress: TextView
    private lateinit var etAge: EditText
    private lateinit var tvOptionOne: TextView
    private lateinit var tvOptionTwo: TextView
    private lateinit var btnNext: Button
    private lateinit var btnSubmit: Button

    private val inputData = mutableListOf<Int>()
    private var currentQuestion = 0
    private val questions = listOf("How old are you ?",
        "Do you often feel nervous ?",
        "Do you experience panic attacks ?",
        "Do you find yourself breathing rapidly when stressed ?",
        "Do you sweat excessively without physical exertion ?",
        "Do you have trouble concentrating on tasks ?",
        "Do you have difficulty sleeping ?",
        "Do you find it hard to keep up with work or school ?",
        "Do you often feel hopeless ?",
        "Do you get angry easily?",
        "Do you tend to overreact in stressful situations?",
        "Have you noticed any changes in your eating habits?",
        "Do you have thoughts of suicide ?",
        "Do you often feel tired even after resting ?",
        "Do you have a close friend you can talk to ?",
        "Do you spend a lot of time on social media ?",
        "Have you experienced any unexpected weight gain ?",
        "Do you consider yourself an introvert ?",
        "Do stressful memories often pop up in your mind ?",
        "Do you frequently have nightmares ?",
        "Do you avoid people or activities you used to enjoy ?",
        "Do you often have negative thoughts about yourself ?",
        "Do you have trouble concentrating ?",
        "Do you often blame yourself for things that go wrong ?",
        "Do you experience hallucinations ?",
        "Do you engage in repetitive behaviors ?",
        "Is there any connection between your mood and changes in weather or certain seasons ?",
        "Do you ever feel an unusual increase in energy?",)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        tvQuestion = findViewById(R.id.tv_question)
        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tv_progress)
        etAge = findViewById(R.id.et_age)
        tvOptionOne = findViewById(R.id.tv_option_one)
        tvOptionTwo = findViewById(R.id.tv_option_two)
        btnNext = findViewById(R.id.btnNext)
        btnSubmit = findViewById(R.id.btnSubmit)

        btnNext.setOnClickListener { handleNext() }
        tvOptionOne.setOnClickListener { handleOptionSelected(1) }
        tvOptionTwo.setOnClickListener { handleOptionSelected(0) }
        btnSubmit.setOnClickListener { submitData() }

        showNextQuestion()
    }

    private fun handleNext() {
        val age = etAge.text.toString().toIntOrNull()
        if (currentQuestion == 0 && age != null) {
            inputData.add(age)
            currentQuestion++
            showNextQuestion()
            btnNext.visibility = View.GONE
            btnSubmit.visibility = View.GONE

        } else if (currentQuestion > 0) {
            currentQuestion++
            showNextQuestion()
        } else {
            Toast.makeText(this, "Please enter a valid age", Toast.LENGTH_SHORT).show()
        }
    }



    private fun handleOptionSelected(value: Int) {
        inputData.add(value)
        currentQuestion++
        showNextQuestion()
    }

    private fun showNextQuestion() {
        if (currentQuestion < questions.size) {
            tvQuestion.text = questions[currentQuestion]
            progressBar.progress = currentQuestion + 1
            tvProgress.text = "${currentQuestion + 1}/28"

            if (currentQuestion == 0) {
                etAge.visibility = View.VISIBLE
                tvOptionOne.visibility = View.GONE
                tvOptionTwo.visibility = View.GONE
            } else {
                tvOptionOne.visibility = View.VISIBLE
                tvOptionTwo.visibility = View.VISIBLE
                etAge.visibility = View.GONE
            }
        } else {
            btnNext.visibility = View.GONE
            btnSubmit.visibility = View.VISIBLE
        }
    }

    private fun submitData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://cloud-run-github-actions-xvjb7vqyxq-et.a.run.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)
        val inputData = InputData(inputData)

        apiService.getPrediction(inputData).enqueue(object : Callback<PredictedResponse> {
            override fun onResponse(call: Call<PredictedResponse>, response: Response<PredictedResponse>) {
                if (response.isSuccessful) {
                    val predictedResponse = response.body()
                    val intent = Intent(this@QuizQuestion, ResultActivity::class.java).apply {
                        putExtra("predicted_label", predictedResponse?.predicted_label)
                        putExtra("predicted_percentages", HashMap(predictedResponse?.predicted_percentages))
                    }
                    startActivity(intent)
                } else {
                    Toast.makeText(this@QuizQuestion, "Failed to get prediction", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<PredictedResponse>, t: Throwable) {
                Toast.makeText(this@QuizQuestion, "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}
