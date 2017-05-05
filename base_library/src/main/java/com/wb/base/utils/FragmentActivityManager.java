package com.wb.base.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.widget.Toast;

import com.wb.base.BaseFragmentActivity;

import java.util.Stack;

public class FragmentActivityManager {
    private static Stack<BaseFragmentActivity> activityStack;
    private static FragmentActivityManager instance;

    private FragmentActivityManager()
    {
    }

    /**
     * 单实例 , UI无需考虑多线程同步问题
     */
    public static FragmentActivityManager getAppManager()
    {
        if (instance == null)
        {
            instance = new FragmentActivityManager();
        }
        return instance;
    }

    /**
     * 添加Activity到栈
     */
    public void addActivity(BaseFragmentActivity activity)
    {
        if (activityStack == null)
        {
            activityStack = new Stack<BaseFragmentActivity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（栈顶Activity）
     */
    public BaseFragmentActivity currentActivity()
    {
        if (activityStack == null || activityStack.isEmpty())
        {
            return null;
        }
        BaseFragmentActivity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 获取当前Activity（栈顶Activity） 没有找到则返回null
     */
    public BaseFragmentActivity findActivity(Class<?> cls)
    {
        BaseFragmentActivity activity = null;
        for (BaseFragmentActivity aty : activityStack)
        {
            if (aty.getClass().equals(cls))
            {
                activity = aty;
                break;
            }
        }
        return activity;
    }

    /**
     * 结束当前Activity（栈顶Activity）
     */
    public void finishActivity()
    {
        BaseFragmentActivity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity(重载)
     */
    public void finishActivity(Activity activity)
    {
        if (activity != null)
        {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定的Activity(重载)
     */
    public void finishActivity(Class<?> cls)
    {
        for (BaseFragmentActivity activity : activityStack)
        {
            if (activity.getClass().equals(cls))
            {
                finishActivity(activity);
            }
        }
    }

    /**
     * 关闭除了指定activity以外的全部activity 如果cls不存在于栈中，则栈全部清空
     *
     * @param cls
     */
    public void finishOthersActivity(Class<?> cls)
    {
        for (BaseFragmentActivity activity : activityStack)
        {
            if (!(activity.getClass().equals(cls)))
            {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity()
    {
        for (int i = 0, size = activityStack.size(); i < size; i++)
        {
            if (null != activityStack.get(i))
            {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 应用程序退出
     */
    public void AppExit(Context context)
    {
        try
        {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e)
        {
            System.exit(0);
        }
    }

    public static void showMessage(String msg)
    {
        Toast.makeText(FragmentActivityManager.getAppManager().currentActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
