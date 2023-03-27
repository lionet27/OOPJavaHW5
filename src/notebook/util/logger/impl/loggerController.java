package notebook.util.logger.impl;

import java.io.IOException;
import java.util.logging.*;

import notebook.controller.impl.UserControllerWithLog;

public class loggerController implements notebook.util.logger.logger {

    public static final String LOGGER_PATH = "logger.txt";

    @Override
    public void log(String text) {
        try {
            Logger logger=Logger.getLogger(UserControllerWithLog.class.getName());            
            logger.setLevel(Level.INFO);        
            FileHandler fh;
        
            fh = new FileHandler("log.txt",true);
        
            logger.addHandler(fh);
            SimpleFormatter sFormat=new SimpleFormatter();
            fh.setFormatter(sFormat);        
                
            logger.info(text);       
           
            fh.close(); 
        // } catch (SecurityException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
           
            
    
}
