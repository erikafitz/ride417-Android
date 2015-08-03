package com.ride417.ride417;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class RequestRide extends Activity implements View.OnClickListener{

    Button submit;
    EditText etPickUp, etDropOff, etPassengers, etPhoneNumber, etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_ride);

        etPickUp = (EditText) findViewById(R.id.pickup);
        etDropOff = (EditText) findViewById(R.id.dropoff);
        etPassengers = (EditText) findViewById(R.id.numPassengers);
        etName = (EditText) findViewById(R.id.name);
        etPhoneNumber = (EditText) findViewById(R.id.phoneNumber);

        submit = (Button) findViewById(R.id.submitButton);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.submitButton:
                String pickup = etPickUp.getText().toString();
                String dropoff = etDropOff.getText().toString();
                String passengers = etPassengers.getText().toString();
                String name = etName.getText().toString();
                String phoneNumber = etPhoneNumber.getText().toString();
                // TODO: Verify phone number

                if (pickup.isEmpty() || dropoff.isEmpty() || passengers.isEmpty() || name.isEmpty() || phoneNumber.isEmpty()) {
                    //alert
                    showAlert("All fields are required");
                } else if (!isNumeric(passengers)) {
                    //alert
                    showAlert("Please enter a valid number (ex. 4 or 17)");
                } else{ //if everything is okay...
                    ParseUser user = ParseUser.getCurrentUser();

                    ParseObject rideRequest = new ParseObject("RideRequest");
                    rideRequest.put("pickupLoc", pickup);
                    rideRequest.put("dropoffLoc", dropoff);
                    rideRequest.put("numPeople", Integer.parseInt(passengers));
                    rideRequest.put("progress", "unassigned");
                    rideRequest.put("name", name);
                    rideRequest.put("number", phoneNumber);

                    rideRequest.saveInBackground();

                    ParseACL parseACL = new ParseACL();
                    parseACL.setPublicReadAccess(true);
                    parseACL.setPublicWriteAccess(true);
                    rideRequest.setACL(parseACL);

                    // Just a placeholder until we make the next activity
                    Toast toast = Toast.makeText(this.getApplicationContext(), "Submitted!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
        }
    }

    public void showAlert(String s){
        new AlertDialog.Builder(this)
                .setTitle("Alert")
                .setMessage(s)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public static boolean isNumeric(String str) // Checks to see if it's a whole number
    {
        try
        {
            int i = Integer.parseInt(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}
