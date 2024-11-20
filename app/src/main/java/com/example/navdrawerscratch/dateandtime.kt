package com.example.navdrawerscratch
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment

import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*

class dateandtime : Fragment() {

    private lateinit var tvSelectedDate: TextView
    private lateinit var tvSelectedTime: TextView
    private var selectedDate: Long = 0
    private var selectedTime: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_date_time, container, false)

        tvSelectedDate = view.findViewById(R.id.tv_selected_date)
        tvSelectedTime = view.findViewById(R.id.tv_selected_time)

        view.findViewById<Button>(R.id.btn_select_date).setOnClickListener {
            showDatePicker()
        }

        view.findViewById<Button>(R.id.btn_select_time).setOnClickListener {
            showTimePicker()
        }

        view.findViewById<Button>(R.id.btn_set_appointment).setOnClickListener {
            setAppointment()
        }

        return view
    }

    @RequiresApi(Build.VERSION_CODES.CUPCAKE)
    private fun showDatePicker() {
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select Appointment Date")
            .build()

        datePicker.addOnPositiveButtonClickListener { selection ->
            selectedDate = selection
            tvSelectedDate.text = "Selected Date: ${DateFormat.format("dd/MM/yyyy", selectedDate)}"
        }
        datePicker.show(parentFragmentManager, "DATE_PICKER")
    }

    private fun showTimePicker() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        TimePickerDialog(requireContext(), { _, selectedHour, selectedMinute ->
            selectedTime = (selectedHour * 3600000 + selectedMinute * 60000).toLong()
            tvSelectedTime.text = "Selected Time: $selectedHour:$selectedMinute"
        }, hour, minute, true).show()
    }

    private fun setAppointment() {
        if (selectedDate == 0L || selectedTime == 0L) {
            Toast.makeText(requireContext(), "Please select both date and time", Toast.LENGTH_SHORT).show()
            return
        }

        // Create a Calendar instance
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = selectedDate
        calendar.set(Calendar.HOUR_OF_DAY, (selectedTime / 3600000.toInt()).toInt())
        calendar.set(Calendar.MINUTE, (selectedTime % 3600000 / 60000).toInt())
        val appointmentTimeInMillis = calendar.timeInMillis

        // Schedule the notification
        val alarmManager = requireActivity().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(requireContext(), timeReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            requireContext(),
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )


        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, appointmentTimeInMillis, pendingIntent)

        Toast.makeText(requireContext(), "Appointment set for ${DateFormat.format("dd/MM/yyyy HH:mm", appointmentTimeInMillis)}", Toast.LENGTH_SHORT).show()
    }
}