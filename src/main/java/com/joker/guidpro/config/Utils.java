package com.joker.guidpro.config;

import com.joker.guidpro.domains.models.enums.UserSatus;

import java.util.UUID;

public class Utils {

    public static String generateRandomString(int length) {
        String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder();
        while (length-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    public static boolean isValidUUID(String uuid) {
        try {
            UUID.fromString(uuid);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static UserSatus getUserStatus(String status) {
        if(status.equals("ACTIVE")){
            return UserSatus.ACTIVE;
        } else if(status.equals("INACTIVE")) {
            return UserSatus.INACTIVE;
        } else {
            return null;
        }
    }


}
