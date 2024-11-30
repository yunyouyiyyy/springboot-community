package org.example.spring1114.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class OnlineCntListener implements HttpSessionListener {
    private static int cnt = 0;
    public static int getCnt() {
        return cnt;
    }
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        cnt++;
        System.out.println("OnlineCnt="+cnt);
    }
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        cnt--;
        System.out.println("OnlineCnt="+cnt);
    }
}
