package fr.jjj.toolbox.web;

import java.io.File;
import java.net.URL;
import java.security.ProtectionDomain;

import org.mortbay.jetty.Handler;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.ContextHandlerCollection;
import org.mortbay.jetty.webapp.WebAppContext;

public class LightServer {

    /**
     * @param args
     */
    public static void main(String[] args) {

        ContextHandlerCollection contexts = new ContextHandlerCollection();

        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setDescriptor("war/WEB-INF/web.xml");

        webAppContext.setResourceBase("war");
        //webAppContext.setContextPath("war");

        contexts.setHandlers(new Handler[] { webAppContext });

        final LightServer jettyServer = new LightServer();
        System.out.println("LightServer created.");
        jettyServer.setHandler(contexts);

        try {
            jettyServer.start();
            System.out.println("LightServer started.");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

//		Runnable runner = new Runnable() {
//			@Override
//			public void run() {
//				new ServerRunner(jettyServer);
//			}
//		};
//		EventQueue.invokeLater(runner);

    }

    private Server server;

    public LightServer() {
        this(8585);
    }

    public LightServer(Integer runningPort) {
        server = new Server(runningPort);
    }

    public void setHandler(ContextHandlerCollection contexts) {
        server.setHandler(contexts);
    }

    public void start() throws Exception {
        server.start();
    }

    public void stop() throws Exception {
        server.stop();
        server.join();
    }

    public boolean isStarted() {
        return server.isStarted();
    }

    public boolean isStopped() {
        return server.isStopped();
    }
}
