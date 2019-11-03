package Post;

import org.apache.log4j.Logger;

public class MainOfficePost {

    private static final Logger log =  Logger.getLogger(MainOfficePost.class);

    public boolean queue(Letter letter){
        log.debug("Sending to main office letter : " + letter);
        return true;
    }
}
