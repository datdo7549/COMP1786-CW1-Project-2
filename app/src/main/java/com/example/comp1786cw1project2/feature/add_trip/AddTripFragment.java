package com.example.comp1786cw1project2.feature.add_trip;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.comp1786cw1project2.databinding.FragmentAddTripBinding;
import com.example.comp1786cw1project2.feature.base.BaseFragment;

import java.util.Calendar;
import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddTripFragment extends BaseFragment<FragmentAddTripBinding, AddTripViewModel> {
    private AddTripViewModel viewModel;
    public static AddTripFragment newInstance() {
        return new AddTripFragment();
    }

    @Override
    protected AddTripViewModel viewModel() {
        viewModel = new ViewModelProvider(this).get(AddTripViewModel.class);
        return viewModel;
    }

    @Override
    public FragmentAddTripBinding onCreateViewBinding(LayoutInflater inflater) {
        return FragmentAddTripBinding.inflate(inflater);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewBinding.edtDateOfTrip.setOnClickListener(v -> showDatePickerDialog());
        viewBinding.btnAddTrip.setOnClickListener(v -> {
            String tripName = Objects.requireNonNull(viewBinding.edtTripName.getText()).toString();
            String destination = Objects.requireNonNull(viewBinding.edtDestination.getText()).toString();
            String dateOfTrip = Objects.requireNonNull(viewBinding.edtDateOfTrip.getText()).toString();
            String description = Objects.requireNonNull(viewBinding.edtDescription.getText()).toString();
            String requiredRisk;
            if (viewBinding.cbRequiredRisk.isChecked()) {
                requiredRisk = "Yes";
            } else {
                requiredRisk = "No";
            }

            if (tripName.isEmpty() || destination.isEmpty() || dateOfTrip.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill to required field", Toast.LENGTH_SHORT).show();
                return;
            }

            viewModel.saveTrip(tripName, destination, dateOfTrip, description, requiredRisk);
            navigateUp();
        });
    }


    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                (view, year, month, dayOfMonth) -> {
                    // Handle the selected date
                    String selectedDate = year + "/" + (month+1) + "/" + dayOfMonth;
                    viewBinding.edtDateOfTrip.setText(selectedDate);
                },
                // Set the initial date to the current date
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

}