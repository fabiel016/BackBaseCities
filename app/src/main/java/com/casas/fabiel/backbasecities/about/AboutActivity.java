package com.casas.fabiel.backbasecities.about;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.casas.fabiel.backbasecities.MainActivity;
import com.casas.fabiel.backbasecities.R;

public class AboutActivity extends AppCompatActivity implements About.View {

    private TextView companyName;
    private TextView companyAddress;
    private TextView companyPostal;
    private TextView companyCity;
    private TextView aboutInfo;
    private Button nextButton;
    private ProgressBar progressBar;
    private android.view.View errorView;
    private android.view.View infoContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        AboutPresenterImpl aboutPresenter = new AboutPresenterImpl(this, this);
        companyName = findViewById(R.id.companyName);
        companyAddress = findViewById(R.id.companyAdress);
        companyPostal = findViewById(R.id.companypostal);
        companyCity = findViewById(R.id.companyCity);
        aboutInfo = findViewById(R.id.aboutInfo);
        progressBar = findViewById(R.id.progressBar);
        errorView = findViewById(R.id.errorView);
        infoContainer = findViewById(R.id.infoContainer);
        nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));
        aboutPresenter.getAboutInfo();
        requestPermissions();
    }

    @Override
    public void setCompanyName(String companyNameString) {
        infoContainer.setVisibility(android.view.View.VISIBLE);
        companyName.setText(companyNameString);
    }

    @Override
    public void setCompanyAddress(String companyAddressString) {
        companyAddress.setText(companyAddressString);
    }

    @Override
    public void setCompanyPostalCode(String postalCodeString) {
        companyPostal.setText(postalCodeString);
    }

    @Override
    public void setCompanyCity(String companyCityString) {
        companyCity.setText(companyCityString);
    }

    @Override
    public void setAboutInfo(String infoString) {
        aboutInfo.setText(infoString);
    }

    @Override
    public void showError() {
        errorView.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(android.view.View.GONE);
    }

    private void requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                }, 1);
            }
        }
    }
}
