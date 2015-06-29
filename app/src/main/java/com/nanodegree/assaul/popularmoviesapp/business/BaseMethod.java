package com.nanodegree.assaul.popularmoviesapp.business;

import android.text.TextUtils;

import com.nanodegree.assaul.popularmoviesapp.business.enums.RequestMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrey Assaul on 28.06.2015.
 */
public abstract class BaseMethod<T extends Enum, Result> {
    private final Map<T, BaseParameter<T, ?>> parameterMap;
    private final String baseUrl;
    private final RequestMethod requestMethod;
    private Result result;

    public BaseMethod(RequestMethod requestMethod, String baseUrl, List<BaseParameter<T, ?>> parameters) {
        parameterMap = new HashMap<>();
        for (BaseParameter<T, ?> parameter : parameters){
            parameterMap.put(parameter.getType(),parameter);
        }
        this.baseUrl = baseUrl;
        this.requestMethod = requestMethod;
    }

    public final Result getResult() {
        return result;
    }

    public final void run() throws IOException{
        if (RequestMethod.GET.equals(requestMethod)){
            sendGET();
        }
    }

    private void sendGET() throws IOException {
        Collection<BaseParameter<T,?>> parameters = parameterMap.values();
        String requestUrl = baseUrl;
        if (parameters.size() > 0){
            requestUrl += ("?"+ TextUtils.join("&", parameters));
        }
        URL url = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod(RequestMethod.GET.name());
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine = inputStreamReader.readLine();
            StringBuilder response = new StringBuilder();
            while (inputLine != null) {
                response.append(inputLine);
                inputLine = inputStreamReader.readLine();
            }
            inputStreamReader.close();
            execute(response);
        }
        //TODO: else {throw bad request exception}
    }

    protected abstract void execute(StringBuilder response);

    protected final BaseParameter<T, ?> getParameterForType(T type){
        if (!parameterMap.containsKey(type)){
            //TODO: throw illegal arg exception
            return null;
        }
        return parameterMap.get(type);
    }

    protected final void setResult(Result result) {
        this.result = result;
    }
}
