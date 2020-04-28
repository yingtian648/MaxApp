package com.zjhj.maxapp.app_protect;

import android.annotation.SuppressLint;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.zjhj.maxapp.App;
import com.zjhj.maxapp.MainActivity;
import com.zjhj.maxapp.utils.*;

import java.util.List;

/**
 * CreateTime 2020/4/26 09:08
 * Author LiuShiHua
 * Description：APP保护
 */
@SuppressLint("NewApi")
public class JobSchedulerService extends JobService {
    private int jobId = 1001;
    private final String pkName = "com.zjhj.maxapp";
    private final String mianClassName = "com.zjhj.maxapp.MainActivity";
    private final long REPLAY_TIME_LONG = 15 * 1000;//任务重复执行时长
    private JobScheduler jobScheduler;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 在服务启动时，直接将任务推到JobScheduler 的任务队列,然后在设定的时间条件到达时，便会直接吊起我们的服务，走onStartJob()方法
        scheduleJob();
        return START_NOT_STICKY;
    }

    /**
     * 如果返回false的话，系统会自动结束本job；只要Job工作正在执行，系统就会代表应用程序保留一个唤醒锁。
     * 在调用此方法之前获取此唤醒锁，并且直到您调用jobFinished（JobParameters，boolean）或系统调用onStopJob（JobParameters）
     * 以通知正在执行的作业它过早关闭之后才会释放。
     * True表示需要执行，返回True时，作业将保持活动状态，直到系统调用jobFinished或者直到该作业所需的条件不足了
     *
     * @param params
     * @return
     */
    @Override
    public boolean onStartJob(JobParameters params) {
        L.Companion.e("JobSchedulerService onStartJob");
        checkAppAlive();
        jobFinished(params, false);//执行完后不会回调onStopJob(),但是会回调onDestroy()
        return true;
    }

    /**
     * 停止该Job。当JobScheduler发觉该Job条件不满足的时候，或者job被抢占被取消的时候的强制回调。
     * 即如果系统确定你必须在有机会调用jobFinished（JobParameters，boolean）之前必须停止执行作业，则调用此方法。
     *
     * @param params
     * @return
     */
    @Override
    public boolean onStopJob(JobParameters params) {
        L.Companion.d("JobSchedulerService onStopJob");
        checkAppAlive();
        scheduleJob();
        return false;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        L.Companion.d("JobSchedulerService onTaskRemoved");
        checkAppAlive();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        L.Companion.d("JobSchedulerService onDestroy");
    }

    /**
     * Send job to the JobScheduler.
     */
    public void scheduleJob() {
        jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        if (jobScheduler != null) {
            if (jobScheduler.schedule(getJobInfo()) == JobScheduler.RESULT_SUCCESS) {
                L.Companion.d("执行JOB成功");
            }
        }
    }

    //校验APP是否启动
    private void checkAppAlive() {
        if (!Tools.isForeground(App.Companion.getContext(), mianClassName)) {
            L.Companion.e("JobScheduer APP未显示在最前面,启动APP");
            startThisActivity();
        }
        if (!Tools.isAppAlive(App.Companion.getContext(), pkName)) {
            L.Companion.d("JobScheduer APP死了,启动APP");
            startThisActivity();
        }
    }

    /**
     * 获取工作内容
     * 必须有一项
     */
    public JobInfo getJobInfo() {
        JobInfo.Builder builder = new JobInfo.Builder(jobId, new ComponentName(this, JobSchedulerService.class));
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        builder.setPersisted(true);//设备重启之后你的任务是否还要继续执行
        builder.setRequiresCharging(false);// 设置是否充电的条件,默认false
        builder.setRequiresDeviceIdle(false);// 设置手机是否空闲的条件,默认false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.setMinimumLatency(REPLAY_TIME_LONG);//执行间隔时间--周期
        } else {
            builder.setPeriodic(REPLAY_TIME_LONG);//执行间隔时间--周期
        }
        return builder.build();
    }

    /**
     * 启动主界面
     * <p>
     * 下面会报错
     */
    private void startThisActivity() {
        Intent intent = new Intent(App.Companion.getContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        App.Companion.getContext().startActivity(intent);
    }
}
