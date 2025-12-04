package com.candileasing.notificationservice.core.constants;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

/**
 * Project title: auth-server
 * Created by john.adeshola
 * Date: 18/02/2021
 * Time: 11:12 AM
 */
public class AppConstant {

    public interface DateFormatters {
        public SimpleDateFormat defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    }

    public interface Utf8CharSet {
        public static String UTF_8 = "UTF-8";
        public static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        public static final String lower = upper.toLowerCase();
        public static final String digits = "0123456789";
        public static final String alphanum = upper + lower + digits;
        public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    }

    public interface RestMessage {
        public static final String success = "Success";
        public static final String successful = "Successful";
        public static final String failed = "Failed";
        public static final String pending = "Pending";
    }
}
