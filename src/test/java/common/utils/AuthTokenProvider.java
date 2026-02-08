package common.utils;

public class AuthTokenProvider {

    public static String getAdminTokenOrThrow() {
        String token = ApiConfig.adminToken();
        if (token == null || token.isBlank()) {
            throw new RuntimeException("ADMIN_TOKEN not set. Set env ADMIN_TOKEN or run with -Dadmin.token=...");
        }
        return token;
    }

    public static String getUserTokenOrThrow() {
        String token = ApiConfig.userToken();
        if (token == null || token.isBlank()) {
            throw new RuntimeException("USER_TOKEN not set. Set env USER_TOKEN or run with -Duser.token=...");
        }
        return token;
    }
}
