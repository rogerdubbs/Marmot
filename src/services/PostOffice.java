package services;

import java.util.Vector;

public class PostOffice {
    private static PostOffice instance;

    private Vector<String> _log;

    private PostOffice() {
        _log = new Vector<>();
    }

    public static PostOffice getInstance() {
        if (instance == null) {
            instance = new PostOffice();
        }
        return instance;
    }

    public int size() {
        return _log.size();
    }

    public void sendEMail(String address, String message) {
        String logString = String.format("<sendEMail address=\"%s\" >%s</sendEmail>\n", address, message);
        _log.add(logString);
    }

    @SuppressWarnings("SameParameterValue")
    public String findEmail(String to, String messageContains) {
        StringBuilder ret = new StringBuilder();
        String log;
        for (String a_log : _log) {
            log = a_log;
            if (log.contains(String.format("address=\"%s\"", to))) {
                if (log.contains(messageContains))
                    ret.append(log);
            }
        }
        return ret.toString();
    }

    public boolean doesLogContain(String to, String message) {
        boolean ret = false;
        String line;
        for (String a_log : _log) {
            line = a_log;
            if (line.contains(to)) {
                if (line.contains(message))
                    ret = true;
            }
        }
        return ret;

    }

    public void clear() {
        _log.clear();
    }
}
