package first.application.by.bentsappfrontend.customViews

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import first.application.by.bentsappfrontend.R
import first.application.by.bentsappfrontend.databinding.ActivityCustomStepperBinding

class CustomStepper(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private var binding: ActivityCustomStepperBinding = ActivityCustomStepperBinding.inflate(
        LayoutInflater.from(context), this, true
    )
    private var stepsNumbers = 0
    var currentStep = 0

    fun startStepper(steps: Int, currentStep: Int) {
        stepsNumbers = steps
        updateSteps(currentStep)
    }

    fun updateSteps(steppedSteps: Int) {
        currentStep = steppedSteps
        binding.linearLayoutStepped.removeAllViews()
        for (i in 1..stepsNumbers) {
            val step = LinearLayout(context)

            when (i) {
                1 -> {
                    when {
                        i < steppedSteps -> {
                            step.setBackgroundResource(R.drawable.custom_stepper_start)
                        }
                        steppedSteps == 1 -> {
                            step.setBackgroundResource(R.drawable.custom_stepper_first)
                        }
                        else -> {
                            step.setBackgroundResource(R.drawable.custom_desactived_stepper_start)
                        }
                    }
                }
                stepsNumbers -> {
                    step.setBackgroundResource(if (i == steppedSteps)
                        R.drawable.custom_stepper_end else R.drawable.custom_desactived_stepper_end)
                }
                else -> {
                    when {
                        i < steppedSteps -> {
                            step.setBackgroundResource(R.drawable.custom_stepper_stepped_bg)
                        }
                        i == steppedSteps -> {
                            step.setBackgroundResource(R.drawable.custom_stepper_end)
                        }
                        else -> {
                            step.setBackgroundResource(R.drawable.custom_desactived_stepper_stepped_bg)
                        }
                    }
                }
            }

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.weight = 1f

            step.layoutParams = params
            binding.linearLayoutStepped.addView(step)
        }
    }

}