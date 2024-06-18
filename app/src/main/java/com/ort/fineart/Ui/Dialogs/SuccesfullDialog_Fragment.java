package com.ort.fineart.Ui.Dialogs;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.ort.fineart.databinding.FragmentSuccesfullBinding;


public class SuccesfullDialog_Fragment extends DialogFragment {
    FragmentSuccesfullBinding binding;
    Sucess_Dialog_Interface sucess_dialog_interface;
    Drawable IconImage;
    String Message;
    String SubMessage;
    String TaskName;
    String ButtonText;
    boolean TaskDoneStatus;

    public SuccesfullDialog_Fragment() {
        // Required empty public constructor
    }
    public SuccesfullDialog_Fragment(Sucess_Dialog_Interface sucess_dialog, Drawable Icon,String Mge,String ButtonTxt,String Task,boolean taskDoneStatus) {
        // Required empty public constructor
        //taskDoneStatus = T then Task Done Sucessfully other Not


        sucess_dialog_interface=sucess_dialog;
        IconImage=Icon;
        Message=Mge;
        TaskName=Task;
        ButtonText=ButtonTxt;
        TaskDoneStatus=taskDoneStatus;


    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSuccesfullBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setCancelable(false);


        binding.IconImg.setImageDrawable(IconImage);
        binding.MesgTxt.setText(Message);

        binding.ConformBtnTxt.setText(ButtonText);

        binding.CloseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sucess_dialog_interface.doneSuccessfully(TaskName,TaskDoneStatus,true);
                dismiss();
            }
        });




        binding.ConformBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sucess_dialog_interface.doneSuccessfully(TaskName,TaskDoneStatus,true);
                dismiss();
            }
        });



    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog customDialog = super.onCreateDialog(savedInstanceState);
        Window window = customDialog.getWindow();


        WindowManager.LayoutParams layoutParams = customDialog.getWindow().getAttributes();

        // Customize the attributes
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT; // Set the width
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT; // Set the height
        layoutParams.gravity = Gravity.CENTER; // Set the gravity (position) of the dialog

        layoutParams.dimAmount = 0.0f; // Adjust dim amount if needed
        customDialog.getWindow().setAttributes(layoutParams);
        customDialog.setCancelable(false);









        if (window != null) {
            // Set the background to be transparent
            window.setBackgroundDrawableResource(android.R.color.transparent);
        }

        return customDialog;
    }
}