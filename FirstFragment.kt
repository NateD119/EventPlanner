package com.studentnate.week13demo

import android.content.ContentValues
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.studentnate.week13demo.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var db:DbHelper = DbHelper(context)

        binding.calendarView.setOnDateChangeListener { calendarView, i, i2, i3 ->
            binding.dateView.text = "${i2+1}/${i3.toString()}"
        }
        binding.add.setOnClickListener {
            val eventData = ContentValues()
            eventData.put("ETitle", binding.editTitle.text.toString())
            eventData.put("EDetail", binding.editDetail.text.toString())
            eventData.put("EDate", binding.dateView.text.toString())
            var result = db.insertEvent(eventData)
            if (result > 0)
                Toast.makeText(this.context, "Event was created", Toast.LENGTH_LONG).show()
                else
                    Toast.makeText(this.context, "Failed to create", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}




