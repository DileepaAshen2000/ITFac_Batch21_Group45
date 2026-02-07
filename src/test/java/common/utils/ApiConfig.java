package common.utils;

public class ApiConfig {

    public static String baseUrl() {
        return System.getProperty("api.baseUrl",
                System.getenv().getOrDefault("API_BASE_URL", "http://localhost:8008"));
    }

    public static String adminToken() {
        return System.getProperty("admin.token", System.getenv("ADMIN_TOKEN"));
    }

    public static String userToken() {
        return System.getProperty("user.token", System.getenv("USER_TOKEN"));
    }
}
