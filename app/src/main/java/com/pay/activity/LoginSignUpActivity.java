package com.pay.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.pay.R;
import com.pay.service.ServiceGenerator;
import com.pay.service.WebService;
import com.pay.utils.UtilKit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginSignUpActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_signup_activity);
    }



  /*  public void callFaqService() {
        WebService webServiceObj;
        UtilKit.showSpinnerDialog(getActivity(), false);
        webServiceObj = ServiceGenerator.createService(WebService.class);
        Call<Model> call = webServiceObj.getService("");
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                UtilKit.dismisssSpinnerDialog();
                try {
                    if (faqmodel.getStatus_code().equalsIgnoreCase(UtilKit.SUCCESSCODE)) {

                    }else{
                        UtilKit.intitializeAlertDialog("",mContext);
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
                UtilKit.dismisssSpinnerDialog();
            }
            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                UtilKit.dismisssSpinnerDialog();
                UtilKit.alertRetrofitExceptionDialog(getActivity(),t);
            }
        });
    }*/

}
