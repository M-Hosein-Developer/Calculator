package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.Toast
import com.example.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private var operator : Char = '+'

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onNumberClicked()
        onOperatorClcked()

    }

    private fun onOperatorClcked() {
        binding.btnBealave.setOnClickListener {

            if (binding.txtExpression.text.isNotEmpty()){
                val text = binding.txtExpression.text.last()
                //روش اول چک کردن
                if (text == '+' || text == '-' || text == '*' || text == '/'){

                }else{
                    appendText("+")
                }
            }
        }

        binding.btnMenha.setOnClickListener {

            if (binding.txtExpression.text.isNotEmpty()) {
                val text = binding.txtExpression.text.last()
                //روش دوم چک کردن
                if (text != '+' && text != '-' && text != '*' && text != '/') {
                    appendText("-")
                }
            }
        }

        binding.btnzarb.setOnClickListener {

            if (binding.txtExpression.text.isNotEmpty()) {
                val text = binding.txtExpression.text.last()
                //روش دوم چک کردن
                if (text != '+' && text != '-' && text != '*' && text != '/') {
                    appendText("*")
                }
            }
        }

        binding.btndiv.setOnClickListener {

            if (binding.txtExpression.text.isNotEmpty()) {
                val text = binding.txtExpression.text.last()
                //روش دوم چک کردن
                if (text != '+' && text != '-' && text != '*' && text != '/') {
                    appendText("/")
                }
            }
        }

        binding.btnOpentParentheses.setOnClickListener {
            appendText("(")
        }

        binding.btnCloseParentheses.setOnClickListener {
            appendText(")")
        }

        binding.btnAc.setOnClickListener {
            binding.txtExpression.text = ""
            binding.txtJavab.text = ""
        }

        binding.btnClear.setOnClickListener {
            val oldText = binding.txtExpression.text.toString()
            if (oldText.isNotEmpty()) {
                binding.txtExpression.text = oldText.substring(0, oldText.length - 1)
            }
        }

        binding.btnmosavi.setOnClickListener {

            try {
                val expression = ExpressionBuilder(binding.txtExpression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble()){
                    binding.txtJavab.text = longResult.toString()
                }else{
                    binding.txtJavab.text = result.toString()
                }
            }catch (e : Exception){
                binding.txtExpression.text = ""
                binding.txtJavab.text = ""
                Toast.makeText(this, "Error!!!", Toast.LENGTH_LONG).show()
            }


        }


    }

    private fun onNumberClicked() {
        binding.btn0.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()) {
                appendText("0")
            }
        }

        binding.btn1.setOnClickListener {
            appendText("1")
        }

        binding.btn2.setOnClickListener {
            appendText("2")
        }

        binding.btn3.setOnClickListener {
            appendText("3")
        }

        binding.btn4.setOnClickListener {
            appendText("4")
        }

        binding.btn5.setOnClickListener {
            appendText("5")
        }

        binding.btn6.setOnClickListener {
            appendText("6")
        }

        binding.btn7.setOnClickListener {
            appendText("7")
        }

        binding.btn8.setOnClickListener {
            appendText("8")
        }

        binding.btn9.setOnClickListener {
            appendText("9")
        }

        binding.btnDot.setOnClickListener {
            if (binding.txtExpression.text.isEmpty() || binding.txtJavab.text.isNotEmpty()) {
                appendText("0.")
            } else if (!binding.txtExpression.text.contains(".")) {
                appendText(".")
            }

        }
    }

    private fun appendText(text: String) {

        if (binding.txtJavab.text.isNotEmpty()){
            binding.txtExpression.text = ""
        }
        binding.txtJavab.text = ""
        binding.txtExpression.append(text)

        val viewTree:ViewTreeObserver = binding.horizentalScrollViewTxtPExpression.viewTreeObserver
        viewTree.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener{
            override fun onGlobalLayout() {
                binding.horizentalScrollViewTxtPExpression.viewTreeObserver.removeOnGlobalLayoutListener(this)
                binding.horizentalScrollViewTxtPExpression.scrollTo(binding.txtExpression.width , 0)
            }

        })

    }

}