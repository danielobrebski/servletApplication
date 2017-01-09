package pl.epoint.dobrebski.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by dobrebski on 09.01.17.
 */
@WebListener
public class SessionListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent event) {
    }

    public void sessionDestroyed(HttpSessionEvent event) {
        System.out.println("Użytkownik został wylogowany");
    }

}
