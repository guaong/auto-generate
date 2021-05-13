package guaong.quick.core.util;

import java.util.logging.Logger;

public class Log {

    private static Logger logger = Logger.getGlobal();

    public static void info(String info){
        logger.info(info);
    }


}
