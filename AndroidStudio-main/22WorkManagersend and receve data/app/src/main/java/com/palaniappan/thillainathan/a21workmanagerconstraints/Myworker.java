package com.palaniappan.thillainathan.a21workmanagerconstraints;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class Myworker extends Worker {
    public Myworker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }
    Data data =getInputData();
    int countinglimit=data.getInt("max_limit",0);
    @NonNull
    @Override
    public Result doWork() {
        for(int i=0;i<=countinglimit;i++){
            Log.i("Tags","count is:"+i);
        }
        return Result.success();
    }
}
