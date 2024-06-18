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

import com.ort.fineart.databinding.FragmentConformationBinding;


public class ConformationDialog_Fragment extends DialogFragment {
    FragmentConformationBinding binding;
    Conformation_Dialog_Interface conformation_dialog_interface;
    Drawable IconImage;
    String Message;
    String SubMessage;
    String TaskName;
    String ButtonText;
    private Object[] mArguments;

    public ConformationDialog_Fragment() {
        // Required empty public constructor
    }

    public ConformationDialog_Fragment(Conformation_Dialog_Interface conformation_interface, Drawable Icon,String Mge,String SubMge,String ButtonTxt,String Task,Object... arguments) {
        // Required empty public constructor
        conformation_dialog_interface=conformation_interface;
        IconImage=Icon;
        Message=Mge;
        SubMessage=SubMge;
        TaskName=Task;
        ButtonText=ButtonTxt;
        this.mArguments = arguments;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentConformationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        setCancelable(false);


        binding.IconImg.setImageDrawable(IconImage);
        binding.MesgTxt.setText(Message);
        binding.subTxt.setText(SubMessage);
        binding.ConformBtnTxt.setText(ButtonText);

        binding.CloseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        binding.closeTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        binding.ConformBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conformation_dialog_interface.GetConformation(TaskName,true,mArguments);
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