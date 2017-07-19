package com.messager;

import io.undertow.Undertow;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pavel on 13.07.17.
 */
public class AppMessage {


        public static void main(String[] args) {
            UndertowJaxrsServer server = new UndertowJaxrsServer();
            server.deploy(AppMessage.RestApp.class);
            final Undertow.Builder builder = Undertow.builder().addHttpListener(getPort(), "0.0.0.0");
            server.start(builder);
        }

    private static int getPort() {
        try {
            String port = java.lang.System.getenv("PORT");
            return port == null ? 8080 : Integer.parseInt(port);
        } catch (NumberFormatException err) {
            return 8080;
        }
    }

    @ApplicationPath("/base")
    public static class RestApp extends Application {
        @Override
        public Set<Class<?>> getClasses() {
            Set<Class<?>> classes = new HashSet<Class<?>>();

            //classes.add(NotesResource.class);
            //classes.add(HelloResourceImpl.class);
            //classes.add(EnvironmentResource.class);
            //classes.add(StudentResource.class);
            classes.add(StoreImpl.class);
            classes.add(Enter.class);
            return classes;
        }
    }
    }
