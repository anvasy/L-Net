package com.anvasy.utils;

import com.anvasy.dao.UsersDAO;
import com.anvasy.database.DataBase;
import com.anvasy.model.User;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.sql.SQLException;

public class OAuthUtils {

    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(OAuthUtils.class);

    private final static String VK_URL = "https://oauth.vk.com/access_token";
    private final static String VK_CLIENT_ID = "6910428";
    private final static String VK_CLIENT_SECRET = "7fKZDlsdGWXYhA8HRzsJ";
    private final static String VK_GET_USERS = "https://api.vk.com/method/users.get";
    private final static String VK_VERSION = "5.92";

    private final static String GIT_URL = "https://github.com/login/oauth/access_token";
    private final static String GIT_CLIENT_ID = "4c40cc2de458433ff4aa";
    private final static String GIT_CLIENT_SECRET = "2205497e535d6a414739126a67149f242c42b1bd";
    private final static String GIT_GET_ACCESS_TOKEN = "https://api.github.com/user?access_token=";

    private final static String REDIRECT_URI = "http://localhost:8080/l-net/auth";
    private final static String REDIRECT_URI_VK = "http://localhost:8080/l-net/vkauth";

    @SuppressWarnings("Duplicates")
    public static JSONObject getVkAccess(String code) throws IOException {

        PostMethod post = new PostMethod(VK_URL);
        post.addParameter("client_id", VK_CLIENT_ID);
        post.addParameter("client_secret", VK_CLIENT_SECRET);
        post.addParameter("redirect_uri", REDIRECT_URI_VK);
        post.addParameter("code", code);

        HttpClient httpclient = new HttpClient();
        httpclient.executeMethod(post);

        return new JSONObject(new JSONTokener(new InputStreamReader(post.getResponseBodyAsStream())));
    }

    public static JSONObject getVkUserdata(String token, String userId) throws IOException {
        PostMethod userDataPostMethod = new PostMethod(VK_GET_USERS);
        userDataPostMethod.addParameter("user_ids", userId);
        userDataPostMethod.addParameter("access_token", token);
        userDataPostMethod.addParameter("v", VK_VERSION);

        HttpClient httpClient = new HttpClient();
        httpClient.executeMethod(userDataPostMethod);

        return new JSONObject(new JSONTokener(new InputStreamReader(userDataPostMethod.getResponseBodyAsStream())));
    }

    @SuppressWarnings("Duplicates")
    public static JSONObject getGitAccess(String code) throws IOException {

        PostMethod post = new PostMethod(GIT_URL);
        post.addRequestHeader("Accept", "application/json");
        post.addParameter("client_id", GIT_CLIENT_ID);
        post.addParameter("client_secret", GIT_CLIENT_SECRET);
        post.addParameter("redirect_uri", REDIRECT_URI);
        post.addParameter("code", code);

        HttpClient httpclient = new HttpClient();
        httpclient.executeMethod(post);

        return new JSONObject(new JSONTokener(new InputStreamReader(post.getResponseBodyAsStream())));
    }

    public static JSONObject getGitUserData(String token) throws IOException {
        GetMethod getMethod = new GetMethod(GIT_GET_ACCESS_TOKEN + token);
        HttpClient userDataClient = new HttpClient();
        userDataClient.executeMethod(getMethod);

        return new JSONObject(new JSONTokener(new InputStreamReader(getMethod.getResponseBodyAsStream())));
    }

    public static User registerGit(JSONObject dataJson, DataBase dataBase) throws SQLException {
        User user = new User();

        String userId = String.valueOf(dataJson.get("id"));
        String name = String.valueOf(dataJson.get("name"));
        int space = name.indexOf(" ");
        user.setName(name.substring(0, space));
        user.setSurname(name.substring(space + 1));

        user.setUsername(userId);
        user.setRegType("git");

        UsersDAO usersDAO = new UsersDAO(dataBase);
        usersDAO.insert(user);

        return user;
    }

    public static User registerVK(JSONObject dataJson, String userId, DataBase dataBase) throws SQLException {
        User user = new User();

        JSONArray userList = dataJson.getJSONArray("response");
        JSONObject userJson = (JSONObject) userList.get(0);

        user.setName(String.valueOf(userJson.get("first_name")));
        user.setSurname(String.valueOf(userJson.get("last_name")));

        user.setUsername(userId);
        user.setRegType("vk");

        UsersDAO usersDAO = new UsersDAO(dataBase);
        usersDAO.insert(user);

        return user;
    }
}
